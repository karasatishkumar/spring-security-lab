package com.techiekernel.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.header.writers.CacheControlHeadersWriter;
import org.springframework.security.web.header.writers.HstsHeaderWriter;
import org.springframework.security.web.header.writers.XContentTypeOptionsHeaderWriter;
import org.springframework.security.web.header.writers.XXssProtectionHeaderWriter;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter.XFrameOptionsMode;

@Configuration
@EnableWebSecurity
class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

	//@Override
	protected void registerAuthentication(AuthenticationManagerBuilder auth)
			throws Exception {
		auth.userDetailsService(this.userDetailsService);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {


		http.formLogin()
				  .loginPage("/techiekernel/signin.html")
				  .loginProcessingUrl("/signin")
				  .defaultSuccessUrl("/techiekernel/welcome.html")
				  .failureUrl("/techiekernel/signin.html?error=true")
				  .usernameParameter("username")
				  .passwordParameter("password")
				  .permitAll(true);

		http.headers()
				  .addHeaderWriter(new XFrameOptionsHeaderWriter(XFrameOptionsMode.SAMEORIGIN))
				  .addHeaderWriter(new XContentTypeOptionsHeaderWriter())
				  .addHeaderWriter(new XXssProtectionHeaderWriter())
				  .addHeaderWriter(new CacheControlHeadersWriter())
				  .addHeaderWriter(new HstsHeaderWriter());

		http.logout().logoutUrl("/signout").deleteCookies("JSESSIONID");


		// nb: the H2 administration console should *not* be left exposed.
		// comment out the mapping path below so that it requires an  
                // authentication to see it.
		//String[] filesToLetThroughUnAuthorized = { H2EmbeddedDatbaseConsoleInitializer.H2_DATABASE_CONSOLE_MAPPING, "/favicon.ico" };
		String[] filesToLetThroughUnAuthorized = {"/favicon.ico" };

		http.authorizeRequests()
				  .antMatchers(filesToLetThroughUnAuthorized).permitAll()
				  .anyRequest().authenticated();
	}
}