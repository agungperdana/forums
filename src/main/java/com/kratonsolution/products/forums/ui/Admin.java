/**
 * 
 */
package com.kratonsolution.products.forums.ui;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.Viewport;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServletService;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com 
 */
@SpringUI(path="/Admin")
@Theme("valo")
@Title("Mark 3")
@Viewport("user-scalable=no,initial-scale=1.0")
public class Admin extends UI
{
	@Override
	protected void init(VaadinRequest request)
	{
		TabSheet sheet = new TabSheet();
		sheet.setWidth("100%");
		sheet.setStyleName(ValoTheme.TABLE_BORDERLESS);
		
		MenuBar bar = new MenuBar();
		bar.setStyleName(ValoTheme.MENUBAR_SMALL);
		bar.setWidth("100%");
		bar.setHeight("35px");
		MenuItem mark3 = bar.addItem("",VaadinIcons.BUG_O, null);
		mark3.addItem("User",VaadinIcons.USER, null);
		mark3.addItem("Tribe Administration",VaadinIcons.TOOLS, null);
		mark3.addSeparator();
		mark3.addItem("Sign Out",VaadinIcons.SIGN_OUT, event->{
			new SecurityContextLogoutHandler().logout(VaadinServletService.getCurrentServletRequest(),null, null);
			SecurityContextHolder.getContext().setAuthentication(null);
			getSession().close();
			Page.getCurrent().setLocation("/");
		});

		VerticalLayout layout = new VerticalLayout();
		layout.setWidth("100%");
		layout.setHeight("100%");
		layout.setSpacing(false);
		layout.setMargin(false);
		layout.addComponent(bar);
		layout.addComponent(sheet);
		layout.setExpandRatio(bar, 0.6f);
		layout.setExpandRatio(sheet, 18f);
		
		setContent(layout);
	}
}
