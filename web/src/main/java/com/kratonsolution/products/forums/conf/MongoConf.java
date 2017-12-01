/**
 * 
 */
package com.kratonsolution.products.forums.conf;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.core.convert.CustomConversions;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com 
 */
@Configuration
public class MongoConf
{
	private List<Converter<?,?>> converters = new ArrayList<>();
	
	@Bean
	public CustomConversions getConversions()
	{
		converters.add(new MongoDBDateToTimestampConverter());
		
		return new CustomConversions(converters);
	}
}
