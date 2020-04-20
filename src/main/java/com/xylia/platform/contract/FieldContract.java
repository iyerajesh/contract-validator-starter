package com.xylia.platform.contract;

import java.util.Map;
import java.util.Objects;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Strings.isNullOrEmpty;

public class FieldContract {

    private String fieldName;
    private String fieldType;

    public FieldContract(final String fieldName, final String fieldType) {
        checkArgument(isNullOrEmpty(fieldName), "fieldName cannot be blank!");
        this.fieldName = fieldName;
        this.fieldType = checkNotNull(fieldType);
    }

    public FieldContract() {
    }
    
    public static FieldContract forStringType(String fieldName) {
        return new FieldContract(fieldName, FieldDataType.CATEGORICAL.name());
    }

    public static FieldContract forNumericType(String fieldName) {
        return new FieldContract(fieldName, FieldDataType.NUMERIC.name());
    }

    public String getFieldName() {
        return fieldName;
    }

    public String getFieldType() {
        return fieldType;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FieldContract that = (FieldContract) o;
        return Objects.equals(fieldName, that.fieldName) &&
                Objects.equals(fieldType, that.fieldType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fieldName, fieldType);
    }

    public boolean validateDataType(Map<String, Object> inputData) {
        if (inputData.containsKey(fieldName) && fieldType.equalsIgnoreCase(FieldDataType.CATEGORICAL.name()))
            return (inputData.get(fieldName) instanceof String) ? true : false;
        else if (inputData.containsKey(fieldName) && fieldType.equalsIgnoreCase(FieldDataType.NUMERIC.name()))
            return (inputData.get(fieldName) instanceof Double) ? true : false;

        return false;
    }
}
