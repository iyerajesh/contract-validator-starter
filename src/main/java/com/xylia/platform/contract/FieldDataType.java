package com.xylia.platform.contract;

import java.util.Arrays;

public enum FieldDataType {

    CATEGORICAL("Categorical"),
    NUMERIC("Numeric");

    private String name;

    FieldDataType(String name) {
        this.name = name;
    }

    public static FieldDataType parse(String name) {
        return Arrays.stream(values())
                .filter(s -> s.name.equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unknown name: " + name));
    }
}
