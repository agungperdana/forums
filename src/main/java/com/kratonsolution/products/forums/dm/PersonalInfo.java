/**
 * 
 */
package com.kratonsolution.products.forums.dm;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com 
 */
@Getter
@Setter
public class PersonalInfo
{
	private String id;
	
	private String name;
	
	private String email;
	
	@Override
	public String toString()
	{
		return name;
	}
}
