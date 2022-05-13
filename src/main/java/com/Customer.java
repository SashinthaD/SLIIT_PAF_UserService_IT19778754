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

public class Customer
		{
	
	//Validate Patterns
		String regexname="^[a-zA-Z +(),.-]+$";
		String regexaddr="^[a-zA-Z0-9+(),. -]+$";
		String regex = "^(.+)@(.+)$";
		String regex2 =  "[0-9]{10}";
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
	// Insert Customer
	public String insertCustomer(String cusName, String cusAddress, String cusEmail,  String cusPhone)
					 {
						 String output = "";
						 
						 /*String regex = "^(.+)@(.+)$";
						 String regex2 =  "[0-9]{10}";*/
						 try
						 {
						 Connection con = connect();
						 if (con == null)
						 {return "Error while connecting to the database for inserting."; }
						 // create a prepared statement
						 String query = "insert into customer (`cusID`,`cusName`,`cusAddress`,`cusEmail`,`cusPhone`)"
						 		+ "values (?, ?, ?, ?, ?)";
						 PreparedStatement preparedStmt = con.prepareStatement(query);
					 
					 // binding values
					 
					 	preparedStmt.setInt(1, 0);
						preparedStmt.setString(2, cusName);
						preparedStmt.setString(3, cusAddress);
						preparedStmt.setString(4, cusEmail);
						preparedStmt.setInt(5, Integer.parseInt(cusPhone));
						
					//Validate Name
						 if(!(cusName.matches(regexname))) {

							output = "Please Enter Your Name !";
						}
						 
					//Validate Addrees
						else if(!(cusAddress.matches(regexaddr))) {

							output = "Please Enter Customer address !";
						}
						 
					//Validate Email Addrees
						else if(!(cusEmail.matches(regex))) {

							output = "Invalid e-mail address !";
						}
					//Validate Phone Number
						else if(!(cusPhone.matches(regex2))) {

							output = "Invalid Phone Number !";
						}
					 // execute the statement
					 
						else { preparedStmt.execute();
						 con.close();
						 String newCustomers = readCustomers();
						 output = "{\"status\":\"success\", \"data\": \"" +
						 newCustomers + "\"}";
						}}
						 catch (Exception e)
						 {
						 output = "{\"status\":\"error\", \"data\":\"Error while inserting the Customer.\"}";
						 System.err.println(e.getMessage());
					 }
						 return output;
						 }
	// View Customer Details
	public String readCustomers()
						 {
						 String output = "";
							 try
							 {
							 Connection con = connect();
							 if (con == null)
							 {return "Error while connecting to the database for reading."; }
 
				 // Prepare the html table to be displayed
				 
				 output = "<table border=\'1\'><tr><th>Customer Name</th><th>Customer Address</th>" +
							 "<th>Customer Email</th>" +
							 "<th>Phone Number</th>" +
							 "<th>Update</th><th>Delete</th></tr>";
				
				 String query = "select * from customer";
				 Statement stmt = con.createStatement();
				 ResultSet rs = stmt.executeQuery(query);
				 
					 // iterate through the rows in the result set
					 while (rs.next())
						 {
						 String cusID = Integer.toString(rs.getInt("cusID"));
						 String cusName = rs.getString("cusName");
						 String cusAddress = rs.getString("cusAddress");
						 String cusEmail = rs.getString("cusEmail");
						 String cusPhone = Integer.toString(rs.getInt("cusPhone"));
						 
						 
						 // Add into the html table
						 output += "<tr><td><input id=\'hidCustomerIDUpdate\' name=\'hidCustomerIDUpdate\' type=\'hidden\' value=\'"+ cusID + "'>" + cusName + "</td>";
						 output += "<td>" + cusAddress + "</td>";
						 output += "<td>" + cusEmail + "</td>";
						 output += "<td>" + cusPhone + "</td>";
		 
		 
						 // buttons
						 output += "<td><input name='btnUpdate' type='button' value='Update' "
								 + "class='btnUpdate btn btn-secondary' data-cusid='" + cusID + "'></td>"
								 + "<td><input name='btnRemove' type='button' value='Remove' "
								 + "class='btnRemove btn btn-danger' data-cusid='" + cusID + "'></td></tr>";
					 }
					 con.close();
					 // Complete the html table
					 output += "</table>";
					 }
						 catch (Exception e)
						 {
						 output = "Error while reading the Customer.";
						 System.err.println(e.getMessage());
						 }
						 return output;
				 }
	// Update Customer Details
	public String updateCustomer(String cusID, String cusName, String cusAddress, String cusEmail,  String cusPhone)

				{
				String output = "";
				
					try
					{
					Connection con = connect();
						if (con == null)
							{return "Error while connecting to the database for updating."; }
							// create a prepared statement
							String query = "UPDATE customer SET cusName=?,cusAddress=?,cusEmail=?,cusPhone=? WHERE cusID=?";
							PreparedStatement preparedStmt = con.prepareStatement(query);
							// binding values
							
							preparedStmt.setString(1, cusName);
							preparedStmt.setString(2, cusAddress);
							preparedStmt.setString(3, cusEmail);
							preparedStmt.setInt(4, Integer.parseInt(cusPhone));
							preparedStmt.setInt(5, Integer.parseInt(cusID));
							

							preparedStmt.execute();
							con.close();
							String newCustomers = readCustomers();
							output = "{\"status\":\"success\", \"data\": \"" +newCustomers + "\"}"; 
							}
							catch (Exception e)
							{
								
							output = "{\"status\":\"error\", \"data\":\"Error while updating the Customer.\"}";
							System.err.println(e.getMessage());
						}
					return output;
				}

	// Delete Customer Details
	public String deleteCustomer(String cusID)
				 {
						 String output = "";
						 try
						 {
						 Connection con = connect();
							 if (con == null)
							 {return "Error while connecting to the database for deleting."; }
							 // create a prepared statement
							 String query = "delete from customer where cusID=?";
							 PreparedStatement preparedStmt = con.prepareStatement(query);
							 // binding values
							 preparedStmt.setInt(1, Integer.parseInt(cusID));
							 // execute the statement
							 preparedStmt.execute();
							 con.close();
							 String newCustomers = readCustomers();
								output = "{\"status\":\"success\", \"data\": \"" +
										newCustomers + "\"}"; 
							 }
							 catch (Exception e)
						 {
						 output = "{\"status\":\"error\", \"data\":\"Error while deleting the Customer.\"}";
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
	 
	 output = "<table border='1'><tr><th>Customer Name</th><th>Customer Address</th>" +
				 "<th>Customer Email</th>" +
				 "<th>Phone Number</th></tr>";
	
	 String query = "select * from customer";
	 Statement stmt = con.createStatement();
	 ResultSet rs = stmt.executeQuery(query);
	 
		 // iterate through the rows in the result set
		 while (rs.next())
			 {
			 String cusID = Integer.toString(rs.getInt("cusID"));
			 String cusName = rs.getString("cusName");
			 String cusAddress = rs.getString("cusAddress");
			 String cusEmail = rs.getString("cusEmail");
			 String cusPhone = Integer.toString(rs.getInt("cusPhone"));
			 
			 
			 // Add into the html table
			 output += "<tr><td>" + cusName + "</td>";
			 output += "<td>" + cusAddress + "</td>";
			 output += "<td>" + cusEmail + "</td>";
			 output += "<td>" + cusPhone + "</td>";


			 
		 }
		 con.close();
		 // Complete the html table
		 output += "</table>";
		 }
			 catch (Exception e)
			 {
			 output = "Error while reading the Customer.";
			 System.err.println(e.getMessage());
			 }
			 return output;
	 }
	}	
}
