package com.xk.tom.domain.model.enums;

import lombok.Getter;

/**
 * 📌 訂單類型 (OrderType)
 * <p>
 * - IMPORT：進口
 * - EXPORT：出口
 * <p>
 * 存到 DB 時建議用小寫字串 ("import", "export")
 * 在程式內則使用 Enum 型別
 *
 * @author yuan Created on 2025/08/04.
 */
@Getter
public enum OrderType {

    IMPORT("import", "進口"),
    EXPORT("export", "出口");

    private final String code;
    private final String label;

    OrderType(String code, String label) {
        this.code = code;
        this.label = label;
    }

    /**
     * 透過 code 找 Enum（for DB / API 轉換）
     */
    public static OrderType fromCode(String code) {
        for (OrderType type : values()) {
            if (type.code.equalsIgnoreCase(code)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown OrderType code: " + code);
    }

}
