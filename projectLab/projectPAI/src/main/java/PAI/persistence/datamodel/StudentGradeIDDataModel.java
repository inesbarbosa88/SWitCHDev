package PAI.persistence.datamodel;

import PAI.persistence.datamodel.courseEdition.CourseEditionIDDataModel;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class StudentGradeIDDataModel implements Serializable {


    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "uniqueNumber",
                    column = @Column(name = "student_id"))
    })
    private StudentIDDataModel _studentIDDataModel;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "_programmeEditionIdDataModel._programmeIDDataModel.programmeAcronym",
                    column = @Column(name = "course_programme_acronym")),
            @AttributeOverride(name = "_programmeEditionIdDataModel._programmeIDDataModel.programmeName",
                    column = @Column(name = "course_programme_name")),
            @AttributeOverride(name = "_courseInStudyPlanIDDataModel.CISPcourseID.courseAcronym",
                    column = @Column(name = "courseid_acronym"))
    })
    private CourseEditionIDDataModel _courseEditionIDDataModel;

    protected StudentGradeIDDataModel() {}

    public StudentGradeIDDataModel (StudentIDDataModel studentIDDataModel, CourseEditionIDDataModel courseEditionIDDataModel){
        if (studentIDDataModel == null || courseEditionIDDataModel == null){
            throw new IllegalArgumentException("Cannot be null");
        }
        _studentIDDataModel = studentIDDataModel;
        _courseEditionIDDataModel = courseEditionIDDataModel;
    }

    public StudentIDDataModel get_studentIDDataModel() {
        return _studentIDDataModel;
    }

    public CourseEditionIDDataModel get_courseEditionIDDataModel() {
        return _courseEditionIDDataModel;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StudentGradeIDDataModel that)) return false;
        return Objects.equals(_studentIDDataModel, that._studentIDDataModel) && Objects.equals(_courseEditionIDDataModel, that._courseEditionIDDataModel);
    }
    @Override
    public int hashCode() {
        return _studentIDDataModel.hashCode() + _courseEditionIDDataModel.hashCode();
    }

}
