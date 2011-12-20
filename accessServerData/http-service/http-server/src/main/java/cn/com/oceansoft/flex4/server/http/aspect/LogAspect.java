package cn.com.oceansoft.flex4.server.http.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * Created by IntelliJ IDEA.
 * User: Hu Jing Ling
 * Date: 11-12-1
 * Time: 下午2:58
 * Description:
 */
@Component  //将当前切面在spring中定义成一个bean
@Aspect
public class LogAspect {

    //这里直接指向具体切入点的定义的方法
    @Before(value = "LogPointcut.logOnGet()&& args(username)")
    public void logOnGet(String username) {
        //实现具体的日志记录
        String s = username;
        System.out.println("log on server side...");
    }


}
