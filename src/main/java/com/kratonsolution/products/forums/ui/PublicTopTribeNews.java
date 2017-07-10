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
public class PublicTopTribeNews extends TopTribeNews
{
	public PublicTopTribeNews()
	{
		super();
		
		layout.addComponent(new VPost());
		layout.addComponent(new VPost());
		layout.addComponent(new VPost());
		layout.addComponent(new VPost());
		layout.addComponent(new VPost());
		layout.addComponent(new VPost());
		layout.addComponent(new VPost());
		layout.addComponent(new VPost());
		layout.addComponent(new VPost());
		layout.addComponent(new VPost());
		layout.addComponent(new VPost());
		layout.addComponent(new VPost());
	}
}
