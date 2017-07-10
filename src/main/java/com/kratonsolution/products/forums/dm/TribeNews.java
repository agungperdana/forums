/**
 * 
 */
package com.kratonsolution.products.forums.dm;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name="tribe_news")
public class TribeNews implements Serializable
{
	@Id
	private String id = UUID.randomUUID().toString();

	@Column(name="time_created")
	private Timestamp timeCreated;
	
	@Column(name="title")
	private String title;
	
	@Column(name="note")
	private String note;
		
	@ManyToOne
	@JoinColumn(name="fk_tribe")
	private Tribe tribe;
	
	@Column(name="creator")
	private String creator;
	
	@Version
	private Long version;
	
	public TribeNews(){}
}
