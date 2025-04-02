package com.xk.upms.domain.service.impl;

import com.xk.upms.domain.dao.repository.UpmsPermissionActionRepository;
import com.xk.upms.domain.model.po.UpmsPermissionAction;
import com.xk.upms.domain.service.UpmsPermissionActionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UpmsPermissionActionServiceImpl implements UpmsPermissionActionService {

    private final UpmsPermissionActionRepository upmsPermissionActionRepository;

    @Override
    public List<UpmsPermissionAction> findAllIn(List<Long> permissionIds) {
        return upmsPermissionActionRepository.findByIsDeletedFalseAndPermissionIdIn(permissionIds);
    }

}
