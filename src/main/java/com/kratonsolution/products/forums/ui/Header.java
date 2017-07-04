/**
 * 
 */
package com.kratonsolution.products.forums.ui;

import com.kratonsolution.products.forums.dm.FindType;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.ValoTheme;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com 
 */
@SpringComponent
@UIScope
public class Header extends HorizontalLayout
{
	private HorizontalLayout logo = new HorizontalLayout();
	
	private HorizontalLayout navigation = new HorizontalLayout();
	
	public Header()
	{
		setMargin(new MarginInfo(false, true, false, false));
		setWidth("100%");
		
		addComponent(logo);
		addComponent(navigation);
		setExpandRatio(logo, 1.1f);
		setExpandRatio(navigation, 1f);

		initLogo();
		initNavogation();
	}
	
	private void initLogo()
	{
		Label icon = new Label("MARK3");
		
		logo.setMargin(new MarginInfo(false,false,false,true));
		logo.setWidth("100%");
		logo.addComponent(icon);
		logo.setComponentAlignment(icon, Alignment.MIDDLE_LEFT);
	}
	
	private void initNavogation()
	{
		navigation.setWidth("100%");
		
		Label lfind = new Label("Find: ");
		
		ComboBox<FindType> finds = new ComboBox<>();
		finds.setItems(FindType.values());
		finds.setWidth("100px");
		finds.setEnabled(true);
		finds.setSelectedItem(FindType.TRIBE);
		
		TextField search = new TextField();
		search.setPlaceholder("Search...");
		search.setWidth("80%");
		
		MenuBar bar = new MenuBar();
		bar.setStyleName(ValoTheme.MENUBAR_BORDERLESS+" "+ValoTheme.MENUBAR_SMALL);
		
		bar.addItem("Sign In",Icons.SIGN_IN,event->{});
		bar.addItem("|",event->{});
		bar.addItem("Create Account",Icons.CREATE_ACCOUNT,event->{});
		
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
