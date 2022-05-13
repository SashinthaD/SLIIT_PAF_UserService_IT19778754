package com;

import java.io.IOException;


import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
* Servlet implementation class CustomerAPI
*/
@WebServlet("/EmployeeAPI")
public class EmployeeAPI extends HttpServlet {
private static final long serialVersionUID = 1L;



Employee empObj = new Employee();




/**
 * @see HttpServlet#HttpServlet()
 */
public EmployeeAPI() {
    super();
    // TODO Auto-generated constructor stub
}

/**
 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
 */
//View Customer Details

protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	// TODO Auto-generated method stub
	response.getWriter().append("Served at: ").append(request.getContextPath());
}

/**
 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
 */
//Insert Employee	
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	// TODO Auto-generated method stub
	// doGet(request, response);
	
	
				String output = empObj.insertEmployee(request.getParameter("employeeName"),
				request.getParameter("employeeEmail"),
				request.getParameter("empAge"),
				request.getParameter("phone"),
				request.getParameter("nic"));
				
				response.getWriter().write(output);
		

}



/**
 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
 */
//Update Employee Details
protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	// TODO Auto-generated method stub
	
	
		 Map paras = getParasMap(request);
		 String output = empObj.updateEmployee(paras.get("hidEmployeeIDSave").toString(),
				 paras.get("employeeName").toString(),
				 paras.get("employeeEmail").toString(),
				 paras.get("empAge").toString(),
				 paras.get("phone").toString(),
				 paras.get("nic").toString());
		 
		 		 response.getWriter().write(output);
		

}

/**
 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
 */
//Delete Employee
protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	// TODO Auto-generated method stub
	
	
		Map paras = getParasMap(request);
		String output = empObj.deleteEmployee(paras.get("employeeNumber").toString());
		response.getWriter().write(output);
		
}




// Convert request parameters to a Map
	private static Map getParasMap(HttpServletRequest request) {
	Map<String, String> map = new HashMap<String, String>();
	try {
	Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
	String queryString = scanner.hasNext() ? scanner.useDelimiter("\\A").next() : "";
	scanner.close();
	
	
	
	String[] params = queryString.split("&");
	for (String param : params) {
	
	
	
	String[] p = param.split("=");
	map.put(p[0], p[1]);
	}
	} catch (Exception e) {
	
	
	}
	return map;
	
	
	
	}
	}