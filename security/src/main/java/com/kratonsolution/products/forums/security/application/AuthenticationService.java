/**
 * 
 */
package com.kratonsolution.products.forums.security.application;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.kratonsolution.products.forums.user.api.domain.User;
import com.kratonsolution.products.forums.user.api.repository.UserRepository;

/**
 * 
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com
 */
@Service
@Transactional(readOnly=true,propagation=Propagation.SUPPORTS)
public class AuthenticationService implements UserDetailsService
{
	@Autowired
	private UserRepository repository;
		
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
	{
		User user = repository.findOneByEmail(username);
		if(user == null)
			throw new UsernameNotFoundException("No User with id "+username);
		
		return new SecurityInformation(user,new ArrayList<Authority>());
	}
}
