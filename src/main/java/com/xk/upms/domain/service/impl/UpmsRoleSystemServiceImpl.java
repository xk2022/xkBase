package com.xk.upms.domain.service.impl;

import com.xk.upms.domain.dao.repository.UpmsRoleSystemRepository;
import com.xk.upms.domain.model.po.UpmsRoleSystem;
import com.xk.upms.domain.service.UpmsRoleSystemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UpmsRoleSystemServiceImpl implements UpmsRoleSystemService {

    private final UpmsRoleSystemRepository upmsRoleSystemRepository;

    @Override
    public void createAll(List<UpmsRoleSystem> upmsRoleSystems) {
        if(null == upmsRoleSystems || upmsRoleSystems.isEmpty()){
            return;
        }
        for(UpmsRoleSystem upmsRoleSystem : upmsRoleSystems){
            upmsRoleSystem.setIsDeleted(false);
        }
        upmsRoleSystemRepository.saveAll(upmsRoleSystems);
    }

    @Override
    public void deleteAll(List<UpmsRoleSystem> upmsRoleSystems) {
        if(null == upmsRoleSystems || upmsRoleSystems.isEmpty()){
            return;
        }
        for(UpmsRoleSystem upmsRoleSystem : upmsRoleSystems){
            upmsRoleSystem.setIsDeleted(true);
        }
        upmsRoleSystemRepository.saveAll(upmsRoleSystems);
    }

    @Override
    public List<UpmsRoleSystem> findAll() {
        return upmsRoleSystemRepository.findByIsDeletedFalse();
    }

    @Override
    public List<UpmsRoleSystem> findAllByRoleId(Long roleId) {
        return upmsRoleSystemRepository.findByIsDeletedFalseAndRoleIdOrderByIdAsc(roleId);
    }

}
