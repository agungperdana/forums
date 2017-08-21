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
public interface TribeNewsRepository extends MongoRepository<TribeNews, String>
{
	@Query("{$and:[{subscribers.email:?0},{approved:true}]}")
	public List<TribeNews> findAllMyNews(Pageable pageable,String email);

	@Query("{approved:true}")
	public List<TribeNews> findAllApproved(Pageable pageable);
	
	public List<TribeNews> findAllByTribe(String tribe);
	
	public List<TribeNews> findAllByTribe(Pageable pageable,String tribe);
}
