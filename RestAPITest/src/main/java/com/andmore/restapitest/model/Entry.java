package com.andmore.restapitest.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

/**
 * Entry entity
 * @author Andres
 */
@Entity
@Table(name="entry")
@JsonSerialize(include = Inclusion.NON_DEFAULT)
public class Entry extends TransferObject {
	private static final long serialVersionUID = 4608706957805037954L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;	
	
	@Column(nullable=false)
	private Double amount;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;
	 
	private Currency currency;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date modified;
	
	@Column(nullable=false)
	private int	rate;
	
	 
	
	@ManyToOne(targetEntity = User.class, optional=false)
	@JoinColumn(name = "owner")
	private User owner;
	
	
	@JsonIgnore
	public User getOwner() {
		return owner;
	}
	public void setOwner(User owner) {
		this.owner = owner;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Currency getCurrency() {
		return currency;
	}
	public void setCurrency(Currency currency) {
		this.currency = currency;
	}
	public Date getModified() {
		return modified;
	}
	public void setModified(Date modified) {
		this.modified = modified;
	}
	public int getRate() {
		return rate;
	}
	public void setRate(int rate) {
		this.rate = rate;
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
