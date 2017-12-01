/**
 * 
 */
package com.kratonsolution.products.forums.web.ui.tribe;


import com.kratonsolution.products.forums.common.util.DateUtil;
import com.kratonsolution.products.forums.common.util.Security;
import com.kratonsolution.products.forums.common.util.Springs;
import com.kratonsolution.products.forums.dm.PersonalInfo;
import com.kratonsolution.products.forums.dm.Tribe;
import com.kratonsolution.products.forums.svc.TribeEventService;
import com.kratonsolution.products.forums.svc.TribeNewsService;
import com.kratonsolution.products.forums.svc.TribeService;
import com.kratonsolution.products.forums.web.ui.Icons;
import com.kratonsolution.products.forums.web.ui.TribeLogo;
import com.vaadin.ui.Button;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TabSheet.Tab;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com 
 */
public class TribeInfo extends Window
{
	private TribeService service = Springs.get(TribeService.class);
	
	private TribeNewsService newsService = Springs.get(TribeNewsService.class);
	
	private TribeEventService eventService = Springs.get(TribeEventService.class);
	
	private TabSheet root = new TabSheet();

	private Tab infos = root.addTab(new HorizontalLayout(), "Tribe Info");
	
	private Tab news = root.addTab(new HorizontalLayout(), "Tribe News");
	
	private Tab events = root.addTab(new HorizontalLayout(), "Tribe Events");
	
	public TribeInfo(Tribe tribe)
	{
		setWidth("75%");
		setHeight("85%");
		setCaption("Tribe Info ["+tribe.getName()+"]");
		setIcon(Icons.TRIBE_TOP);
		setModal(true);
		setResizable(false);
		setDraggable(false);
		
		center();
		
		setContent(root);

		initInfos(tribe);
		initNews(tribe);
		initEvent(tribe);
	}
	
	private void initInfos(Tribe tribe)
	{
		HorizontalLayout layout = (HorizontalLayout)infos.getComponent();
		layout.setMargin(true);
		layout.setSizeFull();
		
		VerticalLayout right = new VerticalLayout();
		right.setSizeFull();
		right.addComponent(new TribeLogo(tribe.getLogo()));
		
		TextField name = new TextField("Tribe Name",tribe.getName());
		name.setWidth("100%");
		name.setEnabled(false);
		
		TextArea description = new TextArea("Short Description",tribe.getDescription());
		description.setWidth("100%");
		description.setEnabled(false);
		description.setHeight("55px");
		
		TextArea goal = new TextArea("Tribe Gial",tribe.getGoal());
		goal.setWidth("100%");
		goal.setHeight("55px");
		goal.setEnabled(false);
		
		DateField date = new DateField("Setup Date",DateUtil.toLocalDate(tribe.getCreated()));
		date.setEnabled(false);
		
		TextField member = new TextField("Current Member",tribe.getFollowers().size()+"");
		member.setWidth("35px");
		member.setEnabled(false);
		
		TextField founder = new TextField("Tribe Founder",tribe.getCreator().getName());
		founder.setWidth("200px");
		founder.setEnabled(false);
		
		TextField chieftein = new TextField("Tribe Chieftein",tribe.getChieftain().getName());
		chieftein.setWidth("200px");
		chieftein.setEnabled(false);
		
		TextField contributors = new TextField("Tribe Contributor","");
		contributors.setWidth("75%");
		contributors.setEnabled(false);
		
		StringBuilder builder = new StringBuilder();
		for(PersonalInfo info:tribe.getContributors())
			builder.append(info.getName()).append(";");

		contributors.setValue(builder.toString());
		
		Button join = new Button("JOIN THIS TRIBE");
		join.addStyleName(ValoTheme.BUTTON_DANGER);
		join.setWidth("30%");
		
		Button leave = new Button("LEAVE THIS TRIBE");
		leave.addStyleName(ValoTheme.BUTTON_DANGER);
		leave.setWidth("30%");
		
		FormLayout form = new FormLayout();
		form.setSizeFull();
		form.setMargin(true);
		form.addComponent(name);
		form.addComponent(description);
		form.addComponent(goal);
		form.addComponent(date);
		form.addComponent(member);
		form.addComponent(founder);
		form.addComponent(chieftein);
		form.addComponent(contributors);
		form.addComponent(tribe.isJoined(Security.getUserEmail())?leave:join);
		
		leave.addClickListener(click->{
			form.removeComponent(leave);
			form.addComponent(join);
			
			service.leave(tribe.getId());
		});
				
		join.addClickListener(click->{
			form.removeComponent(join);
			form.addComponent(leave);
			
			service.join(tribe.getId());
		});
		
		layout.addComponent(form);
		layout.addComponent(right);
		layout.setExpandRatio(form, 4f);
		layout.setExpandRatio(right, 1f);
	}
	
	private void initNews(Tribe tribe)
	{
		HorizontalLayout layout = (HorizontalLayout)news.getComponent();
		layout.addStyleName(ValoTheme.LAYOUT_HORIZONTAL_WRAPPING);
		layout.setMargin(true);
		layout.setSpacing(true);
		
		newsService.findAllByTribe(tribe.getId()).forEach(tnews->{
			layout.addComponent(new NewsDisplay(tnews, "400px"));
		});
	}
	
	private void initEvent(Tribe tribe)
	{
		HorizontalLayout layout = (HorizontalLayout)events.getComponent();
		layout.addStyleName(ValoTheme.LAYOUT_HORIZONTAL_WRAPPING);
		layout.setMargin(true);
		layout.setSpacing(true);
		
		eventService.findAllByTribe(tribe.getId()).forEach(tevent->{
			layout.addComponent(new EventDisplay(tevent, "300px"));
		});
	}
}
