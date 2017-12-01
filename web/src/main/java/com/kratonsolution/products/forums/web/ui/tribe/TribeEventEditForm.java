/**
 * 
 */
package com.kratonsolution.products.forums.web.ui.tribe;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Vector;

import com.kratonsolution.products.forums.common.util.Security;
import com.kratonsolution.products.forums.common.util.Springs;
import com.kratonsolution.products.forums.dm.TribeEvent;
import com.kratonsolution.products.forums.svc.TribeEventService;
import com.kratonsolution.products.forums.web.ui.Icons;
import com.kratonsolution.products.forums.web.ui.TribeListener;
import com.vaadin.data.Binder;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.ui.Button;
import com.vaadin.ui.DateTimeField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.RichTextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com 
 */
public class TribeEventEditForm extends Window
{
	private TribeEventService service = Springs.get(TribeEventService.class);
	
	private FormLayout root = new FormLayout();
	
	private Vector<TribeListener> listeners = new Vector<>();
	
	public TribeEventEditForm(TribeEvent event)
	{
		setWidth("70%");
		setHeight("65%");
		setCaption("Edit Event ["+event.getDescription()+"]");
		setIcon(Icons.TRIBE_TOP);
		setModal(true);
		setResizable(false);
		setDraggable(false);
		
		center();
		
		root.setSpacing(true);
		root.setMargin(true);
		root.setSizeFull();
		
		setContent(root);
		
		buildInfo(event);
	}
	
	private void buildInfo(TribeEvent tev)
	{

		DateTimeField createdDate = new DateTimeField("Event Date");
		createdDate.setValue(LocalDateTime.now(ZoneId.systemDefault()));

		TextField creator = new TextField("Creator");
		creator.setEnabled(false);
		creator.setValue(Security.getUserName());
		
		RichTextArea description = new RichTextArea("Short Description");
		description.setWidth("100%");
		
		Binder<TribeEvent> bind = new Binder<>();
		bind.forField(description).withValidator(new StringLengthValidator("Description cannot be empty", 1, 500)).bind(TribeEvent::getDescription,TribeEvent::setDescription);
		bind.setBean(tev);
		
		Button button = new Button("UPDATE EVENT");
		button.setStyleName(ValoTheme.BUTTON_FRIENDLY);
		button.addClickListener(event->{
			if(bind.validate().isOk())
			{
				button.setEnabled(false);
				
				service.edit(bind.getBean());
				
				Notification.show("Tribe Event update success.");
				
				listeners.forEach(listener->{listener.refresh(null);});
				
				UI.getCurrent().removeWindow(TribeEventEditForm.this);
			}
			else
				Notification.show("Tribe Event update failed.");
		});
		
		root.addComponent(createdDate);
		root.addComponent(creator);
		root.addComponent(description);
		root.addComponent(button);
	}
	
	public void addTribeListener(TribeListener listener)
	{
		if(listener != null)
			listeners.add(listener);
	}
}
