/**
 * 
 */
package com.kratonsolution.products.forums.ui;

import com.kratonsolution.products.forums.common.Springs;
import com.kratonsolution.products.forums.dm.Tribe;
import com.kratonsolution.products.forums.svc.TribeService;
import com.kratonsolution.products.forums.ui.tribe.TribeDisplay;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com 
 */
@SpringComponent
@UIScope
public class PublicTopTribe extends TopTribe
{
	private TribeService service = Springs.get(TribeService.class);
	
	public PublicTopTribe()
	{
		super();
		
		layout.setMargin(new MarginInfo(true,false,false,false));
		layout.setSpacing(true);
		
		for(Tribe tribe:service.findAllApproved())
		{
			TribeDisplay display = new TribeDisplay(tribe);
			display.setWidth("100%");
			display.setHeight("30%");
			
			layout.addComponent(display);
		}
	}
}
