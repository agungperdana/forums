/**
 * 
 */
package com.kratonsolution.products.forums.tribe.api.domain;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com 
 */
public interface PreferencesRepository extends MongoRepository<Preferences, String>
{
}
