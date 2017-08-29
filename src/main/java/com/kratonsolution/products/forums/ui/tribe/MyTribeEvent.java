/**
 * 
 */
package com.kratonsolution.products.forums.ui.tribe;

import java.util.ArrayList;
import java.util.List;

import com.kratonsolution.products.forums.common.Springs;
import com.kratonsolution.products.forums.dm.TribeEvent;
import com.kratonsolution.products.forums.dm.TribeEventRepository;
import com.kratonsolution.products.forums.svc.TribeService;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com 
 */
public class MyTribeEvent extends VerticalLayout
{
	private TribeEventRepository eventRepository = Springs.get(TribeEventRepository.class);

	private TribeService tribeService = Springs.get(TribeService.class);

	private Label title = new Label("<h3><u><font style='color:red;'>T</font>ribe Event</u></h3>", ContentMode.HTML);

	private GridLayout layout = new GridLayout();

	private Panel panel = new Panel(layout);

	public MyTribeEvent()
	{
		setSizeFull();

		panel.setWidth("99%");
		panel.addStyleName(ValoTheme.PANEL_BORDERLESS);

		addComponent(title);
		addComponentsAndExpand(panel);

		layout.setSpacing(true);

		List<TribeEvent> events = new ArrayList<TribeEvent>();

		tribeService.findAllInvolved().forEach(tribe->{
			events.addAll(eventRepository.findAllByTribe(tribe.getId()));
		});

		if(events.isEmpty())
		{
			Label label = new Label(null, ContentMode.HTML);
			label.setValue("<h3>No event.</h3>");

			layout.addComponent(label);
		}
		else
		{
			int rows = (events.size()-(events.size()%4))/4;
			if(rows == 0)
				rows = 1;
			
			layout.setColumns(4);
			layout.setRows(rows);
			
			events.forEach(event->{
				layout.addComponent(new EventDisplay(event,"250px"));
			});
		}
	}
}
