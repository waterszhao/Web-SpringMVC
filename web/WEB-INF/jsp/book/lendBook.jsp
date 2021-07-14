<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>已借阅书本</title>
    <link href="https://cdn.jsdelivr.net/npm/@bootcss/v3.bootcss.com@1.0.10/examples/signin/signin.css"
          rel="stylesheet">

    <link href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <div class="row clearfix col-md-12 column page-header">
        <div class="col-md-8 column">
            <h1>已借阅书本</h1>
        </div>
        <div class="col-md-4 row clearfix">
            <c:choose>
                <c:when test="${cookie.userName != null}">
                    <span>欢迎您，<a
                            href="${pageContext.request.contextPath}/user/toUpdateUser?userID=${cookie.userID.value}">${cookie.userName.value}</a></span>
                    <span><a
                            href="${pageContext.request.contextPath}/user/logout?userID=${cookie.userID.value}&&controlLevel=${sessionScope.control}"
                            class="btn btn-primary">退出登录</a></span>
                    <span><a class="btn btn-primary" href="${pageContext.request.contextPath}/book/toLendBook?userID=${cookie.userID.value}">我借的书</a></span>
                </c:when>
                <c:otherwise>
                        <span>欢迎您，游客！您可以&nbsp;&nbsp;<a href="${pageContext.request.contextPath}/user/toLogin"
                                                       class="btn-primary btn">登录</a>
                        </span>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
    <br/>

    <div class="row clearfix">
        <div class="col-md-6 column">
            <div class="col-md-4 column ">
                <a href="${pageContext.request.contextPath}/book/allBook" class="btn btn-primary ">返回</a>
            </div>
        </div>
        <div class="col-md-2"><span style="color: red;font-size: larger;font-weight: bold;">${error}</span></div>
        <div class="col-md-4 center">
            <form class="form-inline" action="${pageContext.request.contextPath}/book/searchLendBook" method="get">
                <input type="text" name="name" placeholder="请输入想要查询的书本名称" class="text">&nbsp;&nbsp;
                <input type="submit" value="查询" class="btn btn-primary">
                <input type="hidden" name="userID" value="${cookie.userID.value}">
            </form>
        </div>
    </div>
    <div class="row clearfix">
        <div class="col-md-12 column">
            <table class="table table-hover table-striped">
                <tr>
                    <th>书籍编号</th>
                    <th>书籍名称</th>
                    <th>书籍详情</th>
                    <th>借出时间</th>
                    <th>归还时间</th>
                    <c:if test="${sessionScope.control > 0}">
                        <th>操作</th>
                    </c:if>
                </tr>
                <tbody>
                <c:forEach var="book" items="${lendBooks}">
                <tr>
                    <th>${book.bookID}</th>
                    <th>${book.bookName}</th>
                    <th>${book.detail}</th>
                    <th>${book.lendTime}</th>
                    <th>${book.returnTime}</th>
                    <th>
                    <c:choose>
                        <c:when test="${sessionScope.control==1 && book.returnTime.equals('not return')}">
                                <a class="btn btn-primary" href="${pageContext.request.contextPath}/book/returnBook?lendID=${book.lendID}&bookID=${book.bookID}">归还</a> &nbsp;
                        </c:when>
                    </c:choose>
                    </th>
                </tr>
                </c:forEach>
            </table>
        </div>
    </div>
</div>
</body>
</html>
