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
			<input type="text" name="entity.senha" value="${entity.senha}"/><br/>

			<label><fmt:message key="senha"/>:</label>
			<select name="entity.perfil">
			    <option value="" selected="selected">--<fmt:message key="selecione"/>--</option>
			    <c:forEach items="${perfilList}" var="item">
			        <option value="${item}"
			            <c:if test="${entity.perfil eq item}">selected="selected"</c:if>
			        >${item.label}</option>
			    </c:forEach>
			</select><br/><br/>
			<input type="submit" value="<fmt:message key="salvar"/>"/>
		</form>
	</fieldset>
</body>