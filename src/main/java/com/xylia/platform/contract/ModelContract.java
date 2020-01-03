package com.xylia.platform.contract;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ModelContract {

    private final String modelName;
    private List<FieldContract> fieldContracts = new ArrayList<>();

    ModelContract(final String modelName, final List<FieldContract> fieldContracts) {
        this.modelName = modelName;
        this.fieldContracts = fieldContracts;
    }

    public ModelContract(final String modelName) {
        this.modelName = modelName;
    }

    public static ModelContract forModel(final String modelName) {
        return new ModelContract(modelName);
    }

    public static ModelContract forModelWithContract(final String modelName, final List<FieldContract> fieldContracts) {
        return new ModelContract(modelName, fieldContracts);
    }

    public String getModelName() {
        return modelName;
    }

    public List<FieldContract> getFieldContracts() {
        return fieldContracts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ModelContract that = (ModelContract) o;
        return Objects.equals(modelName, that.modelName) &&
                Objects.equals(fieldContracts, that.fieldContracts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(modelName, fieldContracts);
    }
}
