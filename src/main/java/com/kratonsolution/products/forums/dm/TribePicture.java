/**
 * 
 */
package com.kratonsolution.products.forums.dm;

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
public class TribePicture
{
	private String id = UUID.randomUUID().toString();

	private String path;
	
	private String insertedBy;
	
	private Timestamp insertDate;
	
	private boolean displayed;
}
