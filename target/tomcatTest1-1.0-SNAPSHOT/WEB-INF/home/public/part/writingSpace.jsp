
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%--引入editormd的css文件--%>
    <link rel="stylesheet" type="text/css" href="../../../resources/plugin/editormd/css/editormd.min.css">

    <%--引入editormd的js文件
    <script src="../../../resources/plugin/editormd/editormd.min.js" type="text/javascript" charset="UTF-8"/>--%>

</head>
<body>

<header>
    文章标题：<input type="text" id="articleTitle" />
    类别：
    <select id="articleCategory">
    <option value="1">后端</option>
    <option value="2">前端</option>
    <option value="3">嵌入式</option>
    <option value="4">机器学习</option>
    </select>
    <span id="btnList">
				<button type="button" id="publishArticle" onclick="writeArticle.doSubmit();" class="btn btn-info">发布文章</button>
			</span>
</header>
<div id="md-content" style="z-index: 1 !important;">
    <textarea id="articleContent"></textarea>
</div>
<%--引入jq--%>
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
        });/*var previewedHTML = contentEditor.getPreviewedHTML();
        var markdown = contentEditor.getMarkdown();
        var html = contentEditor.getHTML();
        alert(previewedHTML);
        alert(markdown);
        alert(html);*/
    });
</script>
<script>
var writeArticle = {
    doSubmit: function () {
        var title = $("#articleTitle").val();
        var type = $("#articleCategory").val();
        var content = $("#articleContent").val();
        var userId = '${user.userId}';
        // alert(userId);
       /*
        var markdown = contentEditor.getMarkdown();*/
         content = contentEditor.getPreviewedHTML();
        // alert(content);
        /*alert(html);
        alert(markdown);
        alert(previewedHTML);*/
        var html = contentEditor.getHTML();
        var summary = html.replace(/<[^>]*>|/g,"");

        var arg = {'articleTitle':title,'articleContent':content,'articleType':type,'articleSummary':summary};
        $.ajax({
            type:"POST",
            url:"/writing/${user.userId}",
            data:JSON.stringify(arg),
            dataType:'json',
            contentType:"application/json",
            success: function (data) {
                if (data.flag){
                    location.href = "/user/${user.userId}";
                }else{
                    alert(data.errorMsg);
                }
            }
        });

    }
}
</script>
</body>
</html>
