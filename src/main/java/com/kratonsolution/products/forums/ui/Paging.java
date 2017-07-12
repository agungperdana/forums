/**
 * 
 */
package com.kratonsolution.products.forums.ui;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.themes.ValoTheme;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com 
 */
public class Paging extends HorizontalLayout
{
	private int page = 1;

	private int totalPage = 1;

	private Button first = new Button(VaadinIcons.ANGLE_DOUBLE_LEFT);

	private Button prev = new Button(VaadinIcons.ANGLE_LEFT);

	private Label display = new Label();

	private Button next = new Button(VaadinIcons.ANGLE_RIGHT);

	private Button last = new Button(VaadinIcons.ANGLE_DOUBLE_RIGHT);

	public Paging()
	{
		setWidth("100%");
		
		Label empty = new Label();
		empty.setWidth("100%");
		
		HorizontalLayout layout = new HorizontalLayout();
		layout.setWidth("100%");
		
		first.setSizeUndefined();
		first.setStyleName(ValoTheme.BUTTON_FRIENDLY);
		prev.setSizeUndefined();
		prev.setStyleName(ValoTheme.BUTTON_FRIENDLY);
		next.setSizeUndefined();
		next.setStyleName(ValoTheme.BUTTON_FRIENDLY);
		last.setSizeUndefined();
		last.setStyleName(ValoTheme.BUTTON_FRIENDLY);
		
		display.setWidth("100px");
		display.setValue(page+" of "+totalPage);
		
		addComponent(empty);
		addComponent(layout);
		
		layout.addComponent(first);
		layout.addComponent(prev);
		layout.addComponent(display);
		layout.addComponent(next);
		layout.addComponent(last);
		
		layout.setComponentAlignment(first, Alignment.MIDDLE_RIGHT);
		layout.setComponentAlignment(last, Alignment.MIDDLE_RIGHT);
		layout.setComponentAlignment(display, Alignment.MIDDLE_RIGHT);
		layout.setComponentAlignment(next, Alignment.MIDDLE_RIGHT);
		layout.setComponentAlignment(last, Alignment.MIDDLE_RIGHT);
	
		setExpandRatio(empty, 4f);
		setExpandRatio(layout, 1f);
		
		if(totalPage==1)
		{
			first.setEnabled(false);
			prev.setEnabled(false);
			next.setEnabled(false);
			last.setEnabled(false);
		}
	}
}
