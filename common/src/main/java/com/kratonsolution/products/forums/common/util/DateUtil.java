package com.kratonsolution.products.forums.common.util;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
	private final static SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
	
	public static LocalDate toLocalDate(Date date)
	{
		if(date != null)
			return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		
		return null;
	}
	
	public static LocalDate toLocalDate(Timestamp timestamp)
	{
		if(timestamp != null)
			return timestamp.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		
		return null;
	}
	
	public static LocalDateTime toLocalDateTime(Timestamp timestamp)
	{
		if(timestamp != null)
			return timestamp.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
		
		return null;
	}
	
	public static Timestamp toTimestamp(LocalDate localDate)
	{
		if(localDate != null)
			return new Timestamp(java.sql.Date.valueOf(localDate).getTime());
	
		return null;
	}
	
	public static Timestamp toTimestamp(Date date)
	{
		if(date != null)
			return new Timestamp(date.getTime());
	
		return null;
	}
	
	public static Timestamp toTimestamp(LocalDateTime localDateTime)
	{
		if(localDateTime != null)
			return new Timestamp(localDateTime.atZone(ZoneId.systemDefault()).toEpochSecond());
	
		return null;
	}
	
	public static Date toUtilDate(LocalDate date)
	{
		if(date != null)
			return new Date(java.sql.Date.valueOf(date).getTime());
		
		return null;
	}
	
	public static Timestamp now()
	{
		return new Timestamp(System.currentTimeMillis());
	}
	
	public static String format(Date date)
	{
		if(date != null)
			return format.format(date);
		
		return "";
	}
}
