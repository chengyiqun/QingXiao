##API设计文档

#####一、用户登录注册相关

######1. 注册API
```
method= POST
Url:  http://60.205.218.103:80/QingXiao/User/Register
Requert:  header:  (1)accessToken (2)userName
         Body:    (1)json: {'password':'123456','userName':'admin22','phoneNum':'18256914858','identifyCode':'4728','zone':'86'}

Rsponse:   {“reslut”:” ”}  

public  static int  REGISTER_SUCCESS = 2001;

public  static int  IDENTIFYCODE_ERROR = 2002;

public  static int  USER_EXISTED = 2003;

public  static int   USER_ILLEGAL=2004;

验证码验证结果：{"status":468,"error":"Illegal check request."}

验证码验证结果：{"status":200}

结果为{"result":2000}
测试用例： 用户名：18256914858  密码：ab123456

限制：登录密码长度限制：长度为6-16个字符；不能包含空格；不能是9位以下纯数字

```

######2. 登录API  
```
method= POST

Url： http://60.205.218.103:80/QingXiao/User/Login

Requert:  header:  (1)accessToken (2)userName
           Body:    (1)jsonString = "{'phoneNum':'123456','passWord':'admin22'}";
  备注: 以后可以加上设备类型。

Rsponse:  header:  (1)accessToken
Body:  (1) {result=3001, userIntroduction=null, sex=null, avatar=null, userName=admin1}

 用户表的基本信息字段

public  static int  LOGIN_SUCCESS  = 3001; 
public  static int  PASSWORD_ERROR = 3002; 
public  static int  USER_NOTEXIST  = 3003;
```


######3. 更换头像API
```
method= POST
Url：http://60.205.218.103:80/QingXiao/User/updateAvatar

Requert:  header:  (1)accessToken (2)userName
         Body:    (1)头像图片文件  

Rsponse:   {“reslut”:” ”}  

public  static int  TOKEN_ERROR  =3004;  
public  static int  UPDATE_AVATAR_SUCCESS =3005;
```


######4. 修改昵称api
```
method=POST
URI：http://60.205.218.103:80/QingXiao/User/changeName

Requert:  header:  (1)accessToken (2)username

         body: newName= URLEncoder.encode(newName,"UTF-8")

Rsponse:   {“reslut”:” ”}  

public  static int  TOKEN_ERROR =3004;
public  static int CHANGE _ NAME _SUCCESS =3007;
public  static int  NAME_EXISTS =3008;
```


######5. 更改密码API
```
可以进一步讨论使用手机接收短信更改，还是用旧密码更改。
method= POST

Url： [<u>http://60.205.218.103:80/QingXiao/User/updatePassword</u>](http://60.205.218.103:80/QingXiao/User/Register)

Requert:  header:  (1)accessToken (2)userName
         Body:    (1)json:

jsonString = "{'oldPassword':'123456','newPassWord':'admin22'," + "'phoneNum':'18256914858','identifyCode':'4131','zone':'86'}" ;

Rsponse:   {“reslut”:” ”}  

public  static int UPDATE_PASSWORD_SUCCESS =3040;  
public  static int TOKEN_ERROR =3004;  

```


######6. 忘记密码API
```
可以进一步讨论使用手机接收短信更改，还是用旧密码更改。

method= POST

Url： http://60.205.218.103:80/QingXiao/User/updatePassword

Requert:  header:  (1)accessToken (2)userName
         Body:    (1)json:

jsonString = "{'oldPassword':'123456','newPassWord':'admin22'," + "'phoneNum':'18256914858','identifyCode':'4131','zone':'86'}" ;

Rsponse:   {“reslut”:” ”}  

public  static int  FIND_PASSWORD_SUCCESS =3050;
public  static int  TOKEN_ERROR  =3004;
```


