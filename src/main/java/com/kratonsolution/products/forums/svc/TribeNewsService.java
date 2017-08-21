/**
 * 
 */
package com.kratonsolution.products.forums.svc;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kratonsolution.products.forums.common.Security;
import com.kratonsolution.products.forums.dm.Tribe;
import com.kratonsolution.products.forums.dm.TribeNews;
import com.kratonsolution.products.forums.dm.TribeNewsRepository;
import com.kratonsolution.products.forums.dm.TribeRepository;
import com.kratonsolution.products.forums.dm.TribeStatusType;

/**
 * 
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class TribeNewsService
{
	@Autowired
	private TribeNewsRepository repository;

	@Autowired
	private TribeRepository tribeRepository;
	
	public TribeNews findOne(String id)
	{
		return repository.findOne(id);
	}

	public List<TribeNews> findAll()
	{
		return repository.findAll(new PageRequest(0, 50,new Sort(new Order(Direction.DESC,"timeCreated")))).getContent();
	}
	
	public List<TribeNews> findAllApproved()
	{
		return repository.findAllApproved(new PageRequest(0, 50,new Sort(new Order(Direction.DESC,"timeCreated"))));
	}
	
	public List<TribeNews> findAllMyNews()
	{
		return repository.findAllMyNews(new PageRequest(0, 50,new Sort(new Order(Direction.DESC,"timeCreated"))),Security.getUserEmail());
	}
	
	public List<TribeNews> findAllByTribe(String tribeId)
	{
		return repository.findAllByTribe(new PageRequest(0, 50,new Sort(new Order(Direction.DESC,"timeCreated"))),tribeId);
	}

	public int size()
	{
		return Long.valueOf(repository.count()).intValue();
	}

	public List<TribeNews> findAll(int pageIndex,int pageSize)
	{
		return repository.findAll(new PageRequest(pageIndex,pageSize)).getContent();
	}

	public void add(TribeNews news)
	{
		Tribe tribe = tribeRepository.findOne(news.getTribe());
		if(tribe != null)
		{
			news.setApproved(tribe.getLastStatus().getType().equals(TribeStatusType.APPROVED));
			news.getSubscribers().add(tribe.getCreator());
			news.getSubscribers().add(tribe.getChieftain());
			
			tribe.getContributors().forEach(contrib->{
				news.getSubscribers().add(contrib);
			});
			
			tribe.getFollowers().forEach(follow->{
				news.getSubscribers().add(follow);
			});
		}
		
		repository.save(news);
	}

	public void edit(TribeNews news)
	{
		repository.save(news);
	}

	public void delete(String id)
	{
		TribeNews news = repository.findOne(id);
		if(news != null)
		{
			repository.delete(news);
		}
	}

	public void delete(Collection<TribeNews> tribes)
	{
		if(tribes != null)
		{
			for(TribeNews news:tribes)
				delete(news.getId());
		}
	}
}
