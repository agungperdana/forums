/**
 * 
 */
package com.kratonsolution.products.forums.ui.tribe;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Vector;

import com.kratonsolution.products.forums.common.DateUtil;
import com.kratonsolution.products.forums.common.Security;
import com.kratonsolution.products.forums.common.Springs;
import com.kratonsolution.products.forums.dm.PersonalInfo;
import com.kratonsolution.products.forums.dm.Tribe;
import com.kratonsolution.products.forums.dm.TribeNews;
import com.kratonsolution.products.forums.svc.TribeNewsService;
import com.kratonsolution.products.forums.ui.Icons;
import com.kratonsolution.products.forums.ui.TribeListener;
import com.vaadin.data.Binder;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.server.StreamResource;
import com.vaadin.server.StreamResource.StreamSource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.DateTimeField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.RichTextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.Upload;
import com.vaadin.ui.Upload.Receiver;
import com.vaadin.ui.Upload.SucceededEvent;
import com.vaadin.ui.Upload.SucceededListener;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com 
 */
public class TribeNewsForm extends Window
{
	private TribeNewsService service = Springs.get(TribeNewsService.class);
	
	private HorizontalLayout layout = new HorizontalLayout();
	
	private FormLayout left = new FormLayout();
	
	private VerticalLayout right = new VerticalLayout();
		
	private UploadHandler handler = new UploadHandler();
	
	private Button tribeLogo = new Button("128 X 128");
	
	private Upload uploader = new Upload("Choose News Logo",handler);
	
	private Vector<TribeListener> listeners = new Vector<>();
	
	public TribeNewsForm(Tribe tribe)
	{
		setWidth("815px");
		setHeight("625px");
		setCaption("Create New Tribe");
		setIcon(Icons.TRIBE_TOP);
		setModal(true);
		setResizable(false);
		setDraggable(false);
		
		center();
		
		Panel panel = new Panel();
		panel.setWidth("100%");
		panel.setHeight("100%");
		panel.setStyleName(ValoTheme.PANEL_BORDERLESS);
		panel.setContent(left);
		panel.setScrollLeft(450);
		
		left.setCaption("News Info");
		
		uploader.addSucceededListener(handler);
		uploader.setWidth("150px");
		
		tribeLogo.setWidth("150px");
		tribeLogo.setHeight("150px");
		
		right.setWidth("100%");
		right.setHeight("100%");
		right.setCaption("Tribe Logo");
		right.addComponent(tribeLogo);
		right.addComponent(uploader);
		right.setComponentAlignment(uploader, Alignment.TOP_LEFT);
		right.setExpandRatio(tribeLogo, 1f);
		right.setExpandRatio(uploader, 2f);
		
		layout.setMargin(true);
		layout.setWidth("100%");
		layout.setHeight("100%");
		layout.addComponent(panel);
		layout.addComponent(right);
		layout.setExpandRatio(panel, 3f);
		layout.setExpandRatio(right, 1f);
		
		setContent(layout);
		
		buildInfo(tribe);
	}
	
	private void buildInfo(Tribe tribe)
	{
		
		TextField genre = new TextField("Genre");
		genre.setWidth("100%");
		
		TextField title = new TextField("Title");
		title.setWidth("100%");
		
		TextField tags = new TextField("Tags");
		tags.setWidth("100%");

		DateTimeField createdDate = new DateTimeField("Create Date");
		createdDate.setValue(LocalDateTime.now(ZoneId.systemDefault()));
		createdDate.setEnabled(false);

		TextField creator = new TextField("Creator");
		creator.setEnabled(false);
		creator.setValue(Security.getUserName());
		
		RichTextArea description = new RichTextArea("Short Description");
		description.setWidth("100%");
		
		Binder<TribeNews> bind = new Binder<>();
		bind.forField(genre).withValidator(new StringLengthValidator("Name cannot be empty", 1, 500)).bind(TribeNews::getGenre,TribeNews::setGenre);
		bind.forField(title).withValidator(new StringLengthValidator("Title cannot be empty", 1, 500)).bind(TribeNews::getTitle,TribeNews::setTitle);
		bind.forField(description).withValidator(new StringLengthValidator("Description cannot be empty", 1, 500)).bind(TribeNews::getDescription,TribeNews::setDescription);
		bind.forField(tags).withValidator(new StringLengthValidator("Tags cannot be empty", 1, 500)).bind(TribeNews::getTags,TribeNews::setTags);
		bind.setBean(new TribeNews());
		
		Button button = new Button("SUBMIT THIS NEWS");
		button.setStyleName(ValoTheme.BUTTON_FRIENDLY);
		button.addClickListener(event->{
			if(bind.validate().isOk())
			{
				button.setEnabled(false);
				
				bind.getBean().setPicture(handler.logo.toByteArray());
				
				PersonalInfo owner = new PersonalInfo();
				owner.setEmail(Security.getUserEmail());
				owner.setName(Security.getUserName());
				
				bind.getBean().setCreator(owner);
				bind.getBean().setTimeCreated(DateUtil.toTimestamp(createdDate.getValue()));
				bind.getBean().setTribe(tribe.getId());
				
				service.add(bind.getBean());
				
				Notification.show("Tribe News creation success.");
				
				listeners.forEach(listener->{listener.refresh(null);});
				
				UI.getCurrent().removeWindow(TribeNewsForm.this);
			}
			else
				Notification.show("Tribe News creation failed.");
		});
		
		left.addComponent(genre);
		left.addComponent(title);
		left.addComponent(tags);
		left.addComponent(createdDate);
		left.addComponent(creator);
		left.addComponent(description);
		left.addComponent(button);
	}
	
	private class UploadHandler implements Receiver,SucceededListener,StreamSource
	{
		ByteArrayOutputStream logo;
				
		@Override
		public OutputStream receiveUpload(String filename, String mimeType)
		{
			logo = new ByteArrayOutputStream();
			return logo;
		}

		@Override
		public void uploadSucceeded(SucceededEvent event)
		{
			tribeLogo.setCaption("");
			tribeLogo.setIcon(new StreamResource(this,event.getFilename()));
		}

		@Override
		public InputStream getStream()
		{
			return new ByteArrayInputStream(logo.toByteArray());
		}
	}
	
	public void addTribeListener(TribeListener listener)
	{
		if(listener != null)
			listeners.add(listener);
	}
}
