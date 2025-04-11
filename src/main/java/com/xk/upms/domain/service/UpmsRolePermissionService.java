package com.xk.upms.domain.service;

import com.xk.upms.application.model.UpmsRolePermissionRequestDTO;
import com.xk.upms.domain.model.bo.UpmsPermissionBO;
import com.xk.upms.domain.model.bo.UpmsRolePermissionBO;
import com.xk.upms.domain.model.po.UpmsRolePermission;

import java.util.List;
import java.util.UUID;

public interface UpmsRolePermissionService {

    List<UpmsRolePermission> findAll(UUID systemUuid, Long roleId);


    UpmsRolePermissionBO save(UpmsRolePermissionBO upmsRolePermissionBO );
}
