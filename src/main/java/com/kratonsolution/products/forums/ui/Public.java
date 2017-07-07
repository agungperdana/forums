package com.kratonsolution.products.forums.ui;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.Viewport;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.themes.ValoTheme;

@SpringUI
@Theme("forums")
@Title("Mark 3")
@Viewport("user-scalable=no,initial-scale=1.0")
public class Public extends Page
{	
	@Autowired
	private TopTribeNews news;

	@Autowired
	private TopTribe tribes;
	
	@Autowired
	private TribeEvent events;
	
    @Override
    protected void init(VaadinRequest request) 
    {
    	layout.setSizeFull();
    	layout.setStyleName(ValoTheme.LAYOUT_CARD);
    	
    	setContent(layout);
    	
    	display();
    	initContent();
    }

	@Override
	public void initContent()
	{
		GridLayout right = new GridLayout(1,2);
    	right.setWidth("100%");
    	right.setHeight("100%");
    	right.addComponent(tribes);
    	right.addComponent(events);
    	right.setComponentAlignment(tribes, Alignment.TOP_CENTER);
    	right.setComponentAlignment(events, Alignment.BOTTOM_CENTER);
    	
    	content.addComponent(news);
    	content.addComponent(right);
    	content.setExpandRatio(news, 5);
    	content.setExpandRatio(right, 1f);
	}
}
