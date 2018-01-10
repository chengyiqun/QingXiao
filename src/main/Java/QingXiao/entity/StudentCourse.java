package QingXiao.entity;

import java.util.Date;

/**
 * Created by xpb on 2017/10/24.
 */
public class StudentCourse {
    String studentCourseID  ;
    String teachID  ;
    String userID ;
    Date chooseCourseTime ;

    int score;
    int status ;

    public String getStudentCourseID() {
        return studentCourseID;
    }

    public void setStudentCourseID(String studentCourseID) {
        this.studentCourseID = studentCourseID;
    }

    public String getTeachID() {
        return teachID;
    }

    public void setTeachID(String teachID) {
        this.teachID = teachID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public Date getChooseCourseTime() {
        return chooseCourseTime;
    }

    public void setChooseCourseTime(Date chooseCourseTime) {
        this.chooseCourseTime = chooseCourseTime;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
