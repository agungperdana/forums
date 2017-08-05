/**
 * 
 */
package com.kratonsolution.products.forums.dm;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com 
 */
@Getter
@Setter
@Document(collection="tribe")
public class Tribe implements Serializable
{
	@Id
	private String id = UUID.randomUUID().toString();
	
	@Indexed
	private String title;
	
	private String note;

	private String goal;
	
	private Timestamp created;
	
	private TribeStatus lastStatus;
	
	private byte[] logo;
	
	private PersonalInfo chieftain;
	
	@Indexed
	private PersonalInfo creator;
	
	private List<PersonalInfo> contributors = new ArrayList<>();
	
	private Set<TribeStatus> statuses = new HashSet<>();
	
	@DBRef
	private Set<TribeNews> news = new HashSet<>();
	
	@DBRef
	private Set<TribeEvent> events = new HashSet<>();
	
	private Set<TribeFollower> followers = new HashSet<>();
	
	public Tribe(){}
}
