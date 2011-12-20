package cn.com.oceansoft.flex4.server.common.interceptor;

import cn.com.oceansoft.flex4.server.common.entity.User;
import cn.com.oceansoft.flex4.server.common.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * Author: Hu jing ling
 * Date: 8/17/11
 * Description: 自定义 Authentication Success Handler 类
 */
public class CustomAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Resource
    private UserService userService;

    @Override
    @Transactional
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        //登录成功：记录登录IP, 日期, 清除登录失败次数
        String loginIp = ((WebAuthenticationDetails) authentication.getDetails()).getRemoteAddress();
        User user = (User) authentication.getPrincipal();
        user.setLoginIp(loginIp);
        user.setLoginDate(new Date());
        user.setLoginFailureCount(0);
        userService.update(user);

        super.onAuthenticationSuccess(request, response, authentication);    //To change body of overridden methods use File | Settings | File Templates.
    }
}
