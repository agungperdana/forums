/**
 * 
 */
package com.kratonsolution.products.forums.ui.tribe;

import java.util.Vector;
import java.util.stream.Stream;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;

import com.kratonsolution.products.forums.common.Springs;
import com.kratonsolution.products.forums.dm.Tribe;
import com.kratonsolution.products.forums.dm.TribeRepository;
import com.vaadin.data.provider.DataProvider;
import com.vaadin.data.provider.DataProviderListener;
import com.vaadin.data.provider.Query;
import com.vaadin.shared.Registration;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com 
 */
public class TribeProvider implements DataProvider<Tribe, Tribe>
{	
	private TribeRepository repo = Springs.get(TribeRepository.class);
	
	private Vector<Tribe> data = new Vector<>();
	
	public TribeProvider()
	{
		data.addAll(repo.findAll(new PageRequest(0, 50,new Sort(new Order(Direction.DESC, "created")))).getContent());
	}
	
	@Override
	public boolean isInMemory()
	{
		return false;
	}

	@Override
	public int size(Query<Tribe, Tribe> query)
	{
		return (int)repo.count();
	}

	@Override
	public Stream<Tribe> fetch(Query<Tribe, Tribe> query)
	{
		return data.stream();
	}

	@Override
	public void refreshItem(Tribe item)
	{
	}

	@Override
	public void refreshAll()
	{
		data.clear();
		data.addAll(repo.findAll(new PageRequest(0, 50)).getContent());
	}

	@Override
	public Registration addDataProviderListener(DataProviderListener<Tribe> listener)
	{
		return null;
	}
}
