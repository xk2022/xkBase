package com.xk.adm.application.model;

import lombok.Data;

/**
 * 📌 `DictionaryDTO` - 字典數據 DTO
 * 
 * @author yuan Created on 2025/02/25
 */
@Data
public class DictionaryDTO {
	private String id;
	private String type;
	private String key;
	private String value;
	private String description;
}