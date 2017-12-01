/**
 * 
 */
package com.kratonsolution.products.forums.tribe.api.domain;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com 
 */
public interface TribeEventRepository extends MongoRepository<TribeEvent, String>
{
	public List<TribeEvent> findAllByTribe(Pageable pageable,String tribe);
	
	public List<TribeEvent> findAllByTribe(String tribe);
}
