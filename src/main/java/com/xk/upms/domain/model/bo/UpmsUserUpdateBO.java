package com.xk.upms.domain.model.bo;

import lombok.Data;

import java.time.ZonedDateTime;
import java.util.UUID;

@Data
public class UpmsUserUpdateBO {

	private UUID uuid;

	private String name;

	private String email;

	private Boolean deleted;

	private ZonedDateTime deletedTime;

	private String createdBy;

	private ZonedDateTime createdTime;

	private String updatedBy;

	private ZonedDateTime updatedTime;


}
