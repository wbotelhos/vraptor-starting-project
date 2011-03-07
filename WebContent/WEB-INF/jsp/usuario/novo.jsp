<head>
	<title>VRaptor | <fmt:message key="usuario"/> [<fmt:message key="novo"/>]</title>
</head>

<body>
	<fieldset>
		<legend>Usu&aacute;rio</legend>

		<form action="<c:url value='/usuario'/>" method="post">
			<input type="hidden" name="entity.id" value="${entity.id}"/>

			<label><fmt:message key="nome"/>:</label>
			<input type="text" name="entity.nome" value="${entity.nome}"/><br/>

			<label><fmt:message key="email"/>:</label>
			<input type="text" name="entity.email" value="${entity.email}"/><br/>

			<label><fmt:message key="senha"/>:</label>
			<input type="text" name="entity.senha" value="${entity.senha}"/><br/><br/>

			<input type="submit" value="<fmt:message key="salvar"/>"/>
		</form>
	</fieldset>
</body>