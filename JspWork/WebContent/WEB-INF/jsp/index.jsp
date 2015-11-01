<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" type="text/css" href="${path }/res/css/common.css">
		<link rel="stylesheet" type="text/css" href="${path }/res/css/index.css">
		<script type="text/javascript" src="${path }/res/js/jquery.js"></script>
		<script type="text/javascript" src="${path }/res/js/menuData.js"></script>
		<script type="text/javascript">var contextPath = "${path}";</script>
		<script type="text/javascript" src="${path }/res/js/index.js"></script>
		<title>企业信息管理系统</title>
	</head>
	<body>
		<div class="topbar">
			<div class="brand">企业信息管理系统</div>
			<div class="logout" onclick="logout()" style="color:#fafafa">退出登录</div>
			<div class="author">作者：任英鑫 伍攀</div>
		</div>
		<div class="leftbar">
			<div style="height:100px;border-bottom:#222 1px solid"></div>
			<div id="menu"></div>
		</div>
		<div class="main">
			<p class="title" id="title"></p>
			<div class="toolbar">
				<span><a href="javascript:newData()">新建</a></span>
				<span><a href="javascript:editData()">编辑</a></span>
				<span><a href="javascript:deleteData()">删除</a></span>
			</div>
			<div><table id="dataTable"></table></div>
		</div>
		<div id="dataLayer" style="display:none">
			<div id="dataPanel">
				<p></p>
				<table id="dataForm"></table>
				<p></p>
				<p id="action">
					<button onclick="saveData()">保存</button>
					<button onclick="closeLayer()">取消</button>
				</p>
			</div>
		</div>
	</body>
</html>