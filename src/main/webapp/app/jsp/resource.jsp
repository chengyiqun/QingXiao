<%--
  Created by IntelliJ IDEA.
  User: xpb
  Date: 2018/6/7
  Time: 14:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Qing Xiao</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- 引入 jquery -->
    <script src="../build/jquery-3.3.1.min.js" type="text/javascript"></script>
    <!-- 引入 Bootstrap -->
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">

    <!-- 包括所有已编译的插件 -->
    <script src="../build/bootstrap-4.1.0-dist/js/bootstrap.min.js"></script>
    <script src="../build/art-template-master/lib/template-web.js"></script>
    <!--定义方式2 -->
    <%! String DOMIAN="www.qingxiao.xin";%>
</head>
<body>
<div>
    <input type="file" name="img" multiple />
    <button> </button>
    <form id="myForm" action="http://10.10.25.31:8080/myupload/UploadPhotoServlet"
          ENCTYPE="multipart/form-data" METHOD="POST">
        <input type="file" name="multipleFileUpload" multiple />
        <input type="submit" value="提交">
        <input type="reset" value="重设">
    </form>
</div>

</body>
</html>
