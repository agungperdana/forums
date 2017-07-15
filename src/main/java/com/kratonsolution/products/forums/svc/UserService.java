/**
 * 
 */
package com.kratonsolution.products.forums.svc;

import java.util.Collection;
import java.util.List;

import org.apache.logging.log4j.util.Strings;
import org.jasypt.util.password.StrongPasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import com.kratonsolution.products.forums.dm.User;
import com.kratonsolution.products.forums.dm.UserRepository;

/**
 * 
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class UserService
{
	@Autowired
	private UserRepository repository;
			
	private StrongPasswordEncryptor encryptor = new StrongPasswordEncryptor();
		
	public User findOne(String id)
	{
		return repository.findOne(id);
	}
	
	public User byEmail(String email)
	{
		return repository.findOneByEmail(email);
	}
	
	public List<User> findAll()
	{
		return repository.findAll();
	}
	
	public int size()
	{
		return Long.valueOf(repository.count()).intValue();
	}
	
	public List<User> findAll(int pageIndex,int pageSize)
	{
		return repository.findAll(new PageRequest(pageIndex,pageSize)).getContent();
	}
	
	public void add(User user)
	{
		User on = repository.findOneByEmail(user.getEmail());
		if(on != null)
			throw new RuntimeException("User with email "+user.getEmail()+" already exist.");
		
		user.setPassword(encryptor.encryptPassword(user.getPassword()));
		repository.save(user);
	}
	
	public void edit(User user)
	{
		User out = repository.findOne(user.getId());
		if(out != null)
		{
			out.setName(user.getName());
			out.setEmail(user.getEmail());
			out.setBirthDate(user.getBirthDate());
			out.setEnabled(user.isEnabled());
			out.setActivated(user.isActivated());
			
			if(!out.getPassword().equals(user.getPassword()))
				out.setPassword(encryptor.encryptPassword(user.getPassword()));

		
			repository.saveAndFlush(out);
		}
	}
	
	public void delete(@PathVariable String id)
	{
		User user = repository.findOne(id);
		if(user != null)
		{
			repository.delete(user);
		}
	}
	
	
	public void changePassword(String id,String newPassword,String renewPassword)
	{
		if(Strings.isBlank(id))
			throw new RuntimeException("User cannot be empty");
		
		if(Strings.isBlank(newPassword))
			throw new RuntimeException("New Password cannot be empty");
		
		if(Strings.isBlank(renewPassword))
			throw new RuntimeException("Re - type New Password cannot be empty");
		
		if(!newPassword.equals(renewPassword))
			throw new RuntimeException("Password not equals");
			
		StrongPasswordEncryptor encryptor = new StrongPasswordEncryptor();
		
		User user = repository.findOne(id);
		if(user != null)
		{
			user.setPassword(encryptor.encryptPassword(newPassword));
			repository.save(user);
		}
	}
	
	public void delete(Collection<User> users)
	{
		if(users != null)
		{
			for(User user:users)
				delete(user.getId());
		}
	}
}
