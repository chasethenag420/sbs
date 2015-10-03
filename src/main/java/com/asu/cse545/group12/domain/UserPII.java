package com.asu.cse545.group12.domain;

import java.util.Date;

import javax.persistence.Column;

public class UserPII {

	@Column(name = "USERID")
	private int userId;
	

	@Column(name = "SSN")
	private int ssn;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getSsn() {
		return ssn;
	}

	public void setSsn(int ssn) {
		this.ssn = ssn;
	}

	public Date getDateOfBirth() {
		return DateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		DateOfBirth = dateOfBirth;
	}

	@Column(name = "DATEOFBIRTH")
	private Date DateOfBirth;
	

}
