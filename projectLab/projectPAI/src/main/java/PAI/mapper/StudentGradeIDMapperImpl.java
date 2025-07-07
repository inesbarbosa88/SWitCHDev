package PAI.mapper;

import PAI.VOs.*;
import PAI.mapper.courseEdition.CourseEditionIDMapperImpl;
import PAI.persistence.datamodel.StudentGradeIDDataModel;
import PAI.persistence.datamodel.StudentIDDataModel;
import PAI.persistence.datamodel.courseEdition.CourseEditionIDDataModel;
import org.springframework.stereotype.Component;

@Component
public class StudentGradeIDMapperImpl implements IStudentGradeIDMapper{

    private final CourseEditionIDMapperImpl courseEditionIDMapper;
    private final StudentIDMapperImpl studentIDMapperImpl;

    public StudentGradeIDMapperImpl(CourseEditionIDMapperImpl courseEditionIDMapper, StudentIDMapperImpl studentIDMapperImpl) {
        this.courseEditionIDMapper = courseEditionIDMapper;
        this.studentIDMapperImpl = studentIDMapperImpl;
    }

    public StudentGradeIDDataModel toDataModel (StudentGradeID studentGradeID) throws Exception{

        StudentID studentID = studentGradeID.get_studentID();
        CourseEditionID courseEditionID = studentGradeID.get_courseEdition();
        return new StudentGradeIDDataModel(studentIDMapperImpl.domainToDataModel(studentID),courseEditionIDMapper.toDataModel(courseEditionID));
    }

    public StudentGradeID toDomain (StudentGradeIDDataModel studentGradeIDDataModel) throws Exception{

        CourseEditionIDDataModel courseEditionIDDataModel = studentGradeIDDataModel.get_courseEditionIDDataModel();
        StudentIDDataModel studentIDDataModel = studentGradeIDDataModel.get_studentIDDataModel();
        return new StudentGradeID(studentIDMapperImpl.dataModelToDomain(studentIDDataModel),courseEditionIDMapper.toDomain(courseEditionIDDataModel));
    }
}
