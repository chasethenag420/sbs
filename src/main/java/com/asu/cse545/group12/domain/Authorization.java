package com.asu.cse545.group12.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity(name="authorization")
public class Authorization{
	
	@Override
	public String toString() {
		return "Authorization [authorizationId=" + authorizationId + ", authorizedByUserId=" + authorizedByUserId
				+ ", authorizedToUserId=" + authorizedToUserId + ", transactionId=" + transactionId
				+ ", requestCreationTimeStamp=" + requestCreationTimeStamp + ", requestType=" + requestType
				+ ", requestStatus=" + requestStatus + ", assignedToRole=" + assignedToRole + ", requestDescription="
				+ requestDescription + "]";
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="AUTHORIZATIONID")
	private int authorizationId;
	
	@Column(name="AUTHORIZED_BY_USERID")
	private int authorizedByUserId;
	
	@Column(name="AUTHORIZED_TO_USERID")
	private int authorizedToUserId;
	
	@Column(name="TRANSACTION_ID")
	private int transactionId;
	
	@Column(name="REQUEST_CREATION_TS")
	private Date requestCreationTimeStamp;
	
	@Column(name="REQUEST_TYPE")
	private String requestType;
	
	@Column(name="REQUEST_STATUS")
	private String requestStatus;
	
	@Column(name="ASSIGNED_TO_ROLE")
	private String assignedToRole;
	
	@Column(name="REQUEST_DESCRIPTION")
	private String requestDescription;
	
	public int getAuthorizationId() {
		return authorizationId;
	}
	public void setAuthorizationId(int authorizationId) {
		this.authorizationId = authorizationId;
	}
	public int getAuthorizedByUserId() {
		return authorizedByUserId;
	}
	public void setAuthorizedByUserId(int authorizedByUserId) {
		this.authorizedByUserId = authorizedByUserId;
	}
	public int getAuthorizedToUserId() {
		return authorizedToUserId;
	}
	public void setAuthorizedToUserId(int authorizedToUserId) {
		this.authorizedToUserId = authorizedToUserId;
	}
	public int getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}
	public Date getRequestCreationTimeStamp() {
		return requestCreationTimeStamp;
	}
	public void setRequestCreationTimeStamp(Date requestCreationTimeStamp) {
		this.requestCreationTimeStamp = requestCreationTimeStamp;
	}
	public String getRequestType() {
		return requestType;
	}
	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}
	public String getRequestStatus() {
		return requestStatus;
	}
	public void setRequestStatus(String requestStatus) {
		this.requestStatus = requestStatus;
	}

	public String getAssignedToRole() {
		return assignedToRole;
	}
	public void setAssignedToRole(String assignedToRole) {
		this.assignedToRole = assignedToRole;
	}
	public String getRequestDescription() {
		return requestDescription;
	}
	public void setRequestDescription(String requestDescription) {
		this.requestDescription = requestDescription;
	}
	
}