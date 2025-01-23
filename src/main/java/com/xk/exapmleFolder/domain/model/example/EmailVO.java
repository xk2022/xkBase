package com.xk.exapmleFolder.domain.model.example;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

/**
 * 📌 EmailVO.java（值物件 - Value Object）
 * 
 * - **封裝 email 作為值物件**
 * - **確保 email 格式有效**
 * - **JPA @Embeddable 支援**
 * - **不可變設計**
 * 
 * @author yuan Created on 2025/01/21.
 */
@Embeddable
public record EmailVO(
        @Column(name = "email", nullable = false, unique = true) String value
) {
    // ✅ Email 正規表示式（更強的驗證）
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

    /**
     * 📌 建構子 - 確保 email 格式正確
     */
    public EmailVO {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("⚠️ Email 不能為空！");
        }
        String normalized = value.trim().toLowerCase(); // ✅ 清理並標準化
        if (!normalized.matches(EMAIL_REGEX)) {
            throw new IllegalArgumentException("⚠️ 無效的 Email 格式: " + normalized);
        }
    }

    /**
     * 📌 是否為有效 Email
     */
    public boolean isValid() {
        return value != null && value.matches(EMAIL_REGEX);
    }

    /**
     * 📌 避免洩露完整 Email
     */
    @Override
    public String toString() {
        return maskEmail(value);
    }

    /**
     * 📌 Email 隱藏部分內容，防止洩露
     */
    private static String maskEmail(String email) {
        int index = email.indexOf("@");
        if (index > 2) {
            return email.substring(0, 2) + "****" + email.substring(index);
        }
        return "****@****";
    }

    /**
     * 📌 確保 `equals()` 和 `hashCode()` 正確運作
     */
    @Override
    public boolean equals(Object obj) {
        return obj instanceof EmailVO other && Objects.equals(this.value, other.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
    
}
