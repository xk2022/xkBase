package com.xk.upms.domain.model.bo;

import java.time.ZonedDateTime;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 📌 `UpmsRoleBO`（業務物件 - Business Object）
 * 
 * - UpmsRoleCreateUseCase.createSampleRoles()
 * 
 * @author yuan Created on 2025/02/14.
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpmsRoleInitBO {

	private UUID uuid;

	private String code;

	private String title;

	private String description;

	private Long orders;

	private Boolean deleted;

	private ZonedDateTime deletedTime;

	private String createdBy;

	private ZonedDateTime createdTime;

	private String updatedBy;

	private ZonedDateTime updatedTime;


}
