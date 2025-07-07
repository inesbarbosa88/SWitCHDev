package PAI.controller;

import PAI.TestConfig;
import PAI.VOs.NameWithNumbersAndSpecialChars;
import PAI.domain.accessMethod.AccessMethod;
import PAI.domain.accessMethod.AccessMethodFactoryImpl;
import PAI.domain.accessMethod.IAccessMethodFactory;
import PAI.mapper.accessMethod.AccessMethodIDMapperImpl;
import PAI.mapper.accessMethod.AccessMethodMapper;
import PAI.mapper.accessMethod.IAccessMethodMapper;
import PAI.persistence.springdata.accessMethod.AccessMethodRepositorySpringDataImpl;
import PAI.persistence.springdata.accessMethod.IAccessMethodRepositorySpringData;
import PAI.service.accessMethod.AccessMethodServiceImpl;
import PAI.service.accessMethod.IAccessMethodService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Import(TestConfig.class)
class US02ConfigureAccessMethodControllerTest {

    @Autowired
    private IAccessMethodRepositorySpringData repository;

    @Autowired
    private IAccessMethodService service;

    @Autowired
    private IAccessMethodMapper mapper;

    private US02_ConfigureAccessMethodController controller;

    @BeforeEach
    void setUp() {
        controller = new US02_ConfigureAccessMethodController(service);
    }

    @Test
    void shouldSaveAccessMethodInDatabase() {
        // Arrange
        String methodName = "TestMethod1";

        // Act
        boolean result = controller.configureAccessMethod(methodName);

        // Debug logging
        System.out.println("Save result: " + result);
        System.out.println("All records in database:");
        repository.findAll().forEach(am -> {
            System.out.println("Access Method: " + am);
            System.out.println("Name: " + am.getName());
        });

        // Assert
        assertTrue(result);
        assertTrue(repository.findAccessMethodDataModelByName(methodName).isPresent());
    }

    @Test
    void shouldGetBothAccessMethodsInDatabase() {
        // Arrange
        String methodName1 = "TestMethod1";
        String methodName2 = "TestMethod2";

        // Act
        boolean result = controller.configureAccessMethod(methodName1);
        boolean result2 = controller.configureAccessMethod(methodName2);

        // Debug logging
        System.out.println("Save result: " + result);
        System.out.println("All records in database:");
        repository.findAll().forEach(am -> {
            System.out.println("Access Method: " + am);
            System.out.println("Name: " + am.getName());
        });

        // Assert
        assertTrue(result);
        assertTrue(result2);
        assertTrue(repository.findAccessMethodDataModelByName(methodName1).isPresent());
        assertTrue(repository.findAccessMethodDataModelByName(methodName2).isPresent());
    }
}