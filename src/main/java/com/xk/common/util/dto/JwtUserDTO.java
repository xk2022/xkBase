package com.xk.common.util.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Data
public class JwtUserDTO implements UserDetails {

    private Collection<? extends GrantedAuthority> authorities;

    private UUID userUuid;

    private String account;

    private String userName;

    private String email;

    private String cellPhone;

    private String password;

    private UUID roleUuid;

    private boolean enable;

    private boolean lock;

    private List<JwtUserDTO.SystemDTO> systemDTOs;

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
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SystemDTO {

        private UUID systemUuid;

        private String name;

        private List<JwtUserDTO.SystemDTO.PermissionDTO> permissionDTOS;

        @Data
        @AllArgsConstructor
        @NoArgsConstructor
        public static class PermissionDTO {

            private UUID uuid;

            private String name;

            private boolean active;

            private List<JwtUserDTO.SystemDTO.PermissionDTO> permissionDTOs;

            private List<JwtUserDTO.SystemDTO.PermissionDTO.Action> actions;

            @Data
            @AllArgsConstructor
            @NoArgsConstructor
            public static class Action{

                private UUID uuid;

                private String name;

                private boolean active;

            }

        }

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
            List<JwtUserDTO.SystemDTO> systemDTOs) {
        this.userUuid = userUuid;
        this.userName = userName;
        this.email = email;
        this.cellPhone = cellPhone;
        this.roleUuid = roleUuid;
        this.enable = enable;
        this.lock = lock;
        this.systemDTOs = systemDTOs;
    }

}
