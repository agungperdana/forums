/**
 * 
 */
package com.kratonsolution.products.forums.ui;

import com.vaadin.ui.Accordion;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com 
 */
public abstract class TopTribe extends Accordion
{
	protected VerticalLayout layout = new VerticalLayout();

	public TopTribe()
	{
		setWidth("100%");
		setHeight("99%");
		
		layout.setSpacing(true);
		
		addTab(layout,"Top Tribe",Icons.TRIBE_TOP);
		setStyleName(ValoTheme.ACCORDION_BORDERLESS);
	}
}
