/**
 * 
 */
package com.kratonsolution.products.forums.ui.tribe;

import com.kratonsolution.products.forums.common.Security;
import com.kratonsolution.products.forums.common.Springs;
import com.kratonsolution.products.forums.dm.Tribe;
import com.kratonsolution.products.forums.svc.TribeService;
import com.kratonsolution.products.forums.ui.HomeContent;
import com.kratonsolution.products.forums.ui.Icons;
import com.kratonsolution.products.forums.ui.RefreshEvent;
import com.kratonsolution.products.forums.ui.UIRefreshListener;
import com.vaadin.ui.Accordion;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com 
 */
public class ManageTribe extends VerticalLayout implements HomeContent, UIRefreshListener
{
	private TribeService service = Springs.get(TribeService.class);

	private Button addtribe = new Button(Icons.MY_OWN_TRIBE);

	private HorizontalLayout mytribeLayout = new HorizontalLayout();
	
	private HorizontalLayout alltribeLayout = new HorizontalLayout();
	
	public ManageTribe()
	{
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
			UI.getCurrent().addWindow(new TribeForm());
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
		
		addComponent(myTribe);
		addComponent(browseTribe);

		populateMyTribeContent();
		populateBroweTribe();
	}

	private void populateMyTribeContent()
	{
		mytribeLayout.removeAllComponents();
		
		mytribeLayout.addComponent(addtribe);

		for(Tribe tribe:service.findAllInvolved(Security.getUserEmail()))
		{
			TribeDisplay display = new TribeDisplay(tribe);
			display.addClickListener(click->{

			});

			mytribeLayout.addComponent(display);
		}
	}
	
	private void populateBroweTribe()
	{
		for(Tribe tribe:service.findAllApproved())
		{
			TribeDisplay display = new TribeDisplay(tribe);
			display.addClickListener(click->{

			});

			alltribeLayout.addComponent(display);
		}
	}

	@Override
	public void refresh(RefreshEvent event)
	{
		populateMyTribeContent();
		populateBroweTribe();
	}
}
