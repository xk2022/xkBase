package com.xk.upms.domain.service;

import com.xk.upms.domain.model.po.UpmsPermissionAction;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UpmsPermissionActionService {

    List<UpmsPermissionAction> findAllIn(List<Long> permissionIds);

}
