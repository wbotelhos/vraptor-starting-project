function confirmar(title, sim, nao, msg, funcao, largura, altura) {
	$('div#mensagem').dialog({
		title: title,
		buttons: [
			{
				text: sim,
				click: function() {
					$(this).dialog('close');
					funcao();
				}
			},
			{
				text: nao,
				click: function() {
					$(this).dialog('close');
				}
			}
		],
		width: (largura !== undefined) ? largura : 320,
		height: (altura !== undefined) ? altura : 150
	})
	.html(msg)
	.dialog('open');
};

function mensagem(title, msg, largura, altura) {
	$('div#mensagem').dialog({
		'title': title,
		'buttons': {
			'ok': function() {
				$(this).dialog('close');
			}
		},
		width: (largura !== undefined) ? largura : 320,
		height: (altura !== undefined) ? altura : 150
	})
	.html(msg)
	.dialog('open');
};