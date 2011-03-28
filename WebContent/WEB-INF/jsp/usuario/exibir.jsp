<head>
	<title>VRaptor | Usu&aacute;rio [exibir]</title>
</head>

<body>
	ID:		${entity.id}
	Nome:	${entity.nome}
	E-mail:	${entity.email}
	Senha:	${entity.senha} 
	Perfil:	${entity.perfil.label}

	(<a href="<c:url value='/usuario/${entity.id}/editar'/>">Editar</a> |
	<a href="<c:url value='/usuario'/>">Listagem</a>)

	<form action="<c:url value='/usuario/${entity.id}'/>" method="post">
		<input type="hidden" name="_method" value="delete"/>

		<input type="submit" value="Remover"/>
	</form>
</body>