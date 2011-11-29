import cn.com.oceansoft.flex4.server.common.entity.User;
import cn.com.oceansoft.flex4.server.common.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import javax.annotation.Resource;
import java.util.List;

/**
 * 测试类--用户
 */

@ContextConfiguration(value = {"classpath:application-config.xml"})
public class TestUser extends AbstractTransactionalJUnit4SpringContextTests {

    private static final String TEST_ID = "bbf75693-312e-487f-bc5d-807f36bdd44d";

    @Resource
    private UserService userService;

    @Test
    public void simple(){}

    @Test
    @Rollback(value = false)
    public void saveUser(){
        User user = new User();
        user.setUsername("test username");
        user.setPassword("test password");
        user.setEmail("test email");
        user.setIsAccountEnabled(true);
        user.setIsAccountLocked(false);
        user.setIsAccountExpired(false);
        user.setIsCredentialsExpired(false);
        user.setLoginFailureCount(0);
        userService.save(user);
    }

    @Test
    @Rollback(value = false)
    public void updateUser(){
        User user = userService.get(TEST_ID);
        user.setProjId("update project id");
        userService.update(user);
    }

    @Test
    @Rollback(value = false)
    public void deleteUser(){
        User user = userService.get(TEST_ID);
        userService.delete(user);
    }

    @Test
    public void test1(){
        List roleList = userService.getRoleListByUser("1");
        int i = roleList.size();
        Assert.assertEquals(2, i);
    }

}