######7. 下载头像API （确定）
```
method= POST
Url： http://60.205.218.103:80/QingXiao/User/DownloadAvatar

Requert:  header:  (1)accessToken (2)userName
         Body:    (1)json:

jsonString={'avatar':'D:\Users\xpb\IdeaProjects\QingXiao\target\test-classes\avatar\30509637d5b04313be2e10ff37c0992e.jpg'}"

Rsponse:   header:  (1)accessToken (2)userName
          Body:    (1)字节流解析即为文件

public  static int    DOWNLOAD_AVATAR_SUCCESS  =3060; 
public  static int   TOKEN_ERROR  =  3004;
```


#####二、课程相关

######1. 获取教务处验证码  （功能已实现）
```
method= GET
Url： http://127.0.0.1:8080/QingXiao/Course/GetIdentifyCode

Requert:  header:  (1)accessToken (2)userName
         Body:    无

Rsponse:  heaer:  (1)
Body:  (1) responseBody Bitmap二进制流
```


######2. 获取课表
```
Method=POST
Url： http://127.0.0.1:8080/QingXiao/Course/GetCourse
Requert:  header:  (1)accessToken (2)userName
         Body:    无

Rsponse:  heaer:  (1)
Body:  {"result":3202,"courses":[{"startWeek":1,"teachID":"dce1ba26ebfe48d58246b05b1378f484","teacherName":"方贤勇","startYear":2017,"sameTime":0,"endYear":2018,"startSection":1,"courseName":"网络原理实验","dayOfWeek":2,"endSection":4,"everyWeek":1,"semester":2,"id":0,"endWeek":17,"classsroom":"计算机与网络应用实验教学中心","courseID":"69d6e8b628fe453a84500a4b88a7ac25"},{"startWeek":1,"teachID":"c03d6300b79e4bc982b4ed8d6a64aa95","teacherName":"方贤勇","startYear":2017,"sameTime":0,"endYear":2018,"startSection":1,"courseName":"网络原理","dayOfWeek":3,"endSection":3,"everyWeek":0,"semester":2,"id":1,"endWeek":17,"classsroom":"博学北楼B110","courseID":"2a42e203cd784ef4a6e7a8c9546bb562"}]

public  static int UPLOAD_COURSE _SUCCESS = 3201;

public  static int  COURSE_EXISTED=3202;  
public  static int  USER_NOTEXIST*=3003; 
public  static int  TOKEN_ERROR=3004;
public  static int  LOGIN_ERROR=3005;
public  static int   IDENTIFY_CODE_ERROR=3006;
```


######3. 上传教学实体评价信息  （功能已实现）
```
method= POST

Url： http://60.205.218.103:80/QingXiao/TeachComment/Insert

Requert:  header:  (1)accessToken (2)userName

         Body:    (1)private   String  private   String jsonStringInsertTeachComment0   =  {'teachID':'dce1ba26ebfe48d58246b05b1378f484','commentType':0,'score':5,'content':'这是评论这是评论4','isAnonymous':0} 

 jsonStringInsertTeachComment1 =  {'teachID':'dce1ba26ebfe48d58246b05b1378f484','commentType':1,'objectID':'fda3327fb78747d0ab40204435477c3e','content':'这是评论这是回复111','isAnonymous':0,'status':0} 

Rsponse:   header:  (1)
Body:  (1) {result=3221}

public  static int INSERT_COMMENT_SUCCESS  =3221;
public  static int  TEACH_NOTEXIST =3222;
public  static int  COURSE_NOTEXIST =3223;
public  static int  TEACHER_NOTEXIST  =3224; 
public  static int  STUDENTCOURSE_NOTEXIST = 3225;
public  static int TOKEN_ERROR = 3004;  

```

######4. 获取教学实体评价信息
```
最早commentTime 2018-05-13 17:51:39.004
method= POST
Url:  http://60.205.218.103:80/QingXiao/TeachComment/GetCommentList

Requert:  header:  (1)accessToken (2)userName
         Body:    (1)private String jsonStringInsertCourse = {'teachID':'dce1ba26ebfe48d58246b05b1378f484','sinceTime':'2018-05-13 17:51:39.004','nowTime':'2018-05-13 20:00:00.004'} 

Rsponse:  heaer:  (1)
Body:  (1) [{result=3001},{}]
```


