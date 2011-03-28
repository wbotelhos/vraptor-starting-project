<head>
	<title>VRaptor | <fmt:message key="usuario"/> [<fmt:message key="exibir"/>]</title>
</head>

<body>
	ID:		${entity.id}
	Nome:	${entity.nome}
	E-mail:	${entity.email}
	Senha:	${entity.senha} 

	(<a href="<c:url value='/usuario/${entity.id}/editar'/>"><fmt:message key="editar"/></a> |
	<a href="<c:url value='/usuario'/>"><fmt:message key="listagem"/></a>)

	<form action="<c:url value='/usuario/${entity.id}'/>" method="post">
		<input type="hidden" name="_method" value="delete"/>

		<input type="submit" value="<fmt:message key="remover"/>"/>
	</form>
</body>