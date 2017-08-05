/**
 * 
 */
package com.kratonsolution.products.forums.ui;

import com.kratonsolution.products.forums.common.Springs;
import com.kratonsolution.products.forums.svc.TribeNewsService;
import com.kratonsolution.products.forums.ui.tribe.NewsDisplay;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com 
 */
@SpringComponent
@UIScope
public class PublicTopTribeNews extends TopTribeNews
{
	private TribeNewsService service = Springs.get(TribeNewsService.class);
	
	public PublicTopTribeNews()
	{
		super();
		
		service.findAll().forEach(news->{
			layout.addComponent(new NewsDisplay(news));
		});
	}
}
