/**
 * 
 */
package com.kratonsolution.products.forums.ui.tribe;

import com.kratonsolution.products.forums.common.DateUtil;
import com.kratonsolution.products.forums.dm.TribeEvent;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com 
 */
public class EventDisplay extends Panel
{	
	private Label description = new Label(null,ContentMode.HTML);
	
	public EventDisplay(TribeEvent event)
	{
		setWidth("99%");
		setHeight("100px");
		
		GridLayout layout = new GridLayout(1, 1);
		layout.setSizeFull();
		layout.addComponent(description);

		description.setSizeFull();
		
		if(event != null)
		{
			StringBuilder build = new StringBuilder();
			build.append("<table style='border:none;width:100%;height:100%'>");
			build.append("<tbody>");
			build.append("<tr>");
			build.append("<td style='width=15%;font-size:12px;border-right:solid silver 1px;color:blue;font-weight:bolder;' align='center'>"+DateUtil.format(event.getCreated())+"</td>");
			build.append("<td style='width=85%;font-size:12px' align='left'>"+event.getDescription()+"<br/><font style='font-size:10px;color:red;font-weight:bolder;'>By "+event.getCreator()+"</font></td>");
			build.append("</tr>");
			build.append("</tbody>");
			build.append("</table>");
		
			description.setValue(build.toString());
		}
		
		setContent(layout);
	
		addClickListener(click->{
			UI.getCurrent().addWindow(new TribeEventEditForm(event));
		});
	}
}
