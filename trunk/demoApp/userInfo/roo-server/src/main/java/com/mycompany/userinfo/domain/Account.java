package com.mycompany.userinfo.domain;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooEntity
public class Account {

    @NotNull
    @Size(min = 2)
    private String username;

    @NotNull
    private String password;

    private Boolean isDisabled;
}
