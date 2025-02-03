package com.xk.upms.domain.model.bo;

import com.xk.exapmleFolder.domain.model.example.RoleEnum;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 📌 `ExampleBO`（業務物件 - Business Object）
 * 
 * - **封裝使用者的業務邏輯**
 * - **不可變（Immutable）設計**
 * - **提供 `isAdmin()`、`isUser()` 方法**
 * - **工廠方法 `of()`，統一創建邏輯**
 * 
 * @author yuan Created on 2025/01/21.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(onlyExplicitlyIncluded = true) // ✅ 避免洩露敏感資料
public class UpmsUserBO {

    @ToString.Include
    private String username;

//    private EmailVO email;
    private String email;
    
    @ToString.Include
    private RoleEnum role;

    /**
     * 📌 是否為管理員
     */
    public boolean isAdmin() {
        return this.role == RoleEnum.ADMIN;
    }

    /**
     * 📌 是否為一般使用者
     */
    public boolean isUser() {
        return this.role == RoleEnum.USER;
    }

}
