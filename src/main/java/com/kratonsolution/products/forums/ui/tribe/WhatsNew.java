/**
 * 
 */
package com.kratonsolution.products.forums.ui.tribe;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;

import com.kratonsolution.products.forums.common.Security;
import com.kratonsolution.products.forums.common.Springs;
import com.kratonsolution.products.forums.dm.TribeEvent;
import com.kratonsolution.products.forums.dm.TribeEventRepository;
import com.kratonsolution.products.forums.dm.TribeNews;
import com.kratonsolution.products.forums.dm.TribeNewsRepository;
import com.kratonsolution.products.forums.svc.TribeService;
import com.kratonsolution.products.forums.ui.HomeContent;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com 
 */
public class WhatsNew extends GridLayout implements HomeContent
{
	private TribeNewsRepository newsRepository = Springs.get(TribeNewsRepository.class);
	
	private TribeEventRepository eventRepository = Springs.get(TribeEventRepository.class);
	
	private TribeService tribeService = Springs.get(TribeService.class);
	
	public WhatsNew()
	{
		setSizeFull();
		setSpacing(false);
		setMargin(true);
		setColumns(1);
		setRows(2);
		
		initNews();
		initEvent();
	}

	private void initNews()
	{
		Label title = new Label("<h3><u><font style='color:red;'>T</font>op Tribe News</u></h3>", ContentMode.HTML);
		
		HorizontalLayout newsLayout = new HorizontalLayout();
		newsLayout.setStyleName(ValoTheme.LAYOUT_HORIZONTAL_WRAPPING);
		newsLayout.setMargin(true);
		newsLayout.setSpacing(true);

		VerticalLayout root = new VerticalLayout();
		root.setWidth("100%");
		root.setHeight("100%");
		root.addComponent(title);
		root.addComponent(newsLayout);
		root.setExpandRatio(title, 1f);
		root.setExpandRatio(newsLayout, 9f);
		
		Panel panel = new Panel();
		panel.addStyleName(ValoTheme.PANEL_BORDERLESS);
		panel.setHeight("99%");
		panel.setContent(root);
		
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
				newsLayout.addComponent(new NewsDisplay(tnews,"300px"));
			});
		}
	
		addComponent(panel);
	}
	
	private void initEvent()
	{
		Label title = new Label("<h3><u><font style='color:red;'>T</font>op Tribe Event</u></h3>", ContentMode.HTML);
		
		HorizontalLayout eventLayout = new HorizontalLayout();
		eventLayout.setStyleName(ValoTheme.LAYOUT_HORIZONTAL_WRAPPING);
		eventLayout.setMargin(true);
		eventLayout.setSpacing(true);
		
		VerticalLayout root = new VerticalLayout();
		root.setSizeFull();
		root.addComponent(title);
		root.addComponent(eventLayout);
		root.setExpandRatio(title, 1f);
		root.setExpandRatio(eventLayout, 9f);
		
		Panel panel = new Panel();
		panel.addStyleName(ValoTheme.PANEL_BORDERLESS);
		panel.setSizeFull();
		panel.setContent(root);
		
		List<TribeEvent> events = new ArrayList<TribeEvent>();
		
		tribeService.findAllInvolved().forEach(tribe->{
			events.addAll(eventRepository.findAllByTribe(tribe.getId()));
		});
		
		if(events.isEmpty())
		{
			Label label = new Label(null, ContentMode.HTML);
			label.setValue("<h3>No event.</h3>");
		
			eventLayout.addComponent(label);
		}
		else
		{
			events.forEach(event->{
				eventLayout.addComponent(new EventDisplay(event,"250px"));
			});
		}
		
		addComponent(panel);
	}
}
