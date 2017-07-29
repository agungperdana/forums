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
import org.springframework.web.bind.annotation.PathVariable;

import com.kratonsolution.products.forums.dm.Tribe;
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

	public Tribe findOne(String id)
	{
		return repository.findOne(id);
	}

	public List<Tribe> findAll()
	{
		return repository.findAll();
	}
	
	public List<Tribe> findAll(String creatorEmail)
	{
		return repository.findAllByCreatorEmail(creatorEmail);
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

	public void edit(Tribe tribe)
	{
		repository.save(tribe);
	}

	public void delete(@PathVariable String id)
	{
		Tribe tribe = repository.findOne(id);
		if(tribe != null)
		{
			repository.delete(tribe);
		}
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
