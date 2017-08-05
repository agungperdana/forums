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

import com.kratonsolution.products.forums.dm.TribeNews;
import com.kratonsolution.products.forums.dm.TribeNewsRepository;

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

	public TribeNews findOne(String id)
	{
		return repository.findOne(id);
	}

	public List<TribeNews> findAll()
	{
		return repository.findAll(new PageRequest(0, 50,new Sort(new Order(Direction.DESC,"timeCreated")))).getContent();
	}

	public int size()
	{
		return Long.valueOf(repository.count()).intValue();
	}

	public List<TribeNews> findAll(int pageIndex,int pageSize)
	{
		return repository.findAll(new PageRequest(pageIndex,pageSize)).getContent();
	}

	public void add(TribeNews tribe)
	{
		repository.save(tribe);
	}

	public void edit(TribeNews tribe)
	{
		repository.save(tribe);
	}

	public void delete(@PathVariable String id)
	{
		TribeNews tribe = repository.findOne(id);
		if(tribe != null)
		{
			repository.delete(tribe);
		}
	}

	public void delete(Collection<TribeNews> tribes)
	{
		if(tribes != null)
		{
			for(TribeNews tribe:tribes)
				delete(tribe.getId());
		}
	}
}
