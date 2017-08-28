/**
 * 
 */
package com.kratonsolution.products.forums.ui;

import com.kratonsolution.products.forums.common.Springs;
import com.kratonsolution.products.forums.svc.TribeNewsService;
import com.kratonsolution.products.forums.ui.tribe.NewsDisplay;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com 
 */
public class PublicTopTribeNews extends Panel
{
	private Label title = new Label("<h3><u><font style='color:red;'>T</font>op Tribe News</u></h3>", ContentMode.HTML);
	
	private VerticalLayout root = new VerticalLayout();

	private VerticalLayout contents = new VerticalLayout();
	
	private Button more = new Button("Load More", VaadinIcons.CARET_DOWN);
	
	private int howmany = 9;
	
	private int start = 0;
	
	public PublicTopTribeNews()
	{
		setSizeFull();
		setStyleName(ValoTheme.PANEL_BORDERLESS);

		contents.setSpacing(true);
		contents.setMargin(false);
		contents.addComponent(new News(start));
		
		Panel panel = new Panel();
		panel.addStyleName(ValoTheme.PANEL_BORDERLESS);
		panel.setContent(contents);
		panel.setHeight("99%");
		
		more.setWidth("95%");
		more.setHeight("25px");
		more.addStyleName(ValoTheme.BUTTON_ICON_ALIGN_RIGHT);
		more.addStyleName(ValoTheme.BUTTON_LINK);
		more.addClickListener(click->{
			
			start++;
			
			News news = new News(start);
			if(news.getComponentCount() < 9)
				more.setEnabled(false);
			
			contents.addComponent(news);
		});
		
		root.setSpacing(false);
		root.setMargin(new MarginInfo(true,false,true,true));
		root.addComponent(title);
		root.addComponent(contents);
		root.addComponent(more);

		root.setExpandRatio(title, 0.5f);
		root.setExpandRatio(contents, 9f);
		root.setExpandRatio(more, 0.5f);
		root.setComponentAlignment(more, Alignment.MIDDLE_CENTER);

		setContent(root);
	}
	
	private class News extends GridLayout
	{
		private TribeNewsService service = Springs.get(TribeNewsService.class);
		
		public News(int start)
		{
			setSpacing(true);
			setMargin(true);
			setColumns(3);
			setRows(3);
			setWidth("99%");
			
			service.findAll(start, howmany).forEach(news->{
				System.out.println(news.getDescription());
				addComponent(new NewsDisplay(news));
			});
		}
	}
}
