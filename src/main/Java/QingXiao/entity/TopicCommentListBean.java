package QingXiao.entity;

public class TopicCommentListBean {
    @Override
    public String toString() {
        return "TopicCommentListBean{" +
                "commentID='" + commentID + '\'' +
                ", commentTime='" + commentTime + '\'' +
                ", commentType=" + commentType +
                ", likeTimes=" + likeTimes +
                ", replyTimes=" + replyTimes +
                ", content=" + content +
                ", shareTimes=" + shareTimes +
                ", status=" + status +
                ", topicID='" + topicID + '\'' +
                ", userID='" + userID + '\'' +
                ", avatarStoreName='" + avatarStoreName + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }

    /**
     * commentID : 186e8a35c1f047b8a065ed2eb95aabff
     * commentTime : 2018-05-27 05:34:13
     * commentType : 0
     * likeTimes : 0
     * replyTimes : 0
     * content : 0
     * shareTimes : 0
     * status : 0
     * topicID : 086e8a35c1f047b8a065ed2eb95aabff
     * userID : 00b18f51a2434a27913d9b03c2d02011
     * userName : 测试
     */

    private String commentID;
    private String commentTime;
    private int commentType;
    private int likeTimes;
    private int replyTimes;
    private String content;
    private int shareTimes;
    private int status;
    private String topicID;
    private String userID;
    private String avatarStoreName;
    private String userName;

    public String getAvatarStoreName() {
        return avatarStoreName;
    }

    public void setAvatarStoreName(String avatarStoreName) {
        this.avatarStoreName = avatarStoreName;
    }

    public String getCommentID() {
        return commentID;
    }

    public void setCommentID(String commentID) {
        this.commentID = commentID;
    }

    public String getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(String commentTime) {
        this.commentTime = commentTime;
    }

    public int getCommentType() {
        return commentType;
    }

    public void setCommentType(int commentType) {
        this.commentType = commentType;
    }

    public int getLikeTimes() {
        return likeTimes;
    }

    public void setLikeTimes(int likeTimes) {
        this.likeTimes = likeTimes;
    }

    public int getReplyTimes() {
        return replyTimes;
    }

    public void setReplyTimes(int replyTimes) {
        this.replyTimes = replyTimes;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getShareTimes() {
        return shareTimes;
    }

    public void setShareTimes(int shareTimes) {
        this.shareTimes = shareTimes;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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
}