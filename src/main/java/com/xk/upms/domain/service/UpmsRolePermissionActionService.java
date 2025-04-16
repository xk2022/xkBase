package com.xk.upms.domain.service;

import com.xk.upms.domain.model.bo.UpmsRolePermissionActionBO;
import com.xk.upms.domain.model.po.UpmsRolePermissionAction;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UpmsRolePermissionActionService {

    List<UpmsRolePermissionAction> findAllIn(Long roleId, List<Long> upmsPermissionIds);

    UpmsRolePermissionAction save(UpmsRolePermissionActionBO upmsRolePermissionActionBO);

    void deleteAll(List<UpmsRolePermissionAction> upmsRolePermissionActions);

    void saveAll(List<UpmsRolePermissionAction> upmsRolePermissionActions);

}
