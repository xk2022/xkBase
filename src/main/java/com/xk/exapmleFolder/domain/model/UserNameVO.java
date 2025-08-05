package com.xk.exapmleFolder.domain.model;

import lombok.Value;

/**
 * 📌 UserNameVO - 值物件
 * - 封裝使用者名稱，避免散落的字串檢查
 */
@Value
public class UserNameVO {
    String value;

    public boolean isValid() {
        return value != null && value.length() >= 3;
    }
}