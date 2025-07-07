package PAI.domain;

import PAI.VOs.*;
import PAI.ddd.AggregateRoot;


public class TeacherCareerProgression implements AggregateRoot<TeacherCareerProgressionID> {

    private TeacherCareerProgressionID _tcpID;
    private Date _date;
    private TeacherCategoryID _teacherCategoryID;
    private WorkingPercentage _workingPercentage;
    private TeacherID _teacherID;

    //constructor
    public TeacherCareerProgression (TeacherCareerProgressionID teacherCareerProgressionId, Date date, TeacherCategoryID teacherCategoryID, WorkingPercentage workingPercentage, TeacherID teacherID) throws IllegalArgumentException {

        if(teacherCareerProgressionId == null)
            throw new IllegalArgumentException("Teacher Career Progression Id cannot be null!");

        _tcpID = teacherCareerProgressionId;

        if(date == null)
            throw new IllegalArgumentException("Date cannot be null!");

        _date = date;

        if(teacherCategoryID == null)
            throw new IllegalArgumentException("Teacher Category cannot be null!");

        this._teacherCategoryID = teacherCategoryID;

        if(workingPercentage == null)
            throw new IllegalArgumentException("Working Percentage cannot be null!");

        _workingPercentage = workingPercentage;

        if(teacherID == null)
            throw new IllegalArgumentException("Teacher ID cannot be null!");

        _teacherID = teacherID;
    }

    public TeacherCareerProgressionID getID () {
        return _tcpID;
    }

    public TeacherCategoryID getTeacherCategoryID () {

        return _teacherCategoryID;
    }

    public WorkingPercentage getWorkingPercentage () {

        return _workingPercentage;
    }

    public Date getDate() {

        return _date;
    }

    public TeacherID getTeacherID() {

        return _teacherID;
    }

    public boolean isLastDateEqualOrBeforeNewDate(Date newDate) {

        return !_date.getLocalDate().isAfter(newDate.getLocalDate());
    }

    public boolean isDateAfter (TeacherCareerProgression TCP) {

        return _date.getLocalDate().isAfter(TCP.getDate().getLocalDate());
    }

    @Override
    public TeacherCareerProgressionID identity() {
        return _tcpID;
    }

    @Override
    public boolean sameAs(Object object) {

        if(object instanceof TeacherCareerProgression) {

            TeacherCareerProgression tcp = (TeacherCareerProgression) object;

            if (_teacherID.equals(tcp._teacherID) && _date.equals(tcp._date)
                    && _workingPercentage.equals(tcp._workingPercentage) && _teacherCategoryID.equals(tcp._teacherCategoryID))
                return true;
        }

        return false;
    }

}
