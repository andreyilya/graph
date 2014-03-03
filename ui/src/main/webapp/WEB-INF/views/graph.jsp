<%@ page language="java" contentType="text/html; charset=utf8"
         pageEncoding="utf8" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="parts" tagdir="/WEB-INF/tags/parts" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf8">
    <meta http-equiv="Content-Script-Type" content="text/javascript; charset=utf-8">
    <%--//TODO: to variable path--%>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/resources/css/style.css">
    <title>Граф</title>
</head>
<body>


<div class="notes">
    <h1>Граф</h1>

    <div class="wrapper">
        <div id="leftSidebar">
            <form id="queryGraph" method="post" action="" onsubmit='return false;'>
                <parts:formfield label="Целевой объект" value="target"/>
                <parts:formfield label="Глубина рекурсии" value="depth"/>
                <button id="queryGraphButton">Запросить</button>
            </form>
        </div>
        <div id="graphContent">
            <canvas id="viewport"></canvas>
        </div>
    </div>
</div>
<div class="contextMenu" id="contextMenu" >
    <ul>
        <li id="createNode"> Create node</li>
        <li id="deleteNode"> Delete node</li>
    </ul>

</div>
<script src="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/resources/js/jquery.min.js" type="text/javascript"></script>
<script src="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/resources/js/arbor.js" type="text/javascript"></script>
<script src="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/resources/js/arbor-tween.js" type="text/javascript"></script>
<script src="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/resources/js/contextmenu.js" type="text/javascript"></script>
<script src="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/resources/js/init-graph.js" type="text/javascript"></script>
</body>
</html>