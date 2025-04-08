package com.xk.upms.domain.service.impl;

import com.xk.upms.domain.dao.repository.UpmsPermissionActionRepository;
import com.xk.upms.domain.model.po.UpmsRolePermissionAction;
import com.xk.upms.domain.service.UpmsRolePermissionActionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UpmsRolePermissionActionServiceImpl implements UpmsRolePermissionActionService {

    private final UpmsPermissionActionRepository upmsPermissionActionRepository;

    @Override
    public List<UpmsRolePermissionAction> findAllIn(Long roleId, List<Long> upmsPermissionIds) {
        return upmsPermissionActionRepository.findByIsDeletedFalseAndRoleIdAndPermissionIdIn(roleId, upmsPermissionIds);
    }

}
