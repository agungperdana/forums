/**
 * 
 */
package com.kratonsolution.products.forums.ui.tribe;

import com.kratonsolution.products.forums.dm.Tribe;
import com.kratonsolution.products.forums.ui.SmallPicture;
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
	private HorizontalLayout root = new HorizontalLayout();
	
	private SmallPicture picture = new SmallPicture();
	
	private VerticalLayout contents = new VerticalLayout();
	
	private Label title = new Label();
	
	private Label note = new Label();
	
	private Label member = new Label();
	
	public TribeDisplay(Tribe tribe)
	{
		setHeight("100px");
		setWidth("99%");

		root.setMargin(false);
		root.setHeight("100%");
		root.setWidth("100%");
		root.addComponent(picture);
		root.addComponent(contents);
		root.setExpandRatio(picture, 1.5f);
		root.setExpandRatio(contents, 2.5f);
		root.setComponentAlignment(picture, Alignment.MIDDLE_CENTER);
		root.setComponentAlignment(contents, Alignment.MIDDLE_CENTER);
		
		picture.setBinary(tribe.getLogo());
		
		title.setContentMode(ContentMode.HTML);
		title.setValue("<span style='color:blue;font-size:11px;font-weight:bold;width:100%;border-bottom:solid black 1px'>"+tribe.getName()+"</span>");
		title.setWidth("100%");
		
		note.setContentMode(ContentMode.HTML);
		note.setValue("<span style='width:100%;font-size:10px'>"+tribe.getDescription()+"</span>");
		
		member.setContentMode(ContentMode.HTML);
		member.setValue("<span style='color:red;font-size:9px;width:100%;font-weight:bold;'>Member ("+tribe.getFollowers()+")</span>");
		member.setWidth("100%");
		
		contents.setHeight("99%");
		contents.setWidth("100%");
		contents.setSpacing(false);
		contents.setMargin(false);
		contents.addComponent(title);
		contents.addComponent(note);
		contents.addComponent(member);
		contents.setExpandRatio(title, 1f);
		contents.setExpandRatio(note, 3f);
		contents.setExpandRatio(member, 1f);
		contents.setComponentAlignment(title, Alignment.TOP_LEFT);
		contents.setComponentAlignment(note, Alignment.MIDDLE_LEFT);
		contents.setComponentAlignment(member, Alignment.BOTTOM_LEFT);
		
		setContent(root);
	}
}
