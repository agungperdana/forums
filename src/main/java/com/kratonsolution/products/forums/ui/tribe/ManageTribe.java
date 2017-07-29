/**
 * 
 */
package com.kratonsolution.products.forums.ui.tribe;

import com.kratonsolution.products.forums.common.Security;
import com.kratonsolution.products.forums.common.Springs;
import com.kratonsolution.products.forums.dm.Tribe;
import com.kratonsolution.products.forums.svc.TribeService;
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
	private TribeService service = Springs.get(TribeService.class);
	
	public ManageTribe()
	{
		setWidth("100%");
		setHeight("100%");
		setSpacing(true);
		setMargin(true);

		HorizontalLayout mytribe = new HorizontalLayout();
		mytribe.setStyleName(ValoTheme.LAYOUT_HORIZONTAL_WRAPPING);
		mytribe.setSpacing(true);
		mytribe.setMargin(true);
		
		Button addtribe = new Button(Icons.MY_OWN_TRIBE);
		addtribe.setStyleName(ValoTheme.BUTTON_ICON_ONLY);
		addtribe.setHeight("100px");
		addtribe.setWidth("100px");
		addtribe.addClickListener(event->{
			UI.getCurrent().addWindow(new TribeForm());
		});
		
		mytribe.addComponent(addtribe);
		
		for(Tribe tribe:service.findAll(Security.getUserEmail()))
			mytribe.addComponent(new TribeDisplay(tribe));
		
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
