<%--
  Created by IntelliJ IDEA.
  User: xpb
  Date: 2018/5/7
  Time: 23:03
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

    <style>
        html,body{
            height:100%;

        }
        .list-container{
            height: auto;
            width: 100%
        }
        .note-list{
            border: 1px solid #ccc;
            list-style: none;
            height: auto;
            width: 100%
        }
        .have-img{
            border: 1px solid #ccc;
            height: 240px;
            width: 100%
        }
        .content{
            height: 200px;
            width: 100%
        }
        .author{
            float: left;
            height: 60px;
            width: 100%
        }
        .avatar{
            float: left;
            height: 60px;
            width: 60px;
        }
        .info{
            float: left;
            height: 30px;
            width: auto;
        }
        .nickname{
            float: left;
            font-size: smaller;
            height: 30px;
            width: auto;
        }
        .title{
            height: 50px;
            font-size: large;
            width: 100%
        }
        .abstract{
            font-size:medium;
            height: 50px;
            width: 100%;
        }
        .meta{
            height: 20px;
            width: 100%
        }
    </style>
</head>
<body>
<%--
    out.println("<td align=left width=290>"+66666666+"</td>");
    Cookie[] cookies=request.getCookies();
    out.println("<td align=left width=290>"+cookies.length+"</td>");
    for(int i=0;i < cookies.length;i++){
        out.println("Cookie name:" + cookies[i].getName());
        out.println("Cookie value:" + cookies[i].getValue());
        if(cookies[i].getName().toString().equals("username")){
            //console.log("返回cookie数据："+cookies[i].getValue());
            System.out.println(cookies[i].getValue());
            out.println("<td align=left width=290>"+cookies[i].getValue()+"</td>");
            out.print(cookies[i].getValue());
            out.print("66666");
        }
    }
--%>
<nav class="navbar navbar-default" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">Hello QingXiao!</a>
        </div>
        <div>
            <ul class="nav navbar-nav">
                <li><a href="http://www.qingxiao.xin/app/index/rose.html">玫瑰欣赏</a></li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        <img src="http://60.205.218.103:80/avatar/1.jpg" class="img-rounded" height="40" width="40">
                        <span class="caret"></span>
                    </a>

                    <ul class="dropdown-menu">
                        <li><a href="#">我的主页</a></li>
                        <li><a href="#">收藏的文章</a></li>
                        <li><a href="#">喜欢的文章</a></li>
                        <li class="divider"></li>
                        <li><a href="#">设置</a></li>
                        <li class="divider"></li>
                        <li><a href="#">退出</a></li>
                    </ul>
                </li>
                <li><a href="http://www.qingxiao.xin/app/index/wEditor.html" target="_blank">
                    <p style="font-size: large "> 写文章</p>>
                </a></li>
            </ul>
        </div>
    </div>
</nav>
<div class="container">
    <div class="row">
        <div class="col-md-8 col-md-offset-2">
            <div id="list-container" class="">
            </div>
        </div>
    </div>
</div>
<script id="article-list" type="text/html">
    <!-- 文章列表模块 -->
    <ul class=""  style="list-style-type: none;padding:0px; margin:0px" >
        {{ each articleList as article }}

        <li id="note-25410107"   class="">

            <div class="row">
                <div class="col-md-1">
                    <a class="" target="_blank" href="http://60.205.218.103/QingXiao/Article/articleInfo?articleID={{article.articleID }}">
                        <img src="http://60.205.218.103:80/avatar/1.jpg"  height="40" width="40" alt="20">
                    </a>
                </div>
                <div class="col-md-4">
                    <div class="">
                        <a class="" target="_blank" href="http://60.205.218.103/QingXiao/Article/articleInfo?articleID={{article.articleID }}">{{article.author}}</a>
                        <span class="" data-shared-at="2018-03-21T13:20:13+08:00">{{article.releaseTime}}</span>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col-md-8">
                    <div class="">
                        <a class="" style="font-size: large" target="_blank" href="http://60.205.218.103/QingXiao/Article/articleInfo?articleID={{article.articleID }}">{{article.title}}</a>
                        <p class="">
                            {{ article.summary}}
                        </p>
                    </div>
                </div>
                <div class="col-md-4">
                    <a class="" href="http://60.205.218.103/QingXiao/Article/articleInfo?articleID={{article.articleID }}" target="_blank">
                        <img src="http://60.205.218.103:80/articlePicture/e1a283ecfeb54ec3b6b29463915e402b.jpg" class="img-rounded" height="100" width="100">
                    </a>
                </div>

            </div>
            <div class="row">
                <div class="col-md-12">
                    <a target="_blank" href="http://60.205.218.103/QingXiao/Article/articleInfo?articleID={{article.articleID }}">
                        <i class=""></i> 3341
                    </a>
                    <a target="_blank" href="http://60.205.218.103/QingXiao/Article/articleInfo?articleID={{article.articleID }}">
                        <i class=""></i> 23
                    </a>
                    <span><i class=""></i> 158</span>
                </div>
            </div>

            <hr style="height:3px;border:none;border-top:3px double red;" />
        </li>
        {{/each}}
    </ul>
    <!-- 文章列表模块 -->
</script>


<script>

    $(function () {
        $.ajax({
            //url: '/QingXiao/Article/articleList1', // 路径要以QingXiao开头
            url: 'http://www.qingxiao.xin/Article/articleList1', // 路径要以QingXiao开头
            type: 'POST',
            data:{sinceTime:"2018-04-18",nowTime:"2018-05-18"},
            dataType: 'json',
            timeout: 1000,
            cache: false,
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
        function succFunction(tt) {

            console.log("返回文章列表数据："+tt);
            var json = eval(tt); //数组
            console.log("返回文章列表数据json："+json);
            var html = template('article-list', json);
            document.getElementById('list-container').innerHTML = html;
        }
    });
</script>
<div id="ICP">

<div style="width:300px;margin:0 auto; padding:20px 0;">
    <a href=""> <img src="http://www.qingxiao.xin/app/index/img/police_beian.png"></a>
    <a target="_blank" href="http://www.beian.gov.cn/portal/registerSystemInfo?recordcode=34019102000218" style="display:inline-block;text-decoration:none;height:20px;line-height:20px;"><img src="" style="float:left;"/><p style="float:left;height:20px;line-height:20px;margin: 0px 0px 0px 5px; color:#939393;">皖公网安备 34019102000218号</p></a>
</div>
</div>
</body>
</html>