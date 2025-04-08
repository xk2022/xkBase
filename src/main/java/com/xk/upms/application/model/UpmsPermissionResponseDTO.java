package com.xk.upms.application.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpmsPermissionResponseDTO {

	private Long id;

	private String name;

	private boolean active;

	private List<UpmsPermissionResponseDTO> permissions;

	private List<UpmsPermissionResponseDTO.Action> actions;

	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Action{

		private Long id;

		private String name;

		private boolean active;

	}

}
