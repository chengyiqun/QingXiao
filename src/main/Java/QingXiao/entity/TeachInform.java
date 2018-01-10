package QingXiao.entity;

import java.util.Date;

/**
 * Created by xpb on 2017/10/24.
 */
public class TeachInform {
    String teachID  ;
    String teacherID;
    String courseID ;
    int comprehensiveScore ;
    String teachTime ;

    Date startDate ;
    String commentTimes ;
    int status ;

    private int startYear;//学年开始年
    private int endYear;//学年结束年
    private int semester;//学期
    // private String courseName;//课程名
    //private String courseTime;//课程时间，冗余字段
    private String classsroom;//教室
    //private String teacher;//老师
    private int dayOfWeek;//星期几
    private int startSection;//第几节课开始
    private int endSection;//第几节课结束
    private int startWeek;//开始周
    private int endWeek;//结束周
    private int everyWeek;//标记是否是单双周，0为每周,1单周，2双周
    private int sameTime;//标记同一时间段是否有不同的课

    public String getTeachID() {
        return teachID;
    }

    public void setTeachID(String teachID) {
        this.teachID = teachID;
    }

    public String getTeacherID() {
        return teacherID;
    }

    public void setTeacherID(String teacherID) {
        this.teacherID = teacherID;
    }

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public int getComprehensiveScore() {
        return comprehensiveScore;
    }

    public void setComprehensiveScore(int comprehensiveScore) {
        this.comprehensiveScore = comprehensiveScore;
    }

    public String getTeachTime() {
        return teachTime;
    }

    public void setTeachTime(String teachTime) {
        this.teachTime = teachTime;
    }



    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getCommentTimes() {
        return commentTimes;
    }

    public void setCommentTimes(String commentTimes) {
        this.commentTimes = commentTimes;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getStartYear() {
        return startYear;
    }

    public void setStartYear(int startYear) {
        this.startYear = startYear;
    }

    public int getEndYear() {
        return endYear;
    }

    public void setEndYear(int endYear) {
        this.endYear = endYear;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public String getClasssroom() {
        return classsroom;
    }

    public void setClasssroom(String classsroom) {
        this.classsroom = classsroom;
    }

    public int getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(int dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public int getStartSection() {
        return startSection;
    }

    public void setStartSection(int startSection) {
        this.startSection = startSection;
    }

    public int getEndSection() {
        return endSection;
    }

    public void setEndSection(int endSection) {
        this.endSection = endSection;
    }

    public int getStartWeek() {
        return startWeek;
    }

    public void setStartWeek(int startWeek) {
        this.startWeek = startWeek;
    }

    public int getEndWeek() {
        return endWeek;
    }

    public void setEndWeek(int endWeek) {
        this.endWeek = endWeek;
    }

    public int getEveryWeek() {
        return everyWeek;
    }

    public void setEveryWeek(int everyWeek) {
        this.everyWeek = everyWeek;
    }

    public int getSameTime() {
        return sameTime;
    }

    public void setSameTime(int sameTime) {
        this.sameTime = sameTime;
    }
}
