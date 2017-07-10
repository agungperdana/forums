/**
 * 
 */
package com.kratonsolution.products.forums.dm;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
@Table(name="tribe_status")
public class TribeStatus implements Serializable
{
	@Id
	private String id = UUID.randomUUID().toString();

	@Column(name="created_time")
	private Timestamp createdTime;
	
	@Column(name="type")
	@Enumerated(EnumType.STRING)
	private TribeStatusType type = TribeStatusType.CREATED;

	@ManyToOne
	@JoinColumn(name="fk_tribe")
	private Tribe tribe;
	
	@Version
	private Long version;
	
	public TribeStatus(){}
}
