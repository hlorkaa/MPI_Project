package com.example.MPI_Project.config;

import com.example.MPI_Project.domain.Role;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

public class SecurityHelper {

    private Collection<? extends GrantedAuthority> authorities = Collections.emptyList();

    private Authentication authentication;

    public SecurityHelper(SecurityContext context){
        authentication = context.getAuthentication();
        if(Objects.nonNull(authentication))
            authorities = authentication.getAuthorities();
    }

    public boolean isAuthenticated() {
        return Objects.nonNull(authentication) ? authentication.isAuthenticated() : false;
    }

    public boolean isAnonymous() {
        return Objects.nonNull(authentication) ? authentication instanceof AnonymousAuthenticationToken : true;
    }

    public String userName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return Objects.nonNull(authentication) ? authentication.getName() : "";
    }

    public boolean isAdmin() {
        return authorities.stream().anyMatch(role -> role.getAuthority().equals(Role.ADMIN.name()));
    }

    public boolean isWorkman() {
        return authorities.stream().anyMatch(role -> role.getAuthority().equals(Role.WORKMAN.name()));
    }

    public boolean isAccount() {
        return authorities.stream().anyMatch(role -> role.getAuthority().equals(Role.ACCOUNT.name()));
    }

    public boolean isSecurity() {
        return authorities.stream().anyMatch(role -> role.getAuthority().equals(Role.SECURITY.name()));
    }

    public boolean isConsultant() {
        return authorities.stream().anyMatch(role -> role.getAuthority().equals(Role.CONSULTANT.name()));
    }

    public boolean isManager() {
        return authorities.stream().anyMatch(role -> role.getAuthority().equals(Role.MANAGER.name()));
    }

}
