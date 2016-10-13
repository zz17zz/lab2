<%@ taglib prefix="s" uri="/struts-tags" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>图书管理::首页</title>
    <link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
    <script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <nav class="navbar navbar-default" role="navigation">
        <div class="container-fluid">
            <form action="find.action" method="post" class="navbar-form navbar-left" role="form">
                <div class="form-group">
                    <s:textfield required="true" name="name" class="form-control" placeholder="请输入作者姓名"/>
                </div>
                <div class="form-group">
                    <s:submit value="查询" class="btn btn-default"/>
                </div>
            </form>
        </div>
    </nav>
    <table align="center" class="table">
        <tr>
            <th>ISBN</th><th>题目</th><th>作者</th><th>出版社</th><th>选项</th>
        </tr>
        <s:iterator id="u" value="list">
            <tr>
                <td><s:property value="#u.ISBN"/> </td>
                <td><a href="detail.action?ISBN=<s:property value="#u.ISBN"/>"><s:property value="#u.title"/></a></td>
                <td><s:property value="#u.name"/> </td>
                <td><s:property value="#u.publisher"/> </td>
                <td>
                    <a href="delete.action?ISBN=<s:property value="#u.ISBN"/>">删除</a>
                </td>
                <td></td>
            </tr>

        </s:iterator>

    </table>
</div>
</body>
</html>
