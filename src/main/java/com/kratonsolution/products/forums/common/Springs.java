/**
 * 
 */
package com.kratonsolution.products.forums.common;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.vaadin.server.VaadinServlet;

/**
 * 
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com
 */
public class Springs
{
	public static final <T> T get(Class<T> classType)
	{
		WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(VaadinServlet.getCurrent().getServletContext());
		return context.getBean(classType);
	}
}
