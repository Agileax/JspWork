<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" type="text/css" href="${path }/res/css/common.css">
		<link rel="stylesheet" type="text/css" href="${path }/res/css/login.css">
		<script type="text/javascript" src="${path }/res/js/jquery.js"></script>
		<script type="text/javascript">var contextPath = "${path}";</script>
		<script type="text/javascript" src="${path }/res/js/login.js"></script>
		<title>欢迎登陆</title>
	</head>
	<body class="coding_style">
		<table class="code" border="0" cellspacing="0">
			<tr><td class="rownum">1</td><td><span class="gray">&lt;%--</span></td></tr>
			<tr><td class="rownum">2</td><td><span class="gray">&emsp;&emsp;欢迎使用 [企业信息管理系统]</span></td></tr>
			<tr><td class="rownum">3</td><td><span class="gray">&emsp;&emsp;作者 [任英鑫] [伍攀]</span></td></tr>
			<tr><td class="rownum">4</td><td><span class="gray">--%&gt;</span></td></tr>
			<tr><td class="rownum">5</td><td>&lt;html&gt;</td></tr>
			<tr><td class="rownum">6</td><td>&emsp;&emsp;&lt;head&gt;</td></tr>
			<tr><td class="rownum">7</td><td>&emsp;&emsp;&emsp;&emsp;&lt;title&gt;登录&lt;/title&gt;</td></tr>
			<tr><td class="rownum">8</td><td>&emsp;&emsp;&lt;/head&gt;</td></tr>
			<tr><td class="rownum">9</td><td>&emsp;&emsp;&lt;body&gt;</td></tr>
			<tr><td class="rownum">10</td><td>&emsp;&emsp;&emsp;&emsp;&lt;form action="login" method="POST"&gt;</td></tr>
			<tr><td class="rownum">11</td><td>&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&lt;input type="text" name="<span class="white">username</span>" value="<input id="username"/>"/&gt;</td></tr>
			<tr><td class="rownum">12</td><td>&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&lt;input type="password" name="<span class="white">password</span>" value="<input id="password" type="password"/>"/&gt;</td></tr>
			<tr><td class="rownum">13</td><td>&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&lt;input type="submit" value="<button id="submitButton" onclick="login()">登录</button>"/&gt;</td></tr>
			<tr><td class="rownum">14</td><td>&emsp;&emsp;&emsp;&emsp;&lt;/form&gt;</td></tr>
			<tr><td class="rownum">15</td><td>&emsp;&emsp;&lt;/body&gt;</td></tr>
			<tr><td class="rownum">16</td><td>&lt;/html&gt;</td></tr>
		</table>
	</body>
</html>