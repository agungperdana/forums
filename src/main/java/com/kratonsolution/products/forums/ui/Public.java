package com.kratonsolution.products.forums.ui;

import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@SpringUI
@Theme("reindeer")
public class Public extends UI
{
	private VerticalLayout center = new VerticalLayout();
	
    @Override
    protected void init(VaadinRequest request) 
    {
    	center.setSizeFull();
    	center.setMargin(false);
    	center.setSpacing(false);
    	
    	VerticalLayout layout = new VerticalLayout();
    	layout.setSizeFull();
    	layout.setMargin(false);
    	layout.setSpacing(false);
    	
    	setContent(layout);
    }
}
