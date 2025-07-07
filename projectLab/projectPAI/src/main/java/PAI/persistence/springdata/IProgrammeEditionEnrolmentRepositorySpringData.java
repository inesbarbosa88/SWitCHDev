package PAI.persistence.springdata;

import PAI.VOs.ProgrammeEditionID;
import PAI.domain.programmeEdition.ProgrammeEdition;
import PAI.persistence.datamodel.CourseEditionEnrolmentDataModel;
import PAI.persistence.datamodel.ProgrammeEditionEnrolmentDataModel;
import PAI.persistence.datamodel.ProgrammeEditionEnrolmentIDDataModel;
import PAI.persistence.datamodel.StudentIDDataModel;
import PAI.persistence.datamodel.courseEdition.CourseEditionIDDataModel;
import PAI.persistence.datamodel.programmeEdition.ProgrammeEditionIdDataModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IProgrammeEditionEnrolmentRepositorySpringData extends JpaRepository<ProgrammeEditionEnrolmentDataModel, ProgrammeEditionEnrolmentIDDataModel> {

    @Query("SELECT pee FROM ProgrammeEditionEnrolmentDataModel pee WHERE pee._id._programmeEditionIdDataModel = :programmeEditionId")
    List<ProgrammeEditionEnrolmentDataModel> findAllByProgrammeEditionId(@Param("programmeEditionId") ProgrammeEditionIdDataModel programmeEditionId);

    @Query("SELECT pee FROM ProgrammeEditionEnrolmentDataModel pee WHERE pee._id._studentIdDataModel = :studentId AND pee._id._programmeEditionIdDataModel = :programmeEditionId")
    Optional<ProgrammeEditionEnrolmentDataModel> findByStudentAndProgrammeEdition(
            @Param("studentId") StudentIDDataModel studentId,
            @Param("programmeEditionId") ProgrammeEditionIdDataModel programmeEditionId);
}