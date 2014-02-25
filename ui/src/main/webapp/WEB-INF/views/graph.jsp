<%@ page language="java" contentType="text/html; charset=utf8"
         pageEncoding="utf8" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf8">
    <meta http-equiv="Content-Script-Type" content="text/javascript; charset=utf-8">
    <%--//TODO: absolute path--%>
    <link rel="stylesheet" type="text/css" href="resources/css/style.css">
    <title>Граф</title>
</head>
<body>


<div class="notes">
    <h1>Граф</h1>

    <div class="wrapper">
        <div id="leftSidebar">

        </div>
        <div id="graphContent">
            <canvas id="viewport"></canvas>
        </div>
    </div>
</div>

<script src="resources/js/jquery.min.js" type="text/javascript"></script>
<script src="resources/js/arbor.js" type="text/javascript"></script>
<script src="resources/js/arbor-tween.js" type="text/javascript"></script>
<script src="resources/js/init-graph.js" type="text/javascript"></script>
</body>
</html>