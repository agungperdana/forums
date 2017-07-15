package com.kratonsolution.products.forums.ui;

import org.springframework.security.core.context.SecurityContextHolder;

import com.kratonsolution.products.forums.svc.SecurityInformation;
import com.kratonsolution.products.forums.ui.tribe.WhatsNew;
import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Panel;
import com.vaadin.ui.themes.ValoTheme;

@SpringUI(path="/Home")
@Theme("forums")
public class Home extends Page
{	
	private SideMenu side = new SideMenu();
	
	private Panel display = new Panel();
	
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
    	
    	display.setWidth("100%");
    	display.setHeight("100%");
    	
    	content.setSpacing(true);
    	content.setMargin(true);
    	content.addComponent(side);
    	content.addComponent(display);
    	content.setExpandRatio(side, 0.7f);
    	content.setExpandRatio(display, 4.3f);
    	
    	side.getNews().setCommand(event->{
        	display.setContent(new WhatsNew());
        	side.setSelected(side.getNews());
    	});
    	
    	side.getManage().setCommand(event->{
        	side.setSelected(side.getManage());
    	});
    	
    	side.getMessage().setCommand(event->{
        	side.setSelected(side.getMessage());
    	});
    	
    	side.getAccount().setCommand(event->{
        	side.setSelected(side.getAccount());
    	});
    }
}
