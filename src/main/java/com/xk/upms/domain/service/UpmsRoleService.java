package com.xk.upms.domain.service;

import java.util.List;
import java.util.Optional;

import com.xk.upms.domain.model.bo.UpmsRoleBO;

/**
 * 📌 `UpmsRoleService` - 角色領域服務
 * 
 * - **負責處理 User 領域內的商業邏輯**
 * 
 * @author yuan Created on 2025/02/03.
 * @author yuan Updated on 2025/01/01 something note here.
 */
public interface UpmsRoleService {

	// ============= 🟢【C】Create（創建）================
	/**
	 * 📌 創建或更新使用者（回傳 `Optional<T>` 以避免 `null`）
	 * 
	 * @param role 角色物件
	 * @return 儲存後的角色資訊
	 */
	UpmsRoleBO save(UpmsRoleBO role);

	// ============= 🔵【R】Read（查詢）================
	/**
	 * 📌 依據 ID 查詢單筆角色
	 * 
	 * @param roleId 角色 ID
	 * @return 角色物件（若存在）
	 */
	Optional<UpmsRoleBO> findById(Long roleId);

	/**
	 * 📌 查詢所有角色（無分頁） 📌 支援條件查詢（無分頁）
	 * 
	 * @param example
	 * @return
	 */
	List<UpmsRoleBO> findAll();

	// ============= 🟡【U】Update（更新）================
	/**
	 * 📌 更新角色資訊（直接呼叫 `save()`，但可額外擴充業務邏輯）
	 * 
	 * @param roleId
	 * @param updatedEntity
	 * @return
	 */
	UpmsRoleBO update(Long roleId, UpmsRoleBO updatedEntity);

	// ============= 🔴【D】Delete（刪除）================
	/**
	 * 📌 刪除角色（如果 ID 不存在則拋出 `ResourceNotFoundException`）
	 * 
	 * @param roleId 角色 ID
	 * @return 是否成功刪除
	 */
	boolean delete(Long roleId);

}
