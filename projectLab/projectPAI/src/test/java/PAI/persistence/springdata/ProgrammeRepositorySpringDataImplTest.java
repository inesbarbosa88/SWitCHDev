package PAI.persistence.springdata;

import PAI.VOs.Acronym;
import PAI.VOs.DepartmentID;
import PAI.VOs.NameWithNumbersAndSpecialChars;
import PAI.VOs.ProgrammeID;
import PAI.domain.programme.Programme;
import PAI.mapper.programme.IProgrammeIDMapper;
import PAI.mapper.programme.IProgrammeMapper;
import PAI.persistence.datamodel.programme.ProgrammeDataModel;
import PAI.persistence.datamodel.programme.ProgrammeIDDataModel;
import PAI.persistence.springdata.programme.IProgrammeRepositorySpringData;
import PAI.persistence.springdata.programme.ProgrammeRepositorySpringDataImpl;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProgrammeRepositorySpringDataImplTest {

    @Test
    void shouldCreateProgrammeRepoSpringData() {
        //arrange
        IProgrammeRepositorySpringData iProgRepo = mock(IProgrammeRepositorySpringData.class);
        IProgrammeMapper iProgMapper = mock(IProgrammeMapper.class);
        IProgrammeIDMapper iProgIdMapper = mock(IProgrammeIDMapper.class);
        //act
        ProgrammeRepositorySpringDataImpl result = new ProgrammeRepositorySpringDataImpl(iProgMapper,iProgRepo,iProgIdMapper);

        //assert
        assertNotNull(result);
    }

    @Test
    void shouldNotCreateProgrammeRepoSpringDataWhenIProgRepoSpringDataIsNull() {
        //arrange
        IProgrammeMapper iProgMapper = mock(IProgrammeMapper.class);
        IProgrammeIDMapper iProgIdMapper = mock(IProgrammeIDMapper.class);

        //act + assert
        assertThrows(IllegalArgumentException.class, () -> {
            new ProgrammeRepositorySpringDataImpl(iProgMapper,null,iProgIdMapper);
        });
    }

    @Test
    void shouldNotCreateProgrammeRepoSpringDataWhenIProgMapperIsNull() {
        //arrange
        IProgrammeRepositorySpringData iProgRepo = mock(IProgrammeRepositorySpringData.class);
        IProgrammeIDMapper iProgIdMapper = mock(IProgrammeIDMapper.class);
        //act + assert
        assertThrows(IllegalArgumentException.class, () -> {
            new ProgrammeRepositorySpringDataImpl(null,iProgRepo,iProgIdMapper);
        });
    }

    @Test
    void shouldNotCreateProgrammeRepoSpringDataWhenIProgIDMapperIsNull() {
        //arrange
        IProgrammeRepositorySpringData iProgRepo = mock(IProgrammeRepositorySpringData.class);
        IProgrammeMapper iProgMapper = mock(IProgrammeMapper.class);
        //act + assert
        assertThrows(IllegalArgumentException.class, () -> {
            new ProgrammeRepositorySpringDataImpl(iProgMapper,iProgRepo,null);
        });
    }

    @Test
    void shouldSaveProgramme() {
        //arrange
        IProgrammeRepositorySpringData iProgRepo = mock(IProgrammeRepositorySpringData.class);
        IProgrammeMapper iProgMapper = mock(IProgrammeMapper.class);
        IProgrammeIDMapper iProgIdMapper = mock(IProgrammeIDMapper.class);

        Programme prog = mock(Programme.class);
        ProgrammeDataModel progDM = mock(ProgrammeDataModel.class);

        ProgrammeRepositorySpringDataImpl progRepo = new ProgrammeRepositorySpringDataImpl(iProgMapper, iProgRepo,iProgIdMapper);

        //act
        when(iProgMapper.toData(prog)).thenReturn(progDM);
        when(iProgMapper.toDomain(progDM)).thenReturn(prog);

        //assert
        assertNotNull(progRepo.save(prog));
    }

    @Test
    void shouldNotSaveProgrammeWhenProgIsNull() {
        //arrange
        IProgrammeRepositorySpringData iProgRepo = mock(IProgrammeRepositorySpringData.class);
        IProgrammeMapper iProgMapper = mock(IProgrammeMapper.class);
        IProgrammeIDMapper iProgIdMapper = mock(IProgrammeIDMapper.class);

        ProgrammeRepositorySpringDataImpl progRepo = new ProgrammeRepositorySpringDataImpl(iProgMapper, iProgRepo,iProgIdMapper);

        //act + assert
        assertNull(progRepo.save(null));
    }

    @Test
    void shouldUpdateIfIdExists() {
        // Arrange
        IProgrammeRepositorySpringData iProgRepo = mock(IProgrammeRepositorySpringData.class);
        IProgrammeMapper iProgMapper = mock(IProgrammeMapper.class);
        IProgrammeIDMapper iProgIdMapper = mock(IProgrammeIDMapper.class);
        ProgrammeRepositorySpringDataImpl progRepo = new ProgrammeRepositorySpringDataImpl(iProgMapper, iProgRepo,iProgIdMapper);

        ProgrammeIDDataModel id = mock(ProgrammeIDDataModel.class);
        Programme prog = mock(Programme.class);


        when(iProgRepo.existsById(id)).thenReturn(true);
        ProgrammeDataModel dataModel = mock(ProgrammeDataModel.class);
        when(iProgMapper.toData(prog)).thenReturn(dataModel);
        when(iProgRepo.save(dataModel)).thenReturn(dataModel);
        when(iProgMapper.toDomain(dataModel)).thenReturn(prog);

        // Act
        Programme result = progRepo.update(id, prog);

        // Assert
        assertNotNull(result);
    }

    @Test
    void shouldNotUpdateProgrammeWhenProgIsNull() {
        // Arrange
        IProgrammeRepositorySpringData iProgRepo = mock(IProgrammeRepositorySpringData.class);
        IProgrammeMapper iProgMapper = mock(IProgrammeMapper.class);
        IProgrammeIDMapper iProgIdMapper = mock(IProgrammeIDMapper.class);
        ProgrammeRepositorySpringDataImpl progRepo = new ProgrammeRepositorySpringDataImpl(iProgMapper, iProgRepo,iProgIdMapper);

        ProgrammeIDDataModel id = mock(ProgrammeIDDataModel.class);

        when(iProgRepo.existsById(id)).thenReturn(true);
        when(iProgMapper.toData(null)).thenReturn(null);

        // Act + Assert
        Programme result = progRepo.update(id, null);
        assertNull(result);
    }

    @Test
    void shouldThrowWhenProgrammeIDDoesNotExist() {
        // Arrange
        IProgrammeRepositorySpringData iProgRepo = mock(IProgrammeRepositorySpringData.class);
        IProgrammeMapper iProgMapper = mock(IProgrammeMapper.class);
        IProgrammeIDMapper iProgIdMapper = mock(IProgrammeIDMapper.class);
        ProgrammeRepositorySpringDataImpl progRepo = new ProgrammeRepositorySpringDataImpl(iProgMapper, iProgRepo,iProgIdMapper);

        Programme prog = mock(Programme.class);
        ProgrammeID id = mock(ProgrammeID.class);

        ProgrammeIDDataModel idDM = mock(ProgrammeIDDataModel.class);
        when(iProgIdMapper.toData(id)).thenReturn(idDM);
        when(iProgRepo.existsById(idDM)).thenReturn(false);


        // Act + Assert
        assertThrows(EntityNotFoundException.class, () -> progRepo.update(idDM, prog));
    }

    @Test
    void findAllShouldReturnMappedResults() {
        // Arrange
        IProgrammeRepositorySpringData iProgRepo = mock(IProgrammeRepositorySpringData.class);
        IProgrammeMapper iProgMapper = mock(IProgrammeMapper.class);
        IProgrammeIDMapper iProgIdMapper = mock(IProgrammeIDMapper.class);
        ProgrammeRepositorySpringDataImpl progRepo = new ProgrammeRepositorySpringDataImpl(iProgMapper, iProgRepo,iProgIdMapper);

        ProgrammeDataModel dataModel1 = mock(ProgrammeDataModel.class);
        ProgrammeDataModel dataModel2 = mock(ProgrammeDataModel.class);

        Programme programme1 = mock(Programme.class);
        Programme programme2 = mock(Programme.class);

        when(iProgRepo.findAll()).thenReturn(List.of(dataModel1, dataModel2));
        when(iProgMapper.toDomain(dataModel1)).thenReturn(programme1);
        when(iProgMapper.toDomain(dataModel2)).thenReturn(programme2);

        // Act
        List<Programme> result = progRepo.findAll();

        // Assert
        assertEquals(2, result.size());
        assertTrue(result.contains(programme1));
        assertTrue(result.contains(programme2));
    }

    @Test
    void shouldReturnProgrammeWhenIdExists() {
        // Arrange
        IProgrammeRepositorySpringData iProgRepo = mock(IProgrammeRepositorySpringData.class);
        IProgrammeMapper iProgMapper = mock(IProgrammeMapper.class);
        IProgrammeIDMapper iProgIdMapper = mock(IProgrammeIDMapper.class);
        ProgrammeRepositorySpringDataImpl progRepo = new ProgrammeRepositorySpringDataImpl(iProgMapper, iProgRepo,iProgIdMapper);

        ProgrammeID id = new ProgrammeID(
                new NameWithNumbersAndSpecialChars("Master in Informatics and Computer Engineering"),
                new Acronym("MIEIC")
        );
        ProgrammeDataModel dataModel = mock(ProgrammeDataModel.class);
        Programme programme = mock(Programme.class);

        ProgrammeIDDataModel idDM = mock(ProgrammeIDDataModel.class);

        when(iProgRepo.existsById(idDM)).thenReturn(true);

        when(iProgIdMapper.toData(id)).thenReturn(idDM);
        when(iProgRepo.findById(idDM)).thenReturn(Optional.of(dataModel));
        when(iProgMapper.toDomain(dataModel)).thenReturn(programme);

        // Act
        Optional<Programme> result = progRepo.ofIdentity(id);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(programme, result.get());
    }

    @Test
    void shouldReturnEmptyWhenIdDoesNotExist() {
        // Arrange
        IProgrammeRepositorySpringData iProgRepo = mock(IProgrammeRepositorySpringData.class);
        IProgrammeMapper iProgMapper = mock(IProgrammeMapper.class);
        IProgrammeIDMapper iProgIdMapper = mock(IProgrammeIDMapper.class);
        ProgrammeRepositorySpringDataImpl progRepo = new ProgrammeRepositorySpringDataImpl(iProgMapper, iProgRepo,iProgIdMapper);

        ProgrammeID id = new ProgrammeID(
                new NameWithNumbersAndSpecialChars("Non-existing Programme"),
                new Acronym("NEP")
        );

        ProgrammeIDDataModel idDM = mock(ProgrammeIDDataModel.class);

        when(iProgIdMapper.toData(id)).thenReturn(idDM);
        when(iProgRepo.existsById(idDM)).thenReturn(true);
        when(iProgRepo.findById(idDM)).thenReturn(Optional.empty());

        // Act
        Optional<Programme> result = progRepo.ofIdentity(id);

        // Assert
        assertFalse(result.isPresent());
    }

    @Test
    void shouldReturnTrueWhenIdExists() {
        // Arrange
        IProgrammeRepositorySpringData iProgRepo = mock(IProgrammeRepositorySpringData.class);
        IProgrammeMapper iProgMapper = mock(IProgrammeMapper.class);
        IProgrammeIDMapper iProgIdMapper = mock(IProgrammeIDMapper.class);
        ProgrammeRepositorySpringDataImpl progRepo = new ProgrammeRepositorySpringDataImpl(iProgMapper, iProgRepo,iProgIdMapper);

        ProgrammeID id = mock(ProgrammeID.class);

        ProgrammeIDDataModel idDM = mock(ProgrammeIDDataModel.class);

        when(iProgIdMapper.toData(id)).thenReturn(idDM);

        when(iProgRepo.existsById(idDM)).thenReturn(true);

        when(iProgRepo.existsById(idDM)).thenReturn(true);

        // Act
        boolean result = progRepo.containsOfIdentity(id);

        // Assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseWhenIdDoesNotExist() {
        // Arrange
        IProgrammeRepositorySpringData iProgRepo = mock(IProgrammeRepositorySpringData.class);
        IProgrammeMapper iProgMapper = mock(IProgrammeMapper.class);
        IProgrammeIDMapper iProgIdMapper = mock(IProgrammeIDMapper.class);
        ProgrammeRepositorySpringDataImpl progRepo = new ProgrammeRepositorySpringDataImpl(iProgMapper, iProgRepo,iProgIdMapper);

        ProgrammeID id = new ProgrammeID(
                new NameWithNumbersAndSpecialChars("Non-existing Programme"),
                new Acronym("NEP")
        );

        ProgrammeIDDataModel idDM = mock(ProgrammeIDDataModel.class);

        when(iProgRepo.existsById(idDM)).thenReturn(true);

        when(iProgRepo.existsById(idDM)).thenReturn(false);

        // Act
        boolean result = progRepo.containsOfIdentity(id);

        // Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnListOfProgrammeIDsWhenAllInputsAreValidUsingDoubles(){

        //Arrange
        DepartmentID departmentID=mock(DepartmentID.class);
        IProgrammeRepositorySpringData iProgRepo = mock(IProgrammeRepositorySpringData.class);
        IProgrammeMapper iProgMapper = mock(IProgrammeMapper.class);
        IProgrammeIDMapper iProgIdMapper = mock(IProgrammeIDMapper.class);
        ProgrammeRepositorySpringDataImpl progRepo = new ProgrammeRepositorySpringDataImpl(iProgMapper, iProgRepo, iProgIdMapper);
        Programme programme1 = mock(Programme.class);
        Programme programme2 = mock(Programme.class);
        ProgrammeID programmeID1 = mock(ProgrammeID.class);
        ProgrammeID programmeID2 = mock(ProgrammeID.class);
        ProgrammeDataModel dataModel1 = mock(ProgrammeDataModel.class);
        ProgrammeDataModel dataModel2 = mock(ProgrammeDataModel.class);
        when(iProgRepo.findAll()).thenReturn(List.of(dataModel1,dataModel2));
        when(iProgMapper.toDomain(dataModel1)).thenReturn(programme1);
        when(iProgMapper.toDomain(dataModel2)).thenReturn(programme2);
        when(programme1.isInDepartment(departmentID)).thenReturn(true);
        when(programme2.isInDepartment(departmentID)).thenReturn(true);
        when(programme1.identity()).thenReturn(programmeID1);
        when(programme2.identity()).thenReturn(programmeID2);

        //Act
        List<ProgrammeID> programmesWithDepartment= progRepo.findProgrammeByDepartment(departmentID);

        //Assert
        assertTrue(programmesWithDepartment.size()==2);
    }

    @Test
    void shouldReturnEmptyListIfNoProgrammesAreAssociatedToDepartment(){

        //Arrange
        DepartmentID departmentID=mock(DepartmentID.class);
        IProgrammeRepositorySpringData iProgRepo = mock(IProgrammeRepositorySpringData.class);
        IProgrammeMapper iProgMapper = mock(IProgrammeMapper.class);
        IProgrammeIDMapper iProgIdMapper = mock(IProgrammeIDMapper.class);
        ProgrammeRepositorySpringDataImpl progRepo = new ProgrammeRepositorySpringDataImpl(iProgMapper, iProgRepo, iProgIdMapper);
        Programme programme1 = mock(Programme.class);
        Programme programme2 = mock(Programme.class);
        ProgrammeID programmeID1 = mock(ProgrammeID.class);
        ProgrammeID programmeID2 = mock(ProgrammeID.class);
        ProgrammeDataModel dataModel1 = mock(ProgrammeDataModel.class);
        ProgrammeDataModel dataModel2 = mock(ProgrammeDataModel.class);
        when(iProgRepo.findAll()).thenReturn(List.of(dataModel1,dataModel2));
        when(iProgMapper.toDomain(dataModel1)).thenReturn(programme1);
        when(iProgMapper.toDomain(dataModel2)).thenReturn(programme2);
        when(programme1.isInDepartment(departmentID)).thenReturn(false);
        when(programme2.isInDepartment(departmentID)).thenReturn(false);
        when(programme1.identity()).thenReturn(programmeID1);
        when(programme2.identity()).thenReturn(programmeID2);

        //Act
        List<ProgrammeID> programmesWithDepartment= progRepo.findProgrammeByDepartment(departmentID);

        //Assert
        assertTrue(programmesWithDepartment.isEmpty());
    }

    @Test
    void shouldReturnEmptyListIfThereAreNoProgrammesInDatabase(){

        //Arrange
        DepartmentID departmentID=mock(DepartmentID.class);
        IProgrammeRepositorySpringData iProgRepo = mock(IProgrammeRepositorySpringData.class);
        IProgrammeMapper iProgMapper = mock(IProgrammeMapper.class);
        IProgrammeIDMapper iProgIdMapper = mock(IProgrammeIDMapper.class);
        ProgrammeRepositorySpringDataImpl progRepo = new ProgrammeRepositorySpringDataImpl(iProgMapper, iProgRepo, iProgIdMapper);
        Programme programme1 = mock(Programme.class);
        Programme programme2 = mock(Programme.class);
        ProgrammeID programmeID1 = mock(ProgrammeID.class);
        ProgrammeID programmeID2 = mock(ProgrammeID.class);
        when(iProgRepo.findAll()).thenReturn(List.of());
        when(programme1.isInDepartment(departmentID)).thenReturn(true);
        when(programme2.isInDepartment(departmentID)).thenReturn(true);
        when(programme1.identity()).thenReturn(programmeID1);
        when(programme2.identity()).thenReturn(programmeID2);

        //Act
        List<ProgrammeID> programmesWithDepartment= progRepo.findProgrammeByDepartment(departmentID);

        //Assert
        assertTrue(programmesWithDepartment.isEmpty());
    }


}