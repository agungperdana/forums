/**
 * 
 */
package com.kratonsolution.products.forums.ui;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com 
 */
public abstract class Table<T> extends VerticalLayout
{
	protected Toolbar toolbar = new Toolbar();
	
	protected Grid<T> grid = new Grid<T>();

	protected Paging north = new Paging();
	
	protected Paging south = new Paging();
	
	public Table()
	{
		HorizontalLayout top = new HorizontalLayout();
		top.setWidth("100%");
		top.setSpacing(false);
		top.setMargin(false);
		top.addComponent(toolbar);
		top.addComponent(north);
		top.setComponentAlignment(toolbar, Alignment.MIDDLE_LEFT);
		top.setComponentAlignment(north, Alignment.MIDDLE_RIGHT);
		top.setExpandRatio(toolbar, 3.7f);
		top.setExpandRatio(north, 1.3f);
		
		HorizontalLayout bot = new HorizontalLayout();
		bot.setWidth("100%");
		bot.addComponent(south);
		bot.setComponentAlignment(south, Alignment.MIDDLE_RIGHT);
		
		setWidth("100%");
		setSpacing(false);
		setMargin(false);
		addComponent(top);
		addComponent(grid);
		addComponent(bot);
	}

	protected void refresh()
	{
		grid.getDataProvider().refreshAll();
		grid.setDataProvider(grid.getDataProvider());
	}
}
