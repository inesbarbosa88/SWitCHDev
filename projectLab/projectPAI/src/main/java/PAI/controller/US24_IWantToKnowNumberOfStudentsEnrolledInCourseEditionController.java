//package PAI.controller;
//
//import PAI.VOs.CourseEditionID;
//import PAI.persistence.mem.CourseEditionEnrolmentRepositoryImpl;
//import PAI.service.CourseEditionEnrolmentServiceImpl;
//import org.springframework.stereotype.Component;
//
//@Component
//public class US24_IWantToKnowNumberOfStudentsEnrolledInCourseEditionController {
//
//    private CourseEditionEnrolmentServiceImpl courseEditionEnrolmentServiceImpl;
//
//    public US24_IWantToKnowNumberOfStudentsEnrolledInCourseEditionController(CourseEditionEnrolmentServiceImpl courseEditionEnrolmentServiceImpl) {
//
//        if (courseEditionEnrolmentServiceImpl == null) {
//            throw new IllegalArgumentException("Course Edition Enrolment Repository cannot be null");
//        }
//
//        this.courseEditionEnrolmentServiceImpl = courseEditionEnrolmentServiceImpl;
//    }
//
//   public int IWantToKnowNumberOfStudentsEnrolledInCourseEdition(CourseEditionID courseEditionID) throws Exception {
//
//        return courseEditionID.numberOfStudentsEnrolledInCourseEdition(courseEditionID);
//
//    }
//}
