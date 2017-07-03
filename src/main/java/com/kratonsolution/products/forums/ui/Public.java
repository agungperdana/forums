package com.kratonsolution.products.forums.ui;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@SpringUI
@Theme("valo")
public class Public extends UI
{
	private VerticalLayout layout = new VerticalLayout();
	
	@Autowired
	private Header header = new Header();
	
    @Override
    protected void init(VaadinRequest request) 
    {
    	layout.setSizeFull();
    	layout.setMargin(false);
    	layout.setSpacing(false);
    	layout.setStyleName(ValoTheme.LAYOUT_CARD);
    	
    	setContent(layout);
    	
    	dispay();
    }
    
    private void dispay()
    {
    	layout.addComponent(header);
    }
}
