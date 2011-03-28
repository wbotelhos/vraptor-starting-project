<head>
	<title>VRaptor | Usu&aacute;rio [novo]</title>
</head>

<body>
	<fieldset>
		<legend>Usu&aacute;rio</legend>

		<form action="<c:url value='/usuario'/>" method="post">
			<input type="hidden" name="entity.id" value="${entity.id}"/>

			Nome:	<input type="text" name="entity.nome" value="${entity.nome}"/><br/>
			E-mail:	<input type="text" name="entity.email" value="${entity.email}"/><br/>
			Senha:	<input type="text" name="entity.senha" value="${entity.senha}"/><br/>
			Perfil: <select name="entity.perfil">
					    <option value="" selected="selected">--selecione--</option>
					    <c:forEach items="${perfilList}" var="item">
					        <option value="${item}"
					            <c:if test="${entity.perfil eq item}">selected="selected"</c:if>
					        >${item.label}</option>
					    </c:forEach>
					</select><br/><br/>

			<input type="submit" value="salvar"/>
		</form>
	</fieldset>
</body>