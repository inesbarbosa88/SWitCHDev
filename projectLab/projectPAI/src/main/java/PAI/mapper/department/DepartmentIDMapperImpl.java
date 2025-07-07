package PAI.mapper.department;
import PAI.VOs.DepartmentAcronym;
import PAI.VOs.DepartmentID;
import PAI.persistence.datamodel.department.DepartmentIDDataModel;
import org.springframework.stereotype.Component;

@Component
public class DepartmentIDMapperImpl implements IDepartmentIDMapper {

    @Override
    public DepartmentIDDataModel toDataModel(DepartmentID departmentID) {
        if(departmentID==null){
            throw new IllegalArgumentException("DepartmentID cannot be null");
        }

        DepartmentAcronym acronym= departmentID.getAcronym();
        return new DepartmentIDDataModel(acronym.getAcronym());
    }

    @Override
    public DepartmentID toDomainModel(DepartmentIDDataModel departmentIDDataModel) {
        if(departmentIDDataModel==null){
            throw new IllegalArgumentException("DepartmentIDDataModel cannot be null");
        }
        DepartmentAcronym acronym = new DepartmentAcronym(departmentIDDataModel.getDepartmentID());
        return new DepartmentID(acronym);
    }
}

