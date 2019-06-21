/**
 * 
 */
package com.t9.system.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * @author husq
 *
 */
@MappedSuperclass
public class BaseDomain implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String ID;
	
	@Id
	@Column(length=32)
	public String getID() {
		return ID;
	}

	public void setID(String ID) {
		this.ID = ID;
	}
	
}
