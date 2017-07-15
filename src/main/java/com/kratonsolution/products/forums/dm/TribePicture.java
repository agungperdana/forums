/**
 * 
 */
package com.kratonsolution.products.forums.dm;

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
@Table(name="tribe_picture")
public class TribePicture
{
	@Id
	private String id = UUID.randomUUID().toString();

	@Column(name="path")
	private String path;
	
	@ManyToOne
	@JoinColumn(name="fk_tribe")
	private Tribe tribe;
	
	@Column(name="inserted_by")
	private String insertedBy;
	
	@Column(name="inserted_date")
	private Timestamp insertDate;
	
	@Column(name="is_displayed")
	private boolean displayed;
	
	@Version
	private Long version;
}
