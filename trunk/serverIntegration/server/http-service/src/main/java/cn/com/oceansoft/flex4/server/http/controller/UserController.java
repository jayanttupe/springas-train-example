package cn.com.oceansoft.flex4.server.http.controller;

import cn.com.oceansoft.flex4.server.common.entity.User;
import cn.com.oceansoft.flex4.server.common.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;

/**
 * 控制器类--用户控制器
 */
@RequestMapping("/users")
@Controller
public class UserController {

    @Resource
    private UserService userService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public void test(@PathVariable("id") String id, Model model) {
        User user = userService.getUserByUsername("user");
        model.addAttribute("result" , "OK");
    }

    @RequestMapping(method = RequestMethod.GET)
    public void getUsers(Model model) {
        model.addAttribute("userList" , userService.getAll());
    }


}
