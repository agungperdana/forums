/**
 * 
 */
package com.kratonsolution.products.forums.dm;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com 
 */
public interface UserRepository extends JpaRepository<User, String>
{
	public User findOneByEmail(String email);
}
