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
import com.kratonsolution.products.forums.dm.TribeStatus;
import com.kratonsolution.products.forums.dm.TribeStatusType;
import com.kratonsolution.products.forums.dm.User;
import com.kratonsolution.products.forums.dm.UserRepository;
import com.kratonsolution.products.forums.svc.TribeService;
import com.kratonsolution.products.forums.ui.Icons;
import com.kratonsolution.products.forums.ui.MultiCombo;
import com.vaadin.data.Binder;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.server.StreamResource;
import com.vaadin.server.StreamResource.StreamSource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
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
public class TribeForm extends Window
{
	private UserRepository repository = Springs.get(UserRepository.class);
	
	private TribeService service = Springs.get(TribeService.class);
	
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
		
		RichTextArea note = new RichTextArea("Short Description");
		note.setWidth("100%");
		
		RichTextArea goal = new RichTextArea("Tribe Goal");
		goal.setWidth("100%");
		
		DateTimeField setup = new DateTimeField("Setup Date");
		setup.setValue(LocalDateTime.now(ZoneId.systemDefault()));
		setup.setEnabled(false);
		
		TextField member = new TextField("Tribe Member");
		member.setEnabled(false);
		member.setWidth("50px");
		member.setValue("1");
		
		TextField founder = new TextField("Tribe Founder");
		founder.setEnabled(false);
		founder.setValue(Security.getUserName());
		
		ComboBox<PersonalInfo> chief = new ComboBox<>("Tribe Chieftain");
		chief.setWidth("100%");
		
		MultiCombo contribe = new MultiCombo("Contributor");
		
		Vector<PersonalInfo> users = new Vector<>();
		
		for(User user:repository.findAll())
		{
			if(user.isActivated() && user.isEnabled())
			{
				PersonalInfo info = new PersonalInfo();
				info.setId(user.getId());
				info.setName(user.getName());
				info.setEmail(user.getEmail());
				
				users.add(info);
			}
		}
		
		chief.setItems(users);
		contribe.setItems(users);
		
		Binder<Tribe> bind = new Binder<>();
		bind.forField(name).withValidator(new StringLengthValidator("Name cannot be empty", 1, 500)).bind(Tribe::getTitle,Tribe::setTitle);
		bind.forField(note).withValidator(new StringLengthValidator("Description cannot be empty", 1, 500)).bind(Tribe::getNote,Tribe::setNote);
		bind.forField(goal).withValidator(new StringLengthValidator("Goal cannot be empty", 1, 500)).bind(Tribe::getGoal,Tribe::setGoal);
		bind.setBean(new Tribe());
		
		Button button = new Button("SUBMIT THIS TRIBE");
		button.setStyleName(ValoTheme.BUTTON_FRIENDLY);
		button.addClickListener(event->{
			if(bind.validate().isOk())
			{
				button.setEnabled(false);
				
				bind.getBean().setLogo(handler.logo.toByteArray());
				
				PersonalInfo creator = new PersonalInfo();
				creator.setEmail(Security.getUserEmail());
				creator.setName(Security.getUserName());
				
				TribeStatus created = new TribeStatus();
				created.setCreatedTime(DateUtil.now());
				created.setType(TribeStatusType.CREATED);
				
				bind.getBean().getStatuses().add(created);
				
				bind.getBean().setLastStatus(created);
				bind.getBean().setCreator(creator);
				bind.getBean().setCreated(DateUtil.toTimestamp(setup.getValue()));
				bind.getBean().setChieftain(chief.getSelectedItem().get());
				bind.getBean().getContributors().addAll(contribe.getAllSelected());
				
				service.add(bind.getBean());
				
				Notification.show("Tribe creation success.");
				
				UI.getCurrent().removeWindow(TribeForm.this);
			}
			else
				Notification.show("Tribe creation failed.");
		});
		
		left.addComponent(name);
		left.addComponent(note);
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
