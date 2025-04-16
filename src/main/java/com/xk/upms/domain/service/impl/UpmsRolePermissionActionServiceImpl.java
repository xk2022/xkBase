package com.xk.upms.domain.service.impl;

import com.xk.common.handler.BusinessException;
import com.xk.upms.domain.dao.repository.UpmsRolePermissionActionRepository;
import com.xk.upms.domain.model.bo.UpmsRolePermissionActionBO;
import com.xk.upms.domain.model.po.UpmsRolePermissionAction;
import com.xk.upms.domain.service.UpmsRolePermissionActionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public void deleteAll(List<UpmsRolePermissionAction> upmsRolePermissionActions) {
        if(null == upmsRolePermissionActions || upmsRolePermissionActions.isEmpty()){
            throw new BusinessException("角色權限動作清單不得為空");
        }
        for(UpmsRolePermissionAction upmsRolePermissionAction : upmsRolePermissionActions){
            upmsRolePermissionAction.setIsDeleted(true);
        }
        upmsRolePermissionActionRepository.saveAll(upmsRolePermissionActions);
    }

    @Override
    public void saveAll(List<UpmsRolePermissionAction> upmsRolePermissionActions) {
        if(null == upmsRolePermissionActions || upmsRolePermissionActions.isEmpty()){
            throw new BusinessException("角色權限動作清單不得為空");
        }
        for(UpmsRolePermissionAction upmsRolePermissionAction : upmsRolePermissionActions){
            upmsRolePermissionAction.setIsDeleted(false);
        }
        upmsRolePermissionActionRepository.saveAll(upmsRolePermissionActions);
    }

}
