package com.spanbee.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the customer_address database table.
 * 
 */
@Entity
@Table(name="customer_address")
@NamedQuery(name="CustomerAddress.findAll", query="SELECT c FROM CustomerAddress c")
public class CustomerAddress implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Column(name="address_line1")
	private String addressLine1;

	@Column(name="address_line2")
	private String addressLine2;

	private String country;

	private String pincode;

	private String state;

	//bi-directional many-to-one association to Customer
	@ManyToOne
	private Customer customer;

	public CustomerAddress() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAddressLine1() {
		return this.addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return this.addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPincode() {
		return this.pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}