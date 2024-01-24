package com.rbaun.banking.controller;

import org.springframework.security.core.context.SecurityContextHolder;

public abstract class BaseController implements BaseControllerAPI {

    @Override
    public String getLoggedInUsername() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

}
