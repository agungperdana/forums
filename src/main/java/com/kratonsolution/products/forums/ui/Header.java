/**
 * 
 */
package com.kratonsolution.products.forums.ui;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.kratonsolution.products.forums.common.Springs;
import com.kratonsolution.products.forums.dm.FindType;
import com.kratonsolution.products.forums.svc.UserService;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.themes.ValoTheme;

import lombok.Getter;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com 
 */
@Getter
public class Header extends HorizontalLayout
{
	private HorizontalLayout logo = new HorizontalLayout();
	
	private HorizontalLayout navigation = new HorizontalLayout();
	
	private UserDetailsService authService = Springs.get(UserDetailsService.class);
	
	private AuthenticationManager manager = Springs.get(AuthenticationManager.class);
	
	private UserService service = Springs.get(UserService.class);
	
	public Header()
	{
		setMargin(new MarginInfo(false, true, false, false));
		setWidth("100%");
		
		addComponent(logo);
		addComponent(navigation);
		setExpandRatio(logo, 2.5f);
		setExpandRatio(navigation, 2.5f);

		initLogo();
		initNavogation();
	}
	
	private void initLogo()
	{
		Label icon = new Label("");
		icon.setIcon(Icons.LOGO);
		
		logo.setMargin(false);
		logo.setSpacing(false);
		logo.setWidth("100%");
		logo.setHeight("85%");
		logo.addComponent(icon);
		logo.setComponentAlignment(icon, Alignment.TOP_LEFT);
	}
	
	private void initNavogation()
	{
		navigation.setSpacing(true);
		
		Label lfind = new Label("Find: ");
		
		ComboBox<FindType> finds = new ComboBox<>();
		finds.setItems(FindType.values());
		finds.setSelectedItem(FindType.TRIBE);
		finds.setWidth("115px");
		
		TextField search = new TextField();
		search.setPlaceholder("Search...");
		search.setWidth("80%");
		
		MenuBar bar = new MenuBar();
		bar.setStyleName(ValoTheme.MENUBAR_BORDERLESS+" "+ValoTheme.MENUBAR_SMALL);
		
		bar.addItem("Sign In",Icons.SIGN_IN,event->{
			UI.getCurrent().addWindow(new Signin(authService,manager));
		});
		
		bar.addItem("|",event->{});
		
		bar.addItem("Create Account",Icons.CREATE_ACCOUNT,event->{
			UI.getCurrent().addWindow(new Registration());
		});
		
		navigation.addComponent(lfind);
		navigation.addComponent(finds);
		navigation.addComponent(search);
		navigation.addComponent(bar);
		
		navigation.setComponentAlignment(bar, Alignment.MIDDLE_RIGHT);
		navigation.setComponentAlignment(search, Alignment.MIDDLE_RIGHT);
		navigation.setComponentAlignment(finds, Alignment.MIDDLE_RIGHT);
		navigation.setComponentAlignment(lfind, Alignment.MIDDLE_RIGHT);
		
		navigation.setExpandRatio(bar, 5.5f);
		navigation.setExpandRatio(search, 5f);
		navigation.setExpandRatio(finds, 1f);
		navigation.setExpandRatio(lfind, 1f);
	}
}
