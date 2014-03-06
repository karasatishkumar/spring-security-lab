package com.techiekernel.web;

import javax.servlet.ServletContext;

import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.multipart.support.MultipartFilter;
import org.springframework.web.servlet.support.AbstractDispatcherServletInitializer;

public class RestSecurityApplicationInitializer extends AbstractSecurityWebApplicationInitializer {

	@Override
	protected String getDispatcherWebApplicationContextSuffix() {
		return AbstractDispatcherServletInitializer.DEFAULT_SERVLET_NAME;
	}

 	@Override
	protected void afterSpringSecurityFilterChain(ServletContext servletContext) {
		insertFilters(servletContext,
		   new HiddenHttpMethodFilter(),
		   new MultipartFilter() ,
		   new OpenEntityManagerInViewFilter());
	}

	@Override
	protected boolean enableHttpSessionEventPublisher() {
		return true;
	}
}