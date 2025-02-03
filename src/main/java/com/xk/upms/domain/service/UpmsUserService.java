package com.xk.upms.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.xk.upms.domain.model.bo.UpmsUserBO;

/**
 * 📌 `UpmsUserService` - 使用者領域服務
 * 
 * - **負責處理 User 領域內的商業邏輯**
 * 
 * @author yuan Created on 2025/02/03.
 * @author yuan Updated on 2025/01/01 something note here.
 */
public interface UpmsUserService {

	// ============= 🟢【C】Create（創建）================
	/**
	 * 📌 創建或更新使用者（回傳 `Optional<T>` 以避免 `null`）
	 * 
	 * @param user 使用者物件
	 * @return 儲存後的使用者資訊
	 */
	UpmsUserBO save(UpmsUserBO user);

	// ============= 🔵【R】Read（查詢）================
	/**
	 * 📌 依據 ID 查詢單筆使用者
	 * 
	 * @param userId 使用者 ID
	 * @return 使用者物件（若存在）
	 */
	Optional<UpmsUserBO> findById(Long userId);

	/**
	 * 📌 依據 `username` 查詢使用者
	 * 
	 * @param username
	 * @return
	 */
	Optional<UpmsUserBO> findByUsername(String username);

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
//    List<UpmsUserBO> findAll();
//    List<UpmsUserBO> findAll(UpmsUserBO request);
	List<UpmsUserBO> findAll(UpmsUserBO request, Sort sort);

	// ============= 🟡【U】Update（更新）================
	/**
	 * 📌 更新使用者資訊（直接呼叫 `save()`，但可額外擴充業務邏輯）
	 * 
	 * @param userId
	 * @param updateData
	 * @return
	 */
	UpmsUserBO update(Long userId, UpmsUserBO updateData);

	// ============= 🔴【D】Delete（刪除）================
	/**
	 * 📌 刪除使用者（如果 ID 不存在則拋出 `ResourceNotFoundException`）
	 * 
	 * @param userId 使用者 ID
	 * @return 是否成功刪除
	 */
	boolean delete(Long userId);

}
