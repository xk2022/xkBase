package com.xk.tom.domain.model.enums;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

/**
 * 📌 訂單類型 (OrderType)
 * <p>
 * - 讓 DB 存小寫字串
 *
 * @author yuan Created on 2025/08/04.
 */
@Converter(autoApply = true)
public class OrderTypeConverter implements AttributeConverter<OrderType, String> {

    @Override
    public String convertToDatabaseColumn(OrderType type) {
        return (type == null) ? null : type.getCode();
    }

    @Override
    public OrderType convertToEntityAttribute(String dbData) {
        return (dbData == null) ? null : OrderType.fromCode(dbData);
    }

}
