package QingXiao.entity;

import java.util.List;

public class CourseResource {
	String courseResourceID;
	String userID;
	String   courseID;
	String   resourceNumber;
	String   uploadTime;
	String   fileName;
	String resourceStoreName ;
	String  resourceType;
	String  resourceDescribe;
	String  resourcePath;
	 int comprehensiveScore;
	 int likeTimes;
	 int  commentTimes;
	 int  downloadTimes ;
	 int  shareTimes;
	 int costPoints;
	 int  costPointsSum;
	 int privileges;
	 int   status ;
	private List<TopicComment> topicCommentList;

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

	public String getCourseID() {
		return courseID;
	}

	public void setCourseID(String courseID) {
		this.courseID = courseID;
	}

	public String getResourceNumber() {
		return resourceNumber;
	}

	public void setResourceNumber(String resourceNumber) {
		this.resourceNumber = resourceNumber;
	}

	public String getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(String uploadTime) {
		this.uploadTime = uploadTime;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getResourceStoreName() {
		return resourceStoreName;
	}

	public void setResourceStoreName(String resourceStoreName) {
		this.resourceStoreName = resourceStoreName;
	}

	public String getResourceType() {
		return resourceType;
	}

	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}

	public String getResourceDescribe() {
		return resourceDescribe;
	}

	public void setResourceDescribe(String resourceDescribe) {
		this.resourceDescribe = resourceDescribe;
	}

	public String getResourcePath() {
		return resourcePath;
	}

	public void setResourcePath(String resourcePath) {
		this.resourcePath = resourcePath;
	}

	public int getComprehensiveScore() {
		return comprehensiveScore;
	}

	public void setComprehensiveScore(int comprehensiveScore) {
		this.comprehensiveScore = comprehensiveScore;
	}

	public int getLikeTimes() {
		return likeTimes;
	}

	public void setLikeTimes(int likeTimes) {
		this.likeTimes = likeTimes;
	}

	public int getCommentTimes() {
		return commentTimes;
	}

	public void setCommentTimes(int commentTimes) {
		this.commentTimes = commentTimes;
	}

	public int getDownloadTimes() {
		return downloadTimes;
	}

	public void setDownloadTimes(int downloadTimes) {
		this.downloadTimes = downloadTimes;
	}

	public int getShareTimes() {
		return shareTimes;
	}

	public void setShareTimes(int shareTimes) {
		this.shareTimes = shareTimes;
	}

	public int getCostPoints() {
		return costPoints;
	}

	public void setCostPoints(int costPoints) {
		this.costPoints = costPoints;
	}

	public int getCostPointsSum() {
		return costPointsSum;
	}

	public void setCostPointsSum(int costPointsSum) {
		this.costPointsSum = costPointsSum;
	}

	public int getPrivileges() {
		return privileges;
	}

	public void setPrivileges(int privileges) {
		this.privileges = privileges;
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

	@Override
	public String toString() {
		return "CourseResource{" +
				"courseResourceID='" + courseResourceID + '\'' +
				", userID='" + userID + '\'' +
				", courseID='" + courseID + '\'' +
				", resourceNumber='" + resourceNumber + '\'' +
				", uploadTime='" + uploadTime + '\'' +
				", fileName='" + fileName + '\'' +
				", resourceStoreName='" + resourceStoreName + '\'' +
				", resourceType='" + resourceType + '\'' +
				", resourceDescribe='" + resourceDescribe + '\'' +
				", resourcePath='" + resourcePath + '\'' +
				", comprehensiveScore=" + comprehensiveScore +
				", likeTimes=" + likeTimes +
				", commentTimes=" + commentTimes +
				", downloadTimes=" + downloadTimes +
				", shareTimes=" + shareTimes +
				", costPoints=" + costPoints +
				", costPointsSum=" + costPointsSum +
				", privileges=" + privileges +
				", status=" + status +
				", topicCommentList=" + topicCommentList +
				'}';
	}
}
