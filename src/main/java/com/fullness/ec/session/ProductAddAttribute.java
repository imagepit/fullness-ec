package com.fullness.ec.session;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum ProductAddAttribute {
    PRODUCT_ADD_FORM,
    CATEGORIES,
    IMAGE_BYTE_DATA;

    public static List<String> names(){
        return Stream.of(ProductAddAttribute.values())
                .map(ProductAddAttribute::name)
                .collect(Collectors.toList());
    }
}
