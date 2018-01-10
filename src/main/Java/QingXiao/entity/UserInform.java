package QingXiao.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by xpb on 2017/10/21.
 */
public class UserInform {
    String  userID;
    String studentID ;
    String realName ;
    String  userName;
    String avatar ;
    String password ;
    String  tel ;
    String email;
    String qq ;
    String sex	;
    String department ;
    String grade ;
    String entranceTime;
    String userIntroduction ;
    Date registerTime ;
    int  status;


    String access_token;
    Date expires_in ;
    String refresh_token ;


    String phoneNum;
    long time_left;

    String avatarStoreName;
    String avatarRealName;


    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public Date getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(Date expires_in) {
        this.expires_in = expires_in;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public long getTime_left() {
        return time_left;
    }

    public void setTime_left(long time_left) {
        this.time_left = time_left;
    }



    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getEntranceTime() {
        return entranceTime;
    }

    public void setEntranceTime(String entranceTime) {
        this.entranceTime = entranceTime;
    }

    public String getUserIntroduction() {
        return userIntroduction;
    }

    public void setUserIntroduction(String userIntroduction) {
        this.userIntroduction = userIntroduction;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getAvatarStoreName() {
        return avatarStoreName;
    }

    public void setAvatarStoreName(String avatarStoreName) {
        this.avatarStoreName = avatarStoreName;
    }

    public String getAvatarRealName() {
        return avatarRealName;
    }

    public void setAvatarRealName(String avatarRealName) {
        this.avatarRealName = avatarRealName;
    }

    @Override
    public String toString() {
        return "UserInform{" +
                "userID='" + userID + '\'' +
                ", studentID='" + studentID + '\'' +
                ", realName='" + realName + '\'' +
                ", userName='" + userName + '\'' +
                ", avatar='" + avatar + '\'' +
                ", password='" + password + '\'' +
                ", tel='" + tel + '\'' +
                ", email='" + email + '\'' +
                ", qq='" + qq + '\'' +
                ", sex='" + sex + '\'' +
                ", department='" + department + '\'' +
                ", grade='" + grade + '\'' +
                ", entranceTime='" + entranceTime + '\'' +
                ", userIntroduction='" + userIntroduction + '\'' +
                ", registerTime=" + registerTime +
                ", status=" + status +
                ", access_token='" + access_token + '\'' +
                ", expires_in=" + expires_in +
                ", refresh_token='" + refresh_token + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", time_left=" + time_left +
                ", avatarStoreName='" + avatarStoreName + '\'' +
                ", avatarRealName='" + avatarRealName + '\'' +
                '}';
    }
}
