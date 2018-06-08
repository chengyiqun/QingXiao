```
#青校数据库创建语句2-0
#CREATE SCHEMA `qing_xiao` DEFAULT CHARACTER SET utf8 ;
#（1）教师基本信息表
CREATE TABLE teacher_inform
(
  teacher_id          CHAR(32) PRIMARY KEY,
  employee_number    CHAR(10),
  teacher_name       VARCHAR(50),
  department         VARCHAR(30),
  sex                 CHAR(2),

  birthday           DATE,

  teacher_email      VARCHAR(50),
  teacher_tel        CHAR(10),
  office             VARCHAR(50),
  academic_title     VARCHAR(10),
  induction_time     DATE,
  brief_introduction VARCHAR(300),
  status             SMALLINT
);
# (2)university表
CREATE TABLE university
(
  university_id           CHAR(32) PRIMARY KEY,
  university_name         VARCHAR(50),
  province            CHAR(10),
  university_type         CHAR(20), #普通本科，就职高专，中外合作。
  university_property     CHAR(8), #综合类，医药政法理工财经农林示范体育。。。
  edu_directly        SMALLINT, #教育部直属
  is985               SMALLINT,
  is211               SMALLINT,
  level               CHAR(6), #本专其他
  autonomyrs          SMALLINT, #自主划线
  membership          VARCHAR(50), #关系，教育部，委员会，XX部
  university_nature       CHAR(4), #公办/民办
  tuition             VARCHAR(500), #学费情况
  brief_introduction  VARCHAR(500), #简介
  university_code         CHAR(10), #学校代码，大多9位，也有10位的
  official_website    VARCHAR(100),
  old_name            VARCHAR(200), #曾用名
  is_firstrate_university SMALLINT, #一流大学
  is_firstrate_class  SMALLINT, #一流专业
  status              SMALLINT
);

#(3)学院表
CREATE TABLE college
(
  college_id CHAR(32) PRIMARY KEY ,
  college_name VARCHAR(50),
  status SMALLINT
);

#(4)学校学院表
CREATE TABLE university_college
(
  university_college_id CHAR(32) PRIMARY KEY ,
  university_id CHAR(32),
  college_id CHAR(32),
  offical_website VARCHAR(150),
  status SMALLINT,
  FOREIGN KEY (university_id)REFERENCES university(university_id),
  FOREIGN KEY (college_id)REFERENCES college(college_id)
);
#(5)专业表
CREATE TABLE major
(
  major_id CHAR(32) PRIMARY KEY ,
  major_code CHAR(10),
  major_name VARCHAR(50),
  status SMALLINT
);
#(6)学院专业表（参考学校学院表）
CREATE TABLE college_major
(
  college_major_id CHAR(32) PRIMARY KEY ,
  university_college_id  CHAR(32),
  major_id CHAR(32),
  status SMALLINT,
  FOREIGN KEY (university_college_id) REFERENCES university_college(university_college_id),
  FOREIGN KEY (major_id) REFERENCES major(major_id)
);
#(7)学校官方 群组 联系方式 公众号
CREATE TABLE contact_information
(
  contact_id CHAR(32) PRIMARY KEY ,#联系方式id
  university_id CHAR(32),
  contact VARCHAR(240),
  contact_type SMALLINT,#qq群，微信群，公共电话，公众号，其他

  qr_code_store_name CHAR(200),
  qr_code_real_name CHAR(200),

  status SMALLINT,
  FOREIGN KEY (university_id) REFERENCES university(university_id)
);

#（8）课程基本信息表 参考学校
CREATE TABLE course_inform
(
  course_id         CHAR(32) PRIMARY KEY,
  course_code       CHAR(7), #课程代码
  course_name       VARCHAR(40), #课程名
  course_nature     VARCHAR(14), #课程性质
  course_ascription VARCHAR(50), #课程归属 科技素质教育课程，人文素质教育课程
  university_id         CHAR(32),#课程参考学校
  credit  NUMERIC(3, 2), #学分
  class_hours       SMALLINT, #课时
  status            SMALLINT,
  FOREIGN KEY (university_id) REFERENCES university(university_id)
);
#（9）教学基本信息表
CREATE TABLE teach_inform
(
  teach_id            CHAR(32) PRIMARY KEY,
  teacher_id          CHAR(32),
  course_id           CHAR(32),
  comprehensive_score SMALLINT,
  teach_time          VARCHAR(40),
  start_date          DATE NOT NULL,
  comment_times       INT DEFAULT 0,
  start_year          SMALLINT, #学年开始年
  end_year            SMALLINT, #//学年结束年
  semester            SMALLINT, #//学期
  classroom           VARCHAR(40),
  day_of_week         SMALLINT, #//星期几
  start_section       SMALLINT, #//第几节课开始
  end_section         SMALLINT, #//第几节课结束
  start_week          SMALLINT, #//开始周
  end_week            SMALLINT, #//结束周
  every_week          SMALLINT, #//标记是否是单双周，0为每周,1单周，2双周
  same_time           SMALLINT, #//标记同一时间段是否有不同的课
  status              SMALLINT
);
#(10)书籍表
CREATE TABLE book
(
  book_id            CHAR(32) PRIMARY KEY, #书籍ID
  book_name          VARCHAR(50), #名称
  publishing_company VARCHAR(100), #出版社
  price             NUMERIC(5, 2), #价格
  revision           VARCHAR(50), #版次
  publishing_date DATE #出版日期
);
#(11)教学书籍表
CREATE TABLE teach_book
(
  teach_book_id CHAR(32) PRIMARY KEY ,
  teach_id  CHAR(32), #教学id
  book_id   CHAR(32), #书籍id
  book_type CHAR(20), #书籍类型 （学校教材 教辅 推荐教材）

  FOREIGN KEY (teach_id) REFERENCES teach_inform(teach_id),
  FOREIGN KEY (book_id) REFERENCES book(book_id)
);

#(12)专业课程表 作用通过专业确定课程 （查询这个专业有哪些课程）
CREATE TABLE major_course
(
  major_course_id CHAR(32) PRIMARY KEY ,
  course_id CHAR(32),
  major_id CHAR(32),
  FOREIGN KEY (course_id) REFERENCES course_inform(course_id),
  FOREIGN KEY (major_id) REFERENCES college_major(major_id)
);


#（13）用户基本信息表
CREATE TABLE user_inform
(
  user_id           CHAR(32) PRIMARY KEY,
  student_id        CHAR(9),
  id_card           CHAR(18),
  id_verified     SMALLINT,
  qualification  VARCHAR(20),#学历证书
  qualification_verified SMALLINT,


  access_token VARCHAR(500),
  expires_in DATETIME,
  refresh_token VARCHAR(500),
  avatar_store_name CHAR(200),
  avatar_real_name CHAR(200),


  real_name         VARCHAR(50),
  user_name         VARCHAR(50),
  avatar            VARCHAR(100),
  password          VARCHAR(128), #SHA512
  phone_num    varchar(11),#手机号码
  tel               CHAR(11),
  email             VARCHAR(50),
  qq                VARCHAR(13),
  sex               CHAR(2),

  birthday           DATE,

  department        VARCHAR(40),
  grade             CHAR(4),
  entrance_date     DATE, #入学日期
  user_introduction VARCHAR(400),
  register_time     DATETIME,
  private_space     BIGINT UNSIGNED, #个人空间已用容量
  bonus_points      BIGINT,#积分
  reputation_points BIGINT,#信誉分
  exp               BIGINT UNSIGNED,#经验值
  level             INT UNSIGNED,#等级
  dont_allow_find_by_username SMALLINT,
  dont_allow_find_by_tel SMALLINT,
  dont_allow_find_by_qq  SMALLINT,
  dont_allow_found_nearby SMALLINT,
  dont_see_stranger SMALLINT,
  status            SMALLINT
);

#（14）登陆信息记录
CREATE TABLE login_information
(
  login_information_id CHAR(32),
  user_id CHAR(32),
  login_time DATETIME,
  login_place VARCHAR(200),
  login_ipv4 CHAR(15),
  login_ipv6 char(40),
  status SMALLINT,
  FOREIGN KEY (user_id) REFERENCES user_inform(user_id)
);
#（15）签到记录表
CREATE TABLE sign_information
(
  sign_information_id CHAR(32),
  user_id CHAR(32),
  sign_time DATETIME,
  sign_place VARCHAR(200),
  sign_mood VARCHAR(20),
  sign_text VARCHAR(240),
  status SMALLINT,
  FOREIGN KEY (user_id) REFERENCES user_inform(user_id)
);

#（16）教学评价表 teach_comment
CREATE TABLE teach_comment
(
  teach_comment_id CHAR(32) PRIMARY KEY,
  teach_id         VARCHAR(32),
  user_id          CHAR(32),
  comment_type     SMALLINT,#0评论，1回复，
  root_comment     char(32),#comment_type=1才有效
  odject_id        VARCHAR(32),
  to_user_id       CHAR(32),
  score            SMALLINT,
  comment_times    INT DEFAULT 0,#回复数量
  like_times        INT DEFAULT 0,#赞数
  share_times       INT DEFAULT 0,#分享数
  content          VARCHAR(280),
  comment_time     DATETIME NOT NULL,
  is_anonymous     SMALLINT,
  status           SMALLINT,
  FOREIGN KEY (teach_id) REFERENCES teach_inform (teach_id),
  FOREIGN KEY (odject_id) REFERENCES teach_comment (teach_comment_id),
  FOREIGN KEY (to_user_id) REFERENCES user_inform (user_id),
  FOREIGN KEY (user_id) REFERENCES user_inform (user_id)
);
#（17）学生选课基本信息表
# 修改了字段，把student_id改为user_id

CREATE TABLE student_course
(
  student_course_id  CHAR(32) PRIMARY KEY,
  teach_id           CHAR(32),
  user_id            CHAR(32),
  choose_course_date DATE,
  score              SMALLINT, #999优秀，666良好，556及格 555不及格，小于500直接显示
  grade_point     NUMERIC(3,2),#绩点
  resit_rebuild_tag  SMALLINT, #0正常，1补考，2重修，3重修的补考，4挂科
  status             SMALLINT,
  FOREIGN KEY (teach_id) REFERENCES teach_inform (teach_id),
  FOREIGN KEY (user_id) REFERENCES user_inform (user_id)
);
#（18）课程资源基本信息表（
CREATE TABLE course_resource
(
  course_resource_id  CHAR(32) PRIMARY KEY,
  user_id             CHAR(32),
  course_id           CHAR(32),
  resource_number     CHAR(32),
  upload_time         DATETIME     NOT NULL,
  file_name           VARCHAR(200),
  resource_store_name VARCHAR(200) NOT NULL,
  resource_type       VARCHAR(10), #试卷，答案，论文，视频，PPT，音频，书籍，其他。
  resource_describe   VARCHAR(140),
  resource_path       VARCHAR(300),
  comprehensive_score SMALLINT, #百分制
  like_times          INT UNSIGNED DEFAULT 0,
  comment_times       INT UNSIGNED DEFAULT 0,
  download_times      INT UNSIGNED DEFAULT 0,
  share_times         INT UNSIGNED DEFAULT 0,
  cost_points         SMALLINT DEFAULT 0, #积分 （初始1，下载50次加一，最大是5，积累积分到达1000时，免费）
  cost_points_sum    SMALLINT DEFAULT 0,#上传人收获积累积分（）
  privileges         SMALLINT,#权限 （公开，本校，本院，本专业，本版，本人，前期只显示公开和额本校）
  status              SMALLINT,
  FOREIGN KEY (user_id) REFERENCES user_inform (user_id),
  FOREIGN KEY (course_id) REFERENCES course_inform (course_id)
);

#（19）资源评价表
CREATE TABLE resource_comment
(
  resource_comment_id CHAR(32) PRIMARY KEY,
  course_resource_id  VARCHAR(32),
  comment_type        SMALLINT,#0评论，1回复
  root_comment         CHAR(32),#comment_type=1时有效
  odject_id           VARCHAR(32),
  to_user_id          CHAR(32),
  user_id             CHAR(32),

  comment_time     DATETIME,#评论时间
  comment_times      INT DEFAULT 0,#回复数量
  like_times        INT DEFAULT 0,#赞数
  share_times       INT DEFAULT 0,#分享数

  content             VARCHAR(140),
  score               SMALLINT,
  status              SMALLINT,
  FOREIGN KEY (course_resource_id) REFERENCES course_resource (course_resource_id),
  FOREIGN KEY (odject_id) REFERENCES resource_comment (resource_comment_id),
  FOREIGN KEY (to_user_id) REFERENCES user_inform (user_id),
  FOREIGN KEY (user_id) REFERENCES user_inform (user_id)
);
#（20）资源下载基本信息表
CREATE TABLE resource_download_inform
(
  resource_download_id CHAR(32) PRIMARY KEY,
  course_resource_id   CHAR(32),
  user_id              CHAR(32),
  download_time        DATETIME NOT NULL,
  download_times        INT DEFAULT 0,
  status               SMALLINT,
  FOREIGN KEY (course_resource_id) REFERENCES course_resource (course_resource_id),
  FOREIGN KEY (user_id) REFERENCES user_inform (user_id)
);

#(21)主题表
CREATE TABLE subject
(
  subject_id   CHAR(32) PRIMARY KEY,
  creator_id   CHAR(32),
  create_time  DATETIME NOT NULL,
  content      VARCHAR(500),
  subject_type VARCHAR(30),
  topics       INT,
  Image        VARCHAR(300),
  status       SMALLINT,
  FOREIGN KEY (creator_id) REFERENCES user_inform (user_id)
);

#（22）说说基本信息表
CREATE TABLE topic
(
  topic_id      CHAR(32) PRIMARY KEY,
  user_id       CHAR(32),
  subject_id    CHAR(32),
  topic_time          DATETIME NOT NULL,
  content       VARCHAR(500),
  topicPlace    VARCHAR(100),
  comment_times INT UNSIGNED DEFAULT 0,
  like_times    INT UNSIGNED DEFAULT 0,
  share_times   INT UNSIGNED DEFAULT 0,
  browse_times  INT UNSIGNED DEFAULT 0,
  dont_mask_stranger SMALLINT,#不屏蔽陌生人
  status        SMALLINT,
  FOREIGN KEY (user_id) REFERENCES user_inform (user_id),
  FOREIGN KEY (subject_id) REFERENCES subject (subject_id)
);

#（23）说说评价表
CREATE TABLE topic_comment
(
  comment_id    CHAR(32) PRIMARY KEY,
  topic_id      CHAR(32),
  user_id       CHAR(32),
  comment_type  SMALLINT,#0评论，1回复
  root_comment CHAR(32),#comment_type=1时有效
  comment_time          DATETIME NOT NULL,
  object_id     VARCHAR(32),
  to_user_id    CHAR(32),
  like_times    INT UNSIGNED DEFAULT 0,
  reply_times INT UNSIGNED DEFAULT 0,
  share_times   INT UNSIGNED DEFAULT 0,
  content       VARCHAR(240),
  score         SMALLINT,
  status        SMALLINT,
  FOREIGN KEY (topic_id) REFERENCES topic (topic_id),
  FOREIGN KEY (object_id) REFERENCES topic_comment (comment_id),
  FOREIGN KEY (to_user_id) REFERENCES user_inform (user_id),
  FOREIGN KEY (user_id) REFERENCES user_inform (user_id)
);
#（24）说说图片表
CREATE TABLE topic_image
(
  image_id         CHAR(32) PRIMARY KEY,
  topic_id         CHAR(32),

  pic_order        SMALLINT, #图片顺序，1~9
  file_name        VARCHAR(200), #原文件名
  image_store_name VARCHAR(50), #UUID名
  image_describe   VARCHAR(280), #describe是标识符，不能做变量名
  image_path       VARCHAR(300),
  image_type       SMALLINT,
  status           SMALLINT,
  FOREIGN KEY (topic_id) REFERENCES topic (topic_id)
);

#(25) 赞踩表
CREATE TABLE like_unlike_table
(
  like_id   CHAR(32) PRIMARY KEY,
  user_id   CHAR(32),
  like_time  DATETIME,
  like_unlike SMALLINT,
  like_type SMALLINT, #动态，说说评论，资源，资源评价，教师，教学评价,学校联系方式
  object_id CHAR(32),
  status    SMALLINT,
  FOREIGN KEY (user_id) REFERENCES user_inform(user_id)
);

#（26）lable表
CREATE TABLE lable_table
(
  lable_id CHAR(32) PRIMARY KEY ,
  user_id CHAR(32),
  lable     VARCHAR(240),
  lable_time DATETIME,
  lable_type SMALLINT,#动态，说说评论，资源，资源评价，教师，教学评价,学校联系方式
  object_id CHAR(32),
  status SMALLINT,
  FOREIGN KEY (user_id) REFERENCES user_inform(user_id)
);

#(27) 分享表
CREATE TABLE share_table
(
  share_id          CHAR(32) PRIMARY KEY,
  user_id           CHAR(32),
  share_type        SMALLINT, #评论，资源，说说，资源评论，说说评论，app，
  share_destination SMALLINT, #app内，qq好友，qq空间，微信好友，微信朋友圈，微博，
  share_time         DATETIME,
  object_id          CHAR(32),
  status            SMALLINT,
  FOREIGN KEY (user_id) REFERENCES user_inform(user_id)
);
# （28）人物关系表 好友，黑名单
CREATE TABLE relationship
(
  relationship_id CHAR(32) PRIMARY KEY ,
  user_id           CHAR(32),
  relation_user_id CHAR(32),
  is_friend SMALLINT,#好友
  friend_level SMALLINT,#友好程度：最高友好，在好友列表却无法看动态
  is_defriend SMALLINT,#拉黑
  defriend_level SMALLINT,#拉黑程度
  relationship_time DATETIME,
  status SMALLINT,
  FOREIGN KEY (user_id) REFERENCES user_inform(user_id),
  FOREIGN KEY (relation_user_id) REFERENCES user_inform(user_id)
);
# （29）积分变化表
CREATE TABLE bonus_points_change
(
  bonux_points_change_id  CHAR(32) PRIMARY KEY ,
  user_id CHAR(32),
  count int,#变化数量
  change_time DATETIME,
  change_reason VARCHAR(100),
  status SMALLINT,
  FOREIGN KEY (user_id) REFERENCES user_inform(user_id)
);

#（30）经验值变化表
CREATE TABLE exp_change
(
  exp_change_id CHAR(32),
  user_id CHAR(32),
  count int,#变化数量
  change_time DATETIME,
  change_reason VARCHAR(100),
  status SMALLINT,
  FOREIGN KEY (user_id) REFERENCES user_inform(user_id)
);

#（31）信誉变化表
CREATE TABLE reputation_change
(
  reputation_change_id CHAR(32),
  user_id CHAR(32),
  count int,#变化数量
  change_time DATETIME,
  change_reason VARCHAR(100),
  status SMALLINT,
  FOREIGN KEY (user_id) REFERENCES user_inform(user_id)
);

#（32）收藏表
CREATE TABLE collect_table
(
  collect_id CHAR(32) PRIMARY KEY,
  user_id           CHAR(32),
  collect_type        SMALLINT, #评论，资源，说说，资源评论，说说评论
  collect_time         DATETIME,
  object_id          CHAR(32),
  status            SMALLINT,
  FOREIGN KEY (user_id) REFERENCES user_inform(user_id)
);
#（33）浏览表
CREATE TABLE browse_table
(
  browse_id CHAR(32) PRIMARY KEY ,
  user_id           CHAR(32),
  browse_type        SMALLINT, #评论，资源，说说，资源评论，说说评论
  browse_time         DATETIME,
  object_id          CHAR(32),
  status            SMALLINT,
  FOREIGN KEY (user_id) REFERENCES user_inform(user_id)
);

#（34）文章表
CREATE TABLE  article_table
(
  article_id  CHAR(32) PRIMARY KEY ,
  title  varchar(140),
  author  varchar(140),
  author_id CHAR(32),
  summary varchar(200),   #文章摘要，提取前86个字。
  content  varchar(14000),
  lable  varchar(100),
  release_time   DATETIME,
  path  varchar(100),
  comment_times int,
  like_times  int,
  collect_times int,
  share_times int,
  privileges SMALLINT,
  status  SMALLINT,
  FOREIGN KEY (author_id) REFERENCES user_inform(user_id)

);
# 35 验证码表
CREATE TABLE  verify_code_table
(
  verify_code_id  CHAR(32) PRIMARY KEY ,
  phone_number  char(11),
  verify_code  char(4),
  imei    char(17),
  meid     char(15),
  lable  varchar(100),
  send_time   DATETIME,
  privileges SMALLINT,
  status  SMALLINT,
);


```