package com.xk.upms.application.model;

import java.time.ZonedDateTime;

import lombok.Data;

@Data
public class UpmsPermissionResponseDTO {

	private String id;

	private String systemId;

	private String pid;

	private String name;

	private String type;

	private String permissionValue;

	private String uri;

	private String status;

	private String orders;

	private Boolean isDeleted;

	private String deleteUser;

	private ZonedDateTime deleteTime;

}
