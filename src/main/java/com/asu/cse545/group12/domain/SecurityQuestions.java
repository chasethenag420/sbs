package com.asu.cse545.group12.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;


//import org.hibernate.annotations.Table;


@Entity(name="securityquestions")
public class SecurityQuestions implements Serializable {

	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Integer id;	

	@NotNull
	@Column(name = "userid")
	private Integer userId;
	
	@Column(name = "question1")
	private String question1;
	
	@Column(name = "answer1") 
	private String answer1;
	
	@Column(name = "question2")
	private String question2;
	
	@Column(name = "answer2") 
	private String answer2;
	
	@Column(name = "question3")
	private String question3;
	
	@Column(name = "answer3") 
	private String answer3;

	@Column(name="id")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "userid")
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Column(name = "question1")
	public String getQuestion1() {
		return question1;
	}

	public void setQuestion1(String question1) {
		this.question1 = question1;
	}

	@Column(name = "answer1") 
	public String getAnswer1() {
		return answer1;
	}

	public void setAnswer1(String answer1) {
		this.answer1 = answer1;
	}

	@Column(name = "question2")
	public String getQuestion2() {
		return question2;
	}

	public void setQuestion2(String question2) {
		this.question2 = question2;
	}

	@Column(name = "answer2") 
	public String getAnswer2() {
		return answer2;
	}

	public void setAnswer2(String answer2) {
		this.answer2 = answer2;
	}

	@Column(name = "question3")
	public String getQuestion3() {
		return question3;
	}

	public void setQuestion3(String question3) {
		this.question3 = question3;
	}

	@Column(name = "answer3") 
	public String getAnswer3() {
		return answer3;
	}

	public void setAnswer3(String answer3) {
		this.answer3 = answer3;
	}

	@Override
	public String toString() {
		return "SecurityQuestions [id=" + id + ", userId=" + userId + ", question1=" + question1 + ", answer1="
				+ answer1 + ", question2=" + question2 + ", answer2=" + answer2 + ", question3=" + question3
				+ ", answer3=" + answer3 + "]";
	}
	
	

}



