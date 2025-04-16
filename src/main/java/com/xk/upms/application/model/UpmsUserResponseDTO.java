package com.xk.upms.application.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpmsUserResponseDTO{

	private UUID userUuid;

	private String username;

	private String email;

	private String cellPhone;

	private UUID roleUuid;

	private boolean enable;

	private boolean lock;

	private String token;

}
