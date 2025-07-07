package PAI.repository.studyPlanRepository;

import PAI.domain.studyPlan.StudyPlan;

import java.util.ArrayList;
import java.util.List;


public class StudyPlanListFactoryImpl implements IStudyPlanListFactory {

    public List<StudyPlan> newArrayList() {
        return new ArrayList<>();
    }

    
}