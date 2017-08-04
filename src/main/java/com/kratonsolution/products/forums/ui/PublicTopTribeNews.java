/**
 * 
 */
package com.kratonsolution.products.forums.ui;

import com.kratonsolution.products.forums.common.Springs;
import com.kratonsolution.products.forums.svc.TribeService;
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
	private TribeService service = Springs.get(TribeService.class);
	
	public PublicTopTribeNews()
	{
		super();
		
		service.findAll().forEach(tribe->{
			tribe.getNews().forEach(news->{
				layout.addComponent(new NewsDisplay(tribe, news));
			});
		});
	}
}
