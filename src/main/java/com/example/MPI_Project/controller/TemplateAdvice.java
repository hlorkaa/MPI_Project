package com.example.MPI_Project.controller;

import com.example.MPI_Project.config.SecurityHelper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class TemplateAdvice {
    private SecurityHelper securityHelper;

    @ModelAttribute
    public void addDefaultAttribute(Model model) {
        securityHelper = new SecurityHelper(SecurityContextHolder.getContext());

        model.addAttribute("isLoggedIn", securityHelper.isAuthenticated() && !securityHelper.isAnonymous());
        model.addAttribute("userName", securityHelper.userName());
        model.addAttribute("isAdmin", securityHelper.isAdmin());
        model.addAttribute("isWorkman", securityHelper.isWorkman());
        model.addAttribute("isAccount", securityHelper.isAccount());
        model.addAttribute("isSecurity", securityHelper.isSecurity());
        model.addAttribute("isConsultant", securityHelper.isConsultant());
        model.addAttribute("isManager", securityHelper.isManager());

    }
}
