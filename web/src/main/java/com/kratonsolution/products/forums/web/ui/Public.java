package com.kratonsolution.products.forums.web.ui;

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
	private PublicTopTribeNews news = new PublicTopTribeNews();

	private PublicTopTribe tribes = new PublicTopTribe();
	
	private PublicTribeEvent events = new PublicTribeEvent();
	
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
		right.setSizeFull();
    	right.addComponent(tribes);
    	right.addComponent(events);
    	right.setComponentAlignment(tribes, Alignment.TOP_CENTER);
    	right.setComponentAlignment(events, Alignment.BOTTOM_CENTER);
    	
    	content.addComponent(news);
    	content.addComponent(right);
    	content.setExpandRatio(news, 4);
    	content.setExpandRatio(right, 1f);
	}
}
