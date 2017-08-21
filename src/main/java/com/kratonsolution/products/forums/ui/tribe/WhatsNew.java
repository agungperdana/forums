/**
 * 
 */
package com.kratonsolution.products.forums.ui.tribe;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;

import com.kratonsolution.products.forums.common.Security;
import com.kratonsolution.products.forums.common.Springs;
import com.kratonsolution.products.forums.dm.TribeEventRepository;
import com.kratonsolution.products.forums.dm.TribeNews;
import com.kratonsolution.products.forums.dm.TribeNewsRepository;
import com.kratonsolution.products.forums.ui.HomeContent;
import com.kratonsolution.products.forums.ui.Icons;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.Accordion;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com 
 */
public class WhatsNew extends VerticalLayout implements HomeContent
{
	private TribeNewsRepository newsRepository = Springs.get(TribeNewsRepository.class);
	
	private TribeEventRepository eventRepository = Springs.get(TribeEventRepository.class);
	
	private HorizontalLayout newsLayout = new HorizontalLayout();
	
	public WhatsNew()
	{
		setWidth("100%");
		setHeight("100%");
		setSpacing(true);
		setMargin(true);

		newsLayout.setStyleName(ValoTheme.LAYOUT_HORIZONTAL_WRAPPING);
		newsLayout.setMargin(true);
		newsLayout.setSpacing(true);
		
		Accordion news = new Accordion();
		news.setStyleName(ValoTheme.ACCORDION_BORDERLESS);
		news.setWidth("100%");
		news.addTab(newsLayout,"Tribe News",Icons.TRIBE_NEWS);
		
		List<TribeNews> tribeNews = newsRepository.findAllMyNews(new PageRequest(0, 50,new Sort(new Order(Direction.DESC,"timeCreated"))), Security.getUserEmail());
		if(tribeNews.isEmpty())
		{
			Label label = new Label(null, ContentMode.HTML);
			label.setValue("<h3>You are currently not joining any tribe. You can find many interesting tribe on <font style='font-weight:bolder;color:red;'>Manage Tribe</font> menu</h3>");
		
			newsLayout.addComponent(label);
		}
		else
		{
			tribeNews.forEach(tnews->{
				newsLayout.addComponent(new NewsDisplay(tnews));
			});
		}
		
		Accordion events = new Accordion();
		events.setCaption("Tribe Event");
		events.setIcon(Icons.TRIBE_EVENT);
		events.setWidth("100%");
		
		addComponent(news);
		addComponent(events);
	}
}
