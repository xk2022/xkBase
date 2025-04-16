package com.xk.common.util.dto;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Data
public class JwtUserDTO implements UserDetails {

    private Collection<? extends GrantedAuthority> authorities;

    private UUID userUuid;

    private String userName;

    private String email;

    private String cellPhone;

    private String password;

    private UUID roleUuid;

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
            UUID userUuid,
            String userName,
            String email,
            String cellPhone,
            UUID roleUuid,
            boolean enable,
            boolean lock,
            List<SystemDTO> systemDTOs,
            List<PermissionDTO> permissionDTOs) {
        this.userUuid = userUuid;
        this.userName = userName;
        this.email = email;
        this.cellPhone = cellPhone;
        this.roleUuid = roleUuid;
        this.enable = enable;
        this.lock = lock;
        this.systemDTOs = systemDTOs;
        this.permissionDTOs = permissionDTOs;
    }

}
