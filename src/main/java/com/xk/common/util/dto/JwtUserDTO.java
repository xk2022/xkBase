package com.xk.common.util.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtUserDTO implements UserDetails {

    private Collection<? extends GrantedAuthority> authorities;

    private Long userId;

    private String userName;

    private String password;

    private boolean enable;

    private boolean lock;

    private Long roleId;

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

    public JwtUserDTO(String userName){
        this.userName = userName;
    }

    public void setSystems(List<SystemDTO> systemDTOS) {
        ObjectMapper objectMapper = new ObjectMapper();
        this.authorities = systemDTOS.stream()
                .map(system -> {
                    try {
                        String systemJson = objectMapper.writeValueAsString(system);
                        return new SimpleGrantedAuthority(systemJson);
                    }catch (JsonProcessingException e){
                        e.printStackTrace();
                        return null;
                    }
                }).collect(Collectors.toList());
    }

    public List<SystemDTO> getSystems() {
        ObjectMapper objectMapper = new ObjectMapper();
        return authorities.stream()
                .map(grantedAuthority -> {
                    try {
                        return objectMapper.readValue(grantedAuthority.getAuthority(), SystemDTO.class);
                    } catch (IOException e) {
                        e.printStackTrace();
                        return null;
                    }
                }).collect(Collectors.toList());
    }

    public void setPermissions(List<PermissionDTO> permissions) {
        ObjectMapper objectMapper = new ObjectMapper();
        this.authorities = permissions.stream()
                .map(permission -> {
                    try {
                        String permissionJson = objectMapper.writeValueAsString(permission);
                        return new SimpleGrantedAuthority(permissionJson);
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                        return null;
                    }
                }).collect(Collectors.toList());
    }

    public List<PermissionDTO> getPermissions() {
        ObjectMapper objectMapper = new ObjectMapper();
        return authorities.stream()
                .map(grantedAuthority -> {
                    try {
                        return objectMapper.readValue(grantedAuthority.getAuthority(), PermissionDTO.class);
                    } catch (IOException e) {
                        e.printStackTrace();
                        return null;
                    }
                }).collect(Collectors.toList());
    }

}
