package com.xk.upms.application.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpmsPermissionResponseDTO {

	private UUID uuid;

	private String name;

	private boolean active;

	private List<UpmsPermissionResponseDTO> permissions;

	private List<UpmsPermissionResponseDTO.Action> actions;

	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Action{

		private UUID uuid;

		private String name;

		private boolean active;

	}

}
