/**
 * 
 */
package com.kratonsolution.products.forums.ui;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Accordion;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com 
 */
@SpringComponent
@UIScope
public class TribeEvent extends Accordion
{
	private VerticalLayout layout = new VerticalLayout();
	
	public TribeEvent()
	{
		setWidth("100%");
		setHeight("99%");
		addTab(layout,"Top Event",Icons.TRIBE_EVENT);
		setStyleName(ValoTheme.ACCORDION_BORDERLESS);
		
		layout.addComponent(new HPost());
		layout.addComponent(new HPost());
		layout.addComponent(new HPost());
		layout.addComponent(new HPost());
		layout.addComponent(new HPost());
	}
}
