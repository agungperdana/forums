/**
 * 
 */
package com.kratonsolution.products.forums.web.ui;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import com.vaadin.server.StreamResource;
import com.vaadin.server.StreamResource.StreamSource;
import com.vaadin.ui.Button;
import com.vaadin.ui.themes.ValoTheme;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com 
 */
public class TribeLogo extends Button
{
	public TribeLogo(byte[] raw)
	{
		setWidth("150px");
		setHeight("150px");
		addStyleName(ValoTheme.BUTTON_ICON_ONLY);
		setIcon(new StreamResource(new IconStream(raw),"nonane"));
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
}
