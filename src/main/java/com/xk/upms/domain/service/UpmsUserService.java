package com.xk.upms.domain.service;

import com.xk.upms.domain.model.bo.UpmsUserBO;
import com.xk.upms.domain.model.bo.UpmsUserInitBO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * 📌 `UpmsUserService` - 使用者領域服務
 * 
 * - **負責處理 User 領域內的商業邏輯**
 * 
 * @author yuan Created on 2025/02/03.
 * @author yuan Updated on 2025/02/14 saveAllUsers().
 */
public interface UpmsUserService {
	
	// ============= 🟢【C】Create（創建）================
	/**
	 * 📌 創建或更新單一使用者
	 * 
	 *  此方法用於**創建或更新**使用者資訊，會根據 `user` 物件的 ID 判斷：
	 *  - **若 ID 存在**：更新現有使用者資訊
	 *  - **若 ID 不存在**：創建新使用者
	 * 
	 * @param user 使用者物件（`UpmsUserBO`），包含使用者名稱、信箱、密碼等資訊
	 * @return 已儲存的使用者資訊
	 */
	UpmsUserBO save(UpmsUserBO user);

	/**
	 * 📌 批次創建或更新使用者
	 * 
	 *  此方法接收一組 `UpmsUserBO` 物件列表，並對每個使用者執行**創建或更新**：
	 *  - **若 ID 存在**：更新現有使用者資訊
	 *  - **若 ID 不存在**：創建新使用者
	 *  
	 * ⚠️ **請確保列表中的物件不為 `null`，避免發生異常**
	 * 
	 * @param boList 需要儲存或更新的使用者物件列表
	 * @return 已儲存的使用者物件列表
	 */
	List<UpmsUserBO> saveAllUsers(List<UpmsUserInitBO> boList);

	// ============= 🔵【R】Read（查詢）================
	/**
	 * 📌 依據 ID 查詢單筆使用者
	 * 
	 * @param userUuid 使用者 UUID
	 * @return 使用者物件（若存在）
	 */
	Optional<UpmsUserBO> findByUuid(UUID userUuid);

	/**
	 * 📌 依據 `username` 查詢使用者
	 * 
	 * @param username
	 * @return
	 */
	Optional<UpmsUserBO> findByAccount(String username);

	// ============= ⚡【查詢 & 過濾】================
	/**
	 * 📌 查詢所有使用者（支援分頁） 📌 支援條件查詢（分頁）
	 * 
	 * @param example
	 * @param pageable
	 * @return
	 */
//    Page<UpmsUserBO> findAll(Pageable pageable);
	Page<UpmsUserBO> findAll(UpmsUserBO request, Pageable pageable);

	/**
	 * 📌 查詢所有使用者（無分頁） 📌 支援條件查詢（無分頁）
	 * 
	 * @param example
	 * @return
	 */
	List<UpmsUserBO> findAllLike(String keyword, Boolean enabled, Boolean locked);

	// ============= 🟡【U】Update（更新）================
	/**
	 * 📌 更新使用者資訊（直接呼叫 `save()`，但可額外擴充業務邏輯）
	 *
	 * @param updateData
	 * @return
	 */
	UpmsUserBO update(UUID uuid, UpmsUserBO updateData);

	// ============= 🔴【D】Delete（刪除）================
	/**
	 * 📌 刪除使用者（如果 ID 不存在則拋出 `ResourceNotFoundException`）
	 * 
	 * @param userUuid 使用者 UUID
	 * @return 是否成功刪除
	 */
	boolean delete(UUID userUuid);

}
