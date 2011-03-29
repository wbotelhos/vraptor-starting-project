<head>
	<title>VRaptor | Blank Project</title>
</head>

<body>
	<fmt:message key="bem.vindo" />  [${empty sessionScope.language ? 'BR' : sessionScope.language}]:
	<c:choose>
		<c:when test="${empty userSession.user.nome}">
			Visitante<br/><br/>
			
			<form action="<c:url value='/autenticar'/>" method="post">
				E-mail: <input type="text" name="entity.email"/><br/>
				Senha: <input type="text" name="entity.senha"/><br/>

				<input type="submit" value="Autenticar" />
			</form>
		</c:when>
		<c:otherwise>
			${userSession.user.nome} (<a href="<c:url value='/logout'/>">sair</a>)<br/><br/>

			<a href="<c:url value="/usuario" />">Usu&aacute;rios</a> |
			<a href="<c:url value="/usuario/novo" />">Novo Usu&aacute;rio</a><br/>
		</c:otherwise>
	</c:choose><br/>

	<a href="javascript:void(0);" onclick="mensagem('title', 'message');">Mensagem</a> |
	<a href="javascript:void(0);" onclick="confirmar('title', 'yes', 'no', 'confirm?', dummy);">Confirmar</a><br/><br/>

	Palavras-chave:<br/><br/>

	- Annotation;<br/>
	- Component;<br/>
	- CRUD;<br/>
	- Dependency Injection;<br/>
	- Eclipse;<br/>
	- ENUM;<br/>
	- Expression Language (EL);<br/>
	- Generic Class;<br/>
	- Interceptor;<br/>
	- Internacionalization (I18N);<br/> 
	- JPA/Hibernate;<br/>
	- jQuery UI;<br/>
	- jQuery;<br/>
	- JSP;<br/>
	- Log4j;<br/>
	- MVC;<br/>
	- MySQL;<br/>
	- Permission Control;<br/>
	- POJO;<br/>
	- Prelude;<br/>
	- Repository;<br/>
	- REST;<br/>
	- VRaptor 3;<br/>
	- XHTML;<br/><br/>

	Artigos em: <a href="http://www.wbotelhos.com.br/" target="_blank">wbotelhos.com.br</a>
</body>