package common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.application.FacesMessage;

import client.JerseyClient;
import entities.Employee;


@ManagedBean(name = "empAdd")
@SessionScoped
public class AddemployeeBean implements Serializable {
	private static final long serialVersionUID = 1L;
String name,dept,address;
int pkey,age;
public List<Employee> result=  new ArrayList<Employee>();

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getPkey() {
		return pkey;
	}
	public void setPkey(int pkey) {
		this.pkey = pkey;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	/**
	 * 
	 */


//public void save() {
//	if(JerseyClient.store_single(name, dept, age, address))
//	{
//    FacesContext.getCurrentInstance().addMessage(null,
//            new FacesMessage("Welcome " + name + "to the " + dept+ "department"));
//	}
//	else {FacesContext.getCurrentInstance().addMessage(null,
//            new FacesMessage("error while storing in database"));
//	
//	
//	}
//}

public void store_single(){
	if(JerseyClient.store_single(name, dept, age, address)){
		FacesContext.getCurrentInstance().addMessage(null,
	            new FacesMessage("Welcome " + name + "to the " + dept+ "department"));
	}
	else {FacesContext.getCurrentInstance().addMessage(null,
            new FacesMessage("error while storing in database"));}
}
public void update_single(){
	if(JerseyClient.update_single(pkey, dept)){
		
		FacesContext.getCurrentInstance().addMessage(null,
	            new FacesMessage("employee with id: " + pkey + "updated"));
		
		
	}
	else {FacesContext.getCurrentInstance().addMessage(null,
            new FacesMessage("error while updating"));}
	
	
}

public void display_all() {

this.result= JerseyClient.getAll();



}
public List<Employee> getResult() {
return result;
}






}
