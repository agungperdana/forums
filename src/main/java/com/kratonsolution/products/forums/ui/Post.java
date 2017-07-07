/**
 * 
 */
package com.kratonsolution.products.forums.ui;

import com.vaadin.server.Resource;
import com.vaadin.ui.AbstractOrderedLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com 
 */
public abstract class Post extends Panel
{
	protected Image image = new Image();
	
	protected Label note = new Label();
	
	public Post(AbstractOrderedLayout layout)
	{
		setSizeFull();
		
		if(layout != null)
			setContent(layout);

		image.setWidth("100%");
		image.setHeight("100%");
		
		note.setWidth("100%");
		note.setHeight("100%");
		
		layout.addComponent(image);
		layout.addComponent(note);
		layout.setExpandRatio(image, 3f);
		layout.setExpandRatio(note, 1f);
	}
	
	public void setImage(Resource img)
	{
		image.setSource(img);
	}
	
	public void setNews(String news)
	{
		note.setCaption(news);
	}
}
