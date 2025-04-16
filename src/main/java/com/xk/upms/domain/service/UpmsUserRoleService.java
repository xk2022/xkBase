package com.xk.upms.domain.service;

import com.xk.upms.domain.model.bo.UpmsUserRoleBO;

import java.util.Optional;
import java.util.UUID;

public interface UpmsUserRoleService {

    UpmsUserRoleBO save(UpmsUserRoleBO upmsUserRoleBO);

    Optional<UpmsUserRoleBO> findByUserId(UUID userUuid);

    UpmsUserRoleBO update(UpmsUserRoleBO upmsUserRoleBO);

}
