package PAI.factory;

import PAI.VOs.Date;
import PAI.VOs.Description;
import PAI.VOs.SchoolYearID;
import PAI.domain.SchoolYear;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class SchoolYearFactoryImpl implements ISchoolYearFactory {

    public SchoolYear createSchoolYear(Description description, Date startDate, Date endDate) {
        return new SchoolYear(description, startDate, endDate);

    }

    public SchoolYear createSchoolYear(UUID uuid, Description description, Date startDate, Date endDate) {
        return new SchoolYear(uuid, description, startDate, endDate);
    }
}
