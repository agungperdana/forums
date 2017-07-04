package com.kratonsolution.products.forums.ui;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@SpringUI
@Theme("forums")
public class Public extends UI
{
	private VerticalLayout layout = new VerticalLayout();
	
	@Autowired
	private Header header = new Header();
	
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
    	
    	dispay();
    }
    
    private void dispay()
    {
    	HorizontalLayout line = new HorizontalLayout();
    	line.setWidth("100%");
    	line.setHeight("10px");
    	line.setStyleName("top-line");
    
    	HorizontalLayout content = new HorizontalLayout();
    	content.setWidth("100%");
    	content.setHeight("100%");
    	content.setSpacing(false);
    	content.setMargin(false);
    	
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
    	
    	layout.addComponent(header);
    	layout.addComponent(line);
    	layout.addComponent(content);
    	
    	layout.setExpandRatio(header, 0.6f);
    	layout.setExpandRatio(line, 0.2f);
    	layout.setExpandRatio(content, 11f);
    }
}
