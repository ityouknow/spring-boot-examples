package com.neo.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Cron implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private String cron_id;
	
	@Column(nullable = false)
	private String cron;

	public String getCron_id() {
		return cron_id;
	}

	public void setCron_id(String cron_id) {
		this.cron_id = cron_id;
	}

	public String getCron() {
		return cron;
	}

	public void setCron(String cron) {
		this.cron = cron;
	}
	

	

}