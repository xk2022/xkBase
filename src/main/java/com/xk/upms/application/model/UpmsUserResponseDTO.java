package com.xk.upms.application.model;

import com.xk.common.util.dto.JwtUserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpmsUserResponseDTO{

	private UUID uuid;

	private String account;

	private String username;

	private String email;

	private String cellPhone;

	private UUID roleUuid;

	private boolean enabled;

	private boolean locked;

	private List<JwtUserDTO.SystemDTO> systemDTOs;

	private String token;

}
