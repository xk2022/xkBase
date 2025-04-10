package com.xk.upms.application.model;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class UpmsUserResponseDTO{

	private String id;

	private String username;

	private String email;

	private String cellPhone;

	private Long roleId;

	private boolean enable;

	private boolean lock;

	private List<UpmsUserResponseDTO.System> systems;

	private List<UpmsUserResponseDTO.Permission> permissions;

	private String token;

	@Data
	public static class System{

		private UUID uuid;

		private String name;

	}

	@Data
	public static class Permission{

		private Long id;

		private String name;

		private boolean active;

		private List<UpmsUserResponseDTO.Permission> permissions;

		private List<UpmsUserResponseDTO.Permission.Action> actions;

		@Data
		public static class Action{

			private Long id;

			private String name;

			private boolean active;

		}

	}

}
