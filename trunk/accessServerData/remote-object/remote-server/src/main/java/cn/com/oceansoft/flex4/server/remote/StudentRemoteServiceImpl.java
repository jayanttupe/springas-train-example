package cn.com.oceansoft.flex4.server.remote;

import cn.com.oceansoft.flex4.server.common.entity.Student;
import cn.com.oceansoft.flex4.server.common.service.StudentService;
import org.dozer.Mapper;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Description: 学生 remote service 实现类
 */
@Service("studentRemoteService")
public class StudentRemoteServiceImpl implements StudentRemoteService {

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

    public void save(StudentDto studentDto) {
        Student student = new Student();
        student.setName(studentDto.getName());
        student.setStudentNumber(studentDto.getStudentNumber());
        student.setGender(studentDto.getGender());
        studentService.save(student);
    }

    public void update(StudentDto studentDto) {
        Student student = studentService.get(studentDto.getId());
        student.setName(studentDto.getName());
        student.setStudentNumber(studentDto.getStudentNumber());
        student.setGender(studentDto.getGender());
        studentService.update(student);
    }

    public void delete(String id) {
        studentService.delete(id);
    }
}
