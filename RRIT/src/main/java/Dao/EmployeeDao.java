package Dao;

import java.io.IOException;
import java.sql.*;
import Bean.Employee;

public class EmployeeDao {

	
	/*	Setup Database Connection*/
	public static Connection setConnection()
	{
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");		 
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/db?useSSL=false","root","root");  
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return con;
	}
	
	/*	Check For Existing Employee*/
	public static int checkExisting(int id)
	{
		try {
			Connection con=setConnection();
			PreparedStatement prepared = con.prepareStatement("select emp_name from Employee_Details where emp_id = ?");
			prepared.setInt(1,id);
			ResultSet rows = prepared.executeQuery();
			if(rows.next())
			{
				return  1;
			}
			else
			{
				return 0;
			}
		}
			 catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return 0;
	}
	
	
	/*	Employee Login*/
	public static String loginUser(Employee emp)
	{
		String message="";
		try {
			Connection con=setConnection();
			int check = checkExisting(emp.getId());
			if(check==1)
			{
				PreparedStatement prepared = con.prepareStatement("select emp_name,emp_pass,emp_access from Employee_Details where binary emp_id = ?  and binary emp_pass = ? ");
				prepared.setInt(1, emp.getId());
				prepared.setString(2,emp.getPassword());
				ResultSet rows = prepared.executeQuery();
				if(rows.next())
				{
					//System.out.println("Access : "+rows.getInt("emp_access"));
					
					
					message= rows.getString("emp_name");
					int access = rows.getInt("emp_access");
					message+=";"+String.valueOf(access);
				}
				else
				{
					message = "Invalid Password";
				}
			}
			else
			{
				message="No User associated with this ID.";
			}
			con.close();
			}
			 catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return message;
		}
	
	
	/*	Register Employee Details*/
	public static String save(Employee emp) 
	{
		String message="";
		try {
			Connection con=setConnection();
			int check = checkExisting(emp.getId());
			
			if(check==1)
			{
				message="User Exists";
				return message;
			}
			PreparedStatement prepared = con.prepareStatement("insert into Employee_Details (emp_id,emp_name,emp_pass) values(?,?,?);");
			prepared.setInt(1,emp.getId());
			prepared.setString(2, emp.getName());
			prepared.setString(3, emp.getPassword());
			
			if(!emp.getPassword().equals(emp.getCpassword()))
			{
				message="Password doesn't match";
			}
			else
			{
				prepared.executeUpdate();
				message = "success";
			}
			con.close();  
			}
			 catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return message;
		}
	
	
	/*	Update Employee Access*/
	public static String updateAccess(Employee emp,String name) throws IOException, SQLException
	{
		String message="";
		Connection con=setConnection();
		PreparedStatement prepared = con.prepareStatement("update employee_details set emp_access=? where emp_id=?");
		int granted=0;
		if(emp.getMyAccess()!=null)
			{
				 granted=1;
				 message="Access Granted to Employee with ID : "+name;
			}
			else
			{
				message="Access Revoked from Employee with ID : "+name;
				granted=0;			
			}
			prepared.setInt(1,granted);
			prepared.setInt(2, Integer.valueOf(name));
			prepared.executeUpdate();
		return message;
	}
}
