package com.xk.adm.application.model;

import lombok.Data;

/**
 * 📌 `SystemDTO` - 系統資訊 DTO
 */
@Data
public class AdmSystemDTO {
	private String uuid;
	private String code;
	private String name;
	private String description;
	private Boolean enabled;
}