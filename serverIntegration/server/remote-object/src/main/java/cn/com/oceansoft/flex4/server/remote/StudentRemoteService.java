package cn.com.oceansoft.flex4.server.remote;

import java.util.List;

/**
 * Description:  学生 remote service 接口类
 */
public interface StudentRemoteService {

    public List<StudentDto> getAll();

    public void save(StudentDto studentDto);

    public void update(StudentDto studentDto);

    public void delete(String id);

}
