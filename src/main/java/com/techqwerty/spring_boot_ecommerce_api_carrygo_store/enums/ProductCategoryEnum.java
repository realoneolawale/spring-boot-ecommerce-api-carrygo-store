package com.techqwerty.spring_boot_ecommerce_api_carrygo_store.enums;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

public enum ProductCategoryEnum {
    NIKE(1),
    PUMA(2),
    ZARA(3),
    ADDIDAS(4);

    private final int value;

    ProductCategoryEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static ProductCategoryEnum fromCode(int value) {
        for (ProductCategoryEnum p : values()) {
            if (p.value == value)
                return p;
        }
        throw new IllegalArgumentException("Invalid category value: " + value);
    }
}
