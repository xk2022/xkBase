package com.xk.upms.domain.model.bo;

import java.time.ZonedDateTime;
import java.util.List;

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
	/**
	 * 所屬系統
	 */
	private Long systemId;
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
	 * 圖標
	 */
//    private String icon;
	/**
	 * 狀態(0:禁止,1:正常)
	 */
	private Boolean status;
	/**
	 * 排序
	 */
	private Long orders;
	/**
	 * 是否刪除狀態（0:刪除, 1:未刪除）
	 */
	private Boolean isDeleted;

	/**
	 * 刪除的使用者名稱
	 */
	private String deleteUser;

	/**
	 * 用戶被刪除的時間
	 */
	private ZonedDateTime deleteTime;

	/**
	 * 子權限
	 */
	private List<UpmsPermission> children;

	/**
	 * 父權限
	 */
	private UpmsPermission parent;

}
