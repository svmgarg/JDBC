package learn.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import learn.model.Employee;
import learn.util.DbUtils;

public class EmployeeDAO {
	private Connection con = null;

	private Connection getConnection() {
		return this.con;
	}

	private void setConnection(Connection connection) {
		this.con = connection;
	}

	public int insert(Employee emp) {
		int result = 0;
		if (this.con == null) {
			setConnection(DbUtils.getConnection());
		}
		ResultSet rs = null;
		try {
			PreparedStatement pstmt = this.con.prepareStatement("Select max(id) as id  from Employee");
			rs = pstmt.executeQuery();
			rs.next();
			int id = rs.getInt("id")+1;
			emp.setId(id);
			pstmt = this.con.prepareStatement(
					"Insert into Employee (id , username , name , phoneno , salary) values (?, ?, ?, ?, ?)");
			System.out.println("sql query " + pstmt);
			pstmt.setInt(1, emp.getId());
			pstmt.setString(2, emp.getUserName());
			pstmt.setString(3, emp.getName());
			pstmt.setString(4, emp.getPhoneNo());
			pstmt.setInt(5, emp.getSalary());
			System.out.println("Executing sql query ....");
			result = pstmt.executeUpdate();
			System.out.println("No of row inserted in db  " + result);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
