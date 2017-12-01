/**
 * 
 */
package com.kratonsolution.products.forums.tribe.api.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com 
 */
@Getter
@Setter
public class Comment
{
	private PersonalInfo person;
	
	private String comment;
}