######5. 获取教学实体评价的回复
```
Method= POST
URL:http://127.0.0.1:8080/QingXiao/TeachComment/GetCommentReplyList

Requert:  header:  (1)accessToken (2)userName
         Body:    (1) private   String   jsonStringInsertCourse  =  
 {'teachCommentID':'4dd444c89a3c4433b97c1d9342e97f03','sinceTime':'2018-05-13 17:51:39.004','nowTime':'2018-05-14 21:35:00.004'}  

Rsponse:  heaer:  (1)
Body:  (1) [{result=3001},{}]
```


######6. 查询某个老师的评价
```

```
###三. 资源相关

######1. 上传课程资源  （功能已实现）
```
method= POST

Url： http://60.205.218.103:80/QingXiao/CourseResource/Upload
Requert:  header:  (1)accessToken (2)userName

         Body:    (1) private   String  jsonStringUploadCourseResource = "{'resourceDescribe':'考试试卷','courseName':'软件工程课程设计','resourceType': ’ ’ }" ;

MultipartBody.Part mbody = MultipartBody.Part.createFormData("documents",file.getName(),frbody);

 resourceDescribe:考试试卷,课后答案，学习视频，电子教材， 

Rsponse:  heaer:  (1)
Body:  (1){"result":3301}

public  static int UPLOAD_COURSEResource_SUCCESS= 3301;

public  static int  COURSE_NOTEXIST = 3223; 
public  static int  USER_NOTEXIST  = 3003; 
public  static int  TOKEN_ERROR = 3004;
public  static int  JSON_NULL_ERROR  =3302;

```

######2. 下载课程资源  （功能已实现）
```
method= GET
Url:http://60.205.218.103:80/QingXiao/CourseResource/Download

resourceStoreName=’courseResource\727617f18046408493bc9a383532484b.jpg’

Requert:  header:  (1)accessToken (2)userName
         Body:    (1)
request.setParameter( "resourceStoreName" ,"e4f94e875a874f749ee3be1ef374c976.jpg" );

Rsponse:  header:  (1)
Body:  (1) {}
返回字符流

public  static int  DOWNLOAD_COURSE_RESOURCE_COURSE _SUCCESS =3201;
public  static int COURSE_RESOURCE_NOT_EXIST=3302;  
public  static int  USER_NOT_EXIST =3003; 
public  static int  TOKEN_ERROR =3004;
```


######3. 获取课程的资源信息列表  （功能已实现）
```
method= POST
Url：http://60.205.218.103:80/QingXiao/CourseResource/GetList

Requert:  header:  (1)accessToken (2)userName
         Body:    (1) private  String  jsonStringGetCourseResourceList = "{'courseName':'软件工程课程设计','sinceTime':'2017-10-26 22:02:25','nowTime':'2017-10-27 20:52:46.517'}" ;

Rsponse:  header:  (1)
Body:  (1) [{fileName=5.jpg, resourceStoreName=e4f94e875a874f749ee3be1ef374c976.jpg, downloadTimes=0, resourceDescribe=考试试卷, comprehensiveScore=90, commentTimes=0, uploadTime=2017-10-27 12:08:13.661}, {fileName=5.jpg, resourceStoreName=, resourceDescribe=考试试卷, uploadTime=2017-10-26 22:02:25.494}]

public  static int UPLOAD_COURSE_SUCCESS =3201;
public  static int COURSE _EXISTED =3202; 
public  static int  USER_NOTEXIST =3003;  
public  static int  TOKEN_ERROR =3004;

```

######4. 通过课程名获取资源信息列表（暂时与3同）
```
method= POST
Url:http://60.205.218.103:80/QingXiao/CourseResource/QueryByCourseName

Requert:  header:  (1)accessToken (2)userName
         Body:    (1) private String jsonStringGetCourseResourceList =  "{'courseName':'软件工程课程设计'}";

Rsponse:  heaer:  (1)

Body:  (1) {result=3001}

public  static int UPLOAD_COURSE_SUCCESS=3201;

public  static int  COURSE_EXISTED=3202;  
public  static int  USER_NOTEXIST =3003;  
public  static int  TOKEN_ERROR =3004;
```


