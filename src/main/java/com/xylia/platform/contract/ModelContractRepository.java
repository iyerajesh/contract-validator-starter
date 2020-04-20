package com.xylia.platform.contract;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

@Slf4j
public class ModelContractRepository {

    private static final List<ModelContract> modelContracts = new ArrayList<>();

    public static final ModelContractRepository create() {
        return new ModelContractRepository();
    }

    public static final ModelContractRepository createWithModelContract(ModelContract modelContract) {
        ModelContractRepository modelContractRepository = new ModelContractRepository();
        modelContractRepository.addToRepository(modelContract);

        return modelContractRepository;
    }

    public void addToRepository(ModelContract modelContract) {
        checkNotNull(modelContract);
        modelContracts.add(modelContract);
    }

    public ModelContract getContractForModel(String modelName) {
        return modelContracts
                .stream()
                .filter(modelContract -> modelContract.getModelName().equalsIgnoreCase(modelName))
                .findFirst()
                .get();
    }

    public boolean exists(ModelContract modelContract) {
        return modelContracts.contains(modelContract);
    }

}
