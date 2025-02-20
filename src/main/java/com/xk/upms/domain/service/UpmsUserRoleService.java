package com.xk.upms.domain.service;

import com.xk.upms.domain.model.bo.UpmsUserRoleBO;

import java.util.Optional;

public interface UpmsUserRoleService {

    UpmsUserRoleBO save(UpmsUserRoleBO upmsUserRoleBO);

    Optional<UpmsUserRoleBO> findByUserId(Long userId);

    UpmsUserRoleBO update(UpmsUserRoleBO upmsUserRoleBO);

}
