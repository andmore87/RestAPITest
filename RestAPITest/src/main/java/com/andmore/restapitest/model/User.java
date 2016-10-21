package com.andmore.restapitest.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 * User Entity.
 * @author Andres. 
 */
@Entity
@Table(name="user")
public class User extends TransferObject {
	 
	 
	private static final long serialVersionUID = 8654792978659647592L;


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@Column(nullable=false)
	private String  name;
	
	@Column(nullable=false)
	private String  last_name;
	
	@Column(nullable=false)	
	private String  email;
	 
	
	@Column(nullable=false)
	private String  password;
	
	@Column(nullable=false)
	private String	role;
	
	@Column(nullable=false)
	private String	currency;
	
	public User() {
		super();
	}
	
	public User(String email, String password, String name, String last_name, String currency) {
		super();
		this.email = email;
		this.password = password;
		this.name = name;
		this.last_name = last_name;
		this.currency = currency;
	}
	

	@Override
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	 
	
	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	@JsonIgnore
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}
	
	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}
}
