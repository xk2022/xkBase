package com.xk.upms.domain.model.bo;

import java.time.ZonedDateTime;

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
