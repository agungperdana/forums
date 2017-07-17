package com.kratonsolution.products.forums.dm;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com
 */
@Getter
@Setter
public class TribeEvent implements Serializable
{
	private String id = UUID.randomUUID().toString();

	private Timestamp timeCreated;
	
	private String title;
	
	private String note;
	
	private String creator;
	
	public TribeEvent(){}
}
