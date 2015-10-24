package com.asu.cse545.group12.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;


//import org.hibernate.annotations.Table;


@Entity(name="user")
public class Users implements Serializable {

	@Override
	public String toString() {
		return "Users [userId=" + userId + ", firstName=" + firstName + ", middleName=" + middleName + ", lastName="
				+ lastName + ", gender=" + gender + ", userName=" + userName + ", password=" + password + ", roleId="
				+ roleId + ", userStatus=" + userStatus + ", registrationDate=" + registrationDate
				+ ", lastModifieddate=" + lastModifieddate + ", emailId=" + emailId + ", phoneNumber=" + phoneNumber
				+ ", address=" + address + ", city=" + city + ", state=" + state + ", country=" + country + ", zipcode="
				+ zipcode + ", userpii=" + userpii+"]";
	}


	@Id
	@Column(name="USERID")
	//@NotEmpty(message = "Please enter your username.")
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Integer userId;	

	@NotNull
    @NotEmpty(message = "Please enter your Firstname.")
	@Column(name = "FIRSTNAME")
	private String firstName;
	
	@Column(name = "MIDDLENAME")
	private String middleName;
	
	@Column(name = "FAILED_ATTEMPTS") 
	private Integer numAttempts;
	
	

	@Column(name = "FAILED_ATTEMPTS")
	public Integer getNumAttempts() {
		return numAttempts;
	}
	//@Column(name = "FAILED_ATTEMPTS")
	public void setNumAttempts(Integer numAttempts) {
		this.numAttempts = numAttempts;
	}


	@NotNull
    @NotEmpty(message = "Please enter your Lastname.")
	@Column(name = "LASTNAME")
	private String lastName;
	
	@NotNull
    @NotEmpty(message = "Please enter your Gender.")	
	@Column(name = "GENDER")
	private String gender;
	
	@NotNull
    //@NotEmpty(message = "Please enter your Username.")
	@Column(name = "USERNAME")
	private String userName;
	
	@NotNull
    //@NotEmpty(message = "Please enter your password.")
	//@Pattern(regexp="((?=.*[a-z])(?=.*\\d)(?=.*[A-Z]).{8,40})", message="Wrong password!")
    //@Size(min = 6, max = 15, message = "Your password must between 6 and 15 characters")
	@Column(name = "PASSWORD")
	private String password;

	@NotNull
	@Column(name = "ROLEID")
	private Integer roleId;
	
	@Column(name = "USERSTATUS")
	private String userStatus;

	@Column(name = "REGISTRATION_DATE")
	private Date registrationDate;
	

	@Column(name="LAST_MODIFIED_DATE")
	private Date lastModifieddate;
	
	@NotNull
	@Pattern(regexp=".+@.+\\..+", message="Please check your email")
    @NotEmpty(message = "Please enter your Email.")
	@Column(name = "EMAILID")
	private String emailId;
	
	@NotNull
    @NotEmpty(message = "Please enter your Phone Number.")
	@Column(name = "PHONENUM")
	private String phoneNumber;
	
	@Column(name = "ADDRESS")
	private String address;
	
	@Column(name = "CITY")
	private String city;
	
	@Column(name = "STATE")
	private String state;
	
	@Column(name = "COUNTRY")
	private String country;
	
	@Pattern(regexp="[0-9]+", message="Please enter correct zip")
	@Column(name = "ZIPCODE")
	private String zipcode;
	
	@Column(name = "OTP")
	private String OTP;
	
	
	
	private UserPII userpii;
	
	@OneToOne(fetch = FetchType.EAGER, mappedBy = "user", cascade = CascadeType.ALL)
	@JoinColumn(name="USERID")
	public UserPII getUserpii() {
		return this.userpii;
	}
	public void setUserpii(UserPII userpii)
	{
		this.userpii=userpii;
	}
	@Id	
	@Column(name="USERID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getUserId() {
		return userId;
	}


	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Column(name = "FIRSTNAME")
	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name = "LASTNAME")
	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Column(name = "MIDDLENAME")
	public String getMiddleName() {
		return middleName;
	}


	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	@Column(name = "GENDER")
	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}

	@Column(name = "USERNAME")
	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "PASSWORD")
	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "ROLEID")
	public Integer getRoleId() {
		return roleId;
	}


	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	@Column(name = "USERSTATUS")
	public String getUserStatus() {
		return userStatus;
	}


	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}

	@Column(name = "REGISTRATION_DATE")
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
	
	
	@Column(name="LAST_MODIFIED_DATE")
	public Date getLastModifieddate() {
		return lastModifieddate;
	}
	public void setLastModifieddate(Date lastModifieddate) {
		this.lastModifieddate = lastModifieddate;
	}
	@Column(name = "PHONENUM")
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	
	public String getOTP() {
		return OTP;
	}
	public void setOTP(String oTP) {
		OTP = oTP;
	}
	
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	
	
}



