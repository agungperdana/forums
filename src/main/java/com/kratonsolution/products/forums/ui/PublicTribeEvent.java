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
public class PublicTribeEvent extends TrieEvent
{
	public PublicTribeEvent()
	{
		super();
		
		layout.addComponent(new HPost());
		layout.addComponent(new HPost());
		layout.addComponent(new HPost());
		layout.addComponent(new HPost());
		layout.addComponent(new HPost());
	}
}
