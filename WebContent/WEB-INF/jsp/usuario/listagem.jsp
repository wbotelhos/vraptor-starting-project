<head>
	<title>VRaptor | <fmt:message key="usuario"/> [<fmt:message key="listagem"/>]</title>
</head>

<body>
	<c:forEach items="${entityList}" var="entity">
		 | ${entity.id} | ${entity.nome} | ${entity.email} | ${entity.senha} | 

		<form action="<c:url value='/usuario'/>" method="post">
			<input type="hidden" name="_method" value="put"/>
			<input type="hidden" name="entity.id" value="${entity.id}"/>

			<input type="submit" value="<fmt:message key="editar"/>"/>
		</form> 

		<form action="<c:url value='/usuario'/>" method="post">
			<input type="hidden" name="_method" value="delete"/>
			<input type="hidden" name="entity.id" value="${entity.id}"/>

			<input type="submit" value="<fmt:message key="remover"/>"/>
		</form><br/>
	</c:forEach>
</body>