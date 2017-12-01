/**
 * 
 */
package com.kratonsolution.products.forums.web.ui;

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
		setSizeUndefined();
		setSpacing(true);
		setMargin(false);
		setStyleName("paging");
		
		first.setWidth("100%");
		first.setStyleName(ValoTheme.BUTTON_FRIENDLY);
		prev.setWidth("100%");
		prev.setStyleName(ValoTheme.BUTTON_FRIENDLY);
		next.setWidth("100%");
		next.setStyleName(ValoTheme.BUTTON_FRIENDLY);
		last.setWidth("100%");
		last.setStyleName(ValoTheme.BUTTON_FRIENDLY);
		
		display.setWidth("100%");
		display.setValue(page+" of "+totalPage);
		
		addComponent(first);
		addComponent(prev);
		addComponent(display);
		addComponent(next);
		addComponent(last);
		
		setComponentAlignment(first, Alignment.MIDDLE_RIGHT);
		setComponentAlignment(last, Alignment.MIDDLE_RIGHT);
		setComponentAlignment(display, Alignment.MIDDLE_RIGHT);
		setComponentAlignment(next, Alignment.MIDDLE_RIGHT);
		setComponentAlignment(last, Alignment.MIDDLE_RIGHT);
		
		if(totalPage==1)
		{
			first.setEnabled(false);
			prev.setEnabled(false);
			next.setEnabled(false);
			last.setEnabled(false);
		}
	}
}
