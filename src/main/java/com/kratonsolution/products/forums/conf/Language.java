/**
 * 
 */
package com.kratonsolution.products.forums.conf;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com
 */
@Service
public class Language
{
	Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private MessageSource messageSource;
	
	public String get(String name)
	{
		String lang = "en_us";
		
		Locale locale = new Locale(lang);
		
		return messageSource.getMessage(name,new Object[]{},locale);
	}
}
