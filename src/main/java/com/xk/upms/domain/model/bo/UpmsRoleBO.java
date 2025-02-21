package com.xk.upms.domain.model.bo;

import java.time.ZonedDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 📌 `UpmsRoleBO`（業務物件 - Business Object）
 * 
 * - **封裝使用者腳色的業務邏輯**
 * - **不可變（Immutable）設計**
 * 
 * @author hank Created on 2025/02/07
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(onlyExplicitlyIncluded = true) // ✅ 避免洩露敏感資料
public class UpmsRoleBO {

	private Long id;
	
	private String code;
	
	private String title;
	
	private String description;
	
	@ToString.Include
	private Long orders;
	

    private Boolean isDeleted;
    
    private String deleteUser;
    
    private ZonedDateTime deleteTime;

}
