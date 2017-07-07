package com.kratonsolution.products.forums.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

import com.kratonsolution.products.forums.security.svc.SecurityInformation;
import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.themes.ValoTheme;

@SpringUI(path="/Home")
@Theme("forums")
public class Home extends Page
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
    	setContent(layout);
    	
    	display();
    	initContent();
    }
    
    public void initContent()
    {
    	header.getNavigation().removeAllComponents();
    	
    	MenuBar bar = new MenuBar();
    	bar.setSizeUndefined();
    	bar.setStyleName(ValoTheme.MENUBAR_BORDERLESS);
    	
    	SecurityInformation information = (SecurityInformation)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	if(information != null)
    		bar.addItem("Helo "+information.getName(), Icons.SIGN_IN,event->{});
    	
    	header.getNavigation().addComponent(bar);
    	header.getNavigation().setComponentAlignment(bar, Alignment.BOTTOM_RIGHT);
    }
}
