package com.xk.adm.domain.model.bo;

import lombok.Data;

/**
 * 📌 `AdmSystemCreateBO`
 * 
 * - **用於系統建立**，不包含 `uuid`、`createdAt`、`updatedAt` 
 * - **讓建立操作更安全**，避免手動填寫 `uuid`
 * 
 * @author yuan Created on 2025/02/25.
 */
@Data
public class AdmSystemCreateBO {
	private String code; // 系統代碼
	private String name; // 系統名稱
	private String description; // 系統描述
	private Boolean isActive; // 系統是否啟用
}
