package PAI.persistence.springdata;

import PAI.persistence.datamodel.schoolYear.SchoolYearDataModel;
import PAI.persistence.datamodel.schoolYear.SchoolYearIDDataModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ISchoolYearRepositorySpringData extends JpaRepository<SchoolYearDataModel, SchoolYearIDDataModel>{
    @Query("SELECT schoolYear FROM SchoolYearDataModel schoolYear WHERE schoolYear.startDate < CURRENT_DATE AND schoolYear.endDate > CURRENT_DATE")
    Optional<SchoolYearDataModel> findCurrentSchoolYear();
}
