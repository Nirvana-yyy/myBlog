
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
    <%--    引入layui的css文件--%>
    <link href="../resources/plugin/layui/css/layui.css" rel="stylesheet" type="text/css"/>
    <%--    引入layui的js文件--%>
    <script src="../resources/plugin/layui/layui.js" type="text/javascript" charset="UTF-8"></script>
    <script src="../resources/js/jquery-2.1.0.min.js" type="text/javascript"></script>
    <script type="text/javascript">

        //点击更换验证码
        function refreshCode() {
            var vcode = document.getElementById("vcode");
            vcode.src = "/checkCode?time="+new Date().getTime();
        }
        $(function () {
            //给username绑定失去焦点事件
            $("#username").blur(function () {
                //获取username文本输入框的值
                var username = $(this).val();
                //发送Ajax请求
                $.get("/findUser",{username:username},function (data) {
                    var span = $("#s_username");
                    //用户名存在
                    if (data.userExist){
                        span.html(data.msg);
                        span.css("color","green");
                    } else{
                        span.html(data.msg);
                        span.css("color","red");
                    }
                })
            })
        })
    </script>
    <style type="text/css">
        body{
            font-family: "Microsoft YaHei", Helvetica, Arial, Lucida Grande, Tahoma, sans-serif;
            background: url(/resources/img/background/background2.png);
            width:100%;
            height:100%;
        }
    </style>
</head>
<body>

<div class="layui-container" style="width: 40%; padding-top: 150px;">
    <div class="layui-row" style="background-color: #fff;height: 300px;padding-top: 30px">
        <form class="layui-form"  action="/login" method="post">
            <div class="layui-form-item">
                <label class="layui-form-label">用户名</label>
                <div class="layui-input-block">
                    <input type="text" name="username" id="username" required  lay-verify="required" placeholder="请输入用户名" autocomplete="off" class="layui-input">
                    <%--校验用户名是否存在--%>
                    <span id="s_username"></span>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">密码框</label>
                <div class="layui-input-inline">
                    <input type="password" name="password" id="password" required  lay-verify="required" placeholder="请输入密码" autocomplete="off" class="layui-input">
                </div>
                <div class="layui-form-mid layui-word-aux">3到18位的数字或符号</div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label" for="checkCode">验证码</label>
                <div class="layui-input-inline">

                    <input type="text" name="checkCode" id="checkCode" required  lay-verify="required" placeholder="请输入标题" autocomplete="off" class="layui-input">

                </div>
                <div class="layui-form-mid layui-word-aux">

                    <a href="javascript:refreshCode()"><img src="/checkCode" title="看不清请点击图片" id="vcode"/></a>
                </div>

            </div>

            <div class="layui-form-item">
                <div class="layui-input-block">
                    <button class="layui-btn"  type="submit" lay-submit lay-filter="formDemo">立即提交</button>
                    <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                </div>
            </div>
            <a href="/page/register">没有账号？请先注册</a>
        </form>
    </div>
    <%--显示出错的提示框--%>
    <%--响应消息待补全--%>
    <p style="color: #a94442;"><strong>${msg}</strong></p>
</div>


<script>
    //Demo
    /* layui.use('form', function(){
         var form = layui.form;

         //监听提交
         form.on('submit(formDemo)', function(data){
             layer.msg(JSON.stringify(data.field));
             return false;
         });
     });*/


</script>

</body>

</html>