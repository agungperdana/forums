/**
 * 
 */
package com.kratonsolution.products.forums.ui.user;

import java.util.Vector;
import java.util.stream.Stream;

import org.springframework.data.domain.PageRequest;

import com.kratonsolution.products.forums.Springs;
import com.kratonsolution.products.forums.dm.User;
import com.kratonsolution.products.forums.dm.UserRepository;
import com.vaadin.data.provider.DataProvider;
import com.vaadin.data.provider.DataProviderListener;
import com.vaadin.data.provider.Query;
import com.vaadin.shared.Registration;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com 
 */
public class UserProvider implements DataProvider<User, User>
{	
	private UserRepository repo = Springs.get(UserRepository.class);
	
	private Vector<User> data = new Vector<>();
	
	public UserProvider()
	{
		data.addAll(repo.findAll(new PageRequest(0, 50)).getContent());
	}
	
	@Override
	public boolean isInMemory()
	{
		return false;
	}

	@Override
	public int size(Query<User, User> query)
	{
		return (int)repo.count();
	}

	@Override
	public Stream<User> fetch(Query<User, User> query)
	{
		return data.stream();
	}

	@Override
	public void refreshItem(User item)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void refreshAll()
	{
		data.clear();
		data.addAll(repo.findAll(new PageRequest(0, 50)).getContent());
	}

	@Override
	public Registration addDataProviderListener(DataProviderListener<User> listener)
	{
		return null;
	}

}
