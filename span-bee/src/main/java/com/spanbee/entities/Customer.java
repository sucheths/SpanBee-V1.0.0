package com.spanbee.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the customers database table.
 * 
 */
@Entity
@Table(name="customers")
@NamedQuery(name="Customer.findAll", query="SELECT c FROM Customer c")
public class Customer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int id;

	@Column(name="birth_date", nullable=false, length=45)
	private String birthDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_at", nullable=false)
	private Date createdAt;

	@Column(name="created_by", nullable=false, length=45)
	private String createdBy;

	@Column(name="customer_status", nullable=false)
	private byte customerStatus;

	@Column(name="email_address", nullable=false, length=45)
	private String emailAddress;

	@Column(name="first_name", nullable=false, length=45)
	private String firstName;

	@Column(nullable=false)
	private byte gender;

	@Column(name="last_name", nullable=false, length=45)
	private String lastName;

	@Column(name="marital_status", nullable=false, length=45)
	private String maritalStatus;

	@Column(nullable=false, length=45)
	private String mobile;

	@Column(nullable=false, length=45)
	private String password;

	@Column(name="pin_code", nullable=false, length=45)
	private String pinCode;

	@Column(nullable=false, length=45)
	private String place;

	@Column(name="session_id", nullable=false, length=45)
	private String sessionId;

	@Column(nullable=false, length=45)
	private String state;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="updated_at", nullable=false)
	private Date updatedAt;

	@Column(name="updated_by", nullable=false, length=45)
	private String updatedBy;

	public Customer() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBirthDate() {
		return this.birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public Date getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public byte getCustomerStatus() {
		return this.customerStatus;
	}

	public void setCustomerStatus(byte customerStatus) {
		this.customerStatus = customerStatus;
	}

	public String getEmailAddress() {
		return this.emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public byte getGender() {
		return this.gender;
	}

	public void setGender(byte gender) {
		this.gender = gender;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMaritalStatus() {
		return this.maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPinCode() {
		return this.pinCode;
	}

	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}

	public String getPlace() {
		return this.place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getSessionId() {
		return this.sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Date getUpdatedAt() {
		return this.updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getUpdatedBy() {
		return this.updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

}