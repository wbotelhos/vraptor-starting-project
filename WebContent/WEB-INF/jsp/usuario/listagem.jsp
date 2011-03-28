<head>
	<title>VRaptor | <fmt:message key="usuario"/> [<fmt:message key="listagem"/>]</title>
</head>

<body>
	<c:forEach items="${entityList}" var="entity">
		ID:		${entity.id}
		Nome:	${entity.nome}
		E-mail:	${entity.email}
		Senha:	${entity.senha} 

		(<a href="<c:url value='/usuario/${entity.id}/editar'/>"><fmt:message key="editar"/></a>) 

		<form action="<c:url value='/usuario/${entity.id}'/>" method="post">
			<input type="hidden" name="_method" value="delete"/>

			<input type="submit" value="<fmt:message key="remover"/>"/>
		</form><br/>
	</c:forEach>
</body>