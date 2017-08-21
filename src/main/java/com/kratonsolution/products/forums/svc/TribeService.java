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
import com.kratonsolution.products.forums.dm.TribeEventRepository;
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
public class TribeService
{
	@Autowired
	private TribeRepository repository;

	@Autowired
	private TribeNewsRepository newsRepository;

	@Autowired
	private TribeEventRepository eventRepository;

	public Tribe findOne(String id)
	{
		return repository.findOne(id);
	}

	public List<Tribe> findAll()
	{
		return repository.findAll(new PageRequest(0, 50,new Sort(new Order(Direction.DESC,"created")))).getContent();
	}

	public List<Tribe> findAllCreatedBy(String creatorEmail)
	{
		return repository.findAllByCreatorEmail(creatorEmail);
	}

	public List<Tribe> findAllInvolved()
	{
		return repository.findAllByEmail(new PageRequest(0, 50,new Sort(new Order(Direction.DESC,"created"))),Security.getUserEmail());
	}

	public List<Tribe> findAllMyTribe()
	{
		return repository.findAllMyTribe(new PageRequest(0, 50,new Sort(new Order(Direction.DESC,"created"))),Security.getUserEmail());
	}

	public List<Tribe> findAllApproved()
	{
		return repository.findAllByLastStatusType(new PageRequest(0, 20,new Sort(new Order(Direction.DESC,"created"))),TribeStatusType.APPROVED);
	}

	public int size()
	{
		return Long.valueOf(repository.count()).intValue();
	}

	public List<Tribe> findAll(int pageIndex,int pageSize)
	{
		return repository.findAll(new PageRequest(pageIndex,pageSize)).getContent();
	}

	public void add(Tribe tribe)
	{
		repository.save(tribe);
	}

	public void approve(Tribe tribe)
	{
		repository.save(tribe);

		newsRepository.findAllByTribe(tribe.getId()).forEach(news->{
			news.setApproved(true);
			newsRepository.save(news);
		});
	}

	public void edit(Tribe tribe)
	{
		repository.save(tribe);
	}

	public void delete(String id)
	{
		repository.delete(id);

		newsRepository.findAllByTribe(id).forEach(news->{
			newsRepository.delete(news);
		});
		
		eventRepository.findAllByTribe(id).forEach(event->{
			eventRepository.delete(event);
		});
	}

	public void delete(Collection<Tribe> tribes)
	{
		if(tribes != null)
		{
			for(Tribe tribe:tribes)
				delete(tribe.getId());
		}
	}
}
