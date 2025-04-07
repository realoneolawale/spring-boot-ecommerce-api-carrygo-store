package com.techqwerty.spring_boot_ecommerce_api_carrygo_store.enums;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class IntegerToProductCategoryEnumConverter implements Converter<String, ProductCategoryEnum> {
    @Override
    public ProductCategoryEnum convert(String source) {
        int code = Integer.parseInt(source);
        return ProductCategoryEnum.fromCode(code);
    }
}