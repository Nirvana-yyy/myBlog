<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Title</title>
    <link href="../../../resources/css/style.css" rel="stylesheet">
    <link href="../../../resources/plugin/layui/css/layui.css" rel="stylesheet" type="text/css"/>
</head>
<style type="text/css">
    body{
        font-family: "Microsoft YaHei", Helvetica, Arial, Lucida Grande, Tahoma, sans-serif;
        background: url(/resources/img/background/background2.png);
        width:100%;
        height:100%;
    }
</style>
<body>
<%@include file="header.jsp" %>
<div style="background-color: #fff; width: 70%;margin: auto">
    <div style="background-color: #f4f5f5;width: 80%;margin: auto">
        <div style="width: 50%;margin: auto;margin-left: 300px">
            <img src="/resources${user.userAvatar}" style="width: 100px;height: 100px;padding-top: 20px">
        </div>
        <div style="text-align: center;padding-top: 20px">
            <div style="font-size: xx-large">${user.userNickname}</div>
        </div>
        <div style="padding-left: 300px;padding-top: 10px;padding-bottom: 10px">
            <a href="/page/writeArticle" style="font-size: x-large;border-color:#3e94a5; border-width:2px; border-style:solid;box-shadow:1px 2px 2px 2px steelblue">新建文章</a>
        </div>
    </div>
    <div>
        <%--数据展示--%>


        <main id="main" class="site-main" role="main" style="padding-top: 20px">
            <c:forEach items="${articlesPageInfo.list}" var="article">
                <article class="post type-post" >
                    <figure class="thumbnail">
                        <a href="/article/${article.articleId}">
                            <img width="280" height="210"
                                 src="/resources${article.articleRandomPic}"
                                 class="attachment-content size-content wp-post-image"
                                 alt="${article.articleRandomPic}">
                        </a>
                        <span class="cat">
                                    <a href="#">
                                        <c:choose>
                                            <c:when test="${article.articleType == 1}">
                                                后端
                                            </c:when>
                                            <c:when test="${article.articleType == 2}">
                                                前端
                                            </c:when>
                                            <c:when test="${article.articleType == 3}">
                                                嵌入式
                                            </c:when>
                                            <c:otherwise>
                                                机器学习
                                            </c:otherwise>
                                        </c:choose>
                                    </a>
                                </span>
                    </figure>

                    <header class="entry-header">
                        <h2 class="entry-title">
                            <a href="#"
                               rel="bookmark">
                                    ${article.articleTitle}
                            </a>
                        </h2>
                    </header>

                    <div class="entry-content">
                        <div class="archive-content">
                            <c:if test="${fn:length(article.articleSummary) > 166}">
                                ${fn:substring(article.articleSummary,0,166)}...
                            </c:if>
                            <c:if test="${fn:length(article.articleSummary )<= 166}">
                                ${article.articleSummary}
                            </c:if>
                        </div>
                        <span class="title-l"></span>
                        <span class="new-icon">
                                <%--                            <i class="fa fa-bookmark-o"></i>--%>
                        </span>
                        <span class="entry-meta">
                    <span>
                        <i class="fa-user-circle-o"></i>

                    </span>
                            <span class="date">
                                ——${article.user.userNickname}
                                <fmt:formatDate value="${article.articleCreateTime}" pattern="yyyy年MM月dd日"/>
                            &nbsp;&nbsp;
                            </span>


                            <span class="comment">&nbsp;&nbsp;
                                <a href="#" >
                                    <i class="fa fa-comment-o"></i>

                                    <c:choose>
                                        <c:when test="${article.articleCommentCount == 0}">
                                            发表评论
                                        </c:when>
                                        <c:otherwise>
                                            ${article.articleCommentCount}
                                        </c:otherwise>
                                    </c:choose>
                                </a>

                            </span>
                        </span>
                        <div class="clear"></div>
                    </div><!-- .entry-content -->

                    <span class="entry-more" >
                        <a href="/articleDel/${article.articleId}/${article.articleUserId}"
                           rel="bookmark">
                            删除文章
                        </a>
        </span>
                </article>
            </c:forEach>

        </main>
        <%@ include file="paging.jsp"%>

    </div>
</div>
</body>
</html>
