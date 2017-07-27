/**
 * 
 */
package com.kratonsolution.products.forums.ui;

import com.kratonsolution.products.forums.dm.Tribe;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com 
 */
public class TribeDisplay extends Panel
{
	private HorizontalLayout layout = new HorizontalLayout();
	
	private SmallPicture picture = new SmallPicture();
	
	private VerticalLayout contents = new VerticalLayout();
	
	private Label title = new Label();
	
	private Label note = new Label();
	
	private Label member = new Label();
	
	public TribeDisplay(Tribe tribe)
	{
		setHeight("100px");
		setWidth("275px");

		layout.setMargin(true);
		layout.setHeight("100%");
		layout.setWidth("100%");
		layout.addComponent(picture);
		layout.addComponent(contents);
		layout.setExpandRatio(picture, 1f);
		layout.setExpandRatio(contents, 3f);
		layout.setComponentAlignment(picture, Alignment.MIDDLE_CENTER);
		layout.setComponentAlignment(contents, Alignment.MIDDLE_CENTER);
		
		picture.setBinary(tribe.getLogo());
		
		title.setContentMode(ContentMode.HTML);
		title.setValue("<span style='color:blue;font-size:13px;font-weight:bold;width:100%;border-bottom:solid black 1px'>"+tribe.getTitle()+"</span>");
		title.setWidth("100%");
		
		note.setContentMode(ContentMode.HTML);
		note.setValue("<span style='width:100%;font-size:12px'>"+tribe.getNote()+"</span>");
		
		member.setContentMode(ContentMode.HTML);
		member.setValue("<span style='color:red;font-size:11px;width:100%;'>Member ("+tribe.getFollowers()+")</span>");
		member.setWidth("100%");
		
		contents.setHeight("100%");
		contents.setWidth("100%");
		contents.setSpacing(true);
		contents.addComponent(title);
		contents.addComponent(note);
		contents.addComponent(member);
		contents.setExpandRatio(title, 1f);
		contents.setExpandRatio(note, 4f);
		contents.setExpandRatio(member, 1f);
		contents.setComponentAlignment(title, Alignment.TOP_LEFT);
		contents.setComponentAlignment(note, Alignment.MIDDLE_LEFT);
		contents.setComponentAlignment(member, Alignment.BOTTOM_LEFT);
		
		setContent(layout);
	}
}
