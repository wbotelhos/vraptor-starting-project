package br.com.wbotelhos.starting.interceptor;

import static br.com.caelum.vraptor.view.Results.http;
import static br.com.wbotelhos.starting.util.Utils.i18n;

import java.util.Arrays;
import java.util.Collection;

import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.core.InterceptorStack;
import br.com.caelum.vraptor.interceptor.Interceptor;
import br.com.caelum.vraptor.resource.ResourceMethod;
import br.com.wbotelhos.starting.annotation.Permission;
import br.com.wbotelhos.starting.component.UserSession;
import br.com.wbotelhos.starting.controller.IndexController;
import br.com.wbotelhos.starting.controller.UsuarioController;
import br.com.wbotelhos.starting.model.Usuario;
import br.com.wbotelhos.starting.model.common.TipoPerfil;

@Intercepts
public class PermissionInterceptor implements Interceptor {

	private final Result result;
	private final UserSession userSession;
	
	public PermissionInterceptor(Result result, UserSession userSession) {
		this.result = result;
		this.userSession = userSession;
	}

	@SuppressWarnings("unchecked")
	public boolean accepts(ResourceMethod method) {
		return Arrays.asList(UsuarioController.class).contains(method.getMethod().getDeclaringClass());
	}

	public void intercept(InterceptorStack stack, ResourceMethod method, Object resource) {
		Permission controllerList = method.getResource().getType().getAnnotation(Permission.class);
		Permission metodoList = method.getMethod().getAnnotation(Permission.class);

		Usuario user = userSession.getUser();

		if (user == null) {
			if (controllerList == null && metodoList == null) {
				stack.next(method, resource);
			} else {
				result.redirectTo(IndexController.class).index();
			}
		} else if (this.hasAccess(metodoList) && this.hasAccess(controllerList)) {
			stack.next(method, resource);
		} else {
			result.use(http()).sendError(500, i18n("voce.nao.tem.permissao.para.tal.acao"));
		}
	}

	private boolean hasAccess(Permission permissaoList) {
		if (permissaoList == null) {
			return true;
		}

		Usuario user = userSession.getUser();

		Collection<TipoPerfil> perfilList = Arrays.asList(permissaoList.value());

		return perfilList.contains(user.getPerfil());
	}

}