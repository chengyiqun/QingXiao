package QingXiao.entity;

/**
 * Created by xpb on 2017/10/24.
 */
public class CourseInform {


    String courseID ;
    String courseCode;
    String courseName ;
    String courseNature ;
    int credit ;
    int classHours;
    int status ;

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseNature() {
        return courseNature;
    }

    public void setCourseNature(String courseNature) {
        this.courseNature = courseNature;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public int getClassHours() {
        return classHours;
    }

    public void setClassHours(int classHours) {
        this.classHours = classHours;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
