/**
 * 
 */
package com.kratonsolution.products.forums.ui;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import com.vaadin.server.StreamResource;
import com.vaadin.server.StreamResource.StreamSource;
import com.vaadin.ui.Image;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com 
 */
public class SmallPicture extends Image
{
	public SmallPicture(){}
	
	public SmallPicture(byte[] binary)
	{
		if(binary != null)
			setSource(new StreamResource(new Handler(binary), "random"));
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
			setSource(new StreamResource(new Handler(binary), "random"));
	}
}
