package com.xk.upms.application.model;

import lombok.Data;

@Data
public class UpmsUserResponseDTO{

	private String id;

	private String username;

	private String email;

	private String cellPhone;

	private Long roleId;

	private boolean enable;

	private boolean lock;

}
