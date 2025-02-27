package com.xk.adm.application.dto;

import lombok.Data;

/**
 * 📌 `SystemDTO` - 系統資訊 DTO
 * 
 * @author yuan Created on 2025/02/26.
 */
@Data
public class AdmSystemResponse {
	private String uuid;
	private String name;
	private String description;
	private Boolean enabled;
}