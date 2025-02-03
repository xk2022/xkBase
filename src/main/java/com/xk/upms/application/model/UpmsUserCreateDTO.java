package com.xk.upms.application.model;

import java.util.Date;

import lombok.Data;

@Data
public class UpmsUserCreateDTO {

	private String id;

	private String username;

	private String email;

	private String cellPhone;

	private String password;

	private Date lastLogin;

	private Boolean locked;

}
