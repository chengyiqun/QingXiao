package QingXiao.entity;

public class ResourceComment {

	String resourceCommentID ;
	String   courseResourceID ;
	String   userID;
	String   commentTime;
	String  content;
	int  score ;
	int  status ;
	private UserInform userInform;

	public String getResourceCommentID() {
		return resourceCommentID;
	}

	public void setResourceCommentID(String resourceCommentID) {
		this.resourceCommentID = resourceCommentID;
	}

	public String getCourseResourceID() {
		return courseResourceID;
	}

	public void setCourseResourceID(String courseResourceID) {
		this.courseResourceID = courseResourceID;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getCommentTime() {
		return commentTime;
	}

	public void setCommentTime(String commentTime) {
		this.commentTime = commentTime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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

	public UserInform getUserInform() {
		return userInform;
	}

	public void setUserInform(UserInform userInform) {
		this.userInform = userInform;
	}

	@Override
	public String toString() {
		return "ResourceComment{" +
				"resourceCommentID='" + resourceCommentID + '\'' +
				", courseResourceID='" + courseResourceID + '\'' +
				", userID='" + userID + '\'' +
				", commentTime='" + commentTime + '\'' +
				", content='" + content + '\'' +
				", score=" + score +
				", status=" + status +
				", userInform=" + userInform +
				'}';
	}
}
