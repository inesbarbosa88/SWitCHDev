//package PAI.mapper.courseInStudyPlan;
//
//import PAI.VOs.*;
//import PAI.domain.courseInStudyPlan.CourseInStudyPlan;
//import PAI.domain.courseInStudyPlan.ICourseInStudyPlanFactory;
//import PAI.domain.courseInStudyPlan.CourseInStudyPlanFactoryImpl;
//import PAI.mapper.courseID.ICourseIDMapper;
//import PAI.mapper.courseID.CourseIDMapperImpl;
//import PAI.mapper.programme.ProgrammeIDMapperImpl;
//import PAI.mapper.studyPlanID.IStudyPlanIDMapper;
//import PAI.mapper.studyPlanID.StudyPlanIDMapperImpl;
//import PAI.persistence.datamodel.courseInStudyPlan.CourseInStudyPlanDataModel;
//import PAI.persistence.datamodel.courseInStudyPlan.CourseInStudyPlanIDDataModel;
//import PAI.persistence.datamodel.course.CourseIDDataModel;
//import PAI.persistence.datamodel.studyPlan.StudyPlanIDDataModel;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;
//
//class CourseInStudyPlanMapperImplTest {
//
//    private ICourseInStudyPlanMapper mapper;
//    private ICourseInStudyPlanIDMapper courseInStudyPlanIDMapper;
//    private ICourseIDMapper courseIDMapper;
//    private IStudyPlanIDMapper studyPlanIDMapper;
//    private ICourseInStudyPlanFactory courseInStudyPlanFactory;
//
//    @BeforeEach
//    void setUp() throws Exception {
//        courseIDMapper = new CourseIDMapperImpl();
//        studyPlanIDMapper = new StudyPlanIDMapperImpl(new ProgrammeIDMapperImpl());
//        // Passar sempre o StudyPlanIDMapper e o CourseIDMapper ao construtor
//        courseInStudyPlanIDMapper = new CourseInStudyPlanIDMapperImpl(
//                studyPlanIDMapper,
//                courseIDMapper
//        );
//        courseInStudyPlanFactory = new CourseInStudyPlanFactoryImpl();
//        mapper = new CourseInStudyPlanMapperImpl(
//                courseIDMapper,
//                studyPlanIDMapper,
//                courseInStudyPlanIDMapper,
//                courseInStudyPlanFactory
//        );
//    }
//
//    @Test
//    void constructorShouldThrowWhenStudyPlanIDMapperIsNull() {
//        IllegalArgumentException ex = assertThrows(
//                IllegalArgumentException.class,
//                () -> new CourseInStudyPlanMapperImpl(
//                        new CourseIDMapperImpl(),
//                        null,
//                        new CourseInStudyPlanIDMapperImpl(new StudyPlanIDMapperImpl(new ProgrammeIDMapperImpl()), new CourseIDMapperImpl()),
//                        new CourseInStudyPlanFactoryImpl()
//                )
//        );
//        assertEquals("StudyPlanIDMapper cannot be null", ex.getMessage());
//    }
//
//    @Test
//    void constructorShouldThrowWhenCourseIDMapperIsNull() {
//        IllegalArgumentException ex = assertThrows(
//                IllegalArgumentException.class,
//                () -> new CourseInStudyPlanMapperImpl(
//                        null,
//                        new StudyPlanIDMapperImpl(new ProgrammeIDMapperImpl()),
//                        new CourseInStudyPlanIDMapperImpl(new StudyPlanIDMapperImpl(new ProgrammeIDMapperImpl()), new CourseIDMapperImpl()),
//                        new CourseInStudyPlanFactoryImpl()
//                )
//        );
//        assertEquals("CourseIDMapper cannot be null", ex.getMessage());
//    }
//
//    @Test
//    void constructorShouldThrowWhenCourseInStudyPlanIDMapperIsNull() {
//        IllegalArgumentException ex = assertThrows(
//                IllegalArgumentException.class,
//                () -> new CourseInStudyPlanMapperImpl(
//                        new CourseIDMapperImpl(),
//                        new StudyPlanIDMapperImpl(new ProgrammeIDMapperImpl()),
//                        null,
//                        new CourseInStudyPlanFactoryImpl()
//                )
//        );
//        assertEquals("CourseInStudyPlanIDMapper cannot be null", ex.getMessage());
//    }
//
//    @Test
//    void constructorShouldThrowWhenFactoryIsNull() {
//        IllegalArgumentException ex = assertThrows(
//                IllegalArgumentException.class,
//                () -> new CourseInStudyPlanMapperImpl(
//                        new CourseIDMapperImpl(),
//                        new StudyPlanIDMapperImpl(new ProgrammeIDMapperImpl()),
//                        new CourseInStudyPlanIDMapperImpl(new StudyPlanIDMapperImpl(new ProgrammeIDMapperImpl()), new CourseIDMapperImpl()),
//                        null
//                )
//        );
//        assertEquals("CourseInStudyPlanFactory cannot be null", ex.getMessage());
//    }
//
//    @Test
//    void toDataModelShouldMapDomainToDataModel() throws Exception {
//        // Arrange
//        ProgrammeID programmeID = new ProgrammeID(
//                new NameWithNumbersAndSpecialChars("ProgrammeName"),
//                new Acronym("PN")
//        );
//        Date date = new Date("12-03-2005");
//        CourseID courseIDValueObject = new CourseID(
//                new Acronym("ACR"),
//                new Name("NAME")
//        );
//        StudyPlanID studyPlanIDValueObject = new StudyPlanID(programmeID, date);
//        Semester semesterVO = new Semester(2);
//        CurricularYear yearVO = new CurricularYear(3);
//        DurationCourseInCurricularYear durationOfCourse = new DurationCourseInCurricularYear(1);
//        CourseQuantityCreditsEcts quantityOfCreditsEcts = new CourseQuantityCreditsEcts(1);
//
//
//        CourseInStudyPlan domain = courseInStudyPlanFactory
//                .newCourseInStudyPlan(semesterVO, yearVO, courseIDValueObject, studyPlanIDValueObject, durationOfCourse, quantityOfCreditsEcts);
//
//        // Act
//        CourseInStudyPlanDataModel dataModel = mapper.toDataModel(domain);
//
//        // Assert
//        assertNotNull(dataModel);
//
//        // ID composto
//        CourseInStudyPlanIDDataModel expectedIdDM =
//                courseInStudyPlanIDMapper.toDataModel(domain.identity());
//        assertEquals(expectedIdDM, dataModel.getCourseInStudyPlanIDDataModel());
//
//        // Semestre e ano curricular
//        assertEquals(semesterVO.toInt(), dataModel.getSemester());
//        assertEquals(yearVO.toInt(), dataModel.getCurricularYear());
//
//        // Mapeamento dos VOs internos
//        CourseIDDataModel expectedCourseDM = courseIDMapper.toDataModel(domain.getCourseID());
//        StudyPlanIDDataModel expectedStudyPlanDM = studyPlanIDMapper.toDataModel(domain.getStudyplanID());
//        assertEquals(expectedCourseDM,   dataModel.getCourseIDDataModel());
//        assertEquals(expectedStudyPlanDM, dataModel.getStudyPlanIDDataModel());
//    }
//
//    @Test
//    void toDomainShouldMapDataModelToDomain() throws Exception {
//        // Arrange: criar VOs e DataModels
//        ProgrammeID programmeID = new ProgrammeID(
//                new NameWithNumbersAndSpecialChars("ProgrammeName"),
//                new Acronym("PN")
//        );
//        Date date = new Date("12-03-2005");
//        CourseID courseIDValueObject = new CourseID(
//                new Acronym("ACR"),
//                new Name("NAME")
//        );
//        StudyPlanID studyPlanIDValueObject = new StudyPlanID(programmeID, date);
//        Semester semesterVO = new Semester(2);
//        CurricularYear yearVO = new CurricularYear(3);
//        DurationCourseInCurricularYear durationOfCourse = new DurationCourseInCurricularYear(1);
//        CourseQuantityCreditsEcts quantityOfCreditsEcts = new CourseQuantityCreditsEcts(1);
//
//
//        CourseIDDataModel courseIDDataModel = courseIDMapper.toDataModel(courseIDValueObject);
//        StudyPlanIDDataModel studyPlanIDDataModel = studyPlanIDMapper.toDataModel(studyPlanIDValueObject);
//        CourseInStudyPlanIDDataModel compositeIDDataModel =
//                courseInStudyPlanIDMapper.toDataModel(
//                        courseInStudyPlanFactory
//                                .newCourseInStudyPlan(semesterVO, yearVO, courseIDValueObject, studyPlanIDValueObject, durationOfCourse, quantityOfCreditsEcts)
//                                .identity()
//                );
//
//        CourseInStudyPlanDataModel dataModel = new CourseInStudyPlanDataModel(
//                compositeIDDataModel,
//                studyPlanIDDataModel,
//                courseIDDataModel,
//                semesterVO.toInt(),
//                yearVO.toInt(),
//                durationOfCourse.getDuration(),
//                quantityOfCreditsEcts.getQuantity()
//        );
//
//        // Act
//        CourseInStudyPlan domain = mapper.toDomain(dataModel);
//
//        // Assert: valores simples
//        assertNotNull(domain);
//        assertEquals(semesterVO,               domain.getSemester());
//        assertEquals(yearVO,                   domain.getCurricularYear());
//        assertEquals(courseIDValueObject,      domain.getCourseID());
//        assertEquals(studyPlanIDValueObject,   domain.getStudyplanID());
//
//        // Assert: identidade composta
//        assertEquals(courseIDValueObject,     domain.identity().getCourseID());
//        assertEquals(studyPlanIDValueObject,  domain.identity().getStudyPlanID());
//    }
//
//    @Test
//    void toDomain_ShouldThrowRuntimeException_WhenCourseIDMapperFails() throws Exception {
//        // Arrange: mocks individuais
//        ICourseIDMapper mockCourseIDMapper = mock(ICourseIDMapper.class);
//        IStudyPlanIDMapper mockStudyPlanIDMapper = mock(IStudyPlanIDMapper.class);
//        ICourseInStudyPlanIDMapper mockCourseInStudyPlanIDMapper = mock(ICourseInStudyPlanIDMapper.class);
//        ICourseInStudyPlanFactory mockFactory = mock(ICourseInStudyPlanFactory.class);
//
//        CourseInStudyPlanDataModel courseInStudyPlanDataModel = mock(CourseInStudyPlanDataModel.class);
//        when(courseInStudyPlanDataModel.getCourseIDDataModel()).thenReturn(mock(CourseIDDataModel.class));
//
//        // Simular falha no mapeamento do courseID
//        when(mockCourseIDMapper.toDomain(courseInStudyPlanDataModel.getCourseIDDataModel()))
//                .thenThrow(new RuntimeException("Simulated mapping failure"));
//
//        CourseInStudyPlanMapperImpl courseInStudyPlanMapperImpl = new CourseInStudyPlanMapperImpl(
//                mockCourseIDMapper,
//                mockStudyPlanIDMapper,
//                mockCourseInStudyPlanIDMapper,
//                mockFactory
//        );
//
//        // Act & Assert
//        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
//            courseInStudyPlanMapperImpl.toDomain(courseInStudyPlanDataModel);
//        });
//
//        assertTrue(thrown.getMessage().contains("Error trying to map CourseInStudyPlanDataModel back to domain"));
//    }
//}