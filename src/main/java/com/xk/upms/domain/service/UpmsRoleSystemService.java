package com.xk.upms.domain.service;

import com.xk.upms.domain.model.po.UpmsRoleSystem;

import java.util.List;

public interface UpmsRoleSystemService {

    List<UpmsRoleSystem> findAllByRoleId(Long roleId);

}
