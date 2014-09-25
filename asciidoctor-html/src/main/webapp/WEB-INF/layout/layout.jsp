<!doctype html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><sitemesh:write property="title"/></title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" href="${contextPath}/static/css/bootstrap.css"/>
    <link rel="stylesheet" type="text/css" href="${contextPath}/static/css/bootstrap-theme.css"/>
    <script type="text/javascript" src="${contextPath}/static/js/jquery-2.1.1.js"></script>
    <script type="text/javascript" src="${contextPath}/static/js/bootstrap.js"></script>
    <sitemesh:write property="head"/>
</head>
<body>
<nav class="navbar navbar-inverse" role="navigation">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse"
                    data-target="#bs-example-navbar-collapse-1">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
        </div>
        <div class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <li><a href="${contextPath}/">Document list</a></li>
            </ul>
        </div>
    </div>
</nav>
<div class="container-fluid">
    <sitemesh:write property="body"/>
</div>
</body>
</html>