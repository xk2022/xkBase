package com.xk.upms.model.vo;

import java.util.Date;

import lombok.Data;

@Data
public class UpmsUserResp  {

	private String id;

	private String username;

	private String email;
	
	private String cellPhone;
	
	private String password;
	
	private Date lastLogin;
	
	private Boolean locked;
	
	public UpmsUserResp(String id,String username,String email,String cellPhone,Date lastLogin, Boolean locked) {
		this.id = id;
		this.username = username;
		this.email = email;
		this.cellPhone = cellPhone;
		this.lastLogin = lastLogin;
		this.locked = locked;
	}
	
	public UpmsUserResp() {
		
	}



}
