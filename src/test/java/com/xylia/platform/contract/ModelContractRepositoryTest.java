package com.xylia.platform.contract;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.InputStream;
import java.util.List;

import static com.xylia.platform.contract.ModelContract.forModelWithContract;
import static com.xylia.platform.contract.ModelContractRepository.createWithModelContract;

public class ModelContractRepositoryTest {

    private ObjectMapper MAPPER = new ObjectMapper();
    private ModelContract modelContract;
    private ModelContractRepository modelContractRepository;

    private final String MODEL_NAME = "TestModel";

    @Before
    public void setUp() throws Exception {

        InputStream contractStream = this.getClass().getClassLoader().getResourceAsStream("testContract.json");
        List<FieldContract> fieldContracts = MAPPER.readValue(contractStream, new TypeReference<List<FieldContract>>() {
        });
        modelContract = forModelWithContract(MODEL_NAME, fieldContracts);
        modelContractRepository = createWithModelContract(modelContract);
    }

    @Test
    public void getContractForModelAndValidateIfNotEmpty() {
        assertThat(modelContractRepository.getContractForModel(MODEL_NAME)).isNotNull();
    }

    @Test
    public void testFieldContractPopulation() {
        assertThat(modelContract.getFieldContracts().size()).isEqualTo(3);
    }
}