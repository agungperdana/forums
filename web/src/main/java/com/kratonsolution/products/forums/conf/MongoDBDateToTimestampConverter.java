/**
 * 
 */
package com.kratonsolution.products.forums.conf;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

import com.kratonsolution.products.forums.common.util.DateUtil;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com 
 */
public class MongoDBDateToTimestampConverter implements Converter<Date, Timestamp>
{
	@Override
	public Timestamp convert(Date date)
	{
		return DateUtil.toTimestamp(date);
	}
}
