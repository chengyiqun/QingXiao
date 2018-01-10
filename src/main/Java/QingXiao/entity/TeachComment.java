package QingXiao.entity;

public class TeachComment {


	  String teachCommentID;
	  String teachID;
	  String userID ;
	  int  score ;
	  int commentTimes;
	  String content ;
	  String commentTime;
	  int isAnonymous;
	  int isEffective ;
	  int status ;

	public String getTeachCommentID() {
		return teachCommentID;
	}

	public void setTeachCommentID(String teachCommentID) {
		this.teachCommentID = teachCommentID;
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

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getCommentTimes() {
		return commentTimes;
	}

	public void setCommentTimes(int commentTimes) {
		this.commentTimes = commentTimes;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCommentTime() {
		return commentTime;
	}

	public void setCommentTime(String commentTime) {
		this.commentTime = commentTime;
	}

	public int getIsAnonymous() {
		return isAnonymous;
	}

	public void setIsAnonymous(int isAnonymous) {
		this.isAnonymous = isAnonymous;
	}

	public int getIsEffective() {
		return isEffective;
	}

	public void setIsEffective(int isEffective) {
		this.isEffective = isEffective;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}
