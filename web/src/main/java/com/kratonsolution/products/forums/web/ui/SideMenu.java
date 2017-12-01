/**
 * 
 */
package com.kratonsolution.products.forums.web.ui;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinServletService;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

import lombok.Getter;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com 
 */
@Getter
public class SideMenu extends Panel
{
	private VerticalLayout layout = new VerticalLayout();
	
	private MenuBar newsbar = new MenuBar();
	
	private MenuBar managebar = new MenuBar();
	
	private MenuBar messagebar = new MenuBar();
	
	private MenuBar accountbar = new MenuBar();
	
	private MenuBar signoutbar = new MenuBar();
	
	private MenuItem whatNew = newsbar.addItem("What's New",null);
	
	private MenuItem manage = managebar.addItem("Manage Tribe",null);
	
	private MenuItem message = messagebar.addItem("Message",null);
	
	private MenuItem account = accountbar.addItem("My Account",null);
	
	public SideMenu()
	{
		setWidth("100%");
		setHeight("100%");

		newsbar.setStyleName(ValoTheme.MENUBAR_BORDERLESS+" "+ValoTheme.MENUBAR_SMALL);
		newsbar.setWidth("100%");
		
		managebar.setStyleName(ValoTheme.MENUBAR_BORDERLESS+" "+ValoTheme.MENUBAR_SMALL);
		managebar.setWidth("100%");
		
		messagebar.setStyleName(ValoTheme.MENUBAR_BORDERLESS+" "+ValoTheme.MENUBAR_SMALL);
		messagebar.setWidth("100%");
		
		accountbar.setStyleName(ValoTheme.MENUBAR_BORDERLESS+" "+ValoTheme.MENUBAR_SMALL);
		accountbar.setWidth("100%");
		
		signoutbar.setStyleName(ValoTheme.MENUBAR_BORDERLESS+" "+ValoTheme.MENUBAR_SMALL);
		signoutbar.setWidth("100%");
		
		MenuItem signout = signoutbar.addItem("Sign Out",null);
		signout.setCommand(event->{
			new SecurityContextLogoutHandler().logout(VaadinServletService.getCurrentServletRequest(),null, null);
			SecurityContextHolder.getContext().setAuthentication(null);
			getSession().close();
			Page.getCurrent().setLocation("/");
		});

		layout.setSizeUndefined();
		layout.addComponent(newsbar);
		layout.addComponent(managebar);
		layout.addComponent(messagebar);
		layout.addComponent(accountbar);
		layout.addComponent(signoutbar);
		
		setContent(layout);
	}
	
	public void setSelected(MenuItem selected)
	{
		if(selected != null)
		{
			clearSelected();
			
			if(selected.equals(whatNew))
				whatNew.setIcon(VaadinIcons.ARROW_CIRCLE_RIGHT);
			else if(selected.equals(manage))
				manage.setIcon(VaadinIcons.ARROW_CIRCLE_RIGHT);
			else if(selected.equals(message))
				message.setIcon(VaadinIcons.ARROW_CIRCLE_RIGHT);	
			else if(selected.equals(account))
				account.setIcon(VaadinIcons.ARROW_CIRCLE_RIGHT);
		}
	}
	
	private void clearSelected()
	{
		whatNew.setIcon(null);
		manage.setIcon(null);
		message.setIcon(null);
		account.setIcon(null);
	}
}
