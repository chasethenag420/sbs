package com.asu.cse545.group12.domain;

import java.util.Date;

import javax.persistence.Column;

public class UserPII {

	@Column(name = "USERID")
	private int userId;
	

	@Column(name = "SSN")
	private int ssn;

	@Column(name = "DATEOFBIRTH")
	private Date DateOfBirth;
	

}
