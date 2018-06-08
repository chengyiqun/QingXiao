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
    <form id="myForm" action="http://10.10.25.31:8080/myupload/UploadPhotoServlet"
          ENCTYPE="multipart/form-data" METHOD="POST">
        <input type="file" name="multipleFileUpload" multiple id="multipleFileUpload" onchange="onc()" />
        <input type="submit" id="submit" value="提交">
        <input type="reset" value="重设">
    </form>
    <textarea name="multipleFileList" id="multipleFileList" cols ="80" rows="5"> </textarea>>

</div>
<script type="text/javascript">
    function onc(){
        var files = document.getElementById("multipleFileUpload").files;
        var  fileName='';
        for(var i=0; i< files.length; i++){
            //alert(input.files[i].name);
            fileName = fileName + input.files[i].name+"\n";
        }
        var multipleFileList= document.getElementById('multipleFileList');
        multipleFileList.value =fileName;
    }
</script>

<script>
    document.getElementById('submit').addEventListener('click', function () {
        var formData = new FormData(multipleFileUpload);
        var username = document.getElementById("username").value;
        var password = document.getElementById("password").value;
        var verificationCode = document.getElementById("username").value;
        formData.append("phoneNum", username);
        formData.append("password", password);
        formData.append("requestType", "Web");
        var objData = {};
        //formData.forEach(objData[key] = value);
        JSON.stringify($('#username').s);

        for (var entry in formData.entries()){
            objData[entry[0]] = entry[1];
            console.log("返回登录结果："+entry[0]);
            console.log("返回登录结果："+entry[1]);
        }
        objData["phoneNum"]=username;
        objData["password"]=password;
        objData["requestType"]="Web";
        $(function () {
            $.ajax({
                //url: '/QingXiao/User/Register', // 路径要以QingXiao开头
                url: '/User/Register', // 路径要以QingXiao开头
                type: 'POST',
                data: JSON.stringify(objData),
                dataType: 'json',
                contentType: "application/json;charset=utf-8",
                timeout: 1000,
                cache: false,
                async:false, //不是异步处理
                beforeSend: LoadFunction, //加载执行方法
                error: erryFunction,  //错误执行方法
                success: succFunction //成功执行方法
            });
            function LoadFunction() {
                $("#list").html('加载中...');
            }
            function erryFunction() {
                alert("error");
            }
            function succFunction(result) {

                console.log("返回注册结果："+result);
                console.log("返回注册结果："+result["result"]);
                //var json = eval(result); //数组 "("+s+")"
                var json = eval(result);
                console.log("返回注册结果数据json："+json);
                if (result["result"] == 2001) {
                    // 隐藏错误信息提示框
                    //$('#message-info').css('display', 'none');
                    // 设置成功提示信息
                    //$('#message').text(signInResponse["message"]);
                    // 跳转到主页
                    window.location.href = "../../login.jsp";
                }else if (result["result"] == 2002){
                    alert("用户已存在");
                }
            }
        });

    }, false)
</script>
</body>
</html>
