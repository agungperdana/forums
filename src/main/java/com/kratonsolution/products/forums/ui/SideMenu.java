/**
 * 
 */
package com.kratonsolution.products.forums.ui;

import java.util.Optional;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

import com.vaadin.server.Page;
import com.vaadin.server.VaadinServletService;
import com.vaadin.ui.ListSelect;
import com.vaadin.ui.Panel;

import lombok.Getter;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com 
 */
@Getter
public class SideMenu extends Panel
{
	private ListSelect<SideMenuItem> layout = new ListSelect<>();
	
	public SideMenu()
	{
		setWidth("15%");
		setHeight("99%");

		layout.setSizeFull();
		layout.setItems(new SideMenuItem("What's New", "/static/icons/event.png"),
						new SideMenuItem("Manage Tribe", "/static/icons/event.png"),
						new SideMenuItem("Message", "/static/icons/event.png"),
						new SideMenuItem("My Account", "/static/icons/event.png"),
						new SideMenuItem("Sign Out", "/static/icons/event.png"));
		
		layout.addSelectionListener(event->{
			Optional<SideMenuItem> selected = event.getFirstSelectedItem();
			switch (selected.get().getName())
			{
				case "What's New":
				
				break;
				case "Manage Tribe":
					
				break;
				case "Message":
					
				break;
				case "My Account":
					
				break;
				default:
					new SecurityContextLogoutHandler().logout(VaadinServletService.getCurrentServletRequest(),null, null);
					SecurityContextHolder.getContext().setAuthentication(null);
					getSession().close();
					Page.getCurrent().setLocation("/");
				break;
			}
		});
		
		setContent(layout);
	}
	
	@Getter
	private class SideMenuItem
	{
		String icon;
		
		String name;
		
		public SideMenuItem(String name,String icon)
		{
			this.icon = icon;
			this.name = name;
		}
		
		@Override
		public String toString()
		{
			return this.name;
		}
	}
}
