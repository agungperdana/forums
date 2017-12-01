/**
 * 
 */
package com.kratonsolution.products.forums.security.application;

import org.springframework.security.core.context.SecurityContextHolder;

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
	
	public static String getUserEmail()
	{
		SecurityInformation information = (SecurityInformation)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	if(information != null)
    		return information.getUserEmail();
    	
    	return "";
	}
}
