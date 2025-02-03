package com.xk.common.util.dto;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;

@Data
public class JwtUserDTO implements UserDetails {

    private Collection<? extends GrantedAuthority> authorities;

    private String username;

    private String password;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return username;
    }

    @Override
    public String getUsername() {
        return password;
    }

    public JwtUserDTO(String userName){
        this.username = userName;
    }

}
