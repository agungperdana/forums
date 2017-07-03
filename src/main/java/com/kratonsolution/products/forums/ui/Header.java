/**
 * 
 */
package com.kratonsolution.products.forums.ui;

import com.kratonsolution.products.forums.dm.FindType;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
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
		setMargin(new MarginInfo(true, false, false, false));
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
		finds.setSelectedItem(FindType.TRIBE);
		finds.setWidth("100px");
		
		TextField search = new TextField();
		search.setPlaceholder("Search...");
		
		Button login = new Button("Sign In");
		login.setStyleName(ValoTheme.BUTTON_LINK);
		
		Label separator = new Label("|");
		
		Button create = new Button("Create Account");
		create.setStyleName(ValoTheme.BUTTON_LINK);
		
		navigation.addComponent(lfind);
		navigation.addComponent(finds);
		navigation.addComponent(search);
		navigation.addComponent(login);
		navigation.addComponent(separator);
		navigation.addComponent(create);
		
		navigation.setComponentAlignment(create, Alignment.MIDDLE_LEFT);
		navigation.setComponentAlignment(separator, Alignment.MIDDLE_RIGHT);
		navigation.setComponentAlignment(login, Alignment.MIDDLE_RIGHT);
		navigation.setComponentAlignment(search, Alignment.MIDDLE_RIGHT);
		navigation.setComponentAlignment(finds, Alignment.MIDDLE_RIGHT);
		navigation.setComponentAlignment(lfind, Alignment.MIDDLE_RIGHT);
		
		navigation.setExpandRatio(create, 3.5f);
		navigation.setExpandRatio(separator, 1f);
		navigation.setExpandRatio(login, 1f);
		navigation.setExpandRatio(search, 6f);
		navigation.setExpandRatio(finds, 1f);
		navigation.setExpandRatio(lfind, 1f);
	}
}
