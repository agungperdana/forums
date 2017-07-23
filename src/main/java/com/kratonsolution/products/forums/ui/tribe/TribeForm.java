/**
 * 
 */
package com.kratonsolution.products.forums.ui.tribe;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Vector;

import com.kratonsolution.products.forums.common.Security;
import com.kratonsolution.products.forums.common.Springs;
import com.kratonsolution.products.forums.dm.User;
import com.kratonsolution.products.forums.dm.UserRepository;
import com.kratonsolution.products.forums.ui.Icons;
import com.kratonsolution.products.forums.ui.MultiCombo;
import com.vaadin.server.StreamResource;
import com.vaadin.server.StreamResource.StreamSource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
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
public class TribeForm extends Window
{
	private UserRepository repository = Springs.get(UserRepository.class);
	
	private HorizontalLayout layout = new HorizontalLayout();
	
	private FormLayout left = new FormLayout();
	
	private VerticalLayout right = new VerticalLayout();
		
	private UploadHandler handler = new UploadHandler();
	
	private Button tribeLogo = new Button("128 X 128");
	
	private Upload uploader = new Upload("Choose Logo",handler);
	
	public TribeForm()
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
		
		left.setCaption("Tribe Info");
		
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
		
		buildInfo();
	}
	
	private void buildInfo()
	{
		
		TextField name = new TextField("Tribe Name");
		name.setWidth("100%");
		
		TextField description = new TextField("Short Description");
		description.setWidth("100%");
		
		TextArea goal = new TextArea("Tribe Goal");
		goal.setWidth("100%");
		
		DateField setup = new DateField("Setup Date");
		
		TextField member = new TextField("Tribe Member");
		member.setEnabled(false);
		member.setWidth("50px");
		member.setValue("1");
		
		TextField founder = new TextField("Tribe Founder");
		founder.setEnabled(false);
		founder.setValue(Security.getUserName());
		
		TextField chief = new TextField("Tribe Chieftain");
		chief.setEnabled(false);
		
		MultiCombo contribe = new MultiCombo("Contributor");
		
		Vector<String> users = new Vector<>();
		
		for(User user:repository.findAll())
		{
			if(user.isActivated() && user.isEnabled())
				users.add(user.getEmail());
		}
		
		contribe.setItems(users);
		
		Button button = new Button("SUBMIT THIS TRIBE");
		button.setStyleName(ValoTheme.BUTTON_FRIENDLY);
		button.addClickListener(event->{
			
			
		});
		
		left.addComponent(name);
		left.addComponent(description);
		left.addComponent(goal);
		left.addComponent(setup);
		left.addComponent(member);
		left.addComponent(founder);
		left.addComponent(chief);
		left.addComponent(contribe);
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
}
