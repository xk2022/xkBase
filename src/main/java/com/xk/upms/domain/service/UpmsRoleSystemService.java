package com.xk.upms.domain.service;

import com.xk.upms.domain.model.po.UpmsRoleSystem;

import java.util.List;

public interface UpmsRoleSystemService {

    void createAll(List<UpmsRoleSystem> upmsRoleSystems);

    void deleteAll(List<UpmsRoleSystem> upmsRoleSystems);

    List<UpmsRoleSystem> findAll();

    List<UpmsRoleSystem> findAllByRoleId(Long roleId);

}
