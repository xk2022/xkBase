package com.xk.upms.domain.service;

import com.xk.upms.domain.model.po.UpmsRolePermissionAction;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UpmsRolePermissionActionService {

    List<UpmsRolePermissionAction> findAllIn(Long roleId, List<Long> upmsPermissionIds);

}
