package com.kratonsolution.products.forums.dm;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com
 */
@Getter
@Setter
@Document(collection="tribe_event")
public class TribeEvent implements Serializable
{
	@Id
	private String id = UUID.randomUUID().toString();

	private Timestamp timeCreated;
	
	@Indexed
	private String title;
	
	private String note;
	
	private String creator;
	
	@DBRef
	private Tribe tribe;
	
	public TribeEvent(){}
}
