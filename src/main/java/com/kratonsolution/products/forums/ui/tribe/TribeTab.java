/**
 * 
 */
package com.kratonsolution.products.forums.ui.tribe;

import com.vaadin.ui.VerticalLayout;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com 
 */
public class TribeTab extends VerticalLayout
{
	private TribeTable grid = new TribeTable();
	
	public TribeTab()
	{
		setSizeFull();
		setSpacing(false);
		setMargin(false);
		
		grid.setWidth("100%");
		
		addComponent(grid);
	}
}
