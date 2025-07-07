package PAI.persistence.datamodel;

import PAI.persistence.datamodel.accessMethod.AccessMethodIDDataModel;
import PAI.persistence.datamodel.programme.ProgrammeIDDataModel;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "programme_enrolment")
public class ProgrammeEnrolmentDataModel {

    @EmbeddedId
    private ProgrammeEnrolmentIDDataModel programmeEnrolmentID;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "uniqueNumber",  // Change from "id" to "uniqueNumber" to match StudentIDDataModel
                    column = @Column(name = "student_id", insertable = false, updatable = false))
    })
    private StudentIDDataModel studentID;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "programmeAcronym",
                    column = @Column(name = "programme_acronym", insertable = false, updatable = false)),
            @AttributeOverride(name = "programmeName",
                    column = @Column(name = "programme_name", insertable = false, updatable = false))
    })
    private ProgrammeIDDataModel programmeID;

    @Embedded
    private AccessMethodIDDataModel accessMethodID;

    @Column(name = "enrolment_date")
    private LocalDate date;

    public ProgrammeEnrolmentDataModel() {
    }

    public ProgrammeEnrolmentDataModel(ProgrammeEnrolmentIDDataModel programmeEnrolmentIDDM, ProgrammeIDDataModel programmeIDDM,
                                       StudentIDDataModel studentIDDM, AccessMethodIDDataModel accessMethodIDDM, LocalDate date) {

        if (programmeEnrolmentIDDM == null || studentIDDM == null || programmeIDDM == null || accessMethodIDDM == null || date == null) {
            throw new IllegalArgumentException("Parameter cannot be null.");
        }

        this.programmeEnrolmentID = programmeEnrolmentIDDM;
        this.programmeID = programmeIDDM;
        this.studentID = studentIDDM;
        this.accessMethodID = accessMethodIDDM;
        this.date = date;
    }

    public ProgrammeEnrolmentIDDataModel getProgrammeEnrolmentID() {
        return programmeEnrolmentID;
    }

    public ProgrammeIDDataModel getProgrammeID() {
        return programmeID;
    }

    public StudentIDDataModel getStudentID() {
        return studentID;
    }

    public AccessMethodIDDataModel getAccessMethodID() {
        return accessMethodID;
    }

    public LocalDate getDate() {
        return date;
    }
}