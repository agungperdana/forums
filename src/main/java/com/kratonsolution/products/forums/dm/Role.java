/**
 * 
 */
package com.kratonsolution.products.forums.dm;

import java.io.Serializable;
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
@Document(collection="roles")
public class Role implements Serializable
{
	@Id
	private String id = UUID.randomUUID().toString();

	private String name;
	
	private String note;

	public Role(){}
}
