<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册</title>

    <link href="https://cdn.jsdelivr.net/npm/@bootcss/v3.bootcss.com@1.0.10/examples/signin/signin.css"
          rel="stylesheet">

    <link href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<div class="container">

    <div class="row column page-header text-center">
        <h1>用户注册</h1>
    </div>
    <div class="row">
        <div class="col-md-12 text-center">
            <span style="font-weight: bold;color: red">${error}</span>
        </div>
    </div>

    <form class="form-signin" action="${pageContext.request.contextPath}/user/login" method="post">

        <label class="text-primary"> 用户名:</label>
        <input type="text" name="userName" placeholder="请输入用户名" class="form-control" required><br/>

        <label class="text-primary"> 密&nbsp;&nbsp;码:</label>
        <input type="password" name="password" placeholder="请输入密码" class="form-control" required>
        <a href="${pageContext.request.contextPath}/user/forgetPassword">忘记密码？</a><br/><br/>

        <div class="col-md-12">
            <div class="col-md-6">
                <input type="submit" value="登录" class="btn btn-lg btn-primary btn-block">&nbsp;&nbsp;&nbsp;&nbsp;
            </div>
            <div class="col-md-6">
                <a href="${pageContext.request.contextPath}/user/toRegister"
                   class="btn btn-lg btn-primary btn-block">注册</a>
            </div>
        </div>

    </form>

</div>
</body>
</html>
