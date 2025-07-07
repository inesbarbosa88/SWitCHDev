package PAI.mapper.TeacherCareerProgression;

import PAI.VOs.TeacherCareerProgressionID;
import PAI.persistence.datamodel.TeacherCareerProgressionIDDataModel;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TeacherCareerProgressionIDMapperImplTest {

    @Test
    void shouldReturnTeacherCareerProgressionIDDataModelWhenDomainToDataModelIsCalled(){
        // Arrange
        TeacherCareerProgressionIDMapperImpl mapper = new TeacherCareerProgressionIDMapperImpl();
        TeacherCareerProgressionID domainIDDouble = mock(TeacherCareerProgressionID.class);
        UUID idDouble = mock(UUID.class);
        when(domainIDDouble.getIDValue()).thenReturn(idDouble);

        try (MockedConstruction<TeacherCareerProgressionIDDataModel> constructorDouble = mockConstruction(
                TeacherCareerProgressionIDDataModel.class, (mock, context) -> {

                when(mock.getIDValue()).thenReturn(idDouble);
            })) {

            // Act
            TeacherCareerProgressionIDDataModel result = mapper.domainToDataModel(domainIDDouble);

            // Assert
            assertEquals(1, constructorDouble.constructed().size());
            assertSame(constructorDouble.constructed().get(0), result);
            assertEquals(idDouble, result.getIDValue());
        }
    }

    @Test
    void shouldThrowNullPointerExceptionWhenConstructorFailsInDomainToDataModel() {
        // Arrange
        TeacherCareerProgressionIDMapperImpl mapper = new TeacherCareerProgressionIDMapperImpl();
        TeacherCareerProgressionID domainIDDouble = mock(TeacherCareerProgressionID.class);
        when(domainIDDouble.getIDValue()).thenReturn(null);

        // Act & Assert
        assertThrows(NullPointerException.class, () -> mapper.domainToDataModel(domainIDDouble));
    }

    @Test
    void shouldReturnTeacherCareerProgressionIDWhenDataModelToDomainIsCalled() {
        // Arrange
        TeacherCareerProgressionIDMapperImpl mapper = new TeacherCareerProgressionIDMapperImpl();
        TeacherCareerProgressionIDDataModel dataModelIdDouble = mock(TeacherCareerProgressionIDDataModel.class);
        UUID idDouble = mock(UUID.class);

        when(dataModelIdDouble.getIDValue()).thenReturn(idDouble);

        try (MockedConstruction<TeacherCareerProgressionID> constructorDouble = mockConstruction(
                TeacherCareerProgressionID.class, (mock, context) -> {

                    when(mock.getIDValue()).thenReturn(idDouble);
                })) {

            // Act
            TeacherCareerProgressionID result = mapper.dataModelToDomain(dataModelIdDouble);

            // Assert
            assertEquals(1, constructorDouble.constructed().size());
            assertSame(constructorDouble.constructed().get(0), result);
            assertEquals(idDouble, result.getIDValue());
        }
    }

    @Test
    void shouldThrowNullPointerExceptionWhenConstructorFailsInDataModelToDomain() {
        // Arrange
        TeacherCareerProgressionIDMapperImpl mapper = new TeacherCareerProgressionIDMapperImpl();
        TeacherCareerProgressionIDDataModel dataModelIdDouble = mock(TeacherCareerProgressionIDDataModel.class);
        when(dataModelIdDouble.getIDValue()).thenReturn(null);

        try (MockedConstruction<TeacherCareerProgressionID> constructorDouble = mockConstruction(
                TeacherCareerProgressionID.class, invocation -> {

                    throw new NullPointerException("Id can not be null!");
                })) {

            // Act & Assert
            assertThrows(NullPointerException.class, () -> mapper.dataModelToDomain(dataModelIdDouble));
        }
    }
}