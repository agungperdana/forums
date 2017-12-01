/**
 * 
 */
package com.kratonsolution.products.forums.web.ui;

import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;

import com.kratonsolution.products.forums.dm.PersonalInfo;
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
	private ComboBox<PersonalInfo> active = new ComboBox<>();
	
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
				selecteds.addComponent(new Selected(active.getSelectedItem().get()));
				active.setSelectedItem(null);
			}
		});
	}
	
	public void setItems(PersonalInfo items)
	{
		active.setItems(items);
	}
	
	public void setItems(Collection<PersonalInfo> items)
	{
		active.setItems(items);
	}
	
	public void setSelecteds(Collection<PersonalInfo> items)
	{
		items.forEach(item->{
			selecteds.addComponent(new Selected(item));
		});
	}
	
	public Collection<PersonalInfo> getAllSelected()
	{
		Vector<PersonalInfo> results = new Vector<>();
		
		Iterator<Component> iterator = selecteds.iterator();
		while (iterator.hasNext())
		{
			Component com = (Component) iterator.next();
			if(com instanceof Selected)
				results.add(((Selected)com).getBean());
		}
		
		return results;
	}
	
	@Getter
	private class Selected extends HorizontalLayout
	{
		private PersonalInfo bean;
		
		private Label label = new Label();
		
		private Button close = new Button(VaadinIcons.CLOSE_SMALL);
		
		public Selected(PersonalInfo info)
		{
			if(info == null)
				throw new RuntimeException("User information cannot be null");
			
			this.bean = info;
			
			label.setValue(info.getName());
			
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
