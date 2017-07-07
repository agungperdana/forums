/**
 * 
 */
package com.kratonsolution.products.forums.security.svc;

import java.util.List;

import org.apache.logging.log4j.util.Strings;
import org.jasypt.util.password.StrongPasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.annotation.Secured;
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
		
	@Secured({"ROLE_USER_READ","ROLE_SYSTEM_READ"})
	public User findOne(String id)
	{
		return repository.findOne(id);
	}
	
	@Secured("ROLE_USER_READ")
	public List<User> findAll()
	{
		return repository.findAll();
	}
	
	@Secured("ROLE_USER_READ")
	public int size()
	{
		return Long.valueOf(repository.count()).intValue();
	}
	
	@Secured("ROLE_USER_READ")
	public List<User> findAll(int pageIndex,int pageSize)
	{
		return repository.findAll(new PageRequest(pageIndex,pageSize)).getContent();
	}
	
	@Secured("ROLE_USER_CREATE")
	public void add(User user)
	{
		user.setPassword(encryptor.encryptPassword(user.getPassword()));
		repository.save(user);
	}
	
	@Secured("ROLE_USER_UPDATE")
	public void edit(User user)
	{
		repository.saveAndFlush(user);
	}
	
	@Secured("ROLE_USER_DELETE")
	public void delete(@PathVariable String id)
	{
		User user = repository.findOne(id);
		if(user != null)
		{
			repository.delete(user);
		}
	}
	
	@Secured("ROLE_USER_UPDATE")
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
}
