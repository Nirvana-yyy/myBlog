
<%@taglib uri="http://www.rapid-framework.org.cn/rapid" prefix="rapid" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
<%--    <script src="../../resources/js/showdown-1.9.1/dist/showdown.min.js"></script>--%>
    <script src="../../resources/js/jquery-2.1.0.min.js"></script>
    <%--    引入layui的css文件--%>
    <link href="../../resources/plugin/layui/css/layui.css" rel="stylesheet" type="text/css"/>
    <%--引入代码高亮的样式--%>
<%--    <link href="../../resources/js/highlight/tomorrow-night.min.css" rel="stylesheet" type="text/css"/>--%>
<%--    &lt;%&ndash;引入highlight.js&ndash;%&gt;--%>
<%--    <script src="../../resources/js/highlight/highlight.min.js"/>--%>
    <%--    引入layui的js文件--%>
    <script src="../../resources/plugin/layui/layui.js" type="text/javascript" charset="UTF-8"></script>
    <%--    &lt;%&ndash;    引入style.css文件&ndash;%&gt;--%>
    <%--    <link href="../../resources/css/style.css" rel="stylesheet">--%>
    <link rel="stylesheet" type="text/css" href="../../../resources/plugin/editormd/css/editormd.min.css">

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
<%@include file="header.jsp"%>
<rapid:override name="content">


        <div id="articleHeader" style="background-color:#fff;height: 60px;line-height: 60px;">
              <h1>${article.articleTitle}</h1>
        </div>
        <div style="background-color: #f4f5f5; height: 30px;">
            <div id="user" style="float: right">
                <a href="#">——${user.userNickname}</a>
            </div>
        </div>
        <div class="editormd-preview" style="display: block;top: 0px;width: 100%;height: max-content;padding-bottom:150px;position: relative">
            <div class="markdown-body editormd-preview-container" previewcontainer='true' style="padding:20px;">
                    ${article.articleContent}
            </div>
            <div style="background-color: #fff;position: absolute;bottom: 0px;margin-left: 200px">
                <form action="/comment/${article.articleId}/${user.userId}">

                    <div >
                        <textarea name="addComment" placeholder="请输入评论内容" class="layui-textarea" style="width: 80%;background-color:#fff" rows="5" cols="80"></textarea>
                    </div>
                    <input type="submit" value="发表评论" style="margin-left: 200px;background-color: #00aaaa;">

                </form>

            </div>
        </div>

        <div>
            <img src="../../../../resources/img/background/divider.png" width="100%" height="30px">
        </div>

    <div style="width: 800px;box-sizing: border-box;background-color:#f4f5f5;margin-top: 2px;table-layout: fixed  ">
        <table style="background-color: #fff; padding: 10px" width="106%">

            <%@include file="recursive.jsp"%>
            <%--<c:forEach items="${commentNodes}" var="node">

            </c:forEach>--%>
        </table>
    </div>

</rapid:override>

<%@include file="sideBlock1.jsp"%>

<%@include file="footer.jsp"%>
</body>
<%--<script type="text/javascript">
    $(function() {

        var converter = new showdown.Converter();
        converter.setOption("tables",true);
        converter.setOption("tasklists",true);
        converter.setOption("emoji",true);
        converter.setOption("simplifiedAutoLink",true);
        converter.setOption("literalMidWordUnderscores",true);
        converter.setOption("strikethrough",true);
        // converter.setOption("simpleLineBreaks",true);
        converter.setOption("parseImgDimensions",true);
        ///bug:当字符串中有换行时，js会自动加分号
        &lt;%&ndash;var text = '${article.articleContent}';&ndash;%&gt;
        var text = document.getElementById("html").innerText;
        // var text = "## 二十四、Iterator迭代器\n" +
        //     "由于Collection集合没有带索引的方法，故不能用一般的for循环来遍历集合，此时就需要用到Iterator迭代器了"
        html      = converter.makeHtml(text);
        // alert(html);
        var span = $("#html");

        span.html(html);

    });


</script>
<script>
    hljs.initHighlightingOnLoad();
</script>--%>

<script src="../../../resources/js/jquery-2.1.0.min.js"></script>
<%--引入editormd的js文件--%>
<script src="../../../resources/js/editormd.min.js" type="text/javascript"></script>
<%--初始化markdown--%>
<script type="text/javascript">
    //初始化Markdown编辑器
    var contentEditor;
    $(function() {
        contentEditor = editormd("md-content", {
            saveHTMLToTextarea   :true,
            width   : "100%",//宽度
            height  : 500,//高度
            syncScrolling : "single",//单滚动条
            path    : "../../../resources/plugin/editormd/lib/"//依赖的包路径
        });


    });
</script>

</html>