package com.kratonsolution.products.forums.tribe.api.domain;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.UUID;

import org.springframework.data.annotation.Id;
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

	private Timestamp created;
	
	private String description;
	
	private PersonalInfo creator;
	
	private String tribe;
	
	public TribeEvent(){}
}
