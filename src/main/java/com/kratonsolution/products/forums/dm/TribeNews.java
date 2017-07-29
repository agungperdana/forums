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

	private Timestamp timeCreated;
	
	private String title;
	
	private String note;
	
	private String tags;
	
	private byte[] picture;
	
	private PersonalInfo creator;
	
	private List<Comment> comments = new ArrayList<Comment>();
	
	public TribeNews(){}
}
