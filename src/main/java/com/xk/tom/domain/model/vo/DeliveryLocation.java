package com.xk.tom.domain.model.vo;

import lombok.Value;

/**
 * 📌 DeliveryLocation (送貨地點值物件)
 *
 * @author yuan Created on 2025/08/05.
 */
@Value
public class DeliveryLocation {

    String address;

    public DeliveryLocation(String address) {
        if (address == null || address.isBlank()) {
            throw new IllegalArgumentException("送貨地點不可為空");
        }
        this.address = address.trim();
    }

}
