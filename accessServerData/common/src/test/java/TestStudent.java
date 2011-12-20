import cn.com.oceansoft.flex4.server.common.entity.Student;
import cn.com.oceansoft.flex4.server.common.service.StudentService;
import junit.framework.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Date: 11-12-12
 * Time: 下午1:51
 * Description: Student 测试类
 */

@ContextConfiguration(value = {"classpath:application-config.xml"})
public class TestStudent extends AbstractTransactionalJUnit4SpringContextTests{

    @Resource
    private StudentService studentService;

    @Test
    public void simple(){
    }

    @Test
    @Rollback(value = false)
    public void save(){
        int i = 120;
        int gender = 1;
        for (int j = 100; j < i; j++) {
            Student student = new Student();
            student.setBirthDay(new Date());
            student.setGender(gender%2);
            student.setIdentityCard("320504199001011" + String.valueOf(j));
            student.setName("学生" + String.valueOf(j));
            student.setStudentNumber("SN-"+String.valueOf(j));
            studentService.save(student);
            gender++;
        }
    }

    @Test
    public void queryAll(){
        List list = studentService.getAll();
        Assert.assertEquals(50 , list.size());
    }

    @Test
    @Rollback(value = false)
    public void update(){
        Student student = studentService.get("name" , "学生100");
        student.setName("新学生100");
        studentService.update(student);
    }

    @Test
    @Rollback(value = false)
    public void delete(){
        Student student = studentService.get("name" , "新学生100");
        studentService.delete(student);
    }

}
