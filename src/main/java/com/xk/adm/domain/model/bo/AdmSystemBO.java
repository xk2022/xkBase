package com.xk.adm.domain.model.bo;

import java.time.LocalDateTime;

import lombok.Data;

/**
 * 📌 `AdmSystemBO`
 * 
 * - **業務物件（BO）**，用於系統內部邏輯處理 
 * - 包含系統的完整資訊
 * 
 * @author yuan Created on 2025/02/25.
 */
@Data
public class AdmSystemBO {
	private String uuid; // 系統唯一識別碼
	private String code; // 系統代碼
	private String name; // 系統名稱
	private String description; // 系統描述
	private Boolean enabled; // 系統是否啟用
	private Boolean deleted; // 是否已刪除（軟刪除標誌）
	private LocalDateTime createdAt; // 創建時間
	private LocalDateTime updatedAt; // 更新時間
}
