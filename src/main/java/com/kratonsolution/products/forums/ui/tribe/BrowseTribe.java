/**
 * 
 */
package com.kratonsolution.products.forums.ui.tribe;

import com.kratonsolution.products.forums.common.Springs;
import com.kratonsolution.products.forums.svc.TribeService;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com 
 */
public class BrowseTribe extends VerticalLayout
{
	private Label title = new Label("<h3><u><font style='color:red;'>B</font>rowse Tribe</u></h3>", ContentMode.HTML);

	private HorizontalLayout north = new HorizontalLayout();

	private VerticalLayout  contents = new VerticalLayout();

	private Panel panel = new Panel(contents);

	private Button more = new Button("Load More", VaadinIcons.CARET_DOWN);

	private int start = 0;

	public BrowseTribe()
	{
		setSizeFull();

		TextField search = new TextField("Find");
		search.setWidth("250px");
		search.addValueChangeListener(event->{
			start = 0;
			
			contents.removeAllComponents();
			contents.addComponent(new Tribe(start, event.getValue()));
		});

		FormLayout layout = new FormLayout();
		layout.addComponent(search);

		north.setWidth("40%");
		north.setSpacing(true);
		north.addComponent(title);
		north.addComponent(layout);
		north.setComponentAlignment(title, Alignment.MIDDLE_LEFT);
		north.setComponentAlignment(layout, Alignment.MIDDLE_RIGHT);

		panel.setSizeFull();
		panel.addStyleName(ValoTheme.PANEL_BORDERLESS);
		
		more.setWidth("95%");
		more.setHeight("25px");
		more.addStyleName(ValoTheme.BUTTON_ICON_ALIGN_RIGHT);
		more.addStyleName(ValoTheme.BUTTON_LINK);
		more.addClickListener(click->{
			start++;

			Tribe display = new Tribe(start,search.getValue()); 
			if(display.getComponentCount() < 9)
				more.setEnabled(false);

			contents.addComponent(new Tribe(start));
		});

		addComponent(north);
		addComponentsAndExpand(panel);
		
		contents.addComponent(new Tribe(start,search.getValue()));
	}
	
	private class Tribe extends GridLayout
	{
		private TribeService service = Springs.get(TribeService.class);

		public Tribe(int start,String key)
		{
			setRows(2);
			setColumns(4);
			setSpacing(true);
			setWidth("99%");
			
			service.findAllApproved(start, 8,key).forEach(tribe->{
				TribeDisplay display = new TribeDisplay(tribe);
				display.addClickListener(click->{
					UI.getCurrent().addWindow(new TribeInfo(tribe));
				});
				addComponent(display);
			});
		}
		
		public Tribe(int start)
		{
			this(start,null);
		}
	}
}
