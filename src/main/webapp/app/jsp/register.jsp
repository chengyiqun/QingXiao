<%--
  Created by IntelliJ IDEA.
  User: xpb
  Date: 2018/4/25
  Time: 23:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Register</title>

    <script src="../build/AdminLTE/bower_components/jquery/dist/jquery.min.js"></script>
    <link href="../build/AdminLTE/bower_components/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="../build/AdminLTE/bower_components/font-awesome/css/font-awesome.min.css" rel="stylesheet">
    <script src="../build/AdminLTE/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="../index/css/style.css">



</head>
<%
    session.invalidate();
%>
<body>
<div class="container">
    <div class="form row">
        <div class="form-horizontal col-md-offset-3" id="login_form">
            <h3 class="form-title">Register</h3>
            <div class="col-md-9">
                <div class="form-group">
                    <i class="fa fa-user fa-lg"></i>
                    <input class="form-control required" type="text" placeholder="Username" id="username" name="username" autofocus="autofocus" maxlength="20"/>
                </div>
                <div class="form-group">
                    <input class="form-control  col-sm-6" type="text" placeholder="验证码" id="verifyCode" name="verifyCode" maxlength="4"/>
                    <input class="form-control  col-sm-6" type="button" id="getVerifyCode" value="免费获取验证码" onclick="settime(this)" />
                </div>
                <div class="form-group">
                    <i class="fa fa-lock fa-lg"></i>
                    <input class="form-control required" type="password" placeholder="Password" id="password" name="password" maxlength="8"/>
                </div>
                <div class="form-group">
                    <label class="checkbox">
                        <input type="checkbox" name="remember" value="1"/>记住我
                    </label>
                </div>
                <div class="form-group col-md-offset-9">
                    <button type="submit" class="btn btn-success " name="register" id="register">注册</button>
                </div>
            </div>
        </div>
    </div>
</div>
<script language="javascript" type="text/javascript">
    function checkMobile(str) {
        if(str==""){
            alert("手机号不能为空！");
        }else{
            var re = /^1\d{10}$/                          //      以1开始后面加10位数字
            if (re.test(str)) {
                alert("正确");
            } else {
                alert("手机号格式错误！");
            }
        }
    }
</script>

<script type="text/javascript">
    var countdown=60;
    function settime(obj) {
        if (countdown == 0) {
            obj.removeAttribute("disabled");
            obj.value="免费获取验证码";
            countdown = 60;
            return;
        } else {
            obj.setAttribute("disabled", true);
            obj.value="重新发送(" + countdown + ")";
            countdown--;
        }
        setTimeout(function() {
                settime(obj) }
            ,1000)
    }

</script>

<script>
    document.getElementById('getVerifyCode').addEventListener('click', function () {
        var username = document.getElementById("username").value;
        checkMobile(username);  // 检查手机号码
        var formData = new FormData();
        formData.append("phoneNum", username);
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
        objData["requestType"]="Web";
        $(function () {
            $.ajax({
                //url: '/QingXiao/User/Register', // 路径要以QingXiao开头
                url: '/User/GetVerifyCode', // 路径要以QingXiao开头
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

                console.log("返回发送验证码结果："+result);
                console.log("返回发送验证码结果1："+result["result"]);
                console.log("返回发送验证码结果2："+result.result);
                //var json = eval(result); //数组 "("+s+")"
                var json1 = eval("("+result+")"); // data的值是json字符串，这行把字符串转成object
                console.log("返回发送验证码结果数据json1："+json1.result);
                //var json = eval(result);
                console.log("返回发送验证码结果数据json1："+json1);
                if (json1.result == 2009) {
                    //window.location.href = "../../login.jsp";
                }else if (json1.result == 2008){
                    alert("用户已存在");
                }
            }
        });

    }, false)
</script>
<script>
    document.getElementById('register').addEventListener('click', function () {
        var username = document.getElementById("username").value;
        checkMobile(username);  // 检查手机号码
        var formData = new FormData();
        var password = document.getElementById("password").value;
        var identifyCode = document.getElementById("verifyCode").value;
        formData.append("phoneNum", username);
        formData.append("password", password);
        formData.append("identifyCode", identifyCode);
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
        objData["identifyCode"]=identifyCode;
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
