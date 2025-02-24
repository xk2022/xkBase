package com.xk.upms.application.model;

import java.time.ZonedDateTime;

import lombok.Data;

@Data
public class UpmsActionResponseDTO {
	
	
	private Long id;
	
	private String actionName;
	
	private Long permissionId;
	
	private String url;
	
	private Long orders;
	
	private String method;

	private Boolean active;
	
	private Boolean isDeleted;
	
	private String deleteUser;
	
	private ZonedDateTime deleteTime;
}
