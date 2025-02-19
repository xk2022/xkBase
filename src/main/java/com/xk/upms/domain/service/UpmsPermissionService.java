package com.xk.upms.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;

import com.xk.upms.domain.model.bo.UpmsPermissionBO;
import com.xk.upms.domain.model.po.UpmsPermission;

/**
 * 📌 `UpmsPermissionService` - 使用者領域服務
 * 
 * - **負責處理 Permission 領域內的商業邏輯**
 * 
 * @author hank Created on 2025/02/11.
 * @author hank Updated on 2025/02/11 something note here.
 */
public interface UpmsPermissionService {

	// ============= 🟢【C】Create（創建）================
	/**
	 * 📌 創建權限（回傳 `Optional<T>` 以避免 `null`）
	 * 
	 * @param permission 權限物件
	 * @return 儲存後的權限資訊
	 */
	UpmsPermissionBO save(UpmsPermissionBO permission);

	// ============= 🔴【D】Delete（刪除）================
	/**
	 * /** 📌 刪除權限
	 * 
	 * @param permissionId 權限 ID
	 * @return 是否成功刪除
	 */
	boolean delete(Long permissionId);

	// ============= 🔵【R】Read（查詢）================
	/**
	 * 📌 依據 ID 查詢單筆權限
	 * 
	 * @param permissionId 權限 ID
	 * @return 權限物件（若存在）
	 */
	Optional<UpmsPermissionBO> findById(Long permissionId);

	// ============= 🟡【U】Update（更新）================
	/**
	 * 📌 更新權限資訊（直接呼叫 `save()`，但可額外擴充業務邏輯）
	 * 
	 * @param permissionId
	 * @param updatePermissionEntity
	 * @return
	 */
	UpmsPermissionBO update(Long permissionId, UpmsPermissionBO updatePermissionEntity);
	
	/**
	 * 📌 查詢所有權限（無分頁） 📌 支援條件查詢（無分頁）
	 * 
	 * @param sort
	 * @return
	 */
	List<UpmsPermissionBO> findAll(Sort sort);
	
	/**
	 * 📌 查詢所有權限（無分頁） 📌 依照層級查詢
	 * 
	 */
	List<UpmsPermission> buildTree(List<UpmsPermission> permissions,Long pid);
}
