/**
 * 
 */
package com.kratonsolution.products.forums.web.ui.tribe;

import com.kratonsolution.products.forums.common.util.Springs;
import com.kratonsolution.products.forums.dm.Tribe;
import com.kratonsolution.products.forums.svc.TribeService;
import com.kratonsolution.products.forums.web.ui.HomeContent;
import com.kratonsolution.products.forums.web.ui.Icons;
import com.kratonsolution.products.forums.web.ui.TribeEvent;
import com.kratonsolution.products.forums.web.ui.TribeListener;
import com.vaadin.ui.Accordion;
import com.vaadin.ui.Button;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.UI;
import com.vaadin.ui.themes.ValoTheme;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com 
 */
public class ManageTribe extends GridLayout implements HomeContent, TribeListener
{
	private TribeService service = Springs.get(TribeService.class);

	private Button addtribe = new Button(Icons.MY_OWN_TRIBE);

	private HorizontalLayout mytribeLayout = new HorizontalLayout();
	
	private HorizontalLayout alltribeLayout = new HorizontalLayout();
	
	public ManageTribe()
	{
		setColumns(1);
		setRows(2);
		setWidth("100%");
		setHeight("100%");
		setSpacing(true);
		setMargin(true);
		
		mytribeLayout.setStyleName(ValoTheme.LAYOUT_HORIZONTAL_WRAPPING);
		mytribeLayout.setSpacing(true);
		mytribeLayout.setMargin(true);
		
		alltribeLayout.setStyleName(ValoTheme.LAYOUT_HORIZONTAL_WRAPPING);
		alltribeLayout.setSpacing(true);
		alltribeLayout.setMargin(true);

		addtribe.setStyleName(ValoTheme.BUTTON_ICON_ONLY);
		addtribe.setHeight("100px");
		addtribe.setWidth("100px");
		addtribe.addClickListener(event->{
			TribeForm form = new TribeForm();
			form.addTribeListener(ManageTribe.this);
			
			UI.getCurrent().addWindow(form);
		});

		Accordion myTribe = new Accordion();
		myTribe.setStyleName(ValoTheme.ACCORDION_BORDERLESS);
		myTribe.setHeight("99%");
		myTribe.setWidth("100%");
		myTribe.addTab(mytribeLayout,"My Tribe",Icons.TRIBE_NEWS);

		Accordion browseTribe = new Accordion();
		browseTribe.setStyleName(ValoTheme.ACCORDION_BORDERLESS);
		browseTribe.setWidth("100%");
		browseTribe.addTab(alltribeLayout,"Browse Tribe",Icons.TRIBE_EVENT);
		
		addComponent(new MyTribe());
		addComponent(new BrowseTribe());

		populateMyTribeContent();
		populateBrowseTribe();
	}

	private void populateMyTribeContent()
	{
		mytribeLayout.removeAllComponents();
		
		mytribeLayout.addComponent(addtribe);

		for(Tribe tribe:service.findAllInvolved())
		{
			TribeDisplay display = new TribeDisplay(tribe,"250px");
			display.addClickListener(click->{
				UI.getCurrent().addWindow(new TribeEditForm(tribe));
			});

			mytribeLayout.addComponent(display);
		}
	}
	
	private void populateBrowseTribe()
	{
		for(Tribe tribe:service.findAllApproved())
		{
			TribeDisplay display = new TribeDisplay(tribe,"250px");
			display.addClickListener(click->{

			});

			alltribeLayout.addComponent(display);
		}
	}

	@Override
	public void refresh(TribeEvent event)
	{
		populateMyTribeContent();
		populateBrowseTribe();
	}
}
