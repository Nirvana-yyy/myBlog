<%--页头--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--导航栏--%>
<ul class="layui-nav" lay-filter="">
    <c:choose>
        <c:when test="${user == null}">
            <li class="layui-nav-item"><a href="">请先登录</a></li>
        </c:when>
        <c:otherwise>
            <li class="layui-nav-item"><a href="/user/${user.userId}">个人中心</a></li>
        </c:otherwise>
    </c:choose>

    <li class="layui-nav-item <%--layui-this--%>"><a href="/"><i class="layui-icon layui-icon-home"></i>首页</a></li>
    <li class="layui-nav-item"><a href="/type/1">后端</a></li>
    <li class="layui-nav-item"><a href="/type/2">前端</a></li>
    <li class="layui-nav-item"><a href="/type/3">嵌入式</a></li>
    <li class="layui-nav-item"><a href="/type/4">机器学习</a></li>
    <li class="layui-nav-item"><a href=""><i class="layui-icon layui-icon-search"></i> 搜索</a></li>
    <li class="layui-nav-item">
        <a href="javascript:;">反馈</a>
        <dl class="layui-nav-child"> <!-- 二级菜单 -->
            <dd><a href="">联系我们</a></dd>
            <dd><a href="">打赏</a></dd>

        </dl>
    </li>
</ul>

<script>

    //注意：导航 依赖 element 模块，否则无法进行功能性操作
    layui.use('element', function(){
        var element = layui.element;

        //…
    });
</script>
