package com.asu.cse545.group12.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="systemaccess")
public class SystemAccess{
	

	@Id
	@Column(name="ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Id;
	
	@Column(name="IPADDRESS")
	private String ipAddress;
	
	@Column(name="USERNAME")
	private String userName;
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name="ACTION")
	private String action;
	
	@Column(name="REQUESTURL")
	private String requestUrl;
	
	@Column(name="SESSIONID")
	private String sessionId;
	
	@Column(name="TIME")
	private Date time;
		
	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getRequestUrl() {
		return requestUrl;
	}

	public void setRequestUrl(String requestUrl) {
		this.requestUrl = requestUrl;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "SystemAccess [Id=" + Id + ", ipAddress=" + ipAddress + ", userName=" + userName + ", action=" + action
				+ ", requestUrl=" + requestUrl + ", sessionId=" + sessionId + ", time=" + time + "]";
	}
	
	
}