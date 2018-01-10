package QingXiao.entity;

/**
 * Created by xpb on 2018/1/3.
 */
public class TopicImage {
    String imageID;
    String topicID;
    int picOrder;
    String fileName;
    String imageStoreName;
    String imageDescribe;
    String imagePath;
    int imageType;
    int status;

    public String getImageID() {
        return imageID;
    }

    public void setImageID(String imageID) {
        this.imageID = imageID;
    }

    public String getTopicID() {
        return topicID;
    }

    public void setTopicID(String topicID) {
        this.topicID = topicID;
    }

    public int getPicOrder() {
        return picOrder;
    }

    public void setPicOrder(int picOrder) {
        this.picOrder = picOrder;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getImageStoreName() {
        return imageStoreName;
    }

    public void setImageStoreName(String imageStoreName) {
        this.imageStoreName = imageStoreName;
    }

    public String getImageDescribe() {
        return imageDescribe;
    }

    public void setImageDescribe(String imageDescribe) {
        this.imageDescribe = imageDescribe;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public int getImageType() {
        return imageType;
    }

    public void setImageType(int imageType) {
        this.imageType = imageType;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "TopicImage{" +
                "imageID='" + imageID + '\'' +
                ", topicID='" + topicID + '\'' +
                ", picOrder=" + picOrder +
                ", fileName='" + fileName + '\'' +
                ", imageStoreName='" + imageStoreName + '\'' +
                ", imageDescribe='" + imageDescribe + '\'' +
                ", imagePath='" + imagePath + '\'' +
                ", imageType=" + imageType +
                ", status=" + status +
                '}';
    }
}
