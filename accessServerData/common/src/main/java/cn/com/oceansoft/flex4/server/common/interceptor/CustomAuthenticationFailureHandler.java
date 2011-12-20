package cn.com.oceansoft.flex4.server.common.interceptor;

import cn.com.oceansoft.flex4.server.common.entity.User;
import cn.com.oceansoft.flex4.server.common.service.UserService;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * Author: Hu jing ling
 * Date: 8/17/11
 * Description: 自定义 Authentication Failure Handler类l
 */
public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    public static final String SPRING_SECURITY_FORM_USERNAME_KEY = "j_username";

    private String usernameParameter = SPRING_SECURITY_FORM_USERNAME_KEY;

    @Resource
    private UserService userService;

    private String passwordFailureUrl;

    private String accountDisabledUrl;

    private String accountExpiredUrl;

    private String accountLockedUrl;

    private String credentialsExpiredUrl;

    private Integer maxLoginFailureCount;

    public void setMaxLoginFailureCount(Integer maxLoginFailureCount) {
        this.maxLoginFailureCount = maxLoginFailureCount;
    }

    public void setCredentialsExpiredUrl(String credentialsExpiredUrl) {
        this.credentialsExpiredUrl = credentialsExpiredUrl;
    }

    public void setAccountLockedUrl(String accountLockedUrl) {
        this.accountLockedUrl = accountLockedUrl;
    }

    public void setPasswordFailureUrl(String passwordFailureUrl) {
        this.passwordFailureUrl = passwordFailureUrl;
    }

    public void setAccountExpiredUrl(String accountExpiredUrl) {
        this.accountExpiredUrl = accountExpiredUrl;
    }

    public void setAccountDisabledUrl(String accountDisabledUrl) {
        this.accountDisabledUrl = accountDisabledUrl;
    }

    //从 HttpServletRequest 获取用户名
    protected String obtainUsername(HttpServletRequest request) {
        return request.getParameter(usernameParameter);
    }

    @Override
    @Transactional
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        String username = obtainUsername(request);
        User user = userService.getUserByUsername(username);

        //默认密码输出错误次数
        if (maxLoginFailureCount == null) {
            maxLoginFailureCount = 3;
        }

        if (user != null && !user.getIsAccountLocked()) {
            Integer failureCount = user.getLoginFailureCount();
            failureCount++;
            if (failureCount >= maxLoginFailureCount) {
                //账号锁定
                user.setIsAccountLocked(true);
                user.setLoginFailureCount(failureCount);
                userService.update(user);
            } else {
                user.setLoginFailureCount(failureCount);
                userService.update(user);
            }
        }

        //根据错误类型, 返回不同错误页面
        if (exception instanceof DisabledException) {
            super.setDefaultFailureUrl(accountDisabledUrl);
        } else if (exception instanceof AccountExpiredException) {
            super.setDefaultFailureUrl(accountExpiredUrl);
        } else if (exception instanceof LockedException) {
            super.setDefaultFailureUrl(accountLockedUrl);
        } else if (exception instanceof CredentialsExpiredException) {
            super.setDefaultFailureUrl(credentialsExpiredUrl);
        } else {
            super.setDefaultFailureUrl(passwordFailureUrl);
        }
        super.onAuthenticationFailure(request, response, exception);    //To change body of overridden methods use File | Settings | File Templates.
    }
}
