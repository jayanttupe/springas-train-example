package cn.com.oceansoft.flex4.server.ws;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.List;

/**
 * Description: 学生 web service 接口类 <br>
 * <p><b>@WebService</b> 用于声明一个web service   <br>
 * <b>@WebMethod</b> 用于声明一个web service operation name  <br>
 * <b>@WebParam</b> 用于声明一个变量名称 <br> </p>
 *
 */
@WebService
public interface WsStudentService {

    @WebMethod
    public List<StudentDto> getAll();

    @WebMethod
    public OperationOutputType save(
            @WebParam(name = "name") String name,
            @WebParam(name = "stuNum") String studentNumber ,
            @WebParam(name = "gender") Integer gender);

    @WebMethod
    public OperationOutputType update(
            @WebParam(name = "id") String id,
            @WebParam(name = "name") String name,
            @WebParam(name = "stuNum") String studentNumber ,
            @WebParam(name = "gender") Integer gender
    );

    @WebMethod
    public OperationOutputType delete(@WebParam(name = "id") String id);



}
