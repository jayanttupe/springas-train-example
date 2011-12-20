package cn.com.oceansoft.flex4.server.http.controller;

import cn.com.oceansoft.flex4.server.common.entity.Student;
import cn.com.oceansoft.flex4.server.common.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * Date: 11-12-12
 * Time: 下午2:10
 * Description:  学生类控制器
 */
@RequestMapping("/students")
@Controller
public class StudentController extends CommonBindingInitializer{

    @Resource
    private StudentService studentService;

    @RequestMapping(method = RequestMethod.GET)
    public void getAll(Model model) {
        model.addAttribute("studentList", studentService.getAll());
    }

    @RequestMapping(value ="/save" , method = RequestMethod.POST)
    public void save(@Valid Student student, BindingResult bindingResult, Model model){
        System.out.println(student.toString());
        studentService.save(student);
        model.addAttribute("student", student);
    }

    @RequestMapping(value ="/update" , method = RequestMethod.POST)
    public void update(Student student , Model model){
        System.out.println(student.toString());
        Student stu = studentService.get(student.getId());
        stu.setName(student.getName()); //更新姓名
        stu.setStudentNumber(student.getStudentNumber()); //更新学号
        stu.setGender(student.getGender());//更新性别
        studentService.update(stu);
        model.addAttribute("student", stu);
    }

    @RequestMapping(value ="/delete" , method = RequestMethod.POST)
    public void delete(String id , Model model){
        System.out.println(id);
        studentService.delete(id);//根据id删除
        model.addAttribute("id", id);
    }

}
