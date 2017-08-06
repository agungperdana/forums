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
import com.kratonsolution.products.forums.ui.TribeListener;
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
import com.vaadin.ui.TabSheet;
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
public class TribeEditForm extends Window
{
	private TabSheet sheet = new TabSheet();
	
	private HorizontalLayout infoLayout = new HorizontalLayout();
	
	private HorizontalLayout newsLayout = new HorizontalLayout();
	
	private HorizontalLayout eventLayout = new HorizontalLayout();
	
	private UserRepository repository = Springs.get(UserRepository.class);
	
	private TribeService service = Springs.get(TribeService.class);
	
	private FormLayout left = new FormLayout();
	
	private VerticalLayout right = new VerticalLayout();
		
	private UploadHandler handler = new UploadHandler();
	
	private Button tribeLogo = new Button("128 X 128");
	
	private Upload uploader = new Upload("Choose Logo",handler);
	
	private Vector<TribeListener> listeners = new Vector<>();
	
	public TribeEditForm(Tribe tribe)
	{
		setWidth("85%");
		setHeight("95%");
		setCaption("Edit Tribe");
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
		
		infoLayout.setMargin(true);
		infoLayout.setWidth("100%");
		infoLayout.setHeight("100%");
		infoLayout.addComponent(panel);
		infoLayout.addComponent(right);
		infoLayout.setExpandRatio(panel, 3f);
		infoLayout.setExpandRatio(right, 1f);
		
		newsLayout.setStyleName(ValoTheme.LAYOUT_HORIZONTAL_WRAPPING);
		newsLayout.setMargin(true);
		newsLayout.setSpacing(true);
		
		eventLayout.setStyleName(ValoTheme.LAYOUT_HORIZONTAL_WRAPPING);
		eventLayout.setMargin(true);
		eventLayout.setSpacing(true);
		
		sheet.setWidth("99%");
		sheet.setHeight("99%");
		
		sheet.addTab(infoLayout, "Tribe Information");
		sheet.addTab(newsLayout, "Tribe News");
		sheet.addTab(eventLayout, "Tribe Event");
		
		setContent(sheet);
		
		buildInfo(tribe);
		buildNews(tribe);
		buildEvent(tribe);
	}
	
	private void buildInfo(Tribe tribe)
	{
		tribeLogo.setCaption(null);
		tribeLogo.setIcon(new StreamResource(new IconStream(tribe.getLogo()),"nonane"));
		
		TextField name = new TextField("Tribe Name");
		name.setWidth("100%");
		name.setValue(tribe.getTitle());
		
		RichTextArea note = new RichTextArea("Short Description");
		note.setWidth("100%");
		note.setValue(tribe.getNote());
		
		RichTextArea goal = new RichTextArea("Tribe Goal");
		goal.setWidth("100%");
		goal.setValue(tribe.getGoal());
		
		DateTimeField setup = new DateTimeField("Setup Date");
		setup.setValue(LocalDateTime.now(ZoneId.systemDefault()));
		setup.setEnabled(false);
		setup.setValue(DateUtil.toLocalDateTime(tribe.getCreated()));
		
		TextField member = new TextField("Tribe Member");
		member.setEnabled(false);
		member.setWidth("50px");
		member.setValue(tribe.getFollowers().size()+"");
		
		TextField founder = new TextField("Tribe Founder");
		founder.setEnabled(false);
		founder.setValue(tribe.getCreator().getName());
		
		ComboBox<PersonalInfo> chief = new ComboBox<>("Tribe Chieftain");
		chief.setWidth("100%");
		
		MultiCombo contribe = new MultiCombo("Contributor");
		contribe.setSelecteds(tribe.getContributors());
		
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
		
		chief.setSelectedItem(tribe.getChieftain());
		
		Binder<Tribe> bind = new Binder<>();
		bind.forField(name).withValidator(new StringLengthValidator("Name cannot be empty", 1, 500)).bind(Tribe::getTitle,Tribe::setTitle);
		bind.forField(note).withValidator(new StringLengthValidator("Description cannot be empty", 1, 500)).bind(Tribe::getNote,Tribe::setNote);
		bind.forField(goal).withValidator(new StringLengthValidator("Goal cannot be empty", 1, 500)).bind(Tribe::getGoal,Tribe::setGoal);
		bind.setBean(tribe);
		
		Button button = new Button("UPDATE THIS TRIBE");
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
				
				service.edit(bind.getBean());
				
				Notification.show("Tribe creation success.");
				
				listeners.forEach(listener->{listener.refresh(null);});
				
				UI.getCurrent().removeWindow(TribeEditForm.this);
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
	
	private void buildNews(Tribe tribe)
	{
		newsLayout.removeAllComponents();
		
		Button add = new Button("Create News");
		add.setStyleName(ValoTheme.BUTTON_FRIENDLY);
		add.setWidth("250px");
		add.setHeight("250px");
		add.addClickListener(click->{
			
			TribeNewsForm form = new TribeNewsForm(tribe);
			form.addTribeListener(listener->{
				buildInfo(service.findOne(tribe.getId()));
			});

			UI.getCurrent().addWindow(form);
		});
		
		newsLayout.addComponent(add);
		
		tribe.getNews().forEach(news->{
			newsLayout.addComponent(new NewsDisplay(news));
		});
	}
	
	private void buildEvent(Tribe tribe)
	{
		eventLayout.removeAllComponents();
		
		Button add = new Button("Create Event");
		add.setStyleName(ValoTheme.BUTTON_FRIENDLY);
		add.setHeight("100px");
		add.addClickListener(click->{
			
			TribeEventForm form = new TribeEventForm(tribe);
			form.addTribeListener(listener->{
				buildInfo(service.findOne(tribe.getId()));
			});

			UI.getCurrent().addWindow(form);
		});
		
		eventLayout.addComponent(add);
		
		tribe.getEvents().forEach(event->{
			eventLayout.addComponent(new EventDisplay(event));
		});
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
	
	private class IconStream implements StreamSource
	{
		byte[] images;
		
		public IconStream(byte[] imgs)
		{
			this.images = imgs;
		}
		
		@Override
		public InputStream getStream()
		{
			return new ByteArrayInputStream(images);
		}
		
	}
	
	public void addTribeListener(TribeListener listener)
	{
		if(listener != null)
			listeners.add(listener);
	}
	
}
