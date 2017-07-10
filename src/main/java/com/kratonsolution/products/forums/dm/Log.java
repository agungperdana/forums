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
@Table(name="log")
public class Log implements Serializable
{
	@Id
	private String id = UUID.randomUUID().toString();

	@Column(name="event_date")
	private Timestamp timestamp;

	@Column(name="note")
	private String note;
	
	@Column(name="user")
	private String user;
	
	@Version
	private Long version;

	public Log(){}
}
