package cn.com.oceansoft.flex4.server.common.service.impl;

import cn.com.oceansoft.flex4.server.common.dao.StudentDao;
import cn.com.oceansoft.flex4.server.common.entity.Student;
import cn.com.oceansoft.flex4.server.common.service.StudentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Date: 11-12-12
 * Time: 下午1:50
 * Description:  Student Service 实现类
 */
@Service
public class StudentServiceImpl extends BaseServiceImpl<Student , String> implements StudentService{

    @Resource
    public void setBaseDao(StudentDao studentDao) {
        super.setBaseDao(studentDao);
    }

}
