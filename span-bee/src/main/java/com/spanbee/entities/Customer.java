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
	private int id;

	@Column(name="birth_date")
	private String birthDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_at")
	private Date createdAt;

	@Column(name="customer_status")
	private byte customerStatus;

	@Column(name="email_address")
	private String emailAddress;

	@Column(name="first_name")
	private String firstName;

	private String gender;

	@Column(name="last_name")
	private String lastName;

	@Column(name="marital_status")
	private byte maritalStatus;

	private String mobile;

	private String password;

	@Column(name="registration_type")
	private byte registrationType;

	@Column(name="session_id")
	private String sessionId;

	@Column(name="unique_id")
	private String uniqueId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="updated_at")
	private Date updatedAt;

	//bi-directional one-to-one association to CustomerPersonnelInfo
	@OneToOne(mappedBy="customer")
	private CustomerPersonnelInfo customerPersonnelInfo;

	//bi-directional one-to-one association to CustomerProfessionalInfo
	@OneToOne(mappedBy="customer")
	private CustomerProfessionalInfo customerProfessionalInfo;

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

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public byte getMaritalStatus() {
		return this.maritalStatus;
	}

	public void setMaritalStatus(byte maritalStatus) {
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

	public byte getRegistrationType() {
		return this.registrationType;
	}

	public void setRegistrationType(byte registrationType) {
		this.registrationType = registrationType;
	}

	public String getSessionId() {
		return this.sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getUniqueId() {
		return this.uniqueId;
	}

	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}

	public Date getUpdatedAt() {
		return this.updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public CustomerPersonnelInfo getCustomerPersonnelInfo() {
		return this.customerPersonnelInfo;
	}

	public void setCustomerPersonnelInfo(CustomerPersonnelInfo customerPersonnelInfo) {
		this.customerPersonnelInfo = customerPersonnelInfo;
	}

	public CustomerProfessionalInfo getCustomerProfessionalInfo() {
		return this.customerProfessionalInfo;
	}

	public void setCustomerProfessionalInfo(CustomerProfessionalInfo customerProfessionalInfo) {
		this.customerProfessionalInfo = customerProfessionalInfo;
	}

}