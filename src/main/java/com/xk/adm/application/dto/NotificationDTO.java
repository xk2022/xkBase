package com.xk.adm.application.dto;

import lombok.Data;

/**
 * 📌 `NotificationDTO` - 系統通知 DTO
 */
@Data
public class NotificationDTO {
	private String id;
	private String type;
	private String title;
	private String message;
	private String recipient;
	private boolean read;
}