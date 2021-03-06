<head>
	<title>VRaptor | Usu&aacute;rio [novo]</title>
</head>

<body>
	<fieldset>
		<legend>Usu&aacute;rio</legend>

		<c:choose>
			<c:when test="${entity.id == null}">
				<c:set var="uri" value="/usuario" />
			</c:when>
			<c:otherwise>
				<c:set var="uri" value="/usuario/${entity.id}" />
			</c:otherwise>
		</c:choose>

		<form action="${pageContext.request.contextPath}${uri}" method="post">
			<c:if test="${entity.id != null}">
				<input type="hidden" name="_method" value="put"/>
			</c:if>

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

			<input type="submit" value="salvar" class="btn" />
		</form>
	</fieldset>
</body>
