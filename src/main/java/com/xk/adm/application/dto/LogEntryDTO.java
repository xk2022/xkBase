package com.xk.adm.application.dto;

import java.time.LocalDateTime;

import lombok.Data;

/**
 * 📌 `SystemConfigDTO` - 系統設定 DTO
 * 
 * @author yuan Created on 2025/02/25.
 */
@Data
public class LogEntryDTO {
	private String id;
	private String userId;
	private String action;
	private String message;
	private LocalDateTime timestamp;
}
