/**
 * 
 */
package com.kratonsolution.products.forums.ui.tribe;

import com.kratonsolution.products.forums.ui.HomeContent;
import com.kratonsolution.products.forums.ui.Icons;
import com.vaadin.ui.Accordion;
import com.vaadin.ui.VerticalLayout;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com 
 */
public class WhatsNew extends VerticalLayout implements HomeContent
{
	public WhatsNew()
	{
		setWidth("100%");
		setHeight("100%");
		setSpacing(true);
		setMargin(true);
		
		Accordion news = new Accordion();
		news.setCaption("Tribe News");
		news.setIcon(Icons.TRIBE_NEWS);
		news.setWidth("100%");
		
		Accordion events = new Accordion();
		events.setCaption("Tribe Event");
		events.setIcon(Icons.TRIBE_EVENT);
		events.setWidth("100%");
		
		addComponent(news);
		addComponent(events);
	}
}
