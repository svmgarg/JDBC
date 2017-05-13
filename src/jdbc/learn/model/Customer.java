package learn.model;

import java.util.ArrayList;

public class Customer {

	private int customerid;
	private String name;
	private String phoneNo;
	private ArrayList<Address> addressList;

	public int getCustomerid() {
		return customerid;
	}

	public void setCustomerid(int customerid) {
		this.customerid = customerid;
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

	public ArrayList<Address> getAddressList() {
		return addressList;
	}

	public void setAddressList(ArrayList<Address> addressList) {
		this.addressList = addressList;
	}


	@Override
	public String toString() {
		return "Customer [customerid=" + customerid + ", name=" + name + ", phoneNo=" + phoneNo + ", addressList="
				+ addressList + "]";
	}}
