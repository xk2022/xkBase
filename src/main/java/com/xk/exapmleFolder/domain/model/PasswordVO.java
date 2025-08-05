package com.xk.exapmleFolder.domain.model;

import lombok.Value;

/**
 * 📌 PasswordVO - 值物件
 * - 封裝 Hash 後的密碼
 */
@Value
public class PasswordVO {
    String hashedPassword;

    public boolean isHashed() {
        return hashedPassword != null && hashedPassword.startsWith("$2a$"); // e.g. BCrypt
    }
}