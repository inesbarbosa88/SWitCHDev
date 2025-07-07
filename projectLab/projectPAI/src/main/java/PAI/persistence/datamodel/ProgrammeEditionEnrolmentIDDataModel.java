package PAI.persistence.datamodel;

import PAI.persistence.datamodel.programmeEdition.ProgrammeEditionIdDataModel;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ProgrammeEditionEnrolmentIDDataModel implements Serializable {

    @Embedded
    private StudentIDDataModel _studentIdDataModel;
    @Embedded
    private ProgrammeEditionIdDataModel _programmeEditionIdDataModel;

    public ProgrammeEditionEnrolmentIDDataModel() {
    }

    public ProgrammeEditionEnrolmentIDDataModel(StudentIDDataModel studentIdDataModel, ProgrammeEditionIdDataModel programmeEditionIdDataModel) {
        if (studentIdDataModel == null) {
            throw new IllegalArgumentException("studentId cannot be null or blank");
        }
        if (programmeEditionIdDataModel == null) {
            throw new IllegalArgumentException("programmeEditionId cannot be null or blank");
        }
        this._studentIdDataModel = studentIdDataModel;
        this._programmeEditionIdDataModel = programmeEditionIdDataModel;
    }

    public StudentIDDataModel getStudentIdDataModel() {
        return _studentIdDataModel;
    }

    public ProgrammeEditionIdDataModel getProgrammeEditionIdDataModel() {
        return _programmeEditionIdDataModel;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ProgrammeEditionEnrolmentIDDataModel that = (ProgrammeEditionEnrolmentIDDataModel) o;
        return Objects.equals(_studentIdDataModel, that._studentIdDataModel) && Objects.equals(_programmeEditionIdDataModel, that._programmeEditionIdDataModel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(_studentIdDataModel, _programmeEditionIdDataModel);
    }
}


