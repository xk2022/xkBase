package com.xk.upms.domain.service;

import com.xk.upms.domain.model.bo.UpmsUserRoleBO;

import java.util.List;

public interface UpmsUserRoleService {

    UpmsUserRoleBO save(UpmsUserRoleBO upmsUserRoleBO);

    List<UpmsUserRoleBO> findByUserIdIn(List<Long> userId);

}
