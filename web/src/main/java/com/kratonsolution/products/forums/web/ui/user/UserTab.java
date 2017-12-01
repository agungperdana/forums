/**
 * 
 */
package com.kratonsolution.products.forums.web.ui.user;

import com.vaadin.ui.VerticalLayout;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com 
 */
public class UserTab extends VerticalLayout
{
	private UserTable grid = new UserTable();
	
	public UserTab()
	{
		setSizeFull();
		setSpacing(false);
		setMargin(false);
		
		grid.setWidth("100%");
		
		addComponent(grid);
	}
}
