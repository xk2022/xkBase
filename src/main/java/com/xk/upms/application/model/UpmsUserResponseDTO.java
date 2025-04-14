package com.xk.upms.application.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpmsUserResponseDTO{

	private Long id;

	private String username;

	private String email;

	private String cellPhone;

	private Long roleId;

	private boolean enable;

	private boolean lock;

	private String token;

}
