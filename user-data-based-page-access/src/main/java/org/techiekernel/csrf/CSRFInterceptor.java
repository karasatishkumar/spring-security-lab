package org.techiekernel.csrf;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class CSRFInterceptor implements HandlerInterceptor {
	@Autowired
	private CSRFTokenService csrfTokenService;

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		boolean rv = true;
		if (CSRFTokenService.METHODS_TO_CHECK.contains(StringUtils
				.defaultIfBlank(request.getMethod(), "").toUpperCase())
				&& !csrfTokenService.acceptsTokenIn(request)) {
			response.addHeader("X-DailyFratze-InvalidCSRFToken",
					Boolean.toString(true));
			response.sendError(HttpServletResponse.SC_FORBIDDEN);
			rv = false;
		}
		return rv;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}
}