######5. 上传课程资源评价信息   （功能已实现）
```
method= POST
Url： http://60.205.218.103:80/QingXiao/ResourceComment/Insert
Requert:  header:  (1)accessToken (2)userName
         Body:    (1) private  String  jsonStringInsertResourceComment = "{'courseResourceID':'2345fa494648403f8c4f064ac3ecc435','score':90,'content':'很实用！！！','commentType':0}" ;

Rsponse:  header:  (1)
Body:  (1) {result=3351}

public  static int UPLOAD_RESOURCE_COMMENT_SUCCESS=3351;  
public  static int  COURSE_RESOURCE_NOTEXIST =3352;  
public  static int  TOKEN_ERROR=3004;

```

######6. 获取课程资源评价信息 （功能已实现）
```
method= POST
Url: http://60.205.218.103:80/QingXiao/ResourceComment/GetCommentList

Requert:  header:  (1)accessToken (2)userName
         Body:    (1) private  String  jsonStringGetResourceCommentList = "{' courseResourceID':'f2eafd3fd35e447499ab6d7fd23fb90d.jpg','sinceTime':'2017-10-26 22:02:25','nowTime':'2017-10-29 20:52:46.517'}" ;

Rsponse:  header:  (1)
Body:  (1) result：[{"commentTimes":0,"comprehensiveScore":0,"costPoints":0,"costPointsSum":0,"courseID":"226b3e06b5024715809157f1ca4318c4","downloadTimes":0,"likeTimes":0,"privileges":0,"resourcePath":"D:\\Users\\xpb\\IdeaProjects\\QingXiao\\target\\test-classes\\courseResource\\bd092aae9dc94ae096616699cd7da456.jpg","shareTimes":0,"status":0,"topicCommentList":[{"commentID":"b09d9dd695c24ff1974d66c07aa5c93e","commentTime":"2018-01-04 20:25:40.0","commentType":0,"content":"很实用！！！","likeTimes":0,"replyTimes":0,"score":95,"shareTimes":0,"status":0,"toContent":"根评论！！！","toUserName":"admin1","topicID":"2345fa494648403f8c4f064ac3ecc435","userID":"8e59adf6f18045b29bccb02bea96e933","userName":"admin1"}],"userID":"8e59adf6f18045b29bccb02bea96e933"}]

public  static int UPLOAD_COURSE_SUCCESS=3201;
public  static int COURSE _EXISTED =3202;  
public  static int  USER_NOT_EXIST = 3003; 
public  static int  TOKEN_ERROR = 3004;
```


######7. 上传课程资源评价回复信息 （功能已实现）
```
method= POST
Url： http://60.205.218.103:80/QingXiao/ResourceComment/Insert

Requert:  header:  (1)accessToken (2)userName
         Body:    (1) private  String  jsonStringGetResourceCommentList = "{'resourceStroeName':'f2eafd3fd35e447499ab6d7fd23fb90d.jpg','sinceTime':'2017-10-26 22:02:25','nowTime':'2017-10-29 20:52:46.517'}" ;

Rsponse:  header:  (1)
Body:  (1) {result=3371}

public  static int UPLOAD_COURSE_RESOURCE_COMMENT_REPLY_SUCCESS=3371;
```


######8. 获取课程资源评价回复信息 （功能已实现）
```
method= POST
Url: http://60.205.218.103:80/QingXiao/ResourceComment/GetCommentReplyList

Requert:  header:  (1)accessToken (2)userName
         Body:    (1) private  String  jsonStringGetResourceCommentList = "{'resourceStroeName':'f2eafd3fd35e447499ab6d7fd23fb90d.jpg','sinceTime':'2017-10-26 22:02:25','nowTime':'2017-10-29 20:52:46.517'}" ;

Rsponse:  header:  (1)
Body:  (1) result：[{"commentID":"8d06d45cc5954c84ab2182e7b09d1892","commentTime":"2018-01-01 18:13:39.0","commentType":0,"content":"根评论！！！","likeTimes":0,"replyTimes":0,"score":0,"shareTimes":0,"status":0,"topicCommentList":[{"commentID":"b09d9dd695c24ff1974d66c07aa5c93e","commentTime":"2018-01-04 20:25:40.0","commentType":0,"content":"很实用！！！","likeTimes":0,"replyTimes":0,"score":0,"shareTimes":0,"status":0,"toContent":"根评论！！！","toUserName":"admin1","userID":"8e59adf6f18045b29bccb02bea96e933","userName":"admin1"}],"userName":"admin1"}]

public  static int   UPLOAD_COURSE_SUCCESS=3201;
public  static int   COURSE _EXISTED =3202; 
public  static int   USER_NOT_EXIST =3003;  
public  static int   TOKEN_ERROR=3004;
```


###四. 动态相关 

######0. 插入主题（已实现）
```
method= POST
Url： http://60.205.218.103:80/QingXiao/Subject/Insert

Requert:  header:  (1)accessToken (2)userName
         Body:    (1) private  String  jsonStringTopicInsert = "{'content':'一周情侣', subjectType: '娱乐'}" ;

Rsponse:  header:  (1)
Body:  (1) 结果为{"result":3401}

public  static int INSERT_SUBJECT_SUCCESS=3401;
```

######1.插入动态 （已实现）
```
method= POST
Url：http://60.205.218.103:80/QingXiao/Topic/Insert

Requert:  header:  (1)accessToken (2)userName
         Body:    (1) private  String  jsonStringInsertTopic = "{'subjectID':'af9efadc5724433eb8a461c687873064' ,'dontMaskStranger':0,'content':'下雪啦！！！','topicPlace':'安徽大学罄苑校区','lableList':[{'lable':'心情'},{'lable':'休闲'}]}" ;

Rsponse:  header:  (1)
Body:  (1) {"result":3411}

public  static int INSERT_TOPIC_SUCCESS=3411;
```


######2. 获取动态 （已实现）
```
method= POST
Url：http://60.205.218.103:80/QingXiao/Topic/GetTopicList

Requert:  header:  (1)accessToken (2)userName
         Body:    (1)private  String jsonStringGetTopicList = "{'subjectID':'af9efadc5724433eb8a461c687873064'  ,'sinceTime':'2017-10-26 22:02:25','nowTime':'2018-10-29 20:52:46.517'}"  ;

Rsponse:  heaer:  (1)

Body:  (1) 动态result：[{"browseTimes":0,"commentTimes":0,"content":"今天天气不错！！！","dontMaskStranger":0,"likeTimes":0,"shareTimes":0,"status":0,"subjectID":"af9efadc5724433eb8a461c687873064","topicCommentList":[{"commentID":"faeb6f8d5351440bbccb39f0df0409ff","commentTime":"2018-01-03 13:32:45.0","commentType":0,"content":"66666","likeTimes":0,"replyTimes":0,"score":0,"shareTimes":0,"status":0,"toUserName":"admin1","topicID":"d8bedabf84df4488b7fdc6037e4b4722","userID":"8e59adf6f18045b29bccb02bea96e933","userName":"admin1"}],"topicID":"d8bedabf84df4488b7fdc6037e4b4722","topicImageList":[{"imagePath":"D:\\Users\\xpb\\IdeaProjects\\QingXiao\\target\\test-classes\\courseResource\\fcccc3e19264435486ce64aa2f04453c.jpg","imageType":0,"picOrder":0,"status":0,"topicID":"d8bedabf84df4488b7fdc6037e4b4722"},{"imagePath":"D:\\Users\\xpb\\IdeaProjects\\QingXiao\\target\\test-classes\\courseResource\\333d6fd0401f4c3ab43fbfd6d2028376.png","imageType":0,"picOrder":0,"status":0,"topicID":"d8bedabf84df4488b7fdc6037e4b4722"}],"topicTime":"2018-01-02 22:49:38.0","userID":"8e59adf6f18045b29bccb02bea96e933","userName":"admin1"}]

public  static int GET_TOPIC_SUCCESS=3421;
```


######3. 插入动态评论 （已实现）
```
method= POST
Url：http://60.205.218.103:80/QingXiao/Topic/InsertComment

Requert:  header:  (1)accessToken (2)userName
         Body:    (1)private   String  jsonStringInsertTopicComment  =  "{'topicID':'d8bedabf84df4488b7fdc6037e4b4722' ,'commentType':0,'content':'66666','toUserID':'8e59adf6f18045b29bccb02bea96e933'}";

Rsponse:  heaer:  (1)

Body:  (1) {"result":3431}

public  static int INSERT_TOPIC_COMMENT_SUCCESS=3431;
```


######4. 插入动态评论的回复 （已实现）
```
method= POST
Url：http://60.205.218.103:80/QingXiao/Topic/InsertComment

Requert:  header:  (1)accessToken (2)userName
         Body:    (1) private   String  jsonStringInsertTopicCommentReply = "{'topicID':'d8bedabf84df4488b7fdc6037e4b4722' ,'commentType':1,'content':'99999','objectID':'faeb6f8d5351440bbccb39f0df0409ff','toUserID':'8e59adf6f18045b29bccb02bea96e933','userID':''}" ;  

Rsponse:  header:  (1)
Body:  (1) {"result":3431}

public  static int 
 INSERT_TOPIC_COMMENT_REPLY_SUCCESS =3441;
```


######5. 获取动态的评论
```
method= POST

Url：http://60.205.218.103:80/QingXiao/Topic/GetCommentList

Requert:  header:  (1)accessToken (2)userName
         Body:    (1)private  String   jsonStringGetTopicCommentList = "{'topicID':'adabcbe79dca495dae252b28fb190bb8' ,'sinceTime':'2017-10-26 22:02:25','nowTime':'2018-10-29 20:52:46.517'}" ;    

Rsponse:  header:  (1)
Body:  (1) 课程评论信息List:[{score=90, commentTime=2017-10-28 15:58:31.774, content=很实用！！！}, {score=90, commentTime=2017-10-28 15:54:44.170, content=很实用！！！}]

public  static int GET_TOPIC_COMMENT_SUCCESS=3451;
```


######6. 获取动态评论的回复（已实现）
```
method= POST
Url： http://60.205.218.103:80/QingXiao/Topic/GetCommentReplyList

Requert:  header:  (1)accessToken (2)userName
         Body:    (1)  private  String jsonStringGetTopicCommentReplyList 
 =  "{'commentID':'94e7f049355143f6bb1c5a6771336401','sinceTime':'2017-10-26 22:02:25','nowTime':'2018-10-29 20:52:46.517'}" ;  

Rsponse:  heaer:  (1)
Body:  (1) result：[{"commentID":"faeb6f8d5351440bbccb39f0df0409ff","commentTime":"2018-01-03 13:32:45.0","commentType":0,"content":"66666","likeTimes":0,"replyTimes":2,"score":0,"shareTimes":0,"status":0,"**topicCommentList**":[{"commentID":"e01dac248680462991da63762145c0ae","commentTime":"2018-01-04 18:31:49.0","commentType":0,"content":"这是回复的回复","likeTimes":0,"replyTimes":0,"score":0,"shareTimes":0,"status":0,"toContent":"99999","toUserName":"admin1","topicID":"d8bedabf84df4488b7fdc6037e4b4722","userID":"8e59adf6f18045b29bccb02bea96e933","userName":"admin1"},{"commentID":"3a93bd07e2914b28a83aebdabff2300f","commentTime":"2018-01-03 13:34:55.0","commentType":0,"content":"99999","likeTimes":0,"replyTimes":0,"score":0,"shareTimes":0,"status":0,"toContent":"66666","toUserName":"admin1","topicID":"d8bedabf84df4488b7fdc6037e4b4722","userID":"8e59adf6f18045b29bccb02bea96e933","userName":"admin1"}],"userName":"admin1"}]

public  static int GET_TOPIC_COMMENT_REPLY_ SUCCESS*=3461;
```


##五.书籍相关

######1. 插入书籍基本信息
```
method= POST

Url：

Requert:  header:  (1)accessToken (2)userName
         Body:    (1)
Rsponse:  heaer:  (1)

Body:  (1)

```
