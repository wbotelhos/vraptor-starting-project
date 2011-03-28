package br.com.wbotelhos.blank.interceptor;

import java.util.Arrays;

import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.core.InterceptorStack;
import br.com.caelum.vraptor.interceptor.Interceptor;
import br.com.caelum.vraptor.resource.ResourceMethod;
import br.com.wbotelhos.blank.component.UserSession;
import br.com.wbotelhos.blank.controller.IndexController;
import br.com.wbotelhos.blank.controller.LoginController;

@Intercepts
public class LoginInterceptor implements Interceptor {

	private final Result result;
	private final UserSession userSession;
	
	public LoginInterceptor(Result result, UserSession userSession) {
		this.result = result;
		this.userSession = userSession;
	}

	@SuppressWarnings("unchecked")
	public boolean accepts(ResourceMethod method) {
		return !Arrays.asList(LoginController.class).contains(method.getMethod().getDeclaringClass());
	}

	public void intercept(InterceptorStack stack, ResourceMethod method, Object resource) {
		if (userSession.isLogged()) {
			stack.next(method, resource);
		} else {
			result.redirectTo(IndexController.class).index();
		}
	}

}