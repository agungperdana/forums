/**
 * 
 */
package com.kratonsolution.products.forums.ui;

import com.vaadin.ui.Grid;
import com.vaadin.ui.VerticalLayout;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com 
 */
public abstract class Table<T> extends VerticalLayout
{
	protected Toolbar toolbar = new Toolbar();
	
	protected Grid<T> grid = new Grid<T>();

	protected Paging paging = new Paging();
	
	public Table()
	{
		setWidth("100%");
		setSpacing(false);
		setMargin(false);
		addComponent(toolbar);
		addComponent(grid);
		addComponent(paging);
		setExpandRatio(grid, 0.5f);
		setExpandRatio(grid, 4f);
		setExpandRatio(paging, 0.5f);
	}

	protected void refresh()
	{
		grid.getDataProvider().refreshAll();
		grid.setDataProvider(grid.getDataProvider());
	}
}
