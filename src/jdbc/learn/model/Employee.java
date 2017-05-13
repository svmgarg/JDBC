package learn.model;

import java.util.ArrayList;

public class Employee {

	private int id;
	private String  userName;
	private String name;
	private String phoneNo;
	private int Salary;
	private ArrayList<Address> addressList;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public int getSalary() {
		return Salary;
	}
	public void setSalary(int salary) {
		Salary = salary;
	}
	
	public ArrayList<Address> getAddressList() {
		return addressList;
	}
	public void setAddressList(ArrayList<Address> addressList) {
		this.addressList = addressList;
	}
	@Override
	public String toString() {
		return "Employee [id=" + id + ", userName=" + userName + ", name=" + name + ", phoneNo=" + phoneNo + ", Salary="
				+ Salary + ", addressList=" + addressList + "]";
	}
	
	
	
}
