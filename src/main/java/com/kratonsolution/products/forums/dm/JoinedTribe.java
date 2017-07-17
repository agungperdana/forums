/**
 * 
 */
package com.kratonsolution.products.forums.dm;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com 
 */
@Getter
@Setter
public class JoinedTribe implements Serializable
{
	private String tribeId;
	
	private String tribeName;
	
	public JoinedTribe(){}
}
