/**
 * 
 */
package com.kratonsolution.products.forums.dm;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com 
 */
public interface TribeRepository extends MongoRepository<Tribe, String>
{
	public List<Tribe> findAllByCreatorEmail(String email);
}
