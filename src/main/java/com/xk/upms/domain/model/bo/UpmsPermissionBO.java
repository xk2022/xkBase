package com.xk.upms.domain.model.bo;

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
     * 類型(1:目錄,2:菜單,3:按鈕)
     */
    private Byte type;
    /**
     * 權限值
     */
    private String permissionValue;
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
}
