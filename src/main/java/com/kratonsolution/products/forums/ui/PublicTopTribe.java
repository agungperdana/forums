/**
 * 
 */
package com.kratonsolution.products.forums.ui;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com 
 */
@SpringComponent
@UIScope
public class PublicTopTribe extends TopTribe
{
	public PublicTopTribe()
	{
		super();
		
		layout.addComponent(new HPost());
		layout.addComponent(new HPost());
		layout.addComponent(new HPost());
		layout.addComponent(new HPost());
		layout.addComponent(new HPost());
	}
}
