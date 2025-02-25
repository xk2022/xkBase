package com.xk.adm.application.model;

import lombok.Data;

/**
 * 📌 `SystemConfigDTO` - 系統設定 DTO
 * 
 * @author yuan Created on 2025/02/24.
 */
@Data
public class SystemConfigDTO {

	private String systemName;
	private String timeZone;
	private String language;
	private String currency;

}