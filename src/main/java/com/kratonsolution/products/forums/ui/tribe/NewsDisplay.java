/**
 * 
 */
package com.kratonsolution.products.forums.ui.tribe;

import com.kratonsolution.products.forums.common.DateUtil;
import com.kratonsolution.products.forums.dm.Tribe;
import com.kratonsolution.products.forums.dm.TribeNews;
import com.kratonsolution.products.forums.ui.DBImage;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com 
 */
public class NewsDisplay extends Panel
{
	private VerticalLayout layout = new VerticalLayout();
	
	private DBImage image = new DBImage();
	
	private Label description = new Label(null, ContentMode.HTML);
	
	public NewsDisplay(Tribe tribe,TribeNews news)
	{
		setWidth("250px");
		setHeight("250px");
		
		Panel img = new Panel();
		img.setWidth("100%");
		img.setHeight("100%");
		img.setContent(image);
		
		description.setWidth("100%");
		description.setHeight("100%");
		
		layout.setMargin(true);
		layout.setSpacing(true);
		layout.setWidth("100%");
		layout.setHeight("100%");
		layout.addComponent(img);
		layout.addComponent(description);
		layout.setComponentAlignment(img, Alignment.MIDDLE_CENTER);
		layout.setComponentAlignment(description, Alignment.MIDDLE_CENTER);
		layout.setExpandRatio(img, 3f);
		layout.setExpandRatio(description, 2f);
		
		if(news != null)
		{
			image.setByte(news.getPicture());
			
			StringBuilder buffer = new StringBuilder();
			buffer.append("<table style='border:none;width:100%'>");
			buffer.append("<tbody>");
			buffer.append("<tr><td colspan='4' style='font-weight:bolder;font-size:10px;color:red'>"+news.getGenre().toUpperCase()+":"+news.getTitle().toUpperCase()+"</td></tr>");
			buffer.append("<tr>");
			buffer.append("<td width='15%' style='font-weight:bolder;font-size:8px'>Posted date:"+DateUtil.format(news.getTimeCreated())+"</td>");
			buffer.append("<td width='20%' style='font-weight:bolder;font-size:8px'>Posted by:"+news.getCreator().getName()+"</td>");
			buffer.append("<td width='60%' style='font-weight:bolder;font-size:8px'>Tags:"+news.getTags()+"</td>");
			buffer.append("<td width='5%' style='font-weight:bolder;font-size:8px'>Viewed:"+news.getViews()+"</td>");
			buffer.append("</tr>");
			buffer.append("<tr><td colspan='4'>"+news.getDescription()+"</td></tr>");
			buffer.append("</tbody>");
			buffer.append("</table>");
			
			description.setValue(buffer.toString());
		}
		
		setContent(layout);
	}
}
