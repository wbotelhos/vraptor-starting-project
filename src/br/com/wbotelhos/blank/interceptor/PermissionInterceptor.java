package br.com.wbotelhos.blank.interceptor;

import java.util.Arrays;

import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.core.InterceptorStack;
import br.com.caelum.vraptor.interceptor.Interceptor;
import br.com.caelum.vraptor.resource.ResourceMethod;
import br.com.wbotelhos.blank.annotation.Permission;
import br.com.wbotelhos.blank.component.UserSession;
import br.com.wbotelhos.blank.controller.UsuarioController;
import br.com.wbotelhos.blank.model.Usuario;
import br.com.wbotelhos.blank.model.common.TipoPerfil;

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

		if (userSession.getUser() != null && this.isAcesso(metodoList) && this.isAcesso(controllerList)) {
			stack.next(method, resource);
		} else {
			result.notFound();
		}
	}

	private boolean isAcesso(Permission permissaoList) {
		if (permissaoList == null) {
			return true;
		}

		Usuario user = userSession.getUser();

		for (TipoPerfil perfil : permissaoList.value()) {
			if (perfil.equals(user.getPerfil())) {
				return true;
			}
		}		

		return false;
	}

}