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

    <form class="form-signin" action="${pageContext.request.contextPath}/user/register" method="post">

        <label class="text-primary"> * 用户名:</label>
        <input type="text" name="userName" placeholder="请输入用户名" class="form-control" value="${user.userName}"
               required><br/>

        <label class="text-primary"> * 密&nbsp;&nbsp;码:</label>
        <input type="password" name="password" placeholder="请输入密码" class="form-control" value="${user.password}"
               required>

        <label class="text-primary"> * 手机号码：</label>
        <input type="tel" name="telephone" placeholder="请输入手机号" class="form-control" value="${user.telephone}" required>

        <label class="text-primary"> 邮&nbsp;&nbsp;箱: 用于找回密码</label>
        <input type="email" name="email" placeholder="请输入邮箱" class="form-control" value="${user.email}"><br/>

        <input type="submit" value="注册" class="btn btn-lg btn-primary btn-block">&nbsp;&nbsp;&nbsp;&nbsp;
        <a href="${pageContext.request.contextPath}/book/allBook" class="btn btn-primary form-control">返回</a>

    </form>

</div>
</body>
</html>
