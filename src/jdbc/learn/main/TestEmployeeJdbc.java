/*
 * This example show the insertion and deletion using prepared statement 
 * and retrieval of data from database 
 */
package learn.main;
import learn.dao.EmployeeDAO;
import learn.model.Employee;

public class TestEmployeeJdbc {

	public static void main(String[] args) {
		
		Employee  employee = new Employee();
		employee.setName("Shivam");
		employee.setUserName("shivam");
		employee.setPhoneNo("123123123");
		employee.setSalary(28000);
		EmployeeDAO employeeDAO = new EmployeeDAO();
		
		System.out.println("Records Fetched : "  + employeeDAO.retrieve(1).get(0).toString());

		System.out.println("No of Records Inserted : " + employeeDAO.insert(employee));

	}

}
