/**
 * 
 */
package com.kratonsolution.products.forums.dm;

import java.io.Serializable;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com 
 */
@Getter
@Setter
public class TribeRole implements Serializable
{
	private String id = UUID.randomUUID().toString();

	private String name;
	
	private String email;
	
	private TribeRoleType type = TribeRoleType.FOUNDER;
	
	public TribeRole(){}
}
