/**
 * 
 */
package com.kratonsolution.products.forums.dm;

import java.io.Serializable;
import java.sql.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com 
 */
@Getter
@Setter
@Entity
@Table(name="users")
public class User implements Serializable
{
	@Id
	private String id = UUID.randomUUID().toString();

	@Column(name="name")
	private String name;
	
	@Column(name="email",unique=true)
	private String email;
	
	@Column(name="birth_date")
	private Date birthDate;
	
	@Column(name="password")
	private String password;
	
	@Column(name="is_enabled")
	private boolean enabled = false;
	
	@Column(name="is_activated")
	private boolean activated = false;
	
	@Version
	private Long version;

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
