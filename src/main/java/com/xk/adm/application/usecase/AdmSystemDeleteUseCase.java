package com.xk.adm.application.usecase;

import java.util.UUID;

/**
 * 📌 刪除管理系統 Use Case
 *
 * - 提供刪除 `AdmSystem` 的業務邏輯 
 * - 採用 **軟刪除**，不會真的移除資料 
 * - 確保關聯性與約束
 * 
 * @author yuan Created on 2025/02/25.
 */
public interface AdmSystemDeleteUseCase {

	/**
	 * 刪除系統（軟刪除）
	 *
	 * @param uuid 欲刪除的系統 UUID
	 */
	void delete(UUID uuid);
}
