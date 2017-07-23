/**
 * 
 */
package com.kratonsolution.products.forums.common;

import org.springframework.security.core.context.SecurityContextHolder;

import com.kratonsolution.products.forums.svc.SecurityInformation;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com 
 */
public class Security
{
	public static String getUserName()
	{
		SecurityInformation information = (SecurityInformation)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	if(information != null)
    		return information.getName();
    	
    	return "";
	}
}
