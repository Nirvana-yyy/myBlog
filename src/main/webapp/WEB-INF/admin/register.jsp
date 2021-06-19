<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link href="../resources/plugin/layui/css/layui.css" rel="stylesheet" type="text/css"/>
    <script src="../resources/js/jquery-2.1.0.min.js" type="text/javascript"></script>
    <script src="../resources/plugin/layui/layui.js" type="text/javascript" charset="UTF-8"></script>
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
<script>
    var bool ;
    function refreshCode() {
        var vcode = document.getElementById("vcode");
        vcode.src = "/checkCode?time="+new Date().getTime();
    }
    function checkUsername() {

            //获取username文本输入框的值
            var username = document.getElementById("username").value;

            //发送Ajax请求
            $.get("/findUser",{username:username},function (data) {
                var span = $("#s_username");

                //用户名存在
                if (data.userExist){
                    span.html(data.msg_regist);
                    span.css("color","red");
                    bool = false;
                } else{
                    span.html(data.msg_regist);
                    span.css("color","green");
                    bool = true;
                }

            })
    }
    function checkPassword() {
//获取密码
        var password = $("#password").val();
//定义正则
        var reg_password = /^\w{3,18}$/;
//判断，给出提示信息
        var flag = reg_password.test(password);
        if (flag){
//密码名合法
            $("#password").css("border","");
        }else {
//密码非法,加一个红色的边框
            $("#password").css("border","1px solid red");
        }

        return flag;
    }
    $(function () {

        //表单提交
        $("#registerForm").submit(function () {
            /*alert(checkUsername());
            alert(checkPassword());*///不知道为什么这里无法接收到checkUsername的返回bool值

            if (checkPassword() && bool){

                $.post("/register",$("#registerForm").serialize(),function (data){
                    /*alert(data);*/
                    //处理响应的数据data
                    if (data.flag){
                        //注册成功
                        location.href="/page/login";
                    }else{
                        $("#errorMsg").html(data.errorMsg);
                        return false;
                    }
                });
            }else{
                alert("输入不合规范");
            }
            return false;
        });
        $("#password").blur(checkPassword);
        $("#username").blur(checkUsername);
    })
</script>


<div class="layui-container" style="width: 40%; padding-top: 150px;">
    <div class="layui-row" style="background-color: #fff;height: 500px;padding-top: 30px">
        <form class="layui-form" id="registerForm" action="">
            <!--提交处理请求的标识符-->
            <input type="hidden" name="action" value="register">
            <div class="layui-form-item">
                <label class="layui-form-label">用户名</label>
                <div class="layui-input-block">
                    <input type="text" name="username" id="username" required  lay-verify="required" placeholder="请输入用户名" autocomplete="off" class="layui-input">
                    <%--校验用户名是否存在--%>
                    <span id="s_username"></span>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">昵称</label>
                <div class="layui-input-block">
                    <input type="text" name="nickname" id="nickname" required  lay-verify="required" placeholder="请输入用户名" autocomplete="off" class="layui-input">

                </div>
            </div>
            <table style="margin: auto">
                <tr>
                    <td><img src="../../resources/img/avatar/avatar1.jpg" style="width: 50px;height: 50px"></td><td><img src="../../resources/img/avatar/avatar2.jpg"style="width: 50px;height: 50px"></td><td><img src="../../resources/img/avatar/avatar3.jpg"style="width: 50px;height: 50px"></td>
                </tr>
                <tr>
                    <td><img src="../../resources/img/avatar/avatar4.jpg"style="width: 50px;height: 50px"></td><td><img src="../../resources/img/avatar/avatar5.jpg"style="width: 50px;height: 50px"></td><td><img src="../../resources/img/avatar/avatar6.jpg"style="width: 50px;height: 50px"></td>
                </tr>
            </table>
            <div class="layui-form-item">
                <label class="layui-form-label">选择一张图片作为图片</label>
                <div class="layui-input-block" style="width: 50%">
                    <select name="avatar" id="avatar" lay-verify="required" >
                        <option value="1">1</option>
                        <option value="2">2</option>
                        <option value="3">3</option>
                        <option value="4">4</option>
                        <option value="5">5</option>
                        <option value="6">6</option>
                    </select>
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
<%--                          点击图片更换验证码--%>
                    <a href="javascript:refreshCode()"><img src="/checkCode" title="看不清请点击图片" id="vcode"/></a>
                </div>

            </div>

            <div class="layui-form-item">
                <div class="layui-input-block">
                    <input class="layui-btn"  type="submit" id="submit1" lay-submit lay-filter="formDemo">

                </div>
            </div>

        </form>
        <p style="color: #a94442;" id="errorMsg"><strong>${msg}</strong></p>
    </div>
    <%--显示出错的提示框--%>
    <%--响应消息待补全--%>
    <p style="color: #a94442;"><strong>${msg}</strong></p>

</div>
<script>
    //Demo
    layui.use('form', function(){
        var form = layui.form;

        //监听提交
        // form.on('submit(formDemo)', function(data){
        //     layer.msg(JSON.stringify(data.field));
        //     return false;
        // });
    });
</script>
</body>
</html>