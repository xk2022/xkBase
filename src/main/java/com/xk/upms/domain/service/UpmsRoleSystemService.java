package com.xk.upms.domain.service;

import com.xk.upms.domain.model.po.UpmsRoleSystem;

import java.util.List;
import java.util.UUID;

public interface UpmsRoleSystemService {

    void createAll(List<UpmsRoleSystem> upmsRoleSystems);

    void deleteAll(List<UpmsRoleSystem> upmsRoleSystems);

    List<UpmsRoleSystem> findAll();

    List<UpmsRoleSystem> findAllByRoleUuid(UUID roleUuid);

}
