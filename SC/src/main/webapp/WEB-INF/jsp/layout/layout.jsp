<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<title>숏컷</title>
</head>
<body>
	<!-- header -->
	<div id="header">
		<tiles:insertAttribute name="header"/>
	</div>
	
	<!-- left -->
	<div id="left">
		<tiles:insertAttribute name="left"/>
	</div>
	 	
	<!-- container --> 
	<div id="container" style="min-height:500px;">
		<tiles:insertAttribute name="body"/>
	</div>
	
	<!-- footer --> 
	<tiles:insertAttribute name="footer"/>
</body>
</html>