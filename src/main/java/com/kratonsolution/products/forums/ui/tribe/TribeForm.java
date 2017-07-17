/**
 * 
 */
package com.kratonsolution.products.forums.ui.tribe;

import com.kratonsolution.products.forums.ui.Icons;
import com.vaadin.ui.Accordion;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com 
 */
public class TribeForm extends Window
{
	private HorizontalLayout layout = new HorizontalLayout();
	
	private Panel left = new Panel();
	
	private VerticalLayout lContent = new VerticalLayout();
	
	private GridLayout right = new GridLayout(1,2);
	
	private VerticalLayout logoarea = new VerticalLayout();
	
	private VerticalLayout eventarea = new VerticalLayout();
	
	public TribeForm()
	{
		setWidth("835px");
		setHeight("625px");
		setCaption("Create New Tribe");
		setIcon(Icons.TRIBE_TOP);
		setModal(true);
		setResizable(false);
		setDraggable(false);
		
		center();
		
		left.setCaption("Tribe Info");
		left.setContent(lContent);
		left.setWidth("100%");
		
		Accordion pic = new Accordion();
		pic.setStyleName(ValoTheme.ACCORDION_BORDERLESS);
		pic.addTab(logoarea,"Tribe Logo");
		
		Accordion event = new Accordion();
		event.setStyleName(ValoTheme.ACCORDION_BORDERLESS);
		event.addTab(eventarea,"Tribe Event");
		
		right.setWidth("100%");
		right.setHeight("100%");
		right.addComponent(pic);
		right.addComponent(event);
		
		layout.setMargin(true);
		layout.setWidth("100%");
		layout.setHeight("100%");
		layout.addComponent(left);
		layout.addComponent(right);
		layout.setExpandRatio(left, 3f);
		layout.setExpandRatio(right, 1f);
		
		setContent(layout);
	}
}
