/**
 * 
 */
package com.kratonsolution.products.forums.dm;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com 
 */
public interface TribeRepository extends MongoRepository<Tribe, String>
{
	public List<Tribe> findAllByCreatorEmail(String email);
	
	public List<Tribe> findAllByLastStatusType(Pageable pageable,TribeStatusType type);
	
	@Query("{$or:[ {creator.email: ?0}, {chieftain.email: ?0}, {contributors.email:?0} ]}")
	public List<Tribe> findAllByEmail(Pageable pageable,String email);
	
	@Query("{creator.email: ?0}")
	public List<Tribe> findAllMyTribe(Pageable pageable,String email);
}