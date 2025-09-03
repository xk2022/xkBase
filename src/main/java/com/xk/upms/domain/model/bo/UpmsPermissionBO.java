package com.xk.upms.domain.model.bo;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

import com.xk.upms.domain.model.po.UpmsPermission;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 📌 `UpmsRoleBO`（業務物件 - Business Object）
 * 
 * - **封裝使用者腳色的業務邏輯** - **不可變（Immutable）設計**
 * 
 * @author hank Created on 2025/02/07
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(onlyExplicitlyIncluded = true) // ✅ 避免洩露敏感資料
public class UpmsPermissionBO {

	private UUID uuid;

	/**
	 * 所屬系統
	 */
	private UUID systemId;

	/**
	 * 所屬上層
	 */
	private Long pid;

	/**
	 * 名稱
	 */
	private String name;

	/**
	 * 路徑
	 */
	private String uri;

	/**
	 * 狀態(0:禁止,1:正常)
	 */
	private Boolean status;

	/**
	 * 排序
	 */
	private Long orders;

	/**
	 * 刪除的使用者名稱
	 */
	private String deleteUser;


	/**
	 * 子權限
	 */
	private List<UpmsActionBO> children;

	private Boolean deleted;

	private ZonedDateTime deletedTime;

	private String createdBy;

	private ZonedDateTime createdTime;

	private String updatedBy;

	private ZonedDateTime updatedTime;

//
//	/**
//	 * 父權限
//	 */
//	private UpmsPermission parent;

}
