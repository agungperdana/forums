/**
 * 
 */
package com.kratonsolution.products.forums.web.ui;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import com.vaadin.server.StreamResource;
import com.vaadin.server.StreamResource.StreamSource;
import com.vaadin.ui.Image;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com 
 */
public class SmallPicture extends Panel
{
	private VerticalLayout layout = new VerticalLayout();
	
	private Image image = new Image();
	
	public SmallPicture()
	{
		this(null);
	}
	
	public SmallPicture(byte[] binary)
	{
		addStyleName(ValoTheme.PANEL_BORDERLESS);
		setWidth("100%");
		setHeight("100%");

		image.setSizeFull();
		
		layout.setSizeFull();
		layout.addComponentsAndExpand(image);
		
		setContent(layout);
	}
	
	private class Handler implements StreamSource
	{
		byte[] binary;
		
		public Handler(byte[] binary)
		{
			this.binary = binary;
		}
		
		@Override
		public InputStream getStream()
		{
			if(binary != null)
				return new ByteArrayInputStream(binary);
			
			return null;
		}		
	}
	
	public void setBinary(byte[] binary)
	{
		if(binary != null)
			image.setSource(new StreamResource(new Handler(binary), "random"));
	}
}
