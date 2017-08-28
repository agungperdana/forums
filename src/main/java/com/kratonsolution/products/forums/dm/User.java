/**
 * 
 */
package com.kratonsolution.products.forums.dm;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com 
 */
@Getter
@Setter
@Document(collection="users")
public class User implements Serializable
{
	@Id
	private String id = UUID.randomUUID().toString();

	private String name;
	
	private String email;
	
	private Date birthDate;
	
	private String password;
	
	private boolean enabled = false;
	
	private boolean activated = false;
	
	private Language languange = Language.EN_US;

	private List<Message> inbox = new ArrayList<>();
	
	private List<Message> outbox = new ArrayList<>();
	
	public User(){}
	
	public String getEnabledStatus()
	{
		return enabled?"Yes":"No";
	}
	
	public String getActivatedStatus()
	{
		return activated?"Yes":"No";
	}
}
