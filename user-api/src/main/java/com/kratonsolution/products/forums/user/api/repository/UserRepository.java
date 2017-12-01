/**
 * 
 */
package com.kratonsolution.products.forums.user.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.kratonsolution.products.forums.user.api.domain.User;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com 
 */
public interface UserRepository extends MongoRepository<User, String>
{
	public User findOneByEmail(String email);
}
