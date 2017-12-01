/**
 * 
 */
package com.kratonsolution.products.forums.security.application;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com 
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(proxyTargetClass=false)
public class SecurityConf extends WebSecurityConfigurerAdapter
{
	@Override
	protected void configure(HttpSecurity http) throws Exception
	{
		http.authorizeRequests()
			.antMatchers("/").permitAll()
			.antMatchers("/VAADIN/**").permitAll()
			.antMatchers("/vaadinServlet/**").permitAll()
			.antMatchers("/PUSH/**").permitAll()
			.antMatchers("/HEARTBEAT/**").permitAll()
			.anyRequest().fullyAuthenticated()
			.and().csrf().disable();
	}
}
