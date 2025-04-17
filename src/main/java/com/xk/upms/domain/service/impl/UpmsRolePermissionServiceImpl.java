package com.xk.upms.domain.service.impl;

import com.xk.common.handler.BusinessException;
import com.xk.common.util.XkBeanUtils;
import com.xk.upms.domain.dao.repository.UpmsRolePermissionRepository;
import com.xk.upms.domain.model.bo.UpmsPermissionBO;
import com.xk.upms.domain.model.bo.UpmsRolePermissionBO;
import com.xk.upms.domain.model.po.UpmsRolePermission;
import com.xk.upms.domain.service.UpmsRolePermissionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class UpmsRolePermissionServiceImpl implements UpmsRolePermissionService {

    private final UpmsRolePermissionRepository upmsRolePermissionRepository;

    @Override
    public List<UpmsRolePermission> findAll(UUID systemUuid, UUID roleUuid) {
        return upmsRolePermissionRepository.findByIsDeletedFalseAndSystemUuidAndRoleUuid(systemUuid, roleUuid);
    }

    @Override
    public UpmsRolePermissionBO save(UpmsRolePermissionBO upmsRolePermissionBO) {
        UpmsRolePermissionBO resultBO = new UpmsRolePermissionBO();
        log.info("📌 儲存角色權限: {}", upmsRolePermissionBO);
        if (upmsRolePermissionBO == null) {
            throw new IllegalArgumentException("角色權限不能為 null");
        }
        UpmsRolePermission rolePermissionPO = XkBeanUtils.copyProperties(upmsRolePermissionBO, UpmsRolePermission::new);
        UpmsRolePermission savedPO = upmsRolePermissionRepository.save(rolePermissionPO);
        XkBeanUtils.copyPropertiesAutoConvert(savedPO ,resultBO);
        return resultBO;
    }

    @Override
    public void deleteAll(List<UpmsRolePermission> upmsRolePermissions) {
        if(null == upmsRolePermissions || upmsRolePermissions.isEmpty()){
             return;
        }
        for(UpmsRolePermission upmsRolePermission : upmsRolePermissions){
            upmsRolePermission.setIsDeleted(true);
        }
        upmsRolePermissionRepository.saveAll(upmsRolePermissions);
    }

    @Override
    public void saveAll(List<UpmsRolePermission> upmsRolePermissions){
        if(null == upmsRolePermissions || upmsRolePermissions.isEmpty()){
            throw new BusinessException("角色權限清單為空");
        }
        for(UpmsRolePermission upmsRolePermission : upmsRolePermissions){
            upmsRolePermission.setIsDeleted(false);
        }
        upmsRolePermissionRepository.saveAll(upmsRolePermissions);
    }

}
