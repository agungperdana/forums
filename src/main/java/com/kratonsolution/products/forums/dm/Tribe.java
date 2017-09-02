/**
 * 
 */
package com.kratonsolution.products.forums.dm;

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
@Document(collection="tribe")
public class Tribe implements Serializable
{
	@Id
	private String id = UUID.randomUUID().toString();
	
	@Indexed
	private String name;
	
	@Indexed
	private String description;

	@Indexed
	private String goal;
	
	private Timestamp created;
	
	private TribeStatus lastStatus;
	
	private byte[] logo;
	
	private PersonalInfo chieftain;
	
	private PersonalInfo creator;
	
	private Vector<PersonalInfo> contributors = new Vector<>();
	
	private Vector<TribeStatus> statuses = new Vector<>();
	
	private Vector<PersonalInfo> followers = new Vector<>();
	
	public Tribe(){}
	
	public boolean isJoined(String email)
	{
		if(email != null && !email.equals(""))
		{
			for(PersonalInfo info:followers)
			{
				if(info.getEmail().equals(email))
					return true;
			}
		}
		
		return false;
	}
}
