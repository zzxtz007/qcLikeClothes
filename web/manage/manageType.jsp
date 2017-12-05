<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/12/5
  Time: 16:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%@taglib prefix="c" uri="http://java.sun.com/jstl/core" %>--%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>招聘职位分类管理</title>
</head>
<style type="text/css">
    * {
        margin: 0px;
        padding: 0;
        box-sizing: border-box;
    }

    #Main {
        margin: 100px auto auto auto;
        width: 800px;
        height: 600px;
    }

    #lable {
        width: 800px;
        height: 100px;
        background-color: salmon;
    }

    #lable span {
        display: inline-block;
        width: 800px;
        height: 100px;
        line-height: 100px;
        font-size: 50px;
        font-weight: bold;
        text-align: center;
        color: white;
    }

    #table {
        width: 800px;
        height: 500px;
        background-color: #00eb32;
    }

    #control {
        width: 800px;
        height: 100px;
        background-color: paleturquoise;
    }

    .deleteButton {
        width: 20px;
        height: 24px;
        background: url(/img/ic_delete_black_24dp_2x.png) no-repeat center;
        background-size: 20px 20px;
        border: none;
        cursor: pointer;
        transition: all 400ms ease;
    }

    .updateButton {
        width: 20px;
        height: 24px;
        background: url(/img/ic_update_black_24dp_2x.png) no-repeat center;
        background-size: 20px 20px;
        border: none;
        cursor: pointer;
        transition: all 400ms ease;
    }

    table {
        width: 500px;
        margin: 0 auto;
    }

    tr {
        background-color: #4d90fe;
        box-shadow: #cbe1dd 2px 2px 2px;
        transition: all 300ms ease;
    }

    tr:hover {
        background-color: black;
        box-shadow: #cbe1dd 4px 4px 4px;
    }

    tr:hover .updateButton {
        background: url(/img/ic_update_white_24dp_2x.png) no-repeat center;
        background-size: 20px 20px;
    }

    tr:hover .deleteButton {
        background: url(/img/ic_delete_white_24dp_2x.png) no-repeat center;
        background-size: 20px 20px;
    }

    td {
        text-align: center;
        color: #ffffff;
    }


</style>
<body>
<div id="Main">
    <div id="lable"><span>招聘职位分类管理</span></div>
    <div id="table">
        <table id="showRecruitType">
            <c:if test="${!empty requestScope.types}">
                <c:forEach items="${requestScope.types}" var="type"
                           varStatus="status">
                    <tr id="${type.id}">
                        <td>${type.name}</td>
                        <td>${type.description}</td>
                        <td>${type.supName}</td>
                        <td>
                            <button class="updateButton"></button>
                        </td>
                        <td>
                            <button class="deleteButton"></button>
                        </td>
                    </tr>
                </c:forEach>
            </c:if>
        </table>
    </div>
    <div id="control"></div>
</div>
</body>
</body>
</html>