<div id="menu-item">
	<a href="<c:url value='/' />">Home</a> |
	<a href="javascript:void(0);" onclick="mensagem('title', 'message');">Mensagem</a> |
	<a href="javascript:void(0);" onclick="confirmar('title', 'yes', 'no', 'confirm?', dummy);">Confirmar</a>

	<c:if test="${userSession.user != null}">
		| <a href="<c:url value='/usuario' />">Usu&aacute;rios</a> | <a href="<c:url value='/usuario/criar' />">Novo Usu&aacute;rio</a>
	</c:if>
</div>

<div id="identification">
    [ <a href="<c:url value='/translate/pt/br'/>">BR</a>&nbsp; | <a href="<c:url value='/translate/en/us'/>">US</a> ] <fmt:message key="bem.vindo" />:

	<c:choose>
		<c:when test="${userSession.user == null}">Visitante</c:when>
		<c:otherwise>
			${userSession.user.nome} (<a href="<c:url value='/logout'/>">sair</a>)
		</c:otherwise>
	</c:choose>
</div>