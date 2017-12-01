/**
 * 
 */
package com.kratonsolution.products.forums.tribe.api.domain;

import java.sql.Timestamp;
import java.util.UUID;

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
	private String id = UUID.randomUUID().toString();
	
	private Timestamp joinDate;
	
	private PersonalInfo person;

	public TribeFollower(){}
}
