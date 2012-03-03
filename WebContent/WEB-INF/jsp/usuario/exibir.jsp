<head>
	<title>VRaptor | Usu&aacute;rio [exibir]</title>
</head>

<body>
	ID:		${entity.id}
	Nome:	${entity.nome}
	E-mail:	${entity.email}
	Senha:	${entity.senha} 
	Perfil:	${entity.perfil.label}

	(<a href="${pageContext.request.contextPath}/usuario/${entity.id}/editar">Editar</a> | <a href="${pageContext.request.contextPath}/usuario">Listagem</a>)

	<form action="${pageContext.request.contextPath}/usuario/${entity.id}" method="post">
		<input type="hidden" name="_method" value="delete"/>
		<input type="submit" value="Remover"/>
	</form><br/>

	<img src="${pageContext.request.contextPath}/usuario/${entity.id}/thumb" alt="" /><br/><br/>

	<fieldset>
		<legend>Avatar</legend>
		<form action="${pageContext.request.contextPath}/usuario/${entity.id}/image" enctype="multipart/form-data" method="post">
			<input type="file" name="file"/>
			<input type="submit" value="enviar"/>
		</form>
	</fieldset><br/><br/>

	<c:forEach items="${entity.imageList}" var="item">
		<a href="${pageContext.request.contextPath}/usuario/${entity.id}/gallery/${item.imagem}" title="${item.titulo}" rel="gallery">
			<img src="${pageContext.request.contextPath}/usuario/${entity.id}/gallery/${item.imagem}/thumb" alt="" />
		</a>
	</c:forEach>

	<fieldset>
		<legend>Galeria</legend>
		<form action="${pageContext.request.contextPath}/usuario/${entity.id}/gallery" enctype="multipart/form-data" method="post">
	
			Imagem:						<input type="file" name="file"/><br/>
			Descri&ccedil;&atilde;o:	<input type="text" name="entityImage.descricao"/><br/><br/>
	
			<input type="submit" value="enviar"/>
		</form>
	</fieldset>

	<script type="text/javascript">
		$(function() {
			$('a[rel="gallery"]').colorbox({
				maxHeight:	'85%'
			});
		});
	</script>
</body>
