package com.asu.cse545.group12.services;

import java.util.List;

import com.asu.cse545.group12.domain.Employee;

public interface DataService {
	public int insertRow(Employee employee);

	public List<Employee> getList();

	public Employee getRowById(int id);

	public int updateRow(Employee employee);

	public int deleteRow(int id);

}
