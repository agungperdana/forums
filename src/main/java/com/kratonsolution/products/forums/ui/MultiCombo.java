/**
 * 
 */
package com.kratonsolution.products.forums.ui;

import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

import lombok.Getter;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com 
 */
public class MultiCombo extends VerticalLayout
{
	private ComboBox<String> active = new ComboBox<>();
	
	private HorizontalLayout selecteds = new HorizontalLayout();
	
	public MultiCombo(String caption)
	{
		setCaption(caption);
		setWidth("100%");
		
		selecteds.setSizeUndefined();
		selecteds.setStyleName(ValoTheme.LAYOUT_HORIZONTAL_WRAPPING);
		
		addComponent(active);
		addComponent(selecteds);
		
		active.setWidth("100%");
		active.addSelectionListener(event->{
			if(active.getValue() != null && !active.getValue().equals(""))
			{
				selecteds.addComponent(new Choosed(active.getValue(), active.getValue()));
				active.setSelectedItem(null);
			}
		});
	}
	
	public void setItems(String... items)
	{
		active.setItems(items);
	}
	
	public void setItems(Collection<String> items)
	{
		active.setItems(items);
	}
	
	public Collection<Choosed> getAllSelected()
	{
		Vector<Choosed> results = new Vector<>();
		
		Iterator<Component> iterator = iterator();
		while (iterator.hasNext())
		{
			Component com = (Component) iterator.next();
			if(com instanceof Choosed)
				results.add((Choosed)com);
		}
		
		return results;
	}
	
	@Getter
	private class Choosed extends HorizontalLayout
	{
		private String id;
		
		private Label label = new Label();
		
		private Button close = new Button(VaadinIcons.CLOSE_SMALL);
		
		public Choosed(String id,String value)
		{
			this.id = id;
			label.setValue(value);
			
			close.setWidth("20px");
			close.setHeight("20px");
			close.setStyleName(ValoTheme.BUTTON_ICON_ONLY);
			
			setSizeUndefined();
			addComponent(label);
			addComponent(close);
			setExpandRatio(label, 3f);
			setExpandRatio(close, 1f);
			
			close.addClickListener(event->selecteds.removeComponent(this));
		}
	}
}
