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
@WebServlet("/CustomerAPI")
public class CustomerAPI extends HttpServlet {
private static final long serialVersionUID = 1L;



Customer cusObj = new Customer();




/**
 * @see HttpServlet#HttpServlet()
 */
public CustomerAPI() {
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
//Insert Customer	
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	// TODO Auto-generated method stub
	// doGet(request, response);
	
	
				String output = cusObj.insertCustomer(request.getParameter("cusName"),
				request.getParameter("cusAddress"),
				request.getParameter("cusEmail"),
				request.getParameter("cusPhone"));
				
				response.getWriter().write(output);
		

}



/**
 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
 */
//Update Customer Details
protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	// TODO Auto-generated method stub
	
	
		 Map paras = getParasMap(request);
		 String output = cusObj.updateCustomer(paras.get("hidCustomerIDSave").toString(),
				 paras.get("cusName").toString(),
				 paras.get("cusAddress").toString(),
				 paras.get("cusEmail").toString(),
				 paras.get("cusPhone").toString());
		 
		 		 response.getWriter().write(output);
		

}

/**
 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
 */
//Delete Customer
protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	// TODO Auto-generated method stub
	
	
		Map paras = getParasMap(request);
		String output = cusObj.deleteCustomer(paras.get("cusID").toString());
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