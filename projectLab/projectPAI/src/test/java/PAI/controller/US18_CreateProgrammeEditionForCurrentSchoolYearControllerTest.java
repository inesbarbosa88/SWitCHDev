package PAI.controller;

import PAI.VOs.*;
import PAI.domain.SchoolYear;
import PAI.domain.programme.Programme;
import PAI.domain.programmeEdition.IProgrammeEditionFactory;
import PAI.domain.programmeEdition.ProgrammeEditionFactoryImpl;
import PAI.factory.*;
import PAI.persistence.mem.programmeEdition.ProgrammeRepositoryImpl;
import PAI.persistence.mem.SchoolYearRepositoryImpl;
import PAI.repository.ISchoolYearRepository;
import PAI.repository.programmeEditionRepository.IProgrammeEditionListFactory;
import PAI.repository.programmeEditionRepository.IProgrammeEditionRepository;
import PAI.repository.programmeEditionRepository.ProgrammeEditionListFactoryImpl;
import PAI.repository.programmeEditionRepository.ProgrammeEditionRepositoryImpl;
import PAI.repository.programmeRepository.IProgrammeRepository;
import PAI.persistence.mem.programmeEdition.IProgrammeRepositoryListFactory;
import PAI.persistence.mem.programmeEdition.ProgrammeRepositoryListFactoryImpl;
import PAI.service.programme.IProgrammeService;
import PAI.service.programme.ProgrammeServiceImpl;
import PAI.service.programmeEdition.IProgrammeEditionService;
import PAI.service.programmeEdition.ProgrammeEditionService;
import PAI.service.schoolYear.ISchoolYearService;
import PAI.service.schoolYear.SchoolYearServiceImpl;
import org.junit.jupiter.api.Test;

