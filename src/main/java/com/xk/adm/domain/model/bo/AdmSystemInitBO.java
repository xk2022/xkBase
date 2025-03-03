package com.xk.adm.domain.model.bo;

import java.time.ZonedDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * 📌 `AdmSystemInitBO` （業務物件 - Business Object）
 * 
 * - AdmSystemManageUseCase.createSampleSystems()
 * 
 * @author yuan Created on 2025/02/25.
 */
@Getter
@Setter
@AllArgsConstructor
public class AdmSystemInitBO {

	private String createdBy;
	private ZonedDateTime createdTime;

	private String code; // 系統代碼
	private String name; // 系統名稱
	private String description; // 系統描述
	private Boolean enabled; // 啟用狀態

}
