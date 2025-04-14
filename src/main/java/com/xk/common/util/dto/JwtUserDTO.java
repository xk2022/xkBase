package com.xk.common.util.dto;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
public class JwtUserDTO implements UserDetails {

    private Collection<? extends GrantedAuthority> authorities;

    private Long userId;

    private String userName;

    private String email;

    private String cellPhone;

    private String password;

    private Long roleId;

    private boolean enable;

    private boolean lock;

    private List<SystemDTO> systemDTOs;

    private List<PermissionDTO> permissionDTOs;

    private String token;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getUsername() {
        return this.userName;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    public JwtUserDTO() {
    }

    public JwtUserDTO(
            Long userId,
            String userName,
            String email,
            String cellPhone,
            Long roleId,
            boolean enable,
            boolean lock,
            List<SystemDTO> systemDTOs,
            List<PermissionDTO> permissionDTOs) {
        this.userId = userId;
        this.userName = userName;
        this.email = email;
        this.cellPhone = cellPhone;
        this.roleId = roleId;
        this.enable = enable;
        this.lock = lock;
        this.systemDTOs = systemDTOs;
        this.permissionDTOs = permissionDTOs;
    }

}
