package com.example.MPI_Project.domain;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    WORKMAN,
    ACCOUNT,
    SECURITY,
    CONSULTANT,
    MANAGER,
    ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}
