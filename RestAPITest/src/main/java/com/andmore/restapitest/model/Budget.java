/**
 * 
 */
package com.andmore.restapitest.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;


 
/**
 * Budget entity
 * 
 * @author Andres
 *
 */
@Entity
@Table(name = "budget")
@JsonSerialize(include = Inclusion.NON_DEFAULT)
public class Budget extends TransferObject {

	private static final long serialVersionUID = 7725984410533028336L;
	
	@Transient
	private static final String DATEFORMAT = "YYYY-MM-DD";
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(nullable=false)
	private Long limit;

	@Column(nullable=false)
	private double amount;

	@Column(nullable=false)
	private Date start_date;

	@Column(nullable=false)
	private Date end_date;
	
	@ManyToOne(targetEntity = User.class, optional=false)
	@JoinColumn(name = "owner")
	private User owner;
	
	  

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	 
	public Long getLimit() {
		return limit;
	}

	public void setLimit(Long limit) {
		this.limit = limit;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Date getStart_date() {
		return start_date;
	}

	public String getStrStart_date() {
		return new SimpleDateFormat(DATEFORMAT).format(this.start_date);
	}

	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}

	public Date getEnd_date() {
		return end_date;
	}

	public String getStrEnd_date() {
		return new SimpleDateFormat(DATEFORMAT).format(this.end_date);

	}
	

	@JsonIgnore
	public User getOwner() {
		return owner;
	}
	public void setOwner(User owner) {
		this.owner = owner;
	}

	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
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
