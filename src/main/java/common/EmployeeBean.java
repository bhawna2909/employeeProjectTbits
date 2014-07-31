package common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManagerFactory;

import client.JerseyClientPost;
import DAO.EmployeeDAO;



import entities.Employee;

@ManagedBean(name = "emp")
@SessionScoped
public class EmployeeBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public List<Employee> result=  new ArrayList<Employee>();

	
	public void getAll() {
		
		this.result= JerseyClientPost.getAll();
		
		
		
	}
	/*
	 * public void setElementlist(){ result=new ArrayList<Employee>(); }
	 */


	public List<Employee> getResult() {
		return result;
	}

	

}
