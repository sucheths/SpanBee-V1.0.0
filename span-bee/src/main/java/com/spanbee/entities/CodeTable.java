package com.spanbee.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the code_tables database table.
 * 
 */
@Entity
@Table(name="code_tables")
@NamedQuery(name="CodeTable.findAll", query="SELECT c FROM CodeTable c")
public class CodeTable implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Column(name="code_name")
	private String codeName;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_at")
	private Date createdAt;

	@Column(name="default_value")
	private String defaultValue;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="updated_at")
	private Date updatedAt;

	//bi-directional many-to-one association to CodeValue
	@OneToMany(mappedBy="codeTable")
	private List<CodeValue> codeValues;

	public CodeTable() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCodeName() {
		return this.codeName;
	}

	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}

	public Date getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getDefaultValue() {
		return this.defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public Date getUpdatedAt() {
		return this.updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public List<CodeValue> getCodeValues() {
		return this.codeValues;
	}

	public void setCodeValues(List<CodeValue> codeValues) {
		this.codeValues = codeValues;
	}

	public CodeValue addCodeValue(CodeValue codeValue) {
		getCodeValues().add(codeValue);
		codeValue.setCodeTable(this);

		return codeValue;
	}

	public CodeValue removeCodeValue(CodeValue codeValue) {
		getCodeValues().remove(codeValue);
		codeValue.setCodeTable(null);

		return codeValue;
	}

}