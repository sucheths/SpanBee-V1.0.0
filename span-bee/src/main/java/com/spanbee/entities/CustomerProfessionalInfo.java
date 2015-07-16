package com.spanbee.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the customer_professional_info database table.
 * 
 */
@Entity
@Table(name="customer_professional_info")
@NamedQuery(name="CustomerProfessionalInfo.findAll", query="SELECT c FROM CustomerProfessionalInfo c")
public class CustomerProfessionalInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	private int experience;

	@Column(name="field_of_study")
	private int fieldOfStudy;

	private int id;

	private int industry;

	private int qualification;

	private int title;

	@Column(name="yearly_income")
	private int yearlyIncome;

	//bi-directional one-to-one association to Customer
	@OneToOne
	@JoinColumn(name="customer_id")
	private Customer customer;

	public CustomerProfessionalInfo() {
	}

	public int getExperience() {
		return this.experience;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}

	public int getFieldOfStudy() {
		return this.fieldOfStudy;
	}

	public void setFieldOfStudy(int fieldOfStudy) {
		this.fieldOfStudy = fieldOfStudy;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIndustry() {
		return this.industry;
	}

	public void setIndustry(int industry) {
		this.industry = industry;
	}

	public int getQualification() {
		return this.qualification;
	}

	public void setQualification(int qualification) {
		this.qualification = qualification;
	}

	public int getTitle() {
		return this.title;
	}

	public void setTitle(int title) {
		this.title = title;
	}

	public int getYearlyIncome() {
		return this.yearlyIncome;
	}

	public void setYearlyIncome(int yearlyIncome) {
		this.yearlyIncome = yearlyIncome;
	}

	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}