/**
 * 
 */
package com.kratonsolution.products.forums.ui.tribe;

import com.kratonsolution.products.forums.common.DateUtil;
import com.kratonsolution.products.forums.dm.TribeEvent;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com 
 */
public class EventDisplay extends Panel
{	
	private Label description = new Label(null,ContentMode.HTML);
	
	public EventDisplay(TribeEvent event)
	{
		setWidth("250px");
		setHeight("100px");

		description.setWidth("100%");
		description.setHeight("100%");
		
		if(event != null)
		{
			StringBuilder build = new StringBuilder();
			build.append("<table style='border:solid silver 1px;width:100%;font-size=10px'>");
			build.append("<tbody>");
			build.append("<tr>");
			build.append("<td style='width=20%;border-right:solid silver 1px;' align='center'>"+DateUtil.format(event.getCreated())+"</td>");
			build.append("<td style='width=80%' align='left'>"+event.getDescription()+"<br/><font color='red>'By "+event.getCreator()+"</font></td>");
			build.append("</tr>");
			build.append("</tbody>");
			build.append("</table>");
		
			description.setValue(build.toString());
		}
		
		setContent(description);
	}
}
