package com.xk.adm.domain.model.systemConfig;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 📌 `SystemConfigBO`（業務物件 - Business Object）
 * 
 * @author yuan Created on 2025/02/24.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(onlyExplicitlyIncluded = true) // ✅ 避免洩露敏感資料
public class SystemConfigBO {

	private String id;
	private String systemName;
	private String timeZone;
	private String language;
	private String currency;

}