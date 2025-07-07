package PAI.persistence.datamodel;


import PAI.VOs.CourseEditionID;
import PAI.persistence.datamodel.courseEdition.CourseEditionIDDataModel;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name="StudentGrade")
public class StudentGradeDM {

 @EmbeddedId
 private StudentGradeIDDataModel _studentGradeIDDataModel;

 @Column(name = "Grade")
 private double _grade;

 @Column(name = "Date")
 private LocalDate _date;

 public StudentGradeDM(StudentGradeIDDataModel studentGradeIDDataModel, double grade, LocalDate date) {
  this._studentGradeIDDataModel = studentGradeIDDataModel;
  this._grade = grade;
  this._date = date;
 }

 public StudentGradeDM() {
 }

 public StudentGradeIDDataModel getId() {
  return _studentGradeIDDataModel;
 }

 public double get_grade() {
  return _grade;
 }

 public LocalDate get_date() {
  return _date;
 }

 // Access student and course data through ID
 public StudentIDDataModel getStudentId() {
  return _studentGradeIDDataModel.get_studentIDDataModel();
 }

 public CourseEditionIDDataModel getCourseEditionID() {
  return _studentGradeIDDataModel.get_courseEditionIDDataModel();
 }
}
