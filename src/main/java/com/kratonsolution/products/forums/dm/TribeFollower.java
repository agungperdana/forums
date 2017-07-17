/**
 * 
 */
package com.kratonsolution.products.forums.dm;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com 
 */
@Getter
@Setter
public class TribeFollower
{
	private String email;
	
	private String name;
	
	private Date joinDate;

	public TribeFollower(){}
}
