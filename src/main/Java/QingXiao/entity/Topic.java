package QingXiao.entity;

import java.util.List;

/**
 * Created by xpb on 2018/1/3.
 */
public class Topic {
    String topicID;
    String userID;

    String avatarStoreName;
    String userName;  // 添加的字段
    String subjectID;
    String topicTime;
    String content;
    String topicPlace;
    int commentTimes;
    int likeTimes;
    int shareTimes;
    int  browseTimes;
    int dontMaskStranger;
    int status;
   private List<TopicComment>  topicCommentList;
   private List<TopicImage> topicImageList;

    public String getAvatarStoreName() {
        return avatarStoreName;
    }

    public void setAvatarStoreName(String avatarStoreName) {
        this.avatarStoreName = avatarStoreName;
    }

    public String getTopicID() {
        return topicID;
    }

    public void setTopicID(String topicID) {
        this.topicID = topicID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(String subjectID) {
        this.subjectID = subjectID;
    }

    public String getTopicTime() {
        return topicTime;
    }

    public void setTopicTime(String topicTime) {
        this.topicTime = topicTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTopicPlace() {
        return topicPlace;
    }

    public void setTopicPlace(String topicPlace) {
        this.topicPlace = topicPlace;
    }

    public int getCommentTimes() {
        return commentTimes;
    }

    public void setCommentTimes(int commentTimes) {
        this.commentTimes = commentTimes;
    }

    public int getLikeTimes() {
        return likeTimes;
    }

    public void setLikeTimes(int likeTimes) {
        this.likeTimes = likeTimes;
    }

    public int getShareTimes() {
        return shareTimes;
    }

    public void setShareTimes(int shareTimes) {
        this.shareTimes = shareTimes;
    }

    public int getBrowseTimes() {
        return browseTimes;
    }

    public void setBrowseTimes(int browseTimes) {
        this.browseTimes = browseTimes;
    }

    public int getDontMaskStranger() {
        return dontMaskStranger;
    }

    public void setDontMaskStranger(int dontMaskStranger) {
        this.dontMaskStranger = dontMaskStranger;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<TopicComment> getTopicCommentList() {
        return topicCommentList;
    }

    public void setTopicCommentList(List<TopicComment> topicCommentList) {
        this.topicCommentList = topicCommentList;
    }

    public List<TopicImage> getTopicImageList() {
        return topicImageList;
    }

    public void setTopicImageList(List<TopicImage> topicImageList) {
        this.topicImageList = topicImageList;
    }

    @Override
    public String toString() {
        return "Topic{" +
                "topicID='" + topicID + '\'' +
                ", userID='" + userID + '\'' +
                ", avatarStoreName='" + avatarStoreName + '\'' +
                ", userName='" + userName + '\'' +
                ", subjectID='" + subjectID + '\'' +
                ", topicTime='" + topicTime + '\'' +
                ", content='" + content + '\'' +
                ", topicPlace='" + topicPlace + '\'' +
                ", commentTimes=" + commentTimes +
                ", likeTimes=" + likeTimes +
                ", shareTimes=" + shareTimes +
                ", browseTimes=" + browseTimes +
                ", dontMaskStranger=" + dontMaskStranger +
                ", status=" + status +
                ", topicCommentList=" + topicCommentList.toString() +
                ", topicImageList=" + topicImageList.toString() +
                '}';
    }
}
