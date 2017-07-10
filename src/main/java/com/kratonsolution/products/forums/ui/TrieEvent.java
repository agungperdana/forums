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
public abstract class TrieEvent extends Accordion
{
	protected VerticalLayout layout = new VerticalLayout();
	
	public TrieEvent()
	{
		setWidth("100%");
		setHeight("99%");
		addTab(layout,"Top Event",Icons.TRIBE_EVENT);
		setStyleName(ValoTheme.ACCORDION_BORDERLESS);
	}
}
