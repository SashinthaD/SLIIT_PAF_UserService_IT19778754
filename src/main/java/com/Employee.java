/**
 * @author S.H.B Dasanayake
 *         IT19778754
 * 
 */

package com;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

public class Employee
    {
	//Validate Patterns
		String regexname="^[a-zA-Z +(),.-]+$";
		String regex = "^(.+)@(.+)$";
		String regex2 =  "[0-9]{10}";
		String regex3="^[0-9]{9}[vVxX]$";
		
	//A common method to connect to the DB
	private Connection connect()
				 {
						 Connection con = null;
						 try
						 {
						 Class.forName("com.mysql.jdbc.Driver");
						
						 //Provide the correct details: DBServer/DBName, username, password
						 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/ electrogrid", "paf", "12345678");
						 }
						 catch (Exception e)
						 {e.printStackTrace();}
						 return con;
						 }
	//Employee Insert
	public String insertEmployee(String employeeName, String employeeEmail, String empAge,  String phone, String nic)
				 {
				 String output = "";
						 try
						 {
						 Connection con = connect();
						 if (con == null)
						 {return "Error while connecting to the database for inserting."; }
						 // create a prepared statement
						 String query = "insert into employees (`employeeNumber`,`employeeName`,`employeeEmail`,`empAge`,`phone`,`nic`)"
						 		+ "values (?, ?, ?, ?, ?,?)";
						 PreparedStatement preparedStmt = con.prepareStatement(query);
						 
						 // binding values
						 
						 	preparedStmt.setInt(1, 0);
							preparedStmt.setString(2, employeeName);
							preparedStmt.setString(3, employeeEmail);
							preparedStmt.setInt(4, Integer.parseInt(empAge));
							preparedStmt.setInt(5, Integer.parseInt(phone));
							preparedStmt.setString(6, nic);
						
							int i=Integer.parseInt(empAge); 
							
						//Validate Name
							 if(!(employeeName.matches(regexname))) {

								output = "Please Enter Your Name !";
							}	
					
						//Validate Email
							 else if(!(employeeEmail.matches(regex))) {

								output = "Invalid e-mail address !";
							}
							
						//validate Age
							else if(i < 18) {
								output = "Employee Should Overthan 18 Years Old !";
								}
							
						//Validate Phone Number
							else if(!(phone.matches(regex2))) {

								output = "Invalid Phone Number !";
							}
							
						//Validate Nic Number	
							else if(!(nic.matches(regex3))) {

								output = "Invalid NIC Number !";
							}
							
						 // execute the statement
						 
							else {preparedStmt.execute();
						 con.close();
						 String newEmployees = readEmployees();
						 output = "{\"status\":\"success\", \"data\": \"" +
								 newEmployees + "\"}";
						 }}
						 catch (Exception e)
						 {
							 output = "{\"status\":\"error\", \"data\":\"Error while inserting the Employee.\"}";
						 System.err.println(e.getMessage());
						 }
						 return output;
		 }
	//View Employee Details
	public String readEmployees()
						 {
						 String output = "";
						 try
						 {
						 Connection con = connect();
						 if (con == null)
						 {return "Error while connecting to the database for reading."; }
						 
						 	// Prepare the html table to be displayed
						 
							 output = "<table border=\'1\'><tr><th>Employee Name</th><th>Email</th>" +
										 "<th>Employee Age</th>" +
										 "<th>Phone Number</th>" +
										 "<th>NIC</th>" +
										 "<th>Update</th><th>Delete</th></tr>";
							
							 String query = "select * from employees";
							 Statement stmt = con.createStatement();
							 ResultSet rs = stmt.executeQuery(query);
						 
						 // iterate through the rows in the result set
						 while (rs.next())
						 {
							 String employeeNumber = Integer.toString(rs.getInt("employeeNumber"));
							 String employeeName = rs.getString("employeeName");
							 String employeeEmail = rs.getString("employeeEmail");
							 String empAge = Integer.toString(rs.getInt("empAge"));
							 String phone = Integer.toString(rs.getInt("phone"));
							 String nic = rs.getString("nic");
							 
							 // Add into the html table
							 output += "<tr><td><input id=\'hidEmployeeIDUpdate\' name=\'hidEmployeeIDUpdate\' type=\'hidden\' value=\'"+ employeeNumber + "'>" + employeeName + "</td>";
							 output += "<td>" + employeeEmail + "</td>";
							 output += "<td>" + empAge + "</td>";
							 output += "<td>" + phone + "</td>";
							 output += "<td>" + nic + "</td>";
							 
							 // buttons
							 output += "<td><input name='btnUpdate' type='button' value='Update' "
									 + "class='btnUpdate btn btn-secondary' data-employeenumber='" + employeeNumber + "'></td>"
									 + "<td><input name='btnRemove' type='button' value='Remove' "
									 + "class='btnRemove btn btn-danger' data-employeenumber='" + employeeNumber + "'></td></tr>";
						 }
							 con.close();
							 // Complete the html table
							 output += "</table>";
						 }
						 catch (Exception e)
						 {
							 output = "Error while reading the Employee.";
							 System.err.println(e.getMessage());
						 }
						 return output;
						 }
    //Employee Details Update
	public String updateEmployee(String employeeNumber, String employeeName, String employeeEmail, String empAge,  String phone, String nic)

						{
						String output = "";
						try
						{
						Connection con = connect();
						if (con == null)
						{return "Error while connecting to the database for updating."; }
							// create a prepared statement
							String query = "UPDATE employees SET employeeName=?,employeeEmail=?,empAge=?,phone=?,nic=? WHERE employeeNumber=?";
							PreparedStatement preparedStmt = con.prepareStatement(query);
							// binding values
							preparedStmt.setString(1, employeeName);
							preparedStmt.setString(2, employeeEmail);
							preparedStmt.setInt(3, Integer.parseInt(empAge));
							preparedStmt.setInt(4, Integer.parseInt(phone));
							preparedStmt.setString(5, nic);
							preparedStmt.setInt(6, Integer.parseInt(employeeNumber));
							
							
							
							// execute the statement
							preparedStmt.execute();
							con.close();
							String newEmployees = readEmployees();
							output = "{\"status\":\"success\", \"data\": \"" +newEmployees + "\"}"; 
						}
						catch (Exception e)
						{
							output = "{\"status\":\"error\", \"data\":\"Error while updating the Employee.\"}";
							System.err.println(e.getMessage());
						}
						return output;
						}
	//Employee Details Delete
	public String deleteEmployee(String employeeNumber)
						 {
						 String output = "";
						 try
						 {
						 Connection con = connect();
						 if (con == null)
						 {return "Error while connecting to the database for deleting."; }
							 // create a prepared statement
							 String query = "delete from employees where employeeNumber=?";
							 PreparedStatement preparedStmt = con.prepareStatement(query);
							 // binding values
							 preparedStmt.setInt(1, Integer.parseInt(employeeNumber));
							 // execute the statement
							 preparedStmt.execute();
							 con.close();
							 String newEmployees = readEmployees();
								output = "{\"status\":\"success\", \"data\": \"" +newEmployees + "\"}"; 
						 }
						 catch (Exception e)
						 {
							 output = "{\"status\":\"error\", \"data\":\"Error while Delete the Employee.\"}";
							 System.err.println(e.getMessage());
						 }
						 return output;
						 }
	
	//Search profile Details
	public String viewProfile() {


		 {
			 String output = "";
			 try
			 {
			 Connection con = connect();
			 if (con == null)
			 {return "Error while connecting to the database for reading."; }
			 
			 	// Prepare the html table to be displayed
			 
				 output = "<table border='1'><tr><th>Employee Name</th><th>Email</th>" +
							 "<th>Employee Age</th>" +
							 "<th>Phone Number</th>" +
							 "<th>NIC</th></tr>";
				
				 String query = "select * from employees";
				 Statement stmt = con.createStatement();
				 ResultSet rs = stmt.executeQuery(query);
			 
			 // iterate through the rows in the result set
			 while (rs.next())
			 {
				 String employeeNumber = Integer.toString(rs.getInt("employeeNumber"));
				 String employeeName = rs.getString("employeeName");
				 String employeeEmail = rs.getString("employeeEmail");
				 String empAge = Integer.toString(rs.getInt("empAge"));
				 String phone = Integer.toString(rs.getInt("phone"));
				 String nic = rs.getString("nic");
				 
				 // Add into the html table
				 output += "<tr><td>" + employeeName + "</td>";
				 output += "<td>" + employeeEmail + "</td>";
				 output += "<td>" + empAge + "</td>";
				 output += "<td>" + phone + "</td>";
				 output += "<td>" + nic + "</td>";
				 
				 
			 }
				 con.close();
				 // Complete the html table
				 output += "</table>";
			 }
			 catch (Exception e)
			 {
				 output = "Error while reading the Employee.";
				 System.err.println(e.getMessage());
			 }
			 return output;
			 }
	}
}