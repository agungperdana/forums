/**
 * 
 */
package com.kratonsolution.products.forums.web.ui.tribe;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;

import com.kratonsolution.products.forums.common.util.Security;
import com.kratonsolution.products.forums.common.util.Springs;
import com.kratonsolution.products.forums.dm.TribeNews;
import com.kratonsolution.products.forums.dm.TribeNewsRepository;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com 
 */
public class MyTribeNews extends VerticalLayout
{
	private TribeNewsRepository newsRepository = Springs.get(TribeNewsRepository.class);
	
	private Label title = new Label("<h3><u><font style='color:red;'>T</font>ribe News</u></h3>", ContentMode.HTML);

	private GridLayout layout = new GridLayout();
	
	private Panel panel = new Panel(layout);
	
	public MyTribeNews()
	{
		setSizeFull();
		
		panel.setWidth("99%");
		panel.addStyleName(ValoTheme.PANEL_BORDERLESS);
		
		addComponent(title);
		addComponentsAndExpand(panel);
	
		layout.setSpacing(true);
		
		List<TribeNews> tribeNews = newsRepository.findAllMyNews(new PageRequest(0, 50,new Sort(new Order(Direction.DESC,"timeCreated"))), Security.getUserEmail());
		if(tribeNews.isEmpty())
		{
			Label label = new Label(null, ContentMode.HTML);
			label.setValue("<h3>You are currently not joining any tribe. You can find many interesting tribe on <font style='font-weight:bolder;color:red;'>Manage Tribe</font> menu</h3>");
		
			layout.addComponent(label);
		}
		else
		{
			int rows = (tribeNews.size()-(tribeNews.size()%4))/4;
			if(rows == 0)
				rows = 1;
			
			layout.setColumns(4);
			layout.setRows(rows);
			
			tribeNews.forEach(tnews->{
				layout.addComponent(new NewsDisplay(tnews,"252px"));
			});
		}
	}
}
