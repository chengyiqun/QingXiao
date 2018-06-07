<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <style type="text/css">
        #exp-a{height:150px; background:#CCC}
        .div_style { height: 100%;width: 100%}
        html,body{
            height:100%;

        }
        .toolbar {
            border: 1px solid #ccc;
            width: 100% ;

        }
        .text {
            overflow: hidden;
            min-height:400px;
            height: 100%;
            width: 100% ;


        }
        .outDiv {

            height: 100%;
            width: 60% ;
            margin-left:auto;margin-right:auto;

        }

        .outDiv2 {
            overflow: hidden;
            height: 100%;
            width: 100% ;
            margin-left:auto;margin-right:auto;

        }

    </style>
</head>

            <body>
            <div id="divContainer" class="outDiv">

                <div id="divContainer2" class="outDiv">
        <p> ${articleInfo.title  } </p>
        <div id="div2" class="text" > <!--可使用 min-height 实现编辑区域自动增加高度-->
            <p>请输入内容</p>
            <div id="info2">
            </div>
        </div>
    </div>
</div>
<!-- 注意， 只需要引用 JS，无需引用任何 CSS ！！！-->
<!-- 注意， 只需要引用 JS，无需引用任何 CSS ！！！-->
<script type="text/javascript" src="../app/build/release/wangEditor.js"></script>
<script type="text/javascript">
    var E = window.wangEditor
    var editor = new E('','#div2')
    // 或者 var editor = new E( document.getElementById('editor') )
    //editor.customConfig.zIndex = 10
    editor.customConfig.uploadImgMaxSize = 20 * 1024 * 1024;//设置图片大小为20M
    editor.customConfig.uploadImgTimeout = 1000000; //图片上传超时限制10s
    //editor.customConfig.uploadImgServer = 'http://localhost/QingXiao/Article/uploadArticleImage'  //上传图片到服务器
    editor.customConfig.uploadImgServer = '/QingXiao/Article/uploadArticleImage3'  // 上传图片到服务器
    console.log('开始上传！');
    editor.create();
    info1='<blockquote>请输入内容</blockquote><blockquote>眼间，3月所剩无几，即使你再不情不愿，距离2019年只剩下不足9个月。 每逢岁末年初时，人们总喜欢搞个总结盘点，梳理一下过去一年做完的事情，在一番激烈的心理...&nbsp;&nbsp;</blockquote><p><br></p><p><br></p><p><br></p><p><img src="http://60.205.218.103:80/QingXiao//articlePicture/e5ecaf6e3dbf4642bc848468d01301d0.jpg" style="max-width:100%;"><br></p>';
    //document.getElementById("info2").innerHTML=info1;
    var author='${author }';
    console.log('文章作者：'+author);
    var content = '${articleInfo.content }';
    console.log('文章内容：'+content);
    editor.txt.html(content)
    // 禁用编辑功能
    editor.$textElem.attr('contenteditable', false)

    // 开启编辑功能
    //editor.$textElem.attr('contenteditable', true)
</script>
</body>
</html>