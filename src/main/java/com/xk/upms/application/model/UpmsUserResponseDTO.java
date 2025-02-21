package com.xk.upms.application.model;

import java.time.ZonedDateTime;
import java.util.Date;

import lombok.Data;

@Data
public class UpmsUserResponseDTO {

	private String id;

	private String username;

	private String email;

	private String cellPhone;

	private String password;

	private Date lastLogin;

	private Boolean enabled;

	private Boolean locked;
	
	private Boolean isDeleted;
	
	private String deletedUser;
	
	private ZonedDateTime deleteTime;

}
