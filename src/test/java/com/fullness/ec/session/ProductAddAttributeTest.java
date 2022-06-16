package com.fullness.ec.session;

import org.junit.jupiter.api.Test;

import java.util.List;

public class ProductAddAttributeTest {
    @Test
    void namesTest(){
        List<String> enumNames = ProductAddAttribute.names();
        System.out.println(enumNames);
    }
}
