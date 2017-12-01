/**
 * 
 */
package com.kratonsolution.products.forums.web.ui;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import com.kratonsolution.products.forums.common.util.DateUtil;
import com.vaadin.data.Converter;
import com.vaadin.data.Result;
import com.vaadin.data.ValueContext;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com 
 */
public class LocalDateToTimestampConverter implements Converter<LocalDateTime, Timestamp>
{

	@Override
	public Result<Timestamp> convertToModel(LocalDateTime value,ValueContext context)
	{
		if(value != null)
			return Result.ok(DateUtil.toTimestamp(value));
		
		return Result.error("Date cannot be empty");
	}

	@Override
	public LocalDateTime convertToPresentation(Timestamp value,ValueContext context)
	{
		if(value != null)
			return DateUtil.toLocalDateTime(value);
		
		return null;
	}

}
