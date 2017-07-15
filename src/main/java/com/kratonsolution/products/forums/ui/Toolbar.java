/**
 * 
 */
package com.kratonsolution.products.forums.ui;

import com.vaadin.ui.MenuBar;
import com.vaadin.ui.themes.ValoTheme;

import lombok.Getter;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com 
 */
@Getter
public class Toolbar extends MenuBar
{
	private MenuItem add = addItem("New", Icons.NEW_DATA, null);
	
	private MenuItem refresh = addItem("Refresh", Icons.REFRESH_DATA, null);
	
	private MenuItem remove = addItem("Remove", Icons.REMOVE_DATA, null);
	
	public Toolbar()
	{
		setStyleName(ValoTheme.MENUBAR_BORDERLESS);
		setWidth("100%");
	}
}