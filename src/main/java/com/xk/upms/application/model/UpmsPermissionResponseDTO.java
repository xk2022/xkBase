package com.xk.upms.application.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpmsPermissionResponseDTO {

	List<Permission> permissions;

	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Permission{

		private Long id;

		private String name;

		private boolean status;

		private List<Permission> permissions;

		private List<Action> actions;

		@Data
		@NoArgsConstructor
		@AllArgsConstructor
		public static class Action{

			private Long id;

			private String name;

			private boolean active;

		}

	}

}
