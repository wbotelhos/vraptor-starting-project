<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"/>
		<link type="image/x-icon" rel="shortcut icon" href="${pageContext.request.contextPath}/favicon.ico"/>

		<meta name="description" content="VRaptor Starting Project" />
		<meta name="author" content="Washington Botelho dos Santos"/>
		<meta name="keywords" content="vraptor,starting,project"/>

		<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/stylesheet.css"/>

		<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/js/1-jquery-1.6.2.min.js"></script>
		<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/js/jquery-ui-1.8.11.js"></script>
		<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/js/jquery.colorbox-1.3.16.min.js"></script>

		<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/js/javascript.js"></script>

		<title><decorator:title default="VRaptor | Starting Project"/></title>
	</head>
	<body>
		<div id="mensagem"></div>

		<div id="wrapper">
			<div id="topo"><%@ include file="/topo.jsp" %></div>
			<div id="menu"><%@ include file="/menu.jsp" %></div>
	
			<div id="content">
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
			</div>
	
			<div id="rodape"><%@ include file="/rodape.jsp" %></div>
		</div>

		<script type="text/javascript">
			function dummy() {
				alert('Dummy function! =~');
			};

			$.ajaxSetup({
				type: 'GET',
				dataType: 'json',
				jsonp: false,
				contentType: 'application/x-www-form-urlencoded; charset=utf-8',
				error: function(xhr, status, error) {
					mensagem('<fmt:message key="erro"/>', getError(xhr));
				}
			});

			$(function() {
				console.log('Ready! (:');
			});
		</script>
	</body>
</html>