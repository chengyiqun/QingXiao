package QingXiao.entity;

import java.util.Date;

/**
 * Created by xpb on 2017/10/24.
 */
public class TeacherInform {

    String teacherID ;
    String employeeNumber;
    String teacherName ;
    String department ;
    String sex  ;
    Date bornDate ;
    String teacherEmail ;
    String teacherTel ;
    String office ;
    String academicTitle ;
    Date inductionTime ;
    String briefIntroduction ;
    int status ;

    public String getTeacherID() {
        return teacherID;
    }

    public void setTeacherID(String teacherID) {
        this.teacherID = teacherID;
    }

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getBornDate() {
        return bornDate;
    }

    public void setBornDate(Date bornDate) {
        this.bornDate = bornDate;
    }

    public String getTeacherEmail() {
        return teacherEmail;
    }

    public void setTeacherEmail(String teacherEmail) {
        this.teacherEmail = teacherEmail;
    }

    public String getTeacherTel() {
        return teacherTel;
    }

    public void setTeacherTel(String teacherTel) {
        this.teacherTel = teacherTel;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public String getAcademicTitle() {
        return academicTitle;
    }

    public void setAcademicTitle(String academicTitle) {
        this.academicTitle = academicTitle;
    }

    public Date getInductionTime() {
        return inductionTime;
    }

    public void setInductionTime(Date inductionTime) {
        this.inductionTime = inductionTime;
    }

    public String getBriefIntroduction() {
        return briefIntroduction;
    }

    public void setBriefIntroduction(String briefIntroduction) {
        this.briefIntroduction = briefIntroduction;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}
