/**
 * 
 */
package com.kratonsolution.products.forums.ui;

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
public class DBImage extends Button implements StreamSource
{
	private byte[] rawfile;
	
	public DBImage()
	{
		setWidth("100%");
		setHeight("100%");
		setStyleName(ValoTheme.BUTTON_LINK);
	}
	
	public void setByte(byte[] _byte)
	{
		this.rawfile = _byte;
		setIcon(new StreamResource(this,"noname"));
	}
	
	@Override
	public InputStream getStream()
	{
		return new ByteArrayInputStream(this.rawfile);
	}
}