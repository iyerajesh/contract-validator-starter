package com.xylia.platform.contract;

import java.util.*;
import java.util.stream.Collectors;

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

    public final boolean isContractValid(Map<String, Object> inputData) {
        return isFieldNameContractValid(inputData)
                && isFieldContractValid(inputData);
    }

    @Override
    public int hashCode() {
        return Objects.hash(modelName, fieldContracts);
    }

    private boolean isFieldNameContractValid(final Map<String, Object> inputData) {

        Set<String> fieldNamesFromContract = fieldContracts.stream()
                .map(fieldContract -> fieldContract.getFieldName()).collect(Collectors.toSet());

        return fieldNamesFromContract.containsAll(inputData.keySet())
                && fieldNamesFromContract.size() == inputData.size();
    }

    private boolean isFieldContractValid(Map<String, Object> inputData) {
        for (FieldContract fieldContract : fieldContracts) {
            if (!fieldContract.validateDataType(inputData))
                return false;
        }
        return true;
    }

}
