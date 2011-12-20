package cn.com.oceansoft.flex4.server.ws;

import cn.com.oceansoft.flex4.server.common.entity.Student;
import cn.com.oceansoft.flex4.server.common.service.StudentService;
import org.dozer.Mapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jws.WebService;
import java.util.ArrayList;
import java.util.List;

/**
 * Description: 学生 web service 实现类 <br>
 * <p>
 * <b>@WebService.endpointInterface</b> 用于声明实现哪个接口  <br>
 * <b>@Service</b>作用: <br>
 * 1)用于spring 自动注入 service layer bean   <br>
 * 2)用于声明一个bean name，让cxf 配置对应到 implementor <br>
 * <b>@Resource</b>spring自动注入 StudentService, dozer mapper<br>
 * </p>
 */
@Service("wsStudentService")
@WebService(endpointInterface = "cn.com.oceansoft.flex4.server.ws.WsStudentService")
public class WsStudentServiceImpl implements WsStudentService {

    @Resource
    private StudentService studentService;
    @Resource
    private Mapper mapper;

    public List<StudentDto> getAll() {
        List<StudentDto> list = new ArrayList<StudentDto>();
        for (Student student : studentService.getAll()) {
            list.add(mapper.map(student, StudentDto.class));
        }
        return list;
    }

    public OperationOutputType save(String name, String studentNumber, Integer gender) {
        Student student = new Student();
        student.setName(name);
        student.setStudentNumber(studentNumber);
        student.setGender(gender);
        studentService.save(student);
        return new OperationOutputType("true", student.getId());
    }

    public OperationOutputType update(String id, String name,
                                      String studentNumber, Integer gender) {
        Student student = studentService.get(id);
        student.setName(name);
        student.setStudentNumber(studentNumber);
        student.setGender(gender);
        studentService.update(student);
        return new OperationOutputType("true", student.getId());
    }

    public OperationOutputType delete(String id) {
        studentService.delete(id);
        return new OperationOutputType("true", "record has been removed.");
    }
}
