<%@ page import="com.nirvana.domain.extend.CommentNode" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:if test="${commentNodes.size() != 0}">
    <c:forEach items="${commentNodes}" var="nodes">
        <c:choose>
            <c:when test="${nodes.commentLastId != null}">
                <tr id="reply"><td style="padding-left: 40px;height: 20px"><span>${nodes.user.userNickname}回复了${nodes.commentLastNickname}</span></td> </tr>
                <tr id="haveNext">
                    <td style="padding-left:40px;width: 20%"><img src="../../resources${nodes.user.userAvatar}"width="40px" height="40px"><span style="font-size: xx-small">${nodes.user.userNickname}</span></td>
                    <td style="width: 80%">
                        ${nodes.commentContent}
                        <span style="float:right;padding-top:10px;font-size: xx-small"><fmt:formatDate value="${nodes.commentTime}" pattern="yyyy年MM月dd日"></fmt:formatDate> </span>
                            <span style="float: right;font-size: xx-small;padding-right: 2px"><a style="color: #2D93CA" href="javascript:;"class="reply_btn">回复</a></span>
                    </td>

                </tr>
                </tr>
                <tr style="background-color: #f4f5f5;"><td>&nbsp;&nbsp;&nbsp;</td><td>&nbsp;&nbsp;&nbsp;</td></tr>
            </c:when>
            <c:otherwise>
                <tr id="nullNext">
                    <td style="padding-left:20px;width: 20%"><img src="../../resources${nodes.user.userAvatar}"width="40px" height="40px"><span style="font-size: xx-small">${nodes.user.userNickname}</span></td>

                <%--                    <div name="none" style="display: none">&lt;%&ndash;${nodes.commentId}&ndash;%&gt;1</div>--%>
<%--                    <input type="hidden" value="${nodes.commentId}" id="pro_id"/>--%>
                    <td style="width: 80%">
                            ${nodes.commentContent}
                            <div></div>
                            <span style="float:right;padding-top:10px;font-size: xx-small"><fmt:formatDate value="${nodes.commentTime}" pattern="yyyy年MM月dd日"/></span>
                            <span style="float: right;font-size: xx-small;padding-right: 2px"><a style="color: #2D93CA" name="${nodes.commentId}" href="javascript:;"class="reply_btn">回复</a></span>
                    </td>

                </tr>
                <%--<tr id="reply-comment-area">
                    <td style="width: 20%"></td>
                    <td style="width: 60%">
                        <form action="#">
                            <textarea style="border:#2D93CA solid;border-width:1 1 1 1;color:#CCCCCC;" rows="3" cols="40" placeholder="请输入回复内容"></textarea>
                            <input type="submit" value="发表回复">
                        </form>
                    </td>
                    <td style="width: 20%;"></td>
                </tr>--%>
                <tr style="background-color: #f4f5f5;"><td>&nbsp;&nbsp;&nbsp;</td><td>&nbsp;&nbsp;&nbsp;</td></tr>
            </c:otherwise>
        </c:choose>


    <c:choose>
        <c:when test="${nodes.nextNode.size() != 0}">

            <c:set var="commentNodes" value="${nodes.nextNode}" scope="request"/>
            <jsp:include page="recursive.jsp"/>
        </c:when>
        <c:otherwise>

        </c:otherwise>
    </c:choose>

    </c:forEach>
</c:if>
<script type="text/javascript">
   $(function () {
//页面加载完成之后执行

       $(".reply_btn").click(function () {
           $(".reply_textarea").remove();
           var val = $(this).attr("name");<!---->
           $(this).parent().append("<div class='reply_textarea'><form action='' method='get'><textarea name='nextCommentContent' cols='40' rows='5'></textarea><br/><input type='button' class='pro_id' name='"+val+"' value='发表'  onclick='tpformsubmit()'/></div></form>")


       })

   });

</script>
<script type="text/javascript">

    //带参数提交表单
    function tpformsubmit(e) {
        <%--var lastId = "${nodes.commentId}";//不知道为什么获取不到值--%>
        // var lastId = document.getElementById("none").innerHTML;
        var lastId =this.name;
        alert(e);

        var articleId = "${article.articleId}";
        var userId = "${user.userId}";
        var nextCommentContent = $('textarea[name=nextCommentContent]').val();
        url = '/comment/'+articleId+'/'+userId+'?addComment='+nextCommentContent+'&lastId='+lastId;
        window.location.href  = url;
    }

</script>
