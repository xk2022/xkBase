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
    public List<UpmsRoleSystem> findAll() {
        return upmsRoleSystemRepository.findByIsDeletedFalse();
    }

    @Override
    public List<UpmsRoleSystem> findAllByRoleId(Long roleId) {
        return upmsRoleSystemRepository.findByIsDeletedFalseAndRoleIdOrderByIdAsc(roleId);
    }

}
