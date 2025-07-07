package PAI.factory;

import PAI.domain.ProgrammeEnrolment;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProgrammeEnrolmentListFactoryImplTest {

    @Test
    void shouldCreateArrayList() {
        //arrange
        IProgrammeEnrolmentListFactory programmeEnrolmentListFactory = new ProgrammeEnrolmentListFactoryImpl();

        //act
        List<ProgrammeEnrolment> programmeEnrolmentList = programmeEnrolmentListFactory.newArrayList();

        //assert
        assertNotNull(programmeEnrolmentList);
    }

}