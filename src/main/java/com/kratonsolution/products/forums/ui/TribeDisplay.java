/**
 * 
 */
package com.kratonsolution.products.forums.ui;

import com.kratonsolution.products.forums.dm.Tribe;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com 
 */
public class TribeDisplay extends HorizontalLayout
{
	private SmallPicture picture = new SmallPicture();
	
	private GridLayout layout = new GridLayout(1, 3);
	
	private Label title = new Label();
	
	private Label note = new Label();
	
	private Label member = new Label();
	
	public TribeDisplay(Tribe tribe)
	{
		setHeight("100px");
		setWidth("225px");
		
		addComponent(picture);
		addComponent(layout);
		setExpandRatio(picture, 1f);
		setExpandRatio(layout, 3f);
		
		layout.addComponent(title);
		layout.addComponent(note);
		layout.addComponent(member);
		
		picture.setBinary(tribe.getLogo());
		
		title.setValue(tribe.getTitle());
		note.setValue(tribe.getNote());
		member.setValue(tribe.getFollowers().size()+"");
	}
}
