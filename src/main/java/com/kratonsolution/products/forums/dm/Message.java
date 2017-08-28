/**
 * 
 */
package com.kratonsolution.products.forums.dm;

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
public class Message
{
	private String id = UUID.randomUUID().toString();
	
	private String title;
	
	private String content;
	
	private PersonalInfo sender;
	
	private List<PersonalInfo> receivers = new ArrayList<>();
}
