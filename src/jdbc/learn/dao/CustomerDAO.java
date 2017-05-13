package learn.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import learn.model.Address;
import learn.model.Customer;
import learn.model.Employee;
import learn.util.DbUtils;

public class CustomerDAO {
	private Connection con = null;

	private Connection getConnection() {
		return this.con;
	}

	private void setConnection(Connection connection) {
		this.con = connection;
	}

	public int insert(Customer customer) {
		int result = 0;
		if (this.con == null) {
			setConnection(DbUtils.getConnection());
		}
		ResultSet rs = null;
		try {
			this.con.setAutoCommit(false);
			PreparedStatement pstmt = this.con.prepareStatement("Select max(customer_id) as id  from Customer");
			rs = pstmt.executeQuery();
			rs.next();
			int id = rs.getInt("id") + 1;
			customer.setCustomerid(id);
			pstmt = this.con.prepareStatement("Insert into Customer(customer_id , name , phoneno ) values (?, ?, ?)");

			System.out.println("Customer Insertion start....." + pstmt);
			pstmt.setInt(1, customer.getCustomerid());
			pstmt.setString(2, customer.getName());
			pstmt.setString(3, customer.getPhoneNo());

			result = pstmt.executeUpdate();
			System.out.println("No of row Customer Inserted in db  " + result);
			pstmt = this.con.prepareStatement("Select max(address_id) as id  from Address");
			rs = pstmt.executeQuery();

			rs.next();
			id = rs.getInt("id");
			System.out.println("Address Insertion start....." +id);
			String sql = "Insert into Address(address_id  , customer_id , street , city , state , country) values (?, ?, ?,?,?,?)";
			pstmt = this.con.prepareStatement(sql);
			for (Address address : customer.getAddressList()) {
				pstmt.setInt(1, ++id);
				pstmt.setInt(2, customer.getCustomerid());
				pstmt.setString(3, address.getStreet());
				pstmt.setString(4, address.getCity());
				pstmt.setString(5, address.getState());
				pstmt.setString(6, address.getCountry());
				pstmt.addBatch();
			}
			pstmt.executeBatch();

			this.con.commit();
		} catch (SQLException e) {

			try {
				System.out.println("Rollbacking...");
				this.con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}

		return result;
	}

	public List<Employee> retrieve(int id) {
		ResultSet rs = null;
		ArrayList<Employee> employeeList = new ArrayList<Employee>();
		Employee emp = null;
		if (this.con == null) {
			setConnection(DbUtils.getConnection());
		}
		int i = 0;
		try {
			PreparedStatement pstmt = this.con.prepareStatement("Select * from Employee where id = ?");
			System.out.println("sql query " + pstmt);
			pstmt.setInt(1, id);
			System.out.println("Executing sql query ....");
			rs = pstmt.executeQuery();
			System.out.println("fetched Records :");
			while (rs.next()) {
				emp = new Employee();
				emp.setName(rs.getString("name"));
				emp.setId(rs.getInt("id"));
				emp.setUserName(rs.getString("username"));
				emp.setPhoneNo(rs.getString("phoneno"));
				emp.setSalary(rs.getInt("salary"));
				System.out.println(emp.toString());
				employeeList.add(emp);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Exception while fetching");
			e.printStackTrace();
		}

		return employeeList;
	}

}
