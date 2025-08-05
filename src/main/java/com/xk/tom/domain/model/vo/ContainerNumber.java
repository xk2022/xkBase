package com.xk.tom.domain.model.vo;

import lombok.Value;

/**
 * 📌 ContainerNumber (櫃號值物件)
 * - 不可變 (immutable)
 *
 * @author yuan Created on 2025/08/05.
 */
@Value
public class ContainerNumber {

    String value;

    public ContainerNumber(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("櫃號不可為空");
        }
        this.value = value.trim();
    }

}
