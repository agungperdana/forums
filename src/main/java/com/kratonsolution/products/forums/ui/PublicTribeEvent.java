/**
 * 
 */
package com.kratonsolution.products.forums.ui;

import com.kratonsolution.products.forums.common.Springs;
import com.kratonsolution.products.forums.svc.TribeEventService;
import com.kratonsolution.products.forums.ui.tribe.EventDisplay;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com 
 */
@SpringComponent
@UIScope
public class PublicTribeEvent extends TribeEventUI
{
	private TribeEventService service = Springs.get(TribeEventService.class);
	
	public PublicTribeEvent()
	{
		super();
	
		service.findAll().forEach(event->{
			layout.addComponent(new EventDisplay(event));
		});
	}
}
