package entities;

import java.io.Serializable;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(table = "Employee", detachable = "true")
public class Employee implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@PrimaryKey
	@Persistent
	private int pkey;

	@Persistent(column = "name")
	@Column(allowsNull = "false", jdbcType = "varchar")
	private String name;

	@Persistent(column = "dept")
	@Column(allowsNull = "false", jdbcType = "varchar")
	private String dept;

	@Persistent(column = "age")
	@Column(allowsNull = "false", jdbcType = "int")
	private int age;

	@Persistent(column = "address")
	@Column(allowsNull = "false", jdbcType = "varchar")
	private String address;

	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Employee(int pkey, String name, String dept, int age, String address) {
		super();
		this.pkey = pkey;
		this.name = name;
		this.dept = dept;
		this.age = age;
		this.address = address;
	}

	public int getPkey() {
		return pkey;
	}

	public void setPkey(int pkey) {
		this.pkey = pkey;
	}

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

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Employee [pkey=" + pkey + ", name=" + name + ", dept=" + dept
				+ ", age=" + age + ", address=" + address + "]";
	}

}