/**
 * 
 */
package com.kratonsolution.products.forums.tribe.impl.application;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kratonsolution.products.forums.common.Security;
import com.kratonsolution.products.forums.tribe.api.domain.PersonalInfo;
import com.kratonsolution.products.forums.tribe.api.domain.Tribe;
import com.kratonsolution.products.forums.tribe.api.domain.TribeEventRepository;
import com.kratonsolution.products.forums.tribe.api.domain.TribeNewsRepository;
import com.kratonsolution.products.forums.tribe.api.domain.TribeRepository;
import com.kratonsolution.products.forums.tribe.api.domain.TribeStatusType;

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
	
	public List<Tribe> findAllApproved(int page,int howmany)
	{
		return repository.findAllByLastStatusType(new PageRequest(page, howmany,new Sort(new Order(Direction.DESC,"created"))),TribeStatusType.APPROVED);
	}
	
	public List<Tribe> findAllApproved(int page,int howmany,String key)
	{
		if(key == null || key.equals(""))
			return findAllApproved(page, howmany);
		
		return repository.findAllByLastStatusTypeAndNameOrDescriptionLike(new PageRequest(page, howmany,new Sort(new Order(Direction.DESC,"created"))),TribeStatusType.APPROVED,key,key);
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
	
	public void join(String tribe)
	{
		Tribe out = repository.findOne(tribe);
		if(out != null)
		{
			PersonalInfo info = new PersonalInfo();
			info.setName(Security.getUserName());
			info.setEmail(Security.getUserEmail());
			
			out.getFollowers().add(info);
		
			repository.save(out);
		}
	}
	
	public void leave(String tribe)
	{
		Tribe out = repository.findOne(tribe);
		if(out != null)
		{
			Iterator<PersonalInfo> iterator = out.getFollowers().iterator();
			while (iterator.hasNext())
			{
				PersonalInfo info = (PersonalInfo) iterator.next();
				if(info.getEmail().equals(Security.getUserEmail()))
				{
					iterator.remove();
					break;
				}
			}
			
			repository.save(out);
		}
	}
}
