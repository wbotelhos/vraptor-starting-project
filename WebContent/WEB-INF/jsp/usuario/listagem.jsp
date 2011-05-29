<head>
	<title>VRaptor | Usu&aacute;rio [listagem]</title>
</head>

<body>
	<c:forEach items="${entityList}" var="item">
		ID:		${item.id}
		Nome:	${item.nome}
		E-mail:	${item.email}
		Senha:	${item.senha} 
		Perfil:	${item.perfil.label} 

		(<a href="${pageContext.request.contextPath}/usuario/${item.id}">Exibir</a> |
		<a href="${pageContext.request.contextPath}/usuario/${item.id}/editar">Editar</a>) 

		<form action="${pageContext.request.contextPath}/usuario/${item.id}" method="post">
			<input type="hidden" name="_method" value="delete"/>

			<input type="submit" value="Remover"/>
		</form><br/>
	</c:forEach>
</body>