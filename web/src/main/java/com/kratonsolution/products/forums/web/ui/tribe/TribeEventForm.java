/**
 * 
 */
package com.kratonsolution.products.forums.web.ui.tribe;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Vector;

import com.kratonsolution.products.forums.common.util.DateUtil;
import com.kratonsolution.products.forums.common.util.Security;
import com.kratonsolution.products.forums.common.util.Springs;
import com.kratonsolution.products.forums.dm.PersonalInfo;
import com.kratonsolution.products.forums.dm.Tribe;
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
public class TribeEventForm extends Window
{
	private TribeEventService service = Springs.get(TribeEventService.class);
	
	private FormLayout root = new FormLayout();
	
	private Vector<TribeListener> listeners = new Vector<>();
	
	public TribeEventForm(Tribe tribe)
	{
		setWidth("70%");
		setHeight("65%");
		setCaption("Create New Event");
		setIcon(Icons.TRIBE_TOP);
		setModal(true);
		setResizable(false);
		setDraggable(false);
		
		center();
		
		root.setSpacing(true);
		root.setMargin(true);
		root.setSizeFull();
		
		setContent(root);
		
		buildInfo(tribe);
	}
	
	private void buildInfo(Tribe tribe)
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
		bind.setBean(new TribeEvent());
		
		Button button = new Button("SUBMIT THIS EVENT");
		button.setStyleName(ValoTheme.BUTTON_FRIENDLY);
		button.addClickListener(event->{
			if(bind.validate().isOk())
			{
				button.setEnabled(false);
				
				PersonalInfo owner = new PersonalInfo();
				owner.setEmail(Security.getUserEmail());
				owner.setName(Security.getUserName());
				
				bind.getBean().setCreator(owner);
				bind.getBean().setCreated(DateUtil.toTimestamp(createdDate.getValue()));
				bind.getBean().setTribe(tribe.getId());
				
				service.add(bind.getBean());
				
				Notification.show("Tribe Event creation success.");
				
				listeners.forEach(listener->{listener.refresh(null);});
				
				UI.getCurrent().removeWindow(TribeEventForm.this);
			}
			else
				Notification.show("Tribe Event creation failed.");
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
