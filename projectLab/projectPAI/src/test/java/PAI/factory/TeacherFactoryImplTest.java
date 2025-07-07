package PAI.factory;

import PAI.VOs.*;
import PAI.VOs.Location;
import PAI.domain.*;
import PAI.persistence.springdata.TeacherRepositorySpringDataImpl;
import PAI.repository.ITeacherRepository;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TeacherFactoryImplTest {

    // Arrange
    private ITeacherRepository _teacherRepository;
    private TeacherAcronym _teacherAcronymDouble;
    private Name _nameDouble;
    private Email _emailDouble;
    private NIF _nifDouble;
    private PhoneNumber _phoneNumberDouble;
    private AcademicBackground _academicBackgroundDouble;
    private DepartmentID _departmentIDDouble;
    private Street _streetDouble;
    private PostalCode _postalCodeDouble;
    private PAI.VOs.Location _locationDouble;
    private Country _countryDouble;

    private void createTeacherAndArgumentsDouble (){
        _teacherRepository = mock(TeacherRepositorySpringDataImpl.class);
        _teacherAcronymDouble = mock(TeacherAcronym.class);
        _nameDouble = mock(Name.class);
        _emailDouble = mock(Email.class);
        _nifDouble = mock(NIF.class);
        _phoneNumberDouble = mock(PhoneNumber.class);
        _academicBackgroundDouble = mock(AcademicBackground.class);
        _departmentIDDouble = mock(DepartmentID.class);
        _streetDouble = mock(Street.class);
        _postalCodeDouble = mock(PostalCode.class);
        _locationDouble = mock(Location.class);
        _countryDouble = mock(Country.class);
    }

    @Test
    void testTeacherFactoryConstructor () {
        // Arrange
        createTeacherAndArgumentsDouble();

        // Act
        ITeacherFactory teacherFactory = new TeacherFactoryImpl(_teacherRepository);

        // Assert
        assertNotNull(teacherFactory);
    }

    @Test
    void nullRepositoryDoesNotCreateTeacherFactory () {
        // Arrange + Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new TeacherFactoryImpl(null));

        // Assert
        assertEquals("Teacher Repository cannot be null.", exception.getMessage());
    }

    @Test
    void shouldCreateTeacherAndAddressVOUsingFactory() {
        // Arrange
        createTeacherAndArgumentsDouble();
        ITeacherFactory teacherFactory = new TeacherFactoryImpl(_teacherRepository);

        when(_teacherRepository.existsByIDorNIF(new TeacherID(_teacherAcronymDouble), _nifDouble)).thenReturn(false);

        try (MockedConstruction<Address> addressConstruction = mockConstruction(Address.class);
             MockedConstruction<Teacher> teacherConstruction = mockConstruction(Teacher.class)) {

            // Act
            Teacher result = teacherFactory.createTeacher(
                    _teacherAcronymDouble, _nameDouble, _emailDouble, _nifDouble, _phoneNumberDouble,
                    _academicBackgroundDouble, _streetDouble, _postalCodeDouble, _locationDouble, _countryDouble,
                    _departmentIDDouble);

            // Assert
            List<Address> addressInstances = addressConstruction.constructed();
            assertEquals(1, addressInstances.size());

            List<Teacher> teacherInstances = teacherConstruction.constructed();
            assertEquals(1, teacherInstances.size());

            assertSame(teacherInstances.get(0), result);
        }
    }

    @Test
    void shouldThrowExceptionIfExistsByIDorNIFMethodReturnsTrue() {
        // Arrange
        createTeacherAndArgumentsDouble();
        ITeacherFactory teacherFactory = new TeacherFactoryImpl(_teacherRepository);

        when(_teacherRepository.existsByIDorNIF(new TeacherID(_teacherAcronymDouble), _nifDouble)).thenReturn(true);

        try (MockedConstruction<Address> addressConstruction = mockConstruction(Address.class);
             MockedConstruction<Teacher> teacherConstruction = mockConstruction(Teacher.class)) {
            try {
                // Act
                teacherFactory.createTeacher(
                        _teacherAcronymDouble, _nameDouble, _emailDouble, _nifDouble, _phoneNumberDouble,
                        _academicBackgroundDouble, _streetDouble, _postalCodeDouble, _locationDouble, _countryDouble,
                        _departmentIDDouble);
                fail("Expected exception not thrown");
            } catch (Exception exception) {
                assertEquals("Teacher already exists with the provided Acronym: " + _teacherAcronymDouble.toString() + " or NIF: " + _nifDouble.toString(), exception.getMessage())
                ;
            }
        }
    }
//    private static Stream<Arguments> nullParameterCases() {
//        return Stream.of(
//                Arguments.of("Acronym"),
//                Arguments.of("Name"),
//                Arguments.of("Email"),
//                Arguments.of("NIF"),
//                Arguments.of("PhoneNumber"),
//                Arguments.of("AcademicBackground"),
//                Arguments.of("Street"),
//                Arguments.of("PostalCode"),
//                Arguments.of("Location"),
//                Arguments.of("Country"),
//                Arguments.of("Department")
//        );
//    }
//    @ParameterizedTest(name = "shouldPropagateExceptionWhen{0}IsNull")
//    @MethodSource("nullParameterCases")
//    void shouldPropagateExceptionWhenAnyParameterIsNull(String nullField) {
//        // Arrange
//        createTeacherAndArgumentsDouble();
//        ITeacherFactory teacherFactory = new TeacherFactoryImpl(_teacherRepository);
//
//        when(_teacherRepository.existsByIDorNIF(new TeacherID(_teacherAcronymDouble), _nifDouble)).thenReturn(false);
//
//        // Make each field null
//        switch (nullField) {
//            case "Acronym" -> _teacherAcronymDouble = null;
//            case "Name" -> _nameDouble = null;
//            case "Email" -> _emailDouble = null;
//            case "NIF" -> _nifDouble = null;
//            case "PhoneNumber" -> _phoneNumberDouble = null;
//            case "AcademicBackground" -> _academicBackgroundDouble = null;
//            case "Street" -> _streetDouble = null;
//            case "PostalCode" -> _postalCodeDouble = null;
//            case "Location" -> _locationDouble = null;
//            case "Country" -> _countryDouble = null;
//            case "Department" -> _departmentIDDouble = null;
//        }
//
//        try (MockedConstruction<Address> addressInstanceDouble = mockConstruction(Address.class, (mock, context) -> {
//            if (nullField.equals("Street") || nullField.equals("PostalCode") ||
//                    nullField.equals("Location") || nullField.equals("Country")) {
//                throw new IllegalArgumentException("Field is null");
//            }
//        });
//             MockedConstruction<Teacher> teacherInstanceDouble = mockConstruction(Teacher.class, (mock, context) -> {
//                 if (nullField.equals("Acronym") || nullField.equals("Name") || nullField.equals("Email") ||
//                         nullField.equals("NIF") || nullField.equals("PhoneNumber") ||
//                         nullField.equals("AcademicBackground") || nullField.equals("Department")) {
//                     throw new IllegalArgumentException("Field is null");
//                 }
//             })) {
//
//            try{
//                // Act + Assert
//                teacherFactory.createTeacher(
//                        _teacherAcronymDouble, _nameDouble, _emailDouble, _nifDouble, _phoneNumberDouble,
//                        _academicBackgroundDouble, _streetDouble, _postalCodeDouble, _locationDouble,
//                        _countryDouble, _departmentIDDouble
//                );
//                fail("Expected exception not thrown");
//            } catch (Exception e) {
//                assertTrue(e.getCause().getMessage().contains("Field is null"));
//            }
//        }
//    }

    @Test
    void shouldNotReturnNullAddressVO() {
        // Arrange
        createTeacherAndArgumentsDouble();
        ITeacherFactory factory = new TeacherFactoryImpl(_teacherRepository);

        when(_teacherRepository.existsByIDorNIF(new TeacherID(_teacherAcronymDouble), _nifDouble)).thenReturn(false);

        // Act
        Teacher teacher = factory.createTeacher(
                _teacherAcronymDouble, _nameDouble, _emailDouble, _nifDouble, _phoneNumberDouble,
                _academicBackgroundDouble, _streetDouble, _postalCodeDouble, _locationDouble,
                _countryDouble, _departmentIDDouble
        );

        // Assert
        assertNotNull(teacher);
    }
}