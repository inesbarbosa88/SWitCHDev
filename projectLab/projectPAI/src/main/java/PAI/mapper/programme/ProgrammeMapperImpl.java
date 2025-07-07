package PAI.mapper.programme;

import PAI.factory.IProgrammeFactory;
import PAI.domain.programme.Programme;
import PAI.mapper.DegreeType.DegreeTypeIDMapper;
import PAI.mapper.TeacherIDMapperImpl;
import PAI.mapper.department.DepartmentIDMapperImpl;
import PAI.persistence.datamodel.*;
import PAI.VOs.*;
import PAI.persistence.datamodel.DegreeType.DegreeTypeIDDataModel;
import PAI.persistence.datamodel.department.DepartmentIDDataModel;
import PAI.persistence.datamodel.programme.ProgrammeDataModel;
import PAI.persistence.datamodel.programme.ProgrammeIDDataModel;
import org.springframework.stereotype.Component;

@Component
public class ProgrammeMapperImpl implements IProgrammeMapper {

    private final ProgrammeIDMapperImpl _progIDMapper;
    private final TeacherIDMapperImpl _teacherIDMapper;
    private final DepartmentIDMapperImpl _departmentIDMapper;
    private final DegreeTypeIDMapper _degreeTypeIDMapper;
    private final IProgrammeFactory _factory;

    public ProgrammeMapperImpl(ProgrammeIDMapperImpl progIDMapper, TeacherIDMapperImpl teacherIDMapper, DepartmentIDMapperImpl departmentIDMapper, DegreeTypeIDMapper degreeTypeIDMapper, IProgrammeFactory factory) {
        _progIDMapper = progIDMapper;
        _teacherIDMapper = teacherIDMapper;
        _departmentIDMapper = departmentIDMapper;
        _degreeTypeIDMapper = degreeTypeIDMapper;
        _factory = factory;
    }

    public ProgrammeDataModel toData(Programme programme) {

        ProgrammeIDDataModel progIDDM = _progIDMapper.toData(programme.getProgrammeID());
        TeacherIDDataModel teacherIDDM = _teacherIDMapper.toDataModel(programme.getProgrammeDirectorID());
        DepartmentIDDataModel departIDDM = _departmentIDMapper.toDataModel(programme.getDepartment());
        DegreeTypeIDDataModel degreeTIDDM = _degreeTypeIDMapper.toDataModel(programme.getDegreeTypeID());

        return new ProgrammeDataModel(progIDDM, programme.getProgrammeID().getName().getnameWithNumbersAndSpecialChars(), programme.getAcronym().getAcronym(), programme.getQuantSemesters().getQuantityOfSemesters(),
                programme.getQuantEcts().getQuantEcts(), degreeTIDDM, departIDDM, teacherIDDM);
    }

    public Programme toDomain(ProgrammeDataModel programmeDataModel){
        NameWithNumbersAndSpecialChars name = new NameWithNumbersAndSpecialChars(programmeDataModel.getName());
        Acronym acronym = new Acronym(programmeDataModel.getAcronym());
        QuantSemesters quantSemesters = new QuantSemesters(programmeDataModel.getQuantSemesters());
        QuantEcts quantEcts = new QuantEcts(programmeDataModel.getQuantEcts());

        //Para alterar quando tivermos o Mapper
        DegreeTypeID degreeTypeID = _degreeTypeIDMapper.toDomain(programmeDataModel.getDegreeTypeID());

        DepartmentID departID = _departmentIDMapper.toDomainModel(programmeDataModel.getDepartmentID());

        TeacherID teacherID = _teacherIDMapper.toDomain(programmeDataModel.getProgrammeDirectorID());

        ProgrammeID programmeID = _progIDMapper.toDomain(programmeDataModel.getProgID());

        return _factory.reregisterProgramme(name,acronym,quantEcts,quantSemesters,degreeTypeID,departID,teacherID,programmeID);
    }

}
