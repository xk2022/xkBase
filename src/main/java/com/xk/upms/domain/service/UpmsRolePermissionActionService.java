package com.xk.upms.domain.service;

import com.xk.upms.domain.model.bo.UpmsRolePermissionActionBO;
import com.xk.upms.domain.model.po.UpmsRolePermissionAction;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


public interface UpmsRolePermissionActionService {

    List<UpmsRolePermissionAction> findAllIn(UUID roleUuid, List<UUID> upmsPermissionUuids);

    UpmsRolePermissionAction save(UpmsRolePermissionActionBO upmsRolePermissionActionBO);

    void deleteAll(List<UpmsRolePermissionAction> upmsRolePermissionActions);

    void saveAll(List<UpmsRolePermissionAction> upmsRolePermissionActions);

}
