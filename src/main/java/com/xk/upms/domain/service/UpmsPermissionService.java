package com.xk.upms.domain.service;

import com.xk.upms.domain.model.po.UpmsPermission;

import java.util.List;

/**
 * 📌 `UpmsPermissionService` - 使用者領域服務
 * 
 * - **負責處理 Permission 領域內的商業邏輯**
 * 
 * @author hank Created on 2025/02/11.
 * @author hank Updated on 2025/02/11 something note here.
 */
public interface UpmsPermissionService {

	List<UpmsPermission> findAll();

}
