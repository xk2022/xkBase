package com.xk.upms.application.model;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

import lombok.Data;

@Data
public class UpmsRoleResponseDTO {
	
	
	private UUID uuid;
	
	private String code;
	
	private String title;
	
	private String description;

	private List<UUID> systemUuids;
	
	private String orders;
	
	private Boolean isDeleted;
	
	private String deleteUser;
	
	private ZonedDateTime deleteTime;

}
