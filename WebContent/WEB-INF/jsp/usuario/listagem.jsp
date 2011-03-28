<head>
	<title>VRaptor | <fmt:message key="usuario"/> [<fmt:message key="listagem"/>]</title>
</head>

<body>
	<c:forEach items="${entityList}" var="item">
		ID:		${item.id}
		Nome:	${item.nome}
		E-mail:	${item.email}
		Senha:	${item.senha} 
		Perfil:	${item.perfil.label} 

		(<a href="<c:url value='/usuario/${item.id}'/>"><fmt:message key="exibir"/></a> |
		<a href="<c:url value='/usuario/${item.id}/editar'/>"><fmt:message key="editar"/></a>) 

		<form action="<c:url value='/usuario/${item.id}'/>" method="post">
			<input type="hidden" name="_method" value="delete"/>

			<input type="submit" value="<fmt:message key="remover"/>"/>
		</form><br/>
	</c:forEach>
</body>