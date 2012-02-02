<head>
	<title>VRaptor | Starting Project</title>
</head>
<body>
	<c:if test="${userSession.user == null}">
		<form action="${pageContext.request.contextPath}/autenticar" method="post">
			E-mail: <input type="text" name="entity.email"/><br/>
			Senha: <input type="text" name="entity.senha"/><br/>
	
			<input type="submit" value="Autenticar" class="btn" />
		</form>
	</c:if>
</body>