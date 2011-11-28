package com.mycompany.userinfo.web;

import com.mycompany.userinfo.domain.Account;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by IntelliJ IDEA.
 * User: Hu Jing Ling
 * Date: 11-11-28
 * Time: 上午11:18
 * Description: 用户登录控制器
 */

@RequestMapping("/login")
@Controller
public class LoginController {

    @RequestMapping(method = RequestMethod.POST)
    public String login(HttpServletRequest request, Model uiModel) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Account account = null;
        try {
//            account = Account.get
        } catch (Exception e){

        }
        return "success";
    }

}
