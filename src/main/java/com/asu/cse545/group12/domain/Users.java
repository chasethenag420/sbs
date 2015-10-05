package com.asu.cse545.group12.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;


//import org.hibernate.annotations.Table;


@Entity(name="user")
public class Users {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="USERID")
	private int userId;	

	@NotNull
    @NotEmpty(message = "Please enter your Firstname.")
	@Column(name = "FIRSTNAME")
	private String firstName;
	
	@Column(name = "MIDDLENAME")
	private String middleName;

	@NotNull
    @NotEmpty(message = "Please enter your Lastname.")
	@Column(name = "LASTNAME")
	private String lastName;
	
	@NotNull
    @NotEmpty(message = "Please enter your Gender.")	
	@Column(name = "GENDER")
	private String gender;
	
	@NotNull
    //@NotEmpty//(message = "Please enter your Username.")
	@Column(name = "USERNAME")
	private String userName;
	
	@NotNull
    //@NotEmpty//(message = "Please enter your password.")
    @Size(min = 6, max = 15, message = "Your password must between 6 and 15 characters")
	@Column(name = "PASSWORD")
	private String password;

	@NotNull
	@Column(name = "ROLEID")
	private int roleId;
	
	@Column(name = "USERSTATUS")
	private String userStatus;

	@Column(name = "REGISTRATION_DATE")
	private Date registrationDate;
	@Column(name = "LAST_MODIFIED_DATE")
	private Date lastModifiedDate;
	
	@NotNull
    @NotEmpty(message = "Please enter your Email.")
	@Column(name = "EMAILID")
	private String emailId;
	
	@NotNull
    @NotEmpty(message = "Please enter your Phone Number.")
	@Column(name = "PHONENUM")
	private String phoneNumber;
	

	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getMiddleName() {
		return middleName;
	}


	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public int getRoleId() {
		return roleId;
	}


	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}


	public String getUserStatus() {
		return userStatus;
	}


	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}


	public Date getRegistrationDate() {
		return registrationDate;
	}


	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}


	public String getEmailId() {
		return emailId;
	}


	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	
	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}


	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}


	public String getPhoneNumber() {
		return phoneNumber;
	}


	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
}



