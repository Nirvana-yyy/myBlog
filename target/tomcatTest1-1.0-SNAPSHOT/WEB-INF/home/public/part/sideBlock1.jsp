<%--侧边栏部分--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.rapid-framework.org.cn/rapid" prefix="rapid" %>
<div id="sideBlock" class="layui-container">
    <div class="layui-row">

        <div class="layui-col-md9">
            <rapid:block name="content">
                <%@include file="articleList.jsp"%>
            </rapid:block>

        </div>
<%--        侧边卡片占3/12--%>
        <div class="layui-col-md3">
            <div class="layui-row layui-col-space15" style="padding-top: 30px;padding-left: 20px">
                <div class="layui-font-blue layui-bg-cyan">
                    个人中心
                </div>
                <%--                <div class="layui-col-md6">--%>

                <div class="layui-panel layui-bg-gray">
                    <c:choose>
                        <c:when test="${user == null}">
                            <a href="/page/login"><input type="button" value="请先登录" class="layui-btn layui-btn-primary" style="margin-left: 80px"></a>
                        </c:when>

                        <c:otherwise>
                            <div style="padding: 10px;padding-left: 100px"><a href="/user/${user.userId}"><img src="../../resources${user.userAvatar}" width="80px" height="80px"></a></div>
                            <div style="text-align: center"><a href="/user/${user.userId}">${user.userNickname}<a/></div>
                            <div style="padding: 10px">文章总数:&nbsp;&nbsp;${user.articleCount}</div>
                            <div style="padding: 10px">获赞总数:&nbsp;&nbsp;${user.likeCount}</div>
                        </c:otherwise>
                    </c:choose>



                </div>
                <%--                </div>--%>
            </div>

            <div class="layui-row layui-col-space15"style="padding-top: 30px;padding-left: 20px">
                <div class="layui-font-blue layui-bg-cyan">
                    网站概况
                </div>
                <%--                <div class="layui-col-md6">--%>

                <div class="layui-panel layui-bg-gray" style="height: 250px">
                    <div style="color: #3e94a5;font-size: 15px;">这是一个博客网站，涵盖后端、前端、嵌入式、机器学习方面的内容</div>
                    <div><img src="../../../../resources/img/background/font.png" height="100px" width="100%"> </div>
                    <div style="font-size: xx-small;color: #c6c6c6;font-style: italic;margin-top: 40px">还有诸多缺漏之处望各位海涵</div>
                </div>
                <%--                </div>--%>
            </div>

            <div class="layui-row layui-col-space15"style="padding-top: 30px;padding-left: 20px">
                <div class="layui-font-blue layui-bg-cyan">
                    近期评论
                </div>
                <%--                <div class="layui-col-md6">--%>

                <div class="layui-panel layui-bg-gray">
                    <c:forEach var="comment" items="${comments}">
                        <ol>
                            <li>“${comment.commentContent}”</li>
                        </ol>
                    </c:forEach>

                </div>

            </div>
        </div>
    </div>

</div>



<script>
    //注意：导航 依赖 element 模块，否则无法进行功能性操作
    layui.use('element', function(){
        var element = layui.element;

        //…
    });
</script>
