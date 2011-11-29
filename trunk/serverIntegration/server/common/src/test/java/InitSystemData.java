import cn.com.oceansoft.flex4.server.common.entity.Role;
import cn.com.oceansoft.flex4.server.common.entity.User;
import cn.com.oceansoft.flex4.server.common.service.RoleService;
import cn.com.oceansoft.flex4.server.common.service.UserService;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Set;

/**
 * 初始化系统数据
 */
@ContextConfiguration(value = {"classpath:application-config.xml"})
public class InitSystemData extends AbstractTransactionalJUnit4SpringContextTests {

    public static final String PROJECT_ID = "flex4";
    public static final String ROLE_USER = "ROLE_USER";
    public static final String ROLE_ADMIN = "ROLE_ADMIN";
    public static final String USER = "user";
    public static final String ADMIN = "admin";

    @Resource
    private RoleService roleService;
    @Resource
    private UserService userService;

    @Test
    public void simple(){}

    @Test
    @Rollback(value = false)
    public void initRole(){
        createAdminRole();
        createNormalRole();
        createAdminUser();
        createNormalUser();
        assignRole2Normal();
        assignRole2Admin();
    }

    public void assignRole2Normal(){
        Role role = roleService.get("value" , ROLE_USER);
        User user = userService.get("username" , USER);
        Set roleSet = new HashSet();
        roleSet.add(role);
        user.setRoleSet(roleSet);
        userService.update(user);
    }

    public void assignRole2Admin(){
        Role role = roleService.get("value" , ROLE_ADMIN);
        User user = userService.get("username" , ADMIN);
        Set roleSet = new HashSet();
        roleSet.add(role);
        user.setRoleSet(roleSet);
        userService.update(user);
    }

    public void createNormalUser() {
        User user = new User();
        user.setUsername(USER);
        user.setPassword("user");
        user.setEmail("user@" + PROJECT_ID + ".com.cn");
        user.setDisplayName("普通用户");
        user.setIsAccountEnabled(true);
        user.setIsAccountExpired(false);
        user.setIsAccountLocked(false);
        user.setIsCredentialsExpired(false);
        user.setLoginFailureCount(0);
        userService.save(user);
    }

    private void createAdminUser() {
        User user = new User();
        user.setUsername(ADMIN);
        user.setPassword("admin");
        user.setEmail("admin@" + PROJECT_ID + ".com.cn");
        user.setDisplayName("管理员");
        user.setIsAccountEnabled(true);
        user.setIsAccountExpired(false);
        user.setIsAccountLocked(false);
        user.setIsCredentialsExpired(false);
        user.setLoginFailureCount(0);
        userService.save(user);
    }


    public void createAdminRole(){
        Role role =new Role();
        role.setDisplayName("管理员");
        role.setIsSystem(true);
        role.setDescription("系统管理员角色");
        role.setValue(ROLE_ADMIN);
        role.setProjectId(PROJECT_ID);
        roleService.save(role);
    }

    public void createNormalRole(){
        Role role =new Role();
        role.setDisplayName("普通用户角色");
        role.setIsSystem(true);
        role.setDescription("系统普通用户角色");
        role.setValue(ROLE_USER);
        role.setProjectId(PROJECT_ID);
        roleService.save(role);
    }
}
