package PAI.persistence.springdata.Department;

import PAI.persistence.datamodel.department.DepartmentDataModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDepartmentRepositorySpringData extends JpaRepository<DepartmentDataModel,String> {
}
