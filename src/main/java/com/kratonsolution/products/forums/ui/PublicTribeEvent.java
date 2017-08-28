/**
 * 
 */
package com.kratonsolution.products.forums.ui;

import com.kratonsolution.products.forums.common.Springs;
import com.kratonsolution.products.forums.svc.TribeEventService;
import com.kratonsolution.products.forums.ui.tribe.EventDisplay;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com 
 */
public class PublicTribeEvent extends Panel
{
	private Label title = new Label("<h3><u><font style='color:red;'>T</font>op Event</u></h3>", ContentMode.HTML);

	private VerticalLayout root = new  VerticalLayout();

	private VerticalLayout contents = new  VerticalLayout();

	private Button more = new Button("Load More", VaadinIcons.CARET_DOWN);

	private int start = 0;

	public PublicTribeEvent()
	{
		setSizeFull();
		addStyleName(ValoTheme.PANEL_BORDERLESS);

		root.setMargin(new MarginInfo(true,false,true,false));
		root.setSpacing(false);
		root.addComponent(title);
		root.addComponent(contents);
		root.addComponent(more);
		root.setExpandRatio(title, 0.5f);
		root.setExpandRatio(contents, 9f);
		root.setExpandRatio(more, 0.5f);
		root.setComponentAlignment(more, Alignment.MIDDLE_CENTER);

		contents.addComponent(new Event(start));
		
		more.setWidth("95%");
		more.setHeight("25px");
		more.addStyleName(ValoTheme.BUTTON_ICON_ALIGN_RIGHT);
		more.addStyleName(ValoTheme.BUTTON_LINK);
		more.addClickListener(click->{
			start++;
			
			Event display = new Event(start); 
			if(display.getComponentCount() < 3)
				more.setEnabled(false);
			
			contents.addComponent(new Event(start));
		});
		
		setContent(root);
	}
	
	private class Event extends GridLayout
	{
		private TribeEventService service = Springs.get(TribeEventService.class);

		public Event(int start)
		{
			setRows(2);
			setColumns(1);
			setSpacing(true);
			setWidth("99%");
			
			service.findAll(start, 2).forEach(event->{
				addComponent(new EventDisplay(event));
			});
		}
	}
}
