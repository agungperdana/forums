/**
 * 
 */
package com.kratonsolution.products.forums.web.ui;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com 
 */
public class Preferences extends Window
{
	public Preferences()
	{
		setWidth("400px");
		setHeight("500px");
		setCaption("Preferences");
		setIcon(VaadinIcons.TOOLS);

		VerticalLayout layout = new VerticalLayout();
		layout.setWidth("100%");
		layout.setHeight("100%"); 
		
		setContent(layout);
		setModal(true);
		setResizable(false);
		setDraggable(false);

		center();
	}
}
