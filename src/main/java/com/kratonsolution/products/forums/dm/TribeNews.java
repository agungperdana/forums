/**
 * 
 */
package com.kratonsolution.products.forums.dm;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com 
 */
@Getter
@Setter
public class TribeNews implements Serializable
{
	private String id = UUID.randomUUID().toString();

	private String genre;
	
	private String title;
	
	private Timestamp timeCreated;
	
	private PersonalInfo creator;

	private String tags;
	
	private String description;
	
	private byte[] picture;
	
	private int views;

	private List<Comment> comments = new ArrayList<Comment>();
	
	public TribeNews(){}
}
