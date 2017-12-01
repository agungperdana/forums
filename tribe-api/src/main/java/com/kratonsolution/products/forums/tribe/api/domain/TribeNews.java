/**
 * 
 */
package com.kratonsolution.products.forums.tribe.api.domain;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.UUID;
import java.util.Vector;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com 
 */
@Getter
@Setter
@Document(collection="tribe_news")
public class TribeNews implements Serializable
{
	@Id
	private String id = UUID.randomUUID().toString();

	@Indexed
	private String genre;
	
	@Indexed
	private String title;
	
	private Timestamp timeCreated;
	
	private boolean approved = false;
	
	@Indexed
	private PersonalInfo creator;

	@Indexed
	private String tags;
	
	private String description;
	
	private byte[] picture;
	
	private int likes;

	private String tribe;
	
	private Vector<PersonalInfo> subscribers = new Vector<>();
	
	private Vector<Comment> comments = new Vector<>();
	
	public TribeNews(){}
}
