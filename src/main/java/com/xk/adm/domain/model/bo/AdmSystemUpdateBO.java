package com.xk.adm.domain.model.bo;

import lombok.Data;

/**
 * 📌 `AdmSystemUpdateBO`
 * 
 * - **用於系統更新**，不允許修改 `uuid` 
 * - 允許部分欄位更新
 * 
 * @author yuan Created on 2025/02/25.
 */
@Data
public class AdmSystemUpdateBO {
	private String code; // 系統代號
	private String name; // 系統名稱
	private String description; // 系統描述
	private Boolean isActive; // 系統是否啟用
}
