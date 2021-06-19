<%--数据展示--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<main id="main" class="site-main" role="main" style="padding-top: 20px">
    <c:forEach items="${pageInfo.list}" var="article">
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
                    <a href="/article/${article.articleId}"
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
                                <a href="/article/${article.articleId}" >
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

            <span class="entry-more">
                        <a href="/article/${article.articleId}"
                           rel="bookmark">
                            阅读全文
                        </a>
        </span>
        </article>
    </c:forEach>

</main>
<%@ include file="paging.jsp"%>
