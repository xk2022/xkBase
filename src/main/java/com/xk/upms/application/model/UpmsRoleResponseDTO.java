package com.xk.upms.application.model;

import java.time.ZonedDateTime;

import lombok.Data;

@Data
public class UpmsRoleResponseDTO {
	
	
	private String id;
	
	private String code;
	
	private String title;
	
	private String description;
	
	private String orders;
	
	private Boolean isDeleted;
	
	private String deleteUser;
	
	private ZonedDateTime deleteTime;

}
