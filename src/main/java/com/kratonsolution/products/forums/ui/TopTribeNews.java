/**
 * 
 */
package com.kratonsolution.products.forums.ui;

import com.vaadin.ui.Accordion;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.themes.ValoTheme;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com 
 */
public abstract class TopTribeNews extends Accordion
{
	protected GridLayout layout = new GridLayout(3, 4);

	public TopTribeNews()
	{
		setWidth("99%");
		setHeight("100%");
		addTab(layout,"Top Tribe News",Icons.TRIBE_NEWS);
		setStyleName(ValoTheme.ACCORDION_BORDERLESS);

		layout.setWidth("100%");
		layout.setSpacing(true);
		layout.setMargin(true);
	}
}
