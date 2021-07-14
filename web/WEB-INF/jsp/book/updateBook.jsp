<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>修改书籍</title>

    <link href="https://cdn.jsdelivr.net/npm/@bootcss/v3.bootcss.com@1.0.10/examples/signin/signin.css"
          rel="stylesheet">

    <link href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<c:if test="${sessionScope.control != 2}">
    <%
        response.sendRedirect("allBook");
    %>
</c:if>

<div class="container">
    <div class="row clearfix">
        <div class="col-md-12">
            <div class="page-header">
                <h1>修改书籍信息</h1>
            </div>
        </div>
    </div>

    <form action="${pageContext.request.contextPath}/book/updateBook" method="post">

        <input type="hidden" name="bookID" value="${book.bookID}">
        <div class="form-group">
            <label>书籍名称</label>
            <input type="text" name="bookName" class="form-control" value="${book.bookName}" required/>
        </div>
        <div class="form-group">
            <label>书籍数量</label>
            <input type="text" name="bookCount" class="form-control" value="${book.bookCount}" required/>
        </div>
        <div class="form-group">
            <label>书籍描述</label>
            <input type="text" name="detail" class="form-control" value="${book.detail}" required/>
        </div>
        <input type="submit" value="提交" class="btn-primary form-control">&nbsp;&nbsp;&nbsp;
        <a href="${pageContext.request.contextPath}/book/allBook" class="form-control btn btn-primary">返回</a>
    </form>

</div>
</body>
</html>
