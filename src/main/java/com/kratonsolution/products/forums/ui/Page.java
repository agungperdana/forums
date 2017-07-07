/**
 * 
 */
package com.kratonsolution.products.forums.ui;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com 
 */
public abstract class Page extends UI
{
	protected VerticalLayout layout = new VerticalLayout();
	
	protected HorizontalLayout line = new HorizontalLayout();
	
	protected HorizontalLayout content = new HorizontalLayout();
	
	@Autowired
	protected Header header = new Header();
	
	protected void display()
	{
		line.setWidth("100%");
    	line.setHeight("10px");
    	line.setStyleName("top-line");
    	
    	content.setWidth("100%");
    	content.setHeight("100%");
    	content.setSpacing(false);
    	content.setMargin(false);
		
    	layout.setHeight("100%");
    	layout.setStyleName(ValoTheme.LAYOUT_CARD);

    	layout.addComponent(header);
    	layout.addComponent(line);
    	layout.addComponent(content);
    	
    	layout.setExpandRatio(header, 0.6f);
    	layout.setExpandRatio(line, 0.2f);
    	layout.setExpandRatio(content, 11f);
	}
	
	public abstract void initContent();
}
