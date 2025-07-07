package PAI.mapper;

import PAI.VOs.*;
import PAI.domain.ProgrammeEnrolment;
import PAI.factory.IProgrammeEnrolmentFactory;

import PAI.mapper.accessMethod.IAccessMethodIDMapper;
import PAI.mapper.programme.IProgrammeIDMapper;
import PAI.persistence.datamodel.ProgrammeEnrolmentDataModel;
import PAI.persistence.datamodel.ProgrammeEnrolmentIDDataModel;
import PAI.persistence.datamodel.programme.ProgrammeIDDataModel;
import PAI.persistence.datamodel.StudentIDDataModel;
import PAI.persistence.datamodel.accessMethod.AccessMethodIDDataModel;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class ProgrammeEnrolmentMapperImpl implements IProgrammeEnrolmentMapper {

    private final IProgrammeEnrolmentFactory _peFactory;
    private final IProgrammeEnrolmentIDMapper _peIDMapper;
    private final IProgrammeIDMapper _programmeIDMapper;
    private final IStudentIDMapper _studentIDMapper;
    private final IAccessMethodIDMapper _amIDMapper;

    public ProgrammeEnrolmentMapperImpl(@Lazy IProgrammeEnrolmentFactory peFactory,
                                        IProgrammeEnrolmentIDMapper peIDMapper,
                                        IProgrammeIDMapper programmeIDMapper,
                                        IStudentIDMapper studentIDMapper,
                                        IAccessMethodIDMapper amIDMapper) {
        if (peFactory == null) {
            throw new IllegalArgumentException("Programme Enrolment Factory cannot be null.");
        }
        this._peFactory = peFactory;

        if (peIDMapper == null) {
            throw new IllegalArgumentException("Programme Enrolment ID Mapper cannot be null.");
        }
        this._peIDMapper = peIDMapper;

        if (programmeIDMapper == null) {
            throw new IllegalArgumentException("Programme ID Mapper cannot be null.");
        }
        this._programmeIDMapper = programmeIDMapper;

        if (studentIDMapper == null) {
            throw new IllegalArgumentException("Student ID Mapper cannot be null.");
        }
        this._studentIDMapper = studentIDMapper;

        if (amIDMapper == null) {
            throw new IllegalArgumentException("Access Method ID Mapper cannot be null.");
        }
        this._amIDMapper = amIDMapper;
    }

    @Override
    public ProgrammeEnrolment toDomain(ProgrammeEnrolmentDataModel programmeEnrolmentDataModel) {
        if (programmeEnrolmentDataModel == null) {
            return null;
        }

        StudentID studentID = _studentIDMapper.dataModelToDomain(programmeEnrolmentDataModel.getStudentID());
        AccessMethodID accessMethodID = _amIDMapper.toVO(programmeEnrolmentDataModel.getAccessMethodID()).get();
        ProgrammeID programmeID = _programmeIDMapper.toDomain(programmeEnrolmentDataModel.getProgrammeID());
        Date enrolmentDate = new Date(programmeEnrolmentDataModel.getDate());

        return _peFactory.createProgrammeEnrolment(studentID, accessMethodID, programmeID, enrolmentDate);
    }

    @Override
    public ProgrammeEnrolmentDataModel toDataModel(ProgrammeEnrolment programmeEnrolment) {
        if (programmeEnrolment == null) {
            return null;
        }

        ProgrammeEnrolmentIDDataModel peID = _peIDMapper.domainToDataModel(programmeEnrolment.getProgrammeEnrolmentID());
        ProgrammeIDDataModel programmeID = _programmeIDMapper.toData(programmeEnrolment.getProgrammeID());
        StudentIDDataModel studentID = _studentIDMapper.domainToDataModel(programmeEnrolment.getStudentID());
        AccessMethodIDDataModel amID = _amIDMapper.toDataModel(programmeEnrolment.getAccessMethodID()).get();
        LocalDate date = programmeEnrolment.getDate().getLocalDate();

        return new ProgrammeEnrolmentDataModel(peID, programmeID, studentID, amID, date);
    }
}