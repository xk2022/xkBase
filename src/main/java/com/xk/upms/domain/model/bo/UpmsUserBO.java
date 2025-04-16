package com.xk.upms.domain.model.bo;

import lombok.*;

import java.time.ZonedDateTime;
import java.util.UUID;

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

    private UUID uuid;

    @ToString.Include
    private String username;

    private String email;

    private String cellPhone;

    private UUID roleUuid;

    private String password;

    private Boolean enabled;

    private Boolean locked;

    private ZonedDateTime lastLogin;
    
    private Integer failedAttempts = 0;
    
    private Boolean isDeleted;
    
    private String deleteUser;
    
    private ZonedDateTime deleteTime;

}
