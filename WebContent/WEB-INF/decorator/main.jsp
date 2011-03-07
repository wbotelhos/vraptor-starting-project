<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"/>
		<link type="image/x-icon" rel="shortcut icon" href="<c:url value='/favicon.ico'/>"/>

		<meta name="description" content="VRaptor Blank Project" />
		<meta name="author" content="Washington Botelho dos Santos"/>
		<meta name="keywords" content="vraptor,blank,project"/>

		<link type="text/css" rel="stylesheet" href="<c:url value='/css/stylesheet.css'/>"/>

		<script type="text/javascript" src="<c:url value='/js/jquery.js'/>"></script>
		<script type="text/javascript" src="<c:url value='/js/jquery-ui.js'/>"></script>

		<title><decorator:title default="VRaptor | Blank Project"/></title>
	</head>
	<body>
		<c:if test="${not empty errors}">
			<div id="error">
				<c:forEach var="error" items="${errors}">
			  		${error.category} - ${error.message}<br/>
				</c:forEach>
			</div>
		</c:if>

		<c:if test="${not empty message}">
			<div id="message">
				<c:if test="${not empty message}">
					${message}<br/><br/>
				</c:if>
			</div>
		</c:if>

		<decorator:body/>

		<script type="text/javascript">
			$(function() {
				console.log('Ready! (:');
			});
		</script>
	</body>
</html>