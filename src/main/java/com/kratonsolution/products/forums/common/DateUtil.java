package com.kratonsolution.products.forums.common;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 * 
 */

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com 
 */
public class DateUtil
{
	public static LocalDate toLocalDate(Date date)
	{
		if(date != null)
			return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		
		return null;
	}
	
	public static Date toUtilDate(LocalDate date)
	{
		if(date != null)
			return new Date(java.sql.Date.valueOf(date).getTime());
		
		return null;
	}
}
