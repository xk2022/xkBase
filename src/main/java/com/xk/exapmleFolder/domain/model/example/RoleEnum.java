package com.xk.exapmleFolder.domain.model.example;

import java.util.Arrays;
import java.util.Optional;

import lombok.Getter;

/**
 * 📌 `RoleEnum` - 使用者角色列舉
 * 
 * - `ADMIN` - 管理員
 * - `USER` - 註冊用戶
 * - `GUEST` - 訪客
 * - `fromString()` - 支援字串轉換成 Enum
 * - `getDescription()` - 獲取角色描述
 * 
 * @author yuan Created on 2025/01/23.
 * @author yuan Updated on 2025/01/23 something note here.
 */
@Getter
public enum RoleEnum {

    ADMIN("管理員"),
    USER("普通用戶"),
    GUEST("訪客");

    private final String description;

    RoleEnum(String description) {
        this.description = description;
    }

    /**
     * 📌 **取得角色描述**
     * @return String 角色名稱（中文）
     */
    public String getDescription() {
        return description;
    }

    /**
     * 📌 **支援 `String` 轉換為 `RoleEnum`**
     * - 避免 `Enum.valueOf()` 直接拋出 `IllegalArgumentException`
     * @param value 角色字串
     * @return Optional<RoleEnum>
     */
    public static Optional<RoleEnum> fromString(String value) {
        return Arrays.stream(RoleEnum.values())
                .filter(role -> role.name().equalsIgnoreCase(value))
                .findFirst();
    }

    /**
     * 📌 **支援 `String` 轉換為 `RoleEnum`（包含預設值）**
     * - 若無法匹配，則返回 `GUEST`
     * @param value 角色字串
     * @return RoleEnum
     */
    public static RoleEnum fromStringOrDefault(String value) {
        return fromString(value).orElse(GUEST);
    }
    
}
