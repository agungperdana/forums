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

import com.kratonsolution.products.forums.dm.TribeEvent;
import com.kratonsolution.products.forums.dm.TribeEventRepository;

/**
 * 
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class TribeEventService
{
	@Autowired
	private TribeEventRepository repository;

	public TribeEvent findOne(String id)
	{
		return repository.findOne(id);
	}

	public List<TribeEvent> findAll()
	{
		return repository.findAll(new PageRequest(0, 50,new Sort(new Order(Direction.DESC,"created")))).getContent();
	}

	public int size()
	{
		return Long.valueOf(repository.count()).intValue();
	}

	public List<TribeEvent> findAll(int pageIndex,int pageSize)
	{
		return repository.findAll(new PageRequest(pageIndex,pageSize)).getContent();
	}

	public void add(TribeEvent tribe)
	{
		repository.save(tribe);
	}

	public void edit(TribeEvent tribe)
	{
		repository.save(tribe);
	}

	public void delete(String id)
	{
		TribeEvent tribe = repository.findOne(id);
		if(tribe != null)
		{
			repository.delete(tribe);
		}
	}

	public void delete(Collection<TribeEvent> tribes)
	{
		if(tribes != null)
		{
			for(TribeEvent tribe:tribes)
				delete(tribe.getId());
		}
	}
}
