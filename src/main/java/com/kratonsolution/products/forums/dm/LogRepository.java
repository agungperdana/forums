/**
 * 
 */
package com.kratonsolution.products.forums.dm;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com 
 */
public interface LogRepository extends MongoRepository<Log, String>
{

}
