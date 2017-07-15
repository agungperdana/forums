/**
 * 
 */
package com.kratonsolution.products.forums.dm;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com 
 */
@Getter
@Setter
@Entity
@Table(name="tribe")
public class Tribe implements Serializable
{
	@Id
	private String id = UUID.randomUUID().toString();
	
	@Column(name="title")
	private String title;
	
	@Column(name="note")
	private String note;

	@Column(name="time_created")
	private Timestamp created;
	
	@Version
	private Long version;

	@OneToMany(mappedBy="tribe",cascade=CascadeType.ALL,orphanRemoval=true)
	private Set<TribeRole> roles = new HashSet<>();
	
	@OneToMany(mappedBy="tribe",cascade=CascadeType.ALL,orphanRemoval=true)
	private Set<TribeStatus> statuses = new HashSet<>();
	
	@OneToMany(mappedBy="tribe",cascade=CascadeType.ALL,orphanRemoval=true)
	private Set<TribeNews> news = new HashSet<>();
	
	@OneToMany(mappedBy="tribe",cascade=CascadeType.ALL,orphanRemoval=true)
	private Set<TribeEvent> events = new HashSet<>();
	
	@OneToMany(mappedBy="tribe",cascade=CascadeType.ALL,orphanRemoval=true)
	private Set<TribePicture> pictures = new HashSet<>();
	
	public Tribe(){}
}