import javax.swing.text.html.Option;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class US18_CreateProgrammeEditionForCurrentSchoolYearControllerTest {

    //Constructor Tests
    @Test
    void shouldCreateController() throws Exception {
        // Arrange
        IProgrammeEditionListFactory programmeEditionDDDListFactory = new ProgrammeEditionListFactoryImpl();
        IProgrammeEditionFactory programmeEditionFactory = new ProgrammeEditionFactoryImpl();
        IProgrammeEditionRepository programmeEditionRepository = new ProgrammeEditionRepositoryImpl(programmeEditionDDDListFactory);
        IProgrammeEditionService programmeEditionService = new ProgrammeEditionService(programmeEditionFactory, programmeEditionRepository);


        IProgrammeRepositoryListFactory programmeRepositoryListFactory = new ProgrammeRepositoryListFactoryImpl();
        IProgrammeFactory programmeFactory = new ProgrammeFactoryImpl();
        IProgrammeRepository programmeRepository = new ProgrammeRepositoryImpl(programmeRepositoryListFactory);
        IProgrammeService programmeService = new ProgrammeServiceImpl(programmeFactory, programmeRepository);

        ISchoolYearListFactory schoolYearRepositoryListFactory = new SchoolYearListFactoryImpl();
        ISchoolYearFactory schoolYearFactory = new SchoolYearFactoryImpl();
        ISchoolYearRepository schoolYearRepository = new SchoolYearRepositoryImpl(schoolYearFactory, schoolYearRepositoryListFactory);

        ISchoolYearService schoolYearService = new SchoolYearServiceImpl(schoolYearRepository, schoolYearFactory);

        // Act
        US18_CreateProgrammeEditionForCurrentSchoolYearController controller = new US18_CreateProgrammeEditionForCurrentSchoolYearController(programmeEditionService, programmeService, schoolYearService, programmeEditionFactory, schoolYearRepository);

        // Assert
        assertNotNull(controller);
    }

    @Test
    void shouldThrowExceptionWhenProgrammeEditionServiceIsNull() throws Exception {
        // Arrange
        IProgrammeEditionFactory programmeEditionFactory = new ProgrammeEditionFactoryImpl();

        IProgrammeRepositoryListFactory programmeRepositoryListFactory = new ProgrammeRepositoryListFactoryImpl();
        IProgrammeFactory programmeFactory = new ProgrammeFactoryImpl();
        IProgrammeRepository programmeRepository = new ProgrammeRepositoryImpl(programmeRepositoryListFactory);
        IProgrammeService programmeService = new ProgrammeServiceImpl(programmeFactory, programmeRepository);

        ISchoolYearListFactory schoolYearRepositoryListFactory = new SchoolYearListFactoryImpl();
        ISchoolYearFactory schoolYearFactory = new SchoolYearFactoryImpl();
        ISchoolYearRepository schoolYearRepository = new SchoolYearRepositoryImpl(schoolYearFactory, schoolYearRepositoryListFactory);

        ISchoolYearService schoolYearService = new SchoolYearServiceImpl(schoolYearRepository, schoolYearFactory);

        // Act
        Exception exception = assertThrows(Exception.class, () -> {new US18_CreateProgrammeEditionForCurrentSchoolYearController(null, programmeService, schoolYearService,programmeEditionFactory, schoolYearRepository);});

        // Assert
        assertEquals("Programme Edition Service cannot be null", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenProgrammeServiceIsNull() throws Exception {
        // Arrange
        IProgrammeEditionListFactory programmeEditionDDDListFactory = new ProgrammeEditionListFactoryImpl();
        IProgrammeEditionFactory programmeEditionFactory = new ProgrammeEditionFactoryImpl();
        IProgrammeEditionRepository programmeEditionRepository = new ProgrammeEditionRepositoryImpl(programmeEditionDDDListFactory);
        IProgrammeEditionService programmeEditionService = new ProgrammeEditionService(programmeEditionFactory, programmeEditionRepository);


        IProgrammeRepositoryListFactory programmeRepositoryListFactory = new ProgrammeRepositoryListFactoryImpl();
        IProgrammeFactory programmeFactory = new ProgrammeFactoryImpl();
        IProgrammeRepository programmeRepository = new ProgrammeRepositoryImpl(programmeRepositoryListFactory);
        IProgrammeService programmeService = new ProgrammeServiceImpl(programmeFactory, programmeRepository);

        ISchoolYearListFactory schoolYearRepositoryListFactory = new SchoolYearListFactoryImpl();
        ISchoolYearFactory schoolYearFactory = new SchoolYearFactoryImpl();
        ISchoolYearRepository schoolYearRepository = new SchoolYearRepositoryImpl(schoolYearFactory, schoolYearRepositoryListFactory);

        ISchoolYearService schoolYearService = new SchoolYearServiceImpl(schoolYearRepository, schoolYearFactory);

        // Act
        Exception exception = assertThrows(Exception.class, () -> {new US18_CreateProgrammeEditionForCurrentSchoolYearController(programmeEditionService, null, schoolYearService, programmeEditionFactory, schoolYearRepository);});

        // Assert
        assertEquals("Programme Service cannot be null", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenSchoolYearServiceIsNull() throws Exception {
        // Arrange
        IProgrammeEditionListFactory programmeEditionDDDListFactory = new ProgrammeEditionListFactoryImpl();
        IProgrammeEditionFactory programmeEditionFactory = new ProgrammeEditionFactoryImpl();
        IProgrammeEditionRepository programmeEditionRepository = new ProgrammeEditionRepositoryImpl(programmeEditionDDDListFactory);
        IProgrammeEditionService programmeEditionService = new ProgrammeEditionService(programmeEditionFactory, programmeEditionRepository);


        IProgrammeRepositoryListFactory programmeRepositoryListFactory = new ProgrammeRepositoryListFactoryImpl();
        IProgrammeFactory programmeFactory = new ProgrammeFactoryImpl();
        IProgrammeRepository programmeRepository = new ProgrammeRepositoryImpl(programmeRepositoryListFactory);
        IProgrammeService programmeService = new ProgrammeServiceImpl(programmeFactory, programmeRepository);

        ISchoolYearListFactory schoolYearRepositoryListFactory = new SchoolYearListFactoryImpl();
        ISchoolYearFactory schoolYearFactory = new SchoolYearFactoryImpl();
        ISchoolYearRepository schoolYearRepository = new SchoolYearRepositoryImpl(schoolYearFactory, schoolYearRepositoryListFactory);

        ISchoolYearService schoolYearService = new SchoolYearServiceImpl(schoolYearRepository, schoolYearFactory);

        // Act
        Exception exception = assertThrows(Exception.class, () -> {new US18_CreateProgrammeEditionForCurrentSchoolYearController(programmeEditionService, programmeService, null, programmeEditionFactory, schoolYearRepository);});

        // Assert
        assertEquals("School Year Service cannot be null", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenProgrammeEditionFactoryIsNull() throws Exception {
        // Arrange
        IProgrammeEditionListFactory programmeEditionDDDListFactory = new ProgrammeEditionListFactoryImpl();
        IProgrammeEditionFactory programmeEditionFactory = new ProgrammeEditionFactoryImpl();
        IProgrammeEditionRepository programmeEditionRepository = new ProgrammeEditionRepositoryImpl(programmeEditionDDDListFactory);
        IProgrammeEditionService programmeEditionService = new ProgrammeEditionService(programmeEditionFactory, programmeEditionRepository);


        IProgrammeRepositoryListFactory programmeRepositoryListFactory = new ProgrammeRepositoryListFactoryImpl();
        IProgrammeFactory programmeFactory = new ProgrammeFactoryImpl();
        IProgrammeRepository programmeRepository = new ProgrammeRepositoryImpl(programmeRepositoryListFactory);
        IProgrammeService programmeService = new ProgrammeServiceImpl(programmeFactory, programmeRepository);

        ISchoolYearListFactory schoolYearRepositoryListFactory = new SchoolYearListFactoryImpl();
        ISchoolYearFactory schoolYearFactory = new SchoolYearFactoryImpl();
        ISchoolYearRepository schoolYearRepository = new SchoolYearRepositoryImpl(schoolYearFactory, schoolYearRepositoryListFactory);

        ISchoolYearService schoolYearService = new SchoolYearServiceImpl(schoolYearRepository, schoolYearFactory);

        // Act
        Exception exception = assertThrows(Exception.class, () -> {new US18_CreateProgrammeEditionForCurrentSchoolYearController(programmeEditionService, programmeService, schoolYearService,null, schoolYearRepository);});

        // Assert
        assertEquals("Programme Edition Repository cannot be null", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenSchoolYearRepositoryIsNull() throws Exception {
        // Arrange
        IProgrammeEditionListFactory programmeEditionDDDListFactory = new ProgrammeEditionListFactoryImpl();
        IProgrammeEditionFactory programmeEditionFactory = new ProgrammeEditionFactoryImpl();
        IProgrammeEditionRepository programmeEditionRepository = new ProgrammeEditionRepositoryImpl(programmeEditionDDDListFactory);
        IProgrammeEditionService programmeEditionService = new ProgrammeEditionService(programmeEditionFactory, programmeEditionRepository);


        IProgrammeRepositoryListFactory programmeRepositoryListFactory = new ProgrammeRepositoryListFactoryImpl();
        IProgrammeFactory programmeFactory = new ProgrammeFactoryImpl();
        IProgrammeRepository programmeRepository = new ProgrammeRepositoryImpl(programmeRepositoryListFactory);
        IProgrammeService programmeService = new ProgrammeServiceImpl(programmeFactory, programmeRepository);

        ISchoolYearListFactory schoolYearRepositoryListFactory = new SchoolYearListFactoryImpl();
        ISchoolYearFactory schoolYearFactory = new SchoolYearFactoryImpl();
        ISchoolYearRepository schoolYearRepository = new SchoolYearRepositoryImpl(schoolYearFactory, schoolYearRepositoryListFactory);

        ISchoolYearService schoolYearService = new SchoolYearServiceImpl(schoolYearRepository, schoolYearFactory);

        // Act
        Exception exception = assertThrows(Exception.class, () -> {new US18_CreateProgrammeEditionForCurrentSchoolYearController(programmeEditionService, programmeService, schoolYearService,programmeEditionFactory, null);});

        // Assert
        assertEquals("School Year Repository cannot be null", exception.getMessage());
    }



    @Test
    void shouldReturnListOfNamesOfAllExistingProgrammes() throws Exception {
        // Arrange
        IProgrammeEditionListFactory programmeEditionDDDListFactory = new ProgrammeEditionListFactoryImpl();
        IProgrammeEditionFactory programmeEditionFactory = new ProgrammeEditionFactoryImpl();
        IProgrammeEditionRepository programmeEditionRepository = new ProgrammeEditionRepositoryImpl(programmeEditionDDDListFactory);
        IProgrammeEditionService programmeEditionService = new ProgrammeEditionService(programmeEditionFactory, programmeEditionRepository);


        IProgrammeRepositoryListFactory programmeRepositoryListFactory = new ProgrammeRepositoryListFactoryImpl();
        IProgrammeFactory programmeFactory = new ProgrammeFactoryImpl();
        IProgrammeRepository programmeRepository = new ProgrammeRepositoryImpl(programmeRepositoryListFactory);
        IProgrammeService programmeService = new ProgrammeServiceImpl(programmeFactory, programmeRepository);

        ISchoolYearListFactory schoolYearRepositoryListFactory = new SchoolYearListFactoryImpl();
        ISchoolYearFactory schoolYearFactory = new SchoolYearFactoryImpl();
        ISchoolYearRepository schoolYearRepository = new SchoolYearRepositoryImpl(schoolYearFactory, schoolYearRepositoryListFactory);

        ISchoolYearService schoolYearService = new SchoolYearServiceImpl(schoolYearRepository, schoolYearFactory);

        US18_CreateProgrammeEditionForCurrentSchoolYearController controller = new US18_CreateProgrammeEditionForCurrentSchoolYearController(programmeEditionService, programmeService, schoolYearService, programmeEditionFactory, schoolYearRepository);

        NameWithNumbersAndSpecialChars programmeName1 = new NameWithNumbersAndSpecialChars("Licenciatura em Engenharia Informatica");
        Acronym programmeAcronym1 = new Acronym("LEI");
        NameWithNumbersAndSpecialChars programmeName2 = new NameWithNumbersAndSpecialChars("Licenciatura em Engenharia Espacial");
        Acronym programmeAcronym2 = new Acronym("LEE");
        NameWithNumbersAndSpecialChars programmeName3 = new NameWithNumbersAndSpecialChars("Licenciatura em Engenharia Agricula");
        Acronym programmeAcronym3 = new Acronym("LEA");
        QuantEcts quantEcts = new QuantEcts(30);
        QuantSemesters quantSemesters = new QuantSemesters(4);
        DegreeTypeID degreeTypeID = new DegreeTypeID("Licenciatura");
        DepartmentAcronym departmentAcronym = new DepartmentAcronym("DEI");
        DepartmentID departmentID = new DepartmentID(departmentAcronym);
        TeacherAcronym teacherAcronym = new TeacherAcronym("JFC");
        TeacherID teacherID = new TeacherID(teacherAcronym);

        programmeService.registerProgramme(programmeName1, programmeAcronym1, quantEcts, quantSemesters, degreeTypeID, departmentID, teacherID);
        programmeService.registerProgramme(programmeName2, programmeAcronym2, quantEcts, quantSemesters, degreeTypeID, departmentID, teacherID);
        programmeService.registerProgramme(programmeName3, programmeAcronym3, quantEcts, quantSemesters, degreeTypeID, departmentID, teacherID);

        // Act
        Iterable<Programme> listToTest = controller.getAllProgrammes();

        // Assert
        assertNotNull(listToTest);
        long size = StreamSupport.stream(listToTest.spliterator(), false).count();
        assertEquals(3, size);
    }

    @Test
    void shouldReturnEmptyListOfNamesIfProgrammeRepositoryIsEmpty() throws Exception {
        // Arrange
        IProgrammeEditionListFactory programmeEditionDDDListFactory = new ProgrammeEditionListFactoryImpl();
        IProgrammeEditionFactory programmeEditionFactory = new ProgrammeEditionFactoryImpl();
        IProgrammeEditionRepository programmeEditionRepository = new ProgrammeEditionRepositoryImpl(programmeEditionDDDListFactory);
        IProgrammeEditionService programmeEditionService = new ProgrammeEditionService(programmeEditionFactory, programmeEditionRepository);


        IProgrammeRepositoryListFactory programmeRepositoryListFactory = new ProgrammeRepositoryListFactoryImpl();
        IProgrammeFactory programmeFactory = new ProgrammeFactoryImpl();
        IProgrammeRepository programmeRepository = new ProgrammeRepositoryImpl(programmeRepositoryListFactory);
        IProgrammeService programmeService = new ProgrammeServiceImpl(programmeFactory, programmeRepository);

        ISchoolYearListFactory schoolYearRepositoryListFactory = new SchoolYearListFactoryImpl();
        ISchoolYearFactory schoolYearFactory = new SchoolYearFactoryImpl();
        ISchoolYearRepository schoolYearRepository = new SchoolYearRepositoryImpl(schoolYearFactory, schoolYearRepositoryListFactory);

        ISchoolYearService schoolYearService = new SchoolYearServiceImpl(schoolYearRepository, schoolYearFactory);

        US18_CreateProgrammeEditionForCurrentSchoolYearController controller = new US18_CreateProgrammeEditionForCurrentSchoolYearController(programmeEditionService, programmeService, schoolYearService, programmeEditionFactory, schoolYearRepository);

        // Act
        Iterable<Programme> listToTest = controller.getAllProgrammes();

        // Assert
        assertNotNull(listToTest);
        long size = StreamSupport.stream(listToTest.spliterator(), false).count();
        assertEquals(0, size);
    }

    @Test
    void shouldReturnCurrentSchoolYearID() throws Exception {
        // Arrange
        IProgrammeEditionListFactory programmeEditionDDDListFactory = new ProgrammeEditionListFactoryImpl();
        IProgrammeEditionFactory programmeEditionFactory = new ProgrammeEditionFactoryImpl();
        IProgrammeEditionRepository programmeEditionRepository = new ProgrammeEditionRepositoryImpl(programmeEditionDDDListFactory);
        IProgrammeEditionService programmeEditionService = new ProgrammeEditionService(programmeEditionFactory, programmeEditionRepository);

        IProgrammeRepositoryListFactory programmeRepositoryListFactory = new ProgrammeRepositoryListFactoryImpl();
        IProgrammeFactory programmeFactory = new ProgrammeFactoryImpl();
        IProgrammeRepository programmeRepository = new ProgrammeRepositoryImpl(programmeRepositoryListFactory);
        IProgrammeService programmeService = new ProgrammeServiceImpl(programmeFactory, programmeRepository);

        ISchoolYearListFactory schoolYearRepositoryListFactory = new SchoolYearListFactoryImpl();
        ISchoolYearFactory schoolYearFactory = new SchoolYearFactoryImpl();
        ISchoolYearRepository schoolYearRepository = new SchoolYearRepositoryImpl(schoolYearFactory, schoolYearRepositoryListFactory);
        ISchoolYearService schoolYearService = new SchoolYearServiceImpl(schoolYearRepository, schoolYearFactory);


        Description description = new Description("2024/2025");
        Date startDate = new Date("01-09-2024");
        Date endDate = new Date("31-08-2025");
        schoolYearRepository.addSchoolYear(description, startDate, endDate);
        Iterable<SchoolYear> schoolYears = schoolYearRepository.findAll();
        Iterator<SchoolYear> schoolYearIterator = schoolYears.iterator();
        SchoolYear schoolYear = schoolYearIterator.next();
        SchoolYearID expectedID = schoolYear.identity();

        US18_CreateProgrammeEditionForCurrentSchoolYearController controller = new US18_CreateProgrammeEditionForCurrentSchoolYearController(programmeEditionService, programmeService, schoolYearService, programmeEditionFactory, schoolYearRepository);

        // Act
        SchoolYearID result = controller.getCurrentSchoolYear();

        // Assert
        assertNotNull(result);
        assertEquals(expectedID, result);
    }

    @Test
    void shouldReturnNullIfSchoolYearIDIsEmpty() throws Exception {
        // Arrange
        IProgrammeEditionListFactory programmeEditionDDDListFactory = new ProgrammeEditionListFactoryImpl();
        IProgrammeEditionFactory programmeEditionFactory = new ProgrammeEditionFactoryImpl();
        IProgrammeEditionRepository programmeEditionRepository = new ProgrammeEditionRepositoryImpl(programmeEditionDDDListFactory);
        IProgrammeEditionService programmeEditionService = new ProgrammeEditionService(programmeEditionFactory, programmeEditionRepository);

        IProgrammeRepositoryListFactory programmeRepositoryListFactory = new ProgrammeRepositoryListFactoryImpl();
        IProgrammeFactory programmeFactory = new ProgrammeFactoryImpl();
        IProgrammeRepository programmeRepository = new ProgrammeRepositoryImpl(programmeRepositoryListFactory);
        IProgrammeService programmeService = new ProgrammeServiceImpl(programmeFactory, programmeRepository);

        ISchoolYearListFactory schoolYearRepositoryListFactory = new SchoolYearListFactoryImpl();
        ISchoolYearFactory schoolYearFactory = new SchoolYearFactoryImpl();
        ISchoolYearRepository schoolYearRepository = new SchoolYearRepositoryImpl(schoolYearFactory, schoolYearRepositoryListFactory);
        ISchoolYearService schoolYearService = new SchoolYearServiceImpl(schoolYearRepository, schoolYearFactory);

        US18_CreateProgrammeEditionForCurrentSchoolYearController controller = new US18_CreateProgrammeEditionForCurrentSchoolYearController(programmeEditionService, programmeService, schoolYearService, programmeEditionFactory, schoolYearRepository);

        // Act
        SchoolYearID result = controller.getCurrentSchoolYear();

        // Assert
        assertNull(result);
    }

    @Test
    void shouldThrowExceptionIfParameterProgrammeNull() throws Exception {
        // Arrange
        IProgrammeEditionListFactory programmeEditionDDDListFactory = new ProgrammeEditionListFactoryImpl();
        IProgrammeEditionFactory programmeEditionFactory = new ProgrammeEditionFactoryImpl();
        IProgrammeEditionRepository programmeEditionRepository = new ProgrammeEditionRepositoryImpl(programmeEditionDDDListFactory);
        IProgrammeEditionService programmeEditionService = new ProgrammeEditionService(programmeEditionFactory, programmeEditionRepository);


        IProgrammeRepositoryListFactory programmeRepositoryListFactory = new ProgrammeRepositoryListFactoryImpl();
        IProgrammeFactory programmeFactory = new ProgrammeFactoryImpl();
        IProgrammeRepository programmeRepository = new ProgrammeRepositoryImpl(programmeRepositoryListFactory);
        IProgrammeService programmeService = new ProgrammeServiceImpl(programmeFactory, programmeRepository);

        ISchoolYearListFactory schoolYearRepositoryListFactory = new SchoolYearListFactoryImpl();
        ISchoolYearFactory schoolYearFactory = new SchoolYearFactoryImpl();
        ISchoolYearRepository schoolYearRepository = new SchoolYearRepositoryImpl(schoolYearFactory, schoolYearRepositoryListFactory);

        ISchoolYearService schoolYearService = new SchoolYearServiceImpl(schoolYearRepository, schoolYearFactory);

        US18_CreateProgrammeEditionForCurrentSchoolYearController controller = new US18_CreateProgrammeEditionForCurrentSchoolYearController(programmeEditionService, programmeService, schoolYearService, programmeEditionFactory, schoolYearRepository);

        Description description1 = new Description("2023/2024");
        Date startDate1 = new Date("01-09-2023");
        Date endDate1 = new Date("31-08-2024");
        Description description3 = new Description("2025/2026");
        Date startDate3 = new Date("01-09-2025");
        Date endDate3 = new Date("31-08-2026");
        schoolYearRepository.addSchoolYear(description1, startDate1, endDate1);
        schoolYearRepository.addSchoolYear(description3, startDate3, endDate3);
        Iterable<SchoolYear> schoolYears = schoolYearRepository.findAll();
        Iterator<SchoolYear> schoolYearIterator = schoolYears.iterator();
        SchoolYear schoolYear = schoolYearIterator.next();
        SchoolYearID schoolYearID = schoolYear.identity();

        // Act + Assert
        assertThrows(Exception.class, () -> {controller.createAProgrammeEditionForTheCurrentSchoolYear(null,schoolYearID);});
    }

    @Test
    void shouldThrowExceptionIfParameterSchoolYearIDNull() throws Exception {
        // Arrange
        IProgrammeEditionListFactory programmeEditionDDDListFactory = new ProgrammeEditionListFactoryImpl();
        IProgrammeEditionFactory programmeEditionFactory = new ProgrammeEditionFactoryImpl();
        IProgrammeEditionRepository programmeEditionRepository = new ProgrammeEditionRepositoryImpl(programmeEditionDDDListFactory);
        IProgrammeEditionService programmeEditionService = new ProgrammeEditionService(programmeEditionFactory, programmeEditionRepository);


        IProgrammeRepositoryListFactory programmeRepositoryListFactory = new ProgrammeRepositoryListFactoryImpl();
        IProgrammeFactory programmeFactory = new ProgrammeFactoryImpl();
        IProgrammeRepository programmeRepository = new ProgrammeRepositoryImpl(programmeRepositoryListFactory);
        IProgrammeService programmeService = new ProgrammeServiceImpl(programmeFactory, programmeRepository);

        ISchoolYearListFactory schoolYearRepositoryListFactory = new SchoolYearListFactoryImpl();
        ISchoolYearFactory schoolYearFactory = new SchoolYearFactoryImpl();
        ISchoolYearRepository schoolYearRepository = new SchoolYearRepositoryImpl(schoolYearFactory, schoolYearRepositoryListFactory);

        ISchoolYearService schoolYearService = new SchoolYearServiceImpl(schoolYearRepository, schoolYearFactory);

        US18_CreateProgrammeEditionForCurrentSchoolYearController controller = new US18_CreateProgrammeEditionForCurrentSchoolYearController(programmeEditionService, programmeService, schoolYearService, programmeEditionFactory, schoolYearRepository);

        NameWithNumbersAndSpecialChars programmeName1 = new NameWithNumbersAndSpecialChars("Licenciatura em Engenharia Informatica");
        Acronym programmeAcronym1 = new Acronym("LEI");
        NameWithNumbersAndSpecialChars programmeName2 = new NameWithNumbersAndSpecialChars("Licenciatura em Engenharia Espacial");
        Acronym programmeAcronym2 = new Acronym("LEE");
        NameWithNumbersAndSpecialChars programmeName3 = new NameWithNumbersAndSpecialChars("Licenciatura em Engenharia Agricula");
        Acronym programmeAcronym3 = new Acronym("LEA");
        QuantEcts quantEcts = new QuantEcts(30);
        QuantSemesters quantSemesters = new QuantSemesters(4);
        DegreeTypeID degreeTypeID = new DegreeTypeID("Licenciatura");
        DepartmentAcronym departmentAcronym = new DepartmentAcronym("DEI");
        DepartmentID departmentID = new DepartmentID(departmentAcronym);
        TeacherAcronym teacherAcronym = new TeacherAcronym("JFC");
        TeacherID teacherID = new TeacherID(teacherAcronym);

        programmeService.registerProgramme(programmeName1, programmeAcronym1, quantEcts, quantSemesters, degreeTypeID, departmentID, teacherID);
        programmeService.registerProgramme(programmeName2, programmeAcronym2, quantEcts, quantSemesters, degreeTypeID, departmentID, teacherID);
        programmeService.registerProgramme(programmeName3, programmeAcronym3, quantEcts, quantSemesters, degreeTypeID, departmentID, teacherID);
        Iterable<Programme> programmes = programmeRepository.findAll();
        Iterator<Programme> iterator = programmes.iterator();
        Programme programme = iterator.next();

        Description description1 = new Description("2023/2024");
        Date startDate1 = new Date("01-09-2023");
        Date endDate1 = new Date("31-08-2024");
        Description description3 = new Description("2025/2026");
        Date startDate3 = new Date("01-09-2025");
        Date endDate3 = new Date("31-08-2026");
        schoolYearRepository.addSchoolYear(description1, startDate1, endDate1);
        schoolYearRepository.addSchoolYear(description3, startDate3, endDate3);
        Iterable<SchoolYear> schoolYears = schoolYearRepository.findAll();
        Iterator<SchoolYear> schoolYearIterator = schoolYears.iterator();
        SchoolYear schoolYear = schoolYearIterator.next();
        SchoolYearID schoolYearID = schoolYear.identity();
        SchoolYearID schoolYearID1 = mock(SchoolYearID.class);

        // Act + Assert
        assertThrows(Exception.class, () -> {controller.createAProgrammeEditionForTheCurrentSchoolYear(programme,null);});

    }

    @Test
    void shouldReturnTrueIfCreationIsSuccessful() throws Exception {
        // Arrange
        IProgrammeEditionListFactory programmeEditionDDDListFactory = new ProgrammeEditionListFactoryImpl();
        IProgrammeEditionFactory programmeEditionFactory = new ProgrammeEditionFactoryImpl();
        IProgrammeEditionRepository programmeEditionRepository = new ProgrammeEditionRepositoryImpl(programmeEditionDDDListFactory);
        IProgrammeEditionService programmeEditionService = new ProgrammeEditionService(programmeEditionFactory, programmeEditionRepository);


        IProgrammeRepositoryListFactory programmeRepositoryListFactory = new ProgrammeRepositoryListFactoryImpl();
        IProgrammeFactory programmeFactory = new ProgrammeFactoryImpl();
        IProgrammeRepository programmeRepository = new ProgrammeRepositoryImpl(programmeRepositoryListFactory);
        IProgrammeService programmeService = new ProgrammeServiceImpl(programmeFactory, programmeRepository);

        ISchoolYearListFactory schoolYearRepositoryListFactory = new SchoolYearListFactoryImpl();
        ISchoolYearFactory schoolYearFactory = new SchoolYearFactoryImpl();
        ISchoolYearRepository schoolYearRepository = new SchoolYearRepositoryImpl(schoolYearFactory, schoolYearRepositoryListFactory);

        ISchoolYearService schoolYearService = new SchoolYearServiceImpl(schoolYearRepository, schoolYearFactory);

        US18_CreateProgrammeEditionForCurrentSchoolYearController controller = new US18_CreateProgrammeEditionForCurrentSchoolYearController(programmeEditionService, programmeService, schoolYearService, programmeEditionFactory, schoolYearRepository);

        NameWithNumbersAndSpecialChars programmeName1 = new NameWithNumbersAndSpecialChars("Licenciatura em Engenharia Informatica");
        Acronym programmeAcronym1 = new Acronym("LEI");
        NameWithNumbersAndSpecialChars programmeName2 = new NameWithNumbersAndSpecialChars("Licenciatura em Engenharia Espacial");
        Acronym programmeAcronym2 = new Acronym("LEE");
        NameWithNumbersAndSpecialChars programmeName3 = new NameWithNumbersAndSpecialChars("Licenciatura em Engenharia Agricula");
        Acronym programmeAcronym3 = new Acronym("LEA");
        QuantEcts quantEcts = new QuantEcts(30);
        QuantSemesters quantSemesters = new QuantSemesters(4);
        DegreeTypeID degreeTypeID = new DegreeTypeID("Licenciatura");
        DepartmentAcronym departmentAcronym = new DepartmentAcronym("DEI");
        DepartmentID departmentID = new DepartmentID(departmentAcronym);
        TeacherAcronym teacherAcronym = new TeacherAcronym("JFC");
        TeacherID teacherID = new TeacherID(teacherAcronym);

        programmeService.registerProgramme(programmeName1, programmeAcronym1, quantEcts, quantSemesters, degreeTypeID, departmentID, teacherID);
        programmeService.registerProgramme(programmeName2, programmeAcronym2, quantEcts, quantSemesters, degreeTypeID, departmentID, teacherID);
        programmeService.registerProgramme(programmeName3, programmeAcronym3, quantEcts, quantSemesters, degreeTypeID, departmentID, teacherID);
        Iterable<Programme> programmes = programmeRepository.findAll();
        Iterator<Programme> iterator = programmes.iterator();
        Programme programme = iterator.next();

        Description description1 = new Description("2023/2024");
        Date startDate1 = new Date("01-09-2023");
        Date endDate1 = new Date("31-08-2024");
        Description description3 = new Description("2025/2026");
        Date startDate3 = new Date("01-09-2025");
        Date endDate3 = new Date("31-08-2026");
        schoolYearRepository.addSchoolYear(description1, startDate1, endDate1);
        schoolYearRepository.addSchoolYear(description3, startDate3, endDate3);
        Iterable<SchoolYear> schoolYears = schoolYearRepository.findAll();
        Iterator<SchoolYear> schoolYearIterator = schoolYears.iterator();
        SchoolYear schoolYear = schoolYearIterator.next();
        SchoolYearID schoolYearID = schoolYear.identity();
        SchoolYearID schoolYearID1 = mock(SchoolYearID.class);

        // Act
        boolean result = controller.createAProgrammeEditionForTheCurrentSchoolYear(programme, schoolYearID);

        // Assert
        assertTrue(result);
    }
}