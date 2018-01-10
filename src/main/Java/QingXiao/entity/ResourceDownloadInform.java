package QingXiao.entity;

public class ResourceDownloadInform {
	String resourceDownloadID;
	String   courseResourceID ;
	String   userID;
	String   downloadTime ;
	int  status;

	public String getResourceDownloadID() {
		return resourceDownloadID;
	}

	public void setResourceDownloadID(String resourceDownloadID) {
		this.resourceDownloadID = resourceDownloadID;
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

	public String getDownloadTime() {
		return downloadTime;
	}

	public void setDownloadTime(String downloadTime) {
		this.downloadTime = downloadTime;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}
