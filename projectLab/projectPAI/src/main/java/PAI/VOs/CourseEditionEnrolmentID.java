package PAI.VOs;

import PAI.ddd.DomainId;

import java.util.Objects;


public class CourseEditionEnrolmentID implements DomainId {

    private final StudentID _studentID;

    private final CourseEditionID _courseEditionID;

    public CourseEditionEnrolmentID( StudentID studentID, CourseEditionID courseEditionID ) {
        if (studentID == null || courseEditionID == null){
            throw new IllegalArgumentException("StudentID and CourseEditionID cannot be null.");
        }
        _studentID = studentID;
        _courseEditionID = courseEditionID;
    }

    public StudentID findStudentID() {
        return _studentID;
    }

    public CourseEditionID findCourseEditionID() {
        return _courseEditionID;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        CourseEditionEnrolmentID that = (CourseEditionEnrolmentID) obj;
        return Objects.equals(_studentID, that._studentID) && Objects.equals(_courseEditionID, that._courseEditionID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(_studentID, _courseEditionID);
    }

    @Override
    public String toString() {
        return "CourseEditionEnrolmentID =" + _studentID + _courseEditionID;
    }

    public StudentID getStudentID() {
        return _studentID;
    }

    public CourseEditionID getCourseEditionID() {
        return _courseEditionID;
    }
}
