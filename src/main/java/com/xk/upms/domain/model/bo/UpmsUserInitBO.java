package com.xk.upms.domain.model.bo;

import java.time.ZonedDateTime;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 📌 `UpmsUserInitBO`（業務物件 - Business Object）
 * 
 * - UpmsUserCreateUseCase.createSampleUsers()
 * 
 * @author yuan Created on 2025/02/14.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(onlyExplicitlyIncluded = true) // ✅ 避免洩露敏感資料
public class UpmsUserInitBO {

	private UUID uuid;
	private String account;
	private String username;
	private String email;
	private String cellPhone;
	private String password;

	private Boolean deleted;

	private ZonedDateTime deletedTime;

	private String createdBy;

	private ZonedDateTime createdTime;

	private String updatedBy;

	private ZonedDateTime updatedTime;

}
