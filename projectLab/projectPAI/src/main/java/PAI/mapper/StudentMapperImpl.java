package PAI.mapper;

import PAI.VOs.*;
import PAI.domain.Student;
import PAI.persistence.datamodel.*;
import PAI.factory.IStudentFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class StudentMapperImpl implements IStudentMapper {

    private final IStudentFactory _studentFactory;
    private final IStudentIDMapper _studentIDMapper;
    private final IPhoneNumberMapper _phoneNumberMapper;
    private final INIFMapper _nifMapper;
    private final IAddressMapper _addressMapper;
    private final IStudentAcademicEmailMapper _studentAcademicEmailMapper;

    public StudentMapperImpl(@Lazy IStudentFactory studentFactory,
                             IStudentIDMapper studentIDMapper,
                             IPhoneNumberMapper phoneNumberMapper,
                             INIFMapper nifMapper,
                             IAddressMapper addressMapper,
                             IStudentAcademicEmailMapper studentAcademicEmailMapper) {

        if (studentFactory == null) {
            throw new IllegalArgumentException("Student Factory cannot be null.");
        }
        this._studentFactory = studentFactory;

        if (studentIDMapper == null) {
            throw new IllegalArgumentException("StudentID Mapper cannot be null.");
        }
        this._studentIDMapper = studentIDMapper;

        if (phoneNumberMapper == null) {
            throw new IllegalArgumentException("PhoneNumber Mapper cannot be null.");
        }
        this._phoneNumberMapper = phoneNumberMapper;

        if (nifMapper == null) {
            throw new IllegalArgumentException("NIF Mapper cannot be null.");
        }
        this._nifMapper = nifMapper;

        if (addressMapper == null) {
            throw new IllegalArgumentException("Address Mapper cannot be null.");
        }
        this._addressMapper = addressMapper;

        if (studentAcademicEmailMapper == null) {
            throw new IllegalArgumentException("Student Academic Email Mapper cannot be null.");
        }
        this._studentAcademicEmailMapper = studentAcademicEmailMapper;
    }

    public StudentDataModel domainToDataModel(Student student) {
        if (student == null) {
            return null;
        }

        StudentIDDataModel studentID = _studentIDMapper.domainToDataModel(student.getStudentID());
        String name = student.getStudentName().getName();
        NIFDataModel nifDataModel = _nifMapper.domainToDataModel(student.getStudentNIF());
        PhoneNumberDataModel phoneNumberDataModel = _phoneNumberMapper.domainToDataModel(student.getStudentPhoneNumber());
        String email = student.getStudentEmail().getEmail();
        AddressDataModel addressDataModel = _addressMapper.toDataModel(student.getStudentAddress());
        StudentAcademicEmailDataModel studentAcademicEmailDataModel = _studentAcademicEmailMapper.domainToDataModel(student.getStudentAcademicEmail());

        return new StudentDataModel(studentID, name, nifDataModel, phoneNumberDataModel, email, addressDataModel, studentAcademicEmailDataModel);
    }

    public Student dataModelToDomain(StudentDataModel studentDataModel) throws Exception {
        if (studentDataModel == null) {
            return null;
        }

        StudentID studentID = _studentIDMapper.dataModelToDomain(studentDataModel.getStudentID());
        Name name = new Name(studentDataModel.getName());
        NIF nif = _nifMapper.dataModelToDomain(studentDataModel.getNIF());
        PhoneNumber phoneNumber = _phoneNumberMapper.dataModelToDomain(studentDataModel.getPhone());
        Email email = new Email(studentDataModel.getEmail());
        Address address = _addressMapper.toDomain(studentDataModel.getAddress());
        StudentAcademicEmail studentAcademicEmail = _studentAcademicEmailMapper.dataModelToDomain(studentDataModel.getAcademicEmail());

        return _studentFactory.newStudentFromDataModel(studentID, name, nif, phoneNumber, email, address, studentAcademicEmail);
    }
}