/**
 * 
 */
package com.kratonsolution.products.forums.web.ui.tribe;

import com.kratonsolution.products.forums.common.util.Springs;
import com.kratonsolution.products.forums.dm.Tribe;
import com.kratonsolution.products.forums.svc.TribeService;
import com.kratonsolution.products.forums.web.ui.Icons;
import com.kratonsolution.products.forums.web.ui.TribeEvent;
import com.kratonsolution.products.forums.web.ui.TribeListener;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com 
 */
public class MyTribe extends VerticalLayout implements TribeListener
{
	private Label title = new Label("<h3><u><font style='color:red;'>M</font>y Tribe</u></h3>", ContentMode.HTML);

	private HorizontalLayout north = new HorizontalLayout();

	private HorizontalLayout  contents = new HorizontalLayout();
	
	private Button addtribe = new Button(Icons.MY_OWN_TRIBE);

	private Panel panel = new Panel(contents);

	private TribeService service = Springs.get(TribeService.class);
	
	public MyTribe()
	{
		setSizeFull();

		north.setSizeUndefined();
		north.setSpacing(true);
		north.addComponent(title);
		north.setComponentAlignment(title, Alignment.MIDDLE_LEFT);

		panel.setSizeFull();
		panel.addStyleName(ValoTheme.PANEL_BORDERLESS);

		contents.setStyleName(ValoTheme.LAYOUT_HORIZONTAL_WRAPPING);
		contents.setSpacing(true);
		contents.setMargin(true);
		
		addtribe.setStyleName(ValoTheme.BUTTON_ICON_ONLY);
		addtribe.setHeight("100px");
		addtribe.setWidth("100px");
		addtribe.addClickListener(event->{
			TribeForm form = new TribeForm();
			form.addTribeListener(MyTribe.this);
			
			UI.getCurrent().addWindow(form);
		});
		
		addComponent(north);
		addComponentsAndExpand(panel);
		
		populateMyTribeContent();
	}

	private void populateMyTribeContent()
	{
		contents.removeAllComponents();
		contents.addComponent(addtribe);

		for(Tribe tribe:service.findAllInvolved())
		{
			TribeDisplay display = new TribeDisplay(tribe,"250px");
			display.addClickListener(click->{
				UI.getCurrent().addWindow(new TribeEditForm(tribe));
			});

			contents.addComponent(display);
		}
	}
	
	@Override
	public void refresh(TribeEvent event)
	{
		populateMyTribeContent();
	}
}
