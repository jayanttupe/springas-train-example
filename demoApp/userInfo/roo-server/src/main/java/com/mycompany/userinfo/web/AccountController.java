package com.mycompany.userinfo.web;

import com.mycompany.userinfo.domain.Account;
import org.springframework.roo.addon.web.mvc.controller.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/accounts")
@Controller
@RooWebScaffold(path = "accounts", formBackingObject = Account.class)
public class AccountController {

    @RequestMapping(value = "/{id}", params = "reset", method = RequestMethod.GET)
    public String resetPassword(@PathVariable("id") Long id, Model uiModel, HttpServletRequest httpServletRequest) {
        uiModel.asMap().clear();
        Account account = Account.findAccount(id);
        account.setPassword("111111");
        account.merge();
        return "redirect:/accounts/" + encodeUrlPathSegment(account.getId().toString(), httpServletRequest);
    }

    @RequestMapping(value = "/{id}", params = "newPass", method = RequestMethod.GET)
    public String updatePassword(@PathVariable("id") Long id, Model uiModel,
                                 @RequestParam(value = "newPass") String newPass,
                                 HttpServletRequest httpServletRequest) {
        uiModel.asMap().clear();
        Account account = Account.findAccount(id);
        account.setPassword(newPass);
        account.merge();
        return "redirect:/accounts/" + encodeUrlPathSegment(account.getId().toString(), httpServletRequest);
    }

    @RequestMapping(value = "/{id}", params = "disable", method = RequestMethod.GET)
    public String disableAccount(@PathVariable("id") Long id, Model uiModel, HttpServletRequest httpServletRequest) {
        uiModel.asMap().clear();
        Account account = Account.findAccount(id);
        account.setIsDisabled(true);
        account.merge();
        return "redirect:/accounts/" + encodeUrlPathSegment(account.getId().toString(), httpServletRequest);
    }

    @RequestMapping(value = "/{id}", params = "enable", method = RequestMethod.GET)
    public String enableAccount(@PathVariable("id") Long id, Model uiModel, HttpServletRequest httpServletRequest) {
        uiModel.asMap().clear();
        Account account = Account.findAccount(id);
        account.setIsDisabled(false);
        account.merge();
        return "redirect:/accounts/" + encodeUrlPathSegment(account.getId().toString(), httpServletRequest);
    }
}
