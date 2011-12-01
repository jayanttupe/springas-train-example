package cn.com.oceansoft.flex4.server.http.aspect;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * Created by IntelliJ IDEA.
 * User: Hu Jing Ling
 * Date: 11-12-1
 * Time: 下午2:57
 * Description:
 */
@Aspect
public class LogPointcut {

    @Pointcut(value = "execution(* cn.com.oceansoft.flex4.server.common.service.impl.UserServiceImpl.get*(..))")
    public void logOnGet() {
        //无需任何实现，仅仅当作类似一个标记使用
    }

}
