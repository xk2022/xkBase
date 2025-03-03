package com.xk.common.util;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

/**
 * Utils
 * 
 * @param <T>
 * @author yuan Updated on 2025/02/27.
 */
public class GenericUpdateService<T> {

    public <S> T updateEntity(T entity, S request) {
        // 獲取 request 中為 null 或空字串的屬性名稱
        String[] ignoredProperties = getNullOrEmptyPropertyNames(request);

        // 只複製非 null 屬性
        XkBeanUtils.copyPropertiesAutoConvert(request, entity, ignoredProperties);

        return entity;
    }

    private <S> String[] getNullOrEmptyPropertyNames(S source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<>();
        for (PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            
            // 排除 null 值、空字串、Optional.empty()
            if (srcValue == null ||
                (srcValue instanceof Optional && ((Optional<?>) srcValue).isEmpty()) ||
                (srcValue instanceof String && ((String) srcValue).trim().isEmpty())) {
                emptyNames.add(pd.getName());
            }
        }
        return emptyNames.toArray(new String[0]);
    }
}
