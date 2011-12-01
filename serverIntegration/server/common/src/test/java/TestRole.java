import cn.com.oceansoft.flex4.server.common.entity.Role;
import cn.com.oceansoft.flex4.server.common.service.RoleService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import javax.annotation.Resource;
import java.util.Date;

/**
 * 测试类--角色
 */

@ContextConfiguration(value = {"classpath:application-config.xml"})
public class TestRole extends AbstractTransactionalJUnit4SpringContextTests {

    private static final String TEST_ID = "e5856122-9322-4ea4-bafd-bb7385e2af5e";

    @Resource
    private RoleService roleService;

    @Test
    public void simple() {
    }

    @Test
    @Rollback(value = false)
    public void saveRole() {
        Role role = new Role();
        role.setDisplayName("test role");
        role.setValue("ROLE_TEST");
        role.setIsSystem(false);
        role.setProjectId("TEST");
        roleService.save(role);
    }

    @Test
    public void queryRole() {
        Role r = roleService.get(TEST_ID);
        Assert.assertEquals("TEST", r.getProjectId());
    }

    @Test
    @Rollback(value = false)
    public void updateRole() {
        Role r = roleService.get(TEST_ID);
        r.setDescription("updated on :" + new Date().toString());
        roleService.update(r);
    }

    @Test
    @Rollback(value = false)
    public void deleteUser() {
        roleService.delete(TEST_ID);
    }

}
