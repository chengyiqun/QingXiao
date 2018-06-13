<%--
  Created by IntelliJ IDEA.
  User: xpb
  Date: 2018/4/24
  Time: 21:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Qing Xiao</title>

    <script src="app/build/AdminLTE/bower_components/jquery/dist/jquery.min.js"></script>
    <link href="app/build/AdminLTE/bower_components/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="app/build/AdminLTE/bower_components/font-awesome/css/font-awesome.min.css" rel="stylesheet">
    <script src="app/build/AdminLTE/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="app/index/css/login.css">



</head>
<%
    session.invalidate();
%>
<body>
<div class="container">
    <div class="form row">
        <div class="form-horizontal col-md-offset-3" id="login_form">
            <h3 class="form-title">LOGIN</h3>
            <div class="col-md-9">
                <div class="form-group">
                    <i class="fa fa-user fa-lg"></i>
                    <input class="form-control required" type="text" placeholder="Username" id="username" name="username" autofocus="autofocus" maxlength="11"/>
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
                    <button type="submit" class="btn btn-success pull-right" name="login" id="login">登录</button>
                </div>
            </div>
        </div>
    </div>
</div>

<script>
    document.getElementById('register').addEventListener('click', function () {
        //console.log('路径：'+"<%=request.getContextPath()%>");
        window.location.href = "app/jsp/register.jsp";

    }, false)
</script>
<script language="javascript" type="text/javascript">
function checkMobile(str) {
    if(str==""){
        alert("手机号不能为空！");
    }else{
        var re = /^1\d{10}$/                          //      以1开始后面加10位数字
        if (re.test(str)) {
           // alert("正确");
        } else {
            alert("手机号格式错误！");
        }
    }
}
</script>

<script>
    document.getElementById('login').addEventListener('click', function () {
        var username = document.getElementById("username").value;
        checkMobile(username);
        var formData = new FormData();
        var password = document.getElementById("password").value;
        var verificationCode = document.getElementById("username").value;
        formData.append("phoneNum", "username");
        formData.append("password", password);
        formData.append("requestType", "Web");
        var objData = {};
        objData["phoneNum"]=username;
        objData["password"]=password;
        objData["requestType"]="Web";
        $(function () {
            $.ajax({
                //url: '/QingXiao/User/LoginWeb', // 路径要以QingXiao开头
                url: '/User/LoginWeb1', // 路径要以QingXiao开头
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
            })
            function LoadFunction() {
                $("#list").html('加载中...');
            }
            function erryFunction() {
                alert("error");
            }
            function succFunction(result) {

                console.log("返回登录结果："+result);
                console.log("返回登录结果："+result["result"]);
                //var json = eval(result); //数组 "("+s+")"
                var json = eval(result);
                console.log("返回文章列表数据json："+json);
                if (result["result"] == 3001) {
                    // 隐藏错误信息提示框
                    //$('#message-info').css('display', 'none');
                    // 设置成功提示信息
                    //$('#message').text(signInResponse["message"]);
                    // 跳转到主页

                  window.location.href = "app/jsp/index.jsp";
                }else if (result["result"] == 3002){
                    alert("用户名或密码已存在");
                }
            }
        });
    }, false)
    </script>



</body>
</html>
