/**
 * 
 */
package com.kratonsolution.products.forums.ui;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

import com.kratonsolution.products.forums.ui.tribe.TribeTab;
import com.kratonsolution.products.forums.ui.user.UserTab;
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
import com.vaadin.ui.TabSheet.Tab;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com 
 */
@SpringUI(path="/Admin")
@Theme("light")
@Title("Mark 3")
@Viewport("user-scalable=no,initial-scale=1.0")
public class Admin extends UI
{
	@Override
	protected void init(VaadinRequest request)
	{
		TabSheet sheet = new TabSheet();
		
		MenuBar bar = new MenuBar();
		bar.setStyleName(ValoTheme.MENUBAR_BORDERLESS+" "+ValoTheme.MENUBAR_SMALL);
		MenuItem mark3 = bar.addItem("",VaadinIcons.BUG_O, null);
		mark3.addItem("Preferences",VaadinIcons.TOOLS, event->{
			UI.getCurrent().addWindow(new Preferences());
		});
		mark3.addSeparator();

		mark3.addItem("User",Icons.SIGN_IN, event->{
			Tab on = sheet.addTab(new UserTab(),"User",Icons.SIGN_IN);
			on.setClosable(true);
			sheet.setSelectedTab(on);
		});

		mark3.addItem("Tribe Administration",VaadinIcons.BOOK, event->{
			Tab on = sheet.addTab(new TribeTab(),"Tribe Administration",VaadinIcons.BOOK);
			on.setClosable(true);
			sheet.setSelectedTab(on);
		});
		
		mark3.addSeparator();
		
		mark3.addItem("Sign Out",VaadinIcons.SIGN_OUT, event->{
			new SecurityContextLogoutHandler().logout(VaadinServletService.getCurrentServletRequest(),null, null);
			SecurityContextHolder.getContext().setAuthentication(null);
			getSession().close();
			Page.getCurrent().setLocation("/");
		});

		VerticalLayout root = new VerticalLayout(bar);
		root.setSpacing(false);
		root.setMargin(false);
		root.addComponentsAndExpand(sheet);
		
		setContent(root);
	}
}
