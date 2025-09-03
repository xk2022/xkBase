package com.xk.upms.domain.model.bo;

import java.time.ZonedDateTime;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(onlyExplicitlyIncluded = true) 
public class UpmsActionBO {
	
	private UUID uuid;
	
	private String actionName;
	
	private Long permissionId;
	
	private String url;
	
	private Long orders;
	
	private String method;
	
	private Boolean active;
	
	private String deleteUser;

	private Boolean deleted;

	private ZonedDateTime deletedTime;

	private String createdBy;

	private ZonedDateTime createdTime;

	private String updatedBy;

	private ZonedDateTime updatedTime;



}
