package com.finki.mojmentor.Model.enumerations;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    USER,STUDENT,MENTOR;

    @Override
    public String getAuthority() {
        return name();
    }
}
