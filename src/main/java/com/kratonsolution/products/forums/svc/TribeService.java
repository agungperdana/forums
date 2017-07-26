/**
 * 
 */
package com.kratonsolution.products.forums.svc;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import com.kratonsolution.products.forums.dm.Tribe;
import com.kratonsolution.products.forums.dm.TribeRepository;

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

	public void delete(Collection<Tribe> users)
	{
		if(users != null)
		{
			for(Tribe tribe:users)
				delete(tribe.getId());
		}
	}
}
