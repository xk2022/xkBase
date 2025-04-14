package com.xk.upms.domain.service.impl;

import com.xk.upms.domain.dao.repository.UpmsRolePermissionActionRepository;
import com.xk.upms.domain.model.bo.UpmsRolePermissionActionBO;
import com.xk.upms.domain.model.po.UpmsRolePermissionAction;
import com.xk.upms.domain.service.UpmsRolePermissionActionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UpmsRolePermissionActionServiceImpl implements UpmsRolePermissionActionService {

    private final UpmsRolePermissionActionRepository upmsRolePermissionActionRepository;

    @Override
    public List<UpmsRolePermissionAction> findAllIn(Long roleId, List<Long> upmsPermissionIds) {
        return upmsRolePermissionActionRepository.findByIsDeletedFalseAndRoleIdAndPermissionIdIn(roleId, upmsPermissionIds);
    }

    @Override
    public UpmsRolePermissionAction save(UpmsRolePermissionActionBO upmsRolePermissionActionBO) {
        return null;
    }

    @Override
    public List<UpmsRolePermissionAction> deleteAll(List<UpmsRolePermissionAction> upmsRolePermissionActions) {
        upmsRolePermissionActionRepository.deleteAll(upmsRolePermissionActions);
        return upmsRolePermissionActions;
    }

    @Override
    public List<UpmsRolePermissionAction> saveAll(List<UpmsRolePermissionAction> upmsRolePermissionActions) {
        upmsRolePermissionActionRepository.saveAll(upmsRolePermissionActions);
        return upmsRolePermissionActions;
    }

}
