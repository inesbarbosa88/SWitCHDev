package PAI.persistence.datamodel.programmeEdition;
import PAI.persistence.datamodel.programme.ProgrammeIDDataModel;
import PAI.persistence.datamodel.schoolYear.SchoolYearIDDataModel;
import jakarta.persistence.*;

import java.io.Serializable;

@Embeddable
public class ProgrammeEditionIdDataModel implements Serializable {
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "programmeName",
                    column = @Column(name = "edition_programme_name")),
            @AttributeOverride(name = "programmeAcronym",
                    column = @Column(name = "edition_programme_acronym"))
    })
    private ProgrammeIDDataModel _programmeIDDataModel;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "id",
                    column = @Column(name = "edition_school_year"))
    })
    private SchoolYearIDDataModel _schoolYearIDDataModel;

    public ProgrammeEditionIdDataModel() {}

    public ProgrammeEditionIdDataModel(ProgrammeIDDataModel programmeIDDataModel, SchoolYearIDDataModel schoolYearIDDataModel) {
        if(programmeIDDataModel == null) {
            throw new IllegalArgumentException("ProgrammeIDDataModel cannot be null");
        }
        this._programmeIDDataModel = programmeIDDataModel;

        if(schoolYearIDDataModel == null) {
            throw new IllegalArgumentException("SchoolYearIDDataModel cannot be null");
        }
        this._schoolYearIDDataModel = schoolYearIDDataModel;
    }

    //Getters
    public ProgrammeIDDataModel getProgrammeIdDataModel() {
        return this._programmeIDDataModel;
    }
    public SchoolYearIDDataModel get_schoolYearIDDataModel() {
        return this._schoolYearIDDataModel;
    }
}
