package QingXiao.entity;

/**
 * Created by xpb on 2018/1/3.
 */
public class TopicComment {
   String commentID ;
    String topicID  ;
    String userID  ;
    String  userName;   //返回评论用户的名字
    int commentType ;//0评论，1回复
    String rootComment ;//comment_type=1时有效
    String commentTime;
    String   objectID ;
    String  toUserID ;
    String  toUserName;   //返回被评论用户的名字
    int likeTimes   ;
    int replyTimes ;
    int shareTimes  ;
    String    content  ;
    String    toContent  ;  //返回被回复的内容
    int     score   ;
    int   status    ;

    public String getCommentID() {
        return commentID;
    }

    public void setCommentID(String commentID) {
        this.commentID = commentID;
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

    public int getCommentType() {
        return commentType;
    }

    public void setCommentType(int commentType) {
        this.commentType = commentType;
    }

    public String getRootComment() {
        return rootComment;
    }

    public void setRootComment(String rootComment) {
        this.rootComment = rootComment;
    }

    public String getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(String commentTime) {
        this.commentTime = commentTime;
    }

    public String getObjectID() {
        return objectID;
    }

    public void setObjectID(String objectID) {
        this.objectID = objectID;
    }

    public String getToUserID() {
        return toUserID;
    }

    public void setToUserID(String toUserID) {
        this.toUserID = toUserID;
    }

    public String getToUserName() {
        return toUserName;
    }

    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
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

    public int getShareTimes() {
        return shareTimes;
    }

    public void setShareTimes(int shareTimes) {
        this.shareTimes = shareTimes;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getToContent() {
        return toContent;
    }

    public void setToContent(String toContent) {
        this.toContent = toContent;
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

    @Override
    public String toString() {
        return "TopicComment{" +
                "commentID='" + commentID + '\'' +
                ", topicID='" + topicID + '\'' +
                ", userID='" + userID + '\'' +
                ", userName='" + userName + '\'' +
                ", commentType=" + commentType +
                ", rootComment='" + rootComment + '\'' +
                ", commentTime='" + commentTime + '\'' +
                ", objectID='" + objectID + '\'' +
                ", toUserID='" + toUserID + '\'' +
                ", toUserName='" + toUserName + '\'' +
                ", likeTimes=" + likeTimes +
                ", replyTimes=" + replyTimes +
                ", shareTimes=" + shareTimes +
                ", content='" + content + '\'' +
                ", toContent='" + toContent + '\'' +
                ", score=" + score +
                ", status=" + status +
                '}';
    }
}
