<%@ taglib prefix="s" uri="/struts-tags" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>详细信息</title>
    <link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
    <script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <center><h1>详细信息</h1></center>
<table class="table">
    <tr>
        <td>ISBN:</td>
        <td><s:property value="book.ISBN"/></td>
    </tr>
    <tr>
        <td>题目:</td>
        <td><s:property value="book.title"/></td>
    </tr>
    <tr>
        <td>作者:</td>
        <td><s:property value="book.name"/></td>
    </tr>
    <tr>
        <td>出版社:</td>
        <td><s:property value="book.publisher"/></td>
    </tr>
    <tr>
        <td>出版日期:</td>
        <td><s:property value="book.publishDate"/></td>
    </tr>
    <tr>
        <td>作者年龄:</td>
        <td><s:property value="book.age"/></td>
    </tr>
    <tr>
        <td>作者国家:</td>
        <td><s:property value="book.country"/></td>
    </tr>
</table>
</div>
</body>
</html>
