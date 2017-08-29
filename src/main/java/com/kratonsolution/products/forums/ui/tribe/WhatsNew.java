/**
 * 
 */
package com.kratonsolution.products.forums.ui.tribe;

import com.kratonsolution.products.forums.ui.HomeContent;
import com.vaadin.ui.GridLayout;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com 
 */
public class WhatsNew extends GridLayout implements HomeContent
{
	public WhatsNew()
	{
		setSizeFull();
		setSpacing(false);
		setMargin(true);
		setColumns(1);
		setRows(2);

		addComponent(new MyTribeNews());
		addComponent(new MyTribeEvent());
	}
}
