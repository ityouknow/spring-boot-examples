package com.tools.schedulejob.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name="t_logs")
public class Logs implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Long id;
	@Column(nullable = false, unique = false)
	
	private String url;
	@Column(nullable = false, unique = false)
	private String method;
	
	@Column(nullable = false, unique = false)
	private String action;

	public Logs() {
		super();
	}
	public Logs(String action, String method, String url) {
		super();
		this.url = url;
		this.method = method;
		this.action = action;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}


	public String getMethod() {
		return method;
	}


	public void setMethod(String method) {
		this.method = method;
	}
	

}