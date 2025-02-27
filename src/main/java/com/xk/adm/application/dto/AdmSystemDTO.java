package com.xk.adm.application.dto;

import lombok.Data;

/**
 * 📌 `SystemDTO` - 系統資訊 DTO
 * 
 * @author yuan Created on 2025/02/25.
 */
@Data
public class AdmSystemDTO {
	private String uuid;
	private String code;
	private String name;
	private String description;
	private Boolean enabled;
}