package com.xk.exapmleFolder.domain.model;

import lombok.Value;

/**
 * 📌 EmailVO - 值物件
 * - 負責封裝 Email 格式與驗證
 */
@Value
public class EmailVO {
    String value;

    public boolean isValid() {
        return value != null && value.matches("^[A-Za-z0-9+_.-]+@(.+)$");
    }
}