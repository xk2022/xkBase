package com.xk.upms.model.vo;

import java.util.Date;

import lombok.Data;

@Data
public class UpmsUserResp {

	private String id;

	private String username;

	private String email;
	
	private String cellPhone;
	
	private String password;
	
	private Date lastLogin;
	
	private Boolean locked;

}
