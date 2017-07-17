/**
 * 
 */
package com.kratonsolution.products.forums.ui.tribe;

import com.kratonsolution.products.forums.ui.HomeContent;
import com.kratonsolution.products.forums.ui.Icons;
import com.vaadin.ui.Accordion;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com 
 */
public class ManageTribe extends VerticalLayout implements HomeContent
{
	public ManageTribe()
	{
		setWidth("100%");
		setHeight("100%");
		setSpacing(true);
		setMargin(true);

		HorizontalLayout mytribe = new HorizontalLayout();
		mytribe.setStyleName(ValoTheme.LAYOUT_HORIZONTAL_WRAPPING);
		mytribe.setSizeFull();
		mytribe.setSpacing(true);
		mytribe.setMargin(true);
		
		Button addtribe = new Button(Icons.MY_OWN_TRIBE);
		addtribe.setHeight("100px");
		addtribe.setWidth("100px");
		addtribe.setStyleName(ValoTheme.BUTTON_LINK);
		addtribe.addClickListener(event->{
			UI.getCurrent().addWindow(new TribeForm());
		});
		
		mytribe.addComponent(addtribe);
		
		Accordion news = new Accordion();
		news.setStyleName(ValoTheme.ACCORDION_BORDERLESS);
		news.setHeight("99%");
		news.setWidth("100%");
		news.addTab(mytribe,"My Tribe",Icons.TRIBE_NEWS);
		
		Accordion events = new Accordion();
		events.setCaption("Browse Tribe");
		events.setIcon(Icons.TRIBE_EVENT);
		events.setWidth("100%");
		
		addComponent(news);
		addComponent(events);
	}
}
