<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<head>
    <title>Bootstrap 实例</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<body>

<div class="container">
    <div class="jumbotron">
        <h1>我的第一个 Bootstrap 页面</h1>
        <p>重置窗口大小，查看响应式效果！</p>
    </div>
    <div class="row">
        <div class="col-sm-4">
            <h3>第一列</h3>
            <p>学的不仅是技术，更是梦想！</p>
            <p>再牛逼的梦想,也抵不住你傻逼似的坚持！</p>
        </div>
        <div class="col-sm-4">
            <h3>第二列</h3>
            <p>学的不仅是技术，更是梦想！</p>
            <p>再牛逼的梦想,也抵不住你傻逼似的坚持！</p>
        </div>
        <div class="col-sm-4">
            <h3>第三列</h3>
            <p>学的不仅是技术，更是梦想！</p>
            <p>再牛逼的梦想,也抵不住你傻逼似的坚持！</p>
        </div>
    </div>
</div>

</body>
</html>
