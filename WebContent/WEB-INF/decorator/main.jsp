<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"/>
		<link type="image/x-icon" rel="shortcut icon" href="<c:url value='/favicon.ico'/>"/>

		<meta name="description" content="VRaptor Starting Project" />
		<meta name="author" content="Washington Botelho dos Santos"/>
		<meta name="keywords" content="vraptor,starting,project"/>

		<link type="text/css" rel="stylesheet" href="<c:url value='/css/stylesheet.css'/>"/>

		<script type="text/javascript" src="<c:url value='/js/1-jquery-1.5.2.js'/>"></script>
		<script type="text/javascript" src="<c:url value='/js/jquery-ui-1.8.11.js'/>"></script>

		<script type="text/javascript" src="<c:url value='/js/javascript.js'/>" charset="utf-8"></script>

		<title><decorator:title default="VRaptor | Starting Project"/></title>
	</head>
	<body>
		<div id="mensagem"></div>

		<c:if test="${not empty errors}">
			<div id="errors" class="error">
				<c:forEach var="error" items="${errors}">
			  		${error.category} - ${error.message}<br/>
				</c:forEach>
			</div>
		</c:if>

		<c:if test="${not empty error}">
			<div id="error" class="error">${error}</div>
		</c:if>

		<c:if test="${not empty message}">
			<div id="message" class="message">${message}</div>
		</c:if>

		<decorator:body/>

		<script type="text/javascript">
			function dummy() {
				alert('Dummy function! =~');
			};

			$.ajaxSetup({
				contentType: 'application/x-www-form-urlencoded; charset=utf-8',
				error: function() {
					mensagem('<fmt:message key="erro"/>', '<fmt:message key="erro.ajax"/>');
				}
			});

			$(function() {
				console.log('Ready! (:');
			});
		</script>
	</body>
</html>