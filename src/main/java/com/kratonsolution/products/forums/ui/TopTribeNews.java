/**
 * 
 */
package com.kratonsolution.products.forums.ui;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Accordion;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.themes.ValoTheme;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com 
 */
@SpringComponent
@UIScope
public class TopTribeNews extends Accordion
{
	private GridLayout layout = new GridLayout(3, 100);
	
	public TopTribeNews()
	{
		setWidth("99%");
		setHeight("100%");
		addTab(layout,"Top Tribe News",Icons.TRIBE_NEWS);
		setStyleName(ValoTheme.ACCORDION_BORDERLESS);
	}
}
