package model;

import java.sql.*;

public class Bill {
	
	//Database connection
	private Connection connect() {
		Connection con = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			//connection details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/electrogriddb", "root", "");
		}
		catch(Exception e) {
		 e.printStackTrace();
		}
		
		return con;
	}
	
	//Read Bills
	public String readBills() {
		String output = "";
		
		try {
			Connection con = connect();
			
			if(con == null) {
				return("Error while connecting to the database for reading.");
			}
			
			//Prepare HTML table to be shown
			output = "<table border='1'>"
					+ "<tr>"
					+ "<th>Bill ID</th>"
					+ "<th>Billing Date</th>"
					+ "<th>Unit Cost</th>" 
					+ "<th>Units Used</th>"
					+ "<th>Service Charge</th>"
					+ "<th>Total Cost</th>"
					+ "<th>Bill Settled</th>"
					+ "<th>User ID</th>"
					+ "<th>Update</th><th>Remove</th></tr>";
			
			String query = "select * from bills";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			//loop through resultset rows
			while(rs.next()) {
				String billID = Integer.toString(rs.getInt("billID"));
				Date billDate = rs.getDate("billDate");
				String unitCost = Integer.toString(rs.getInt("unitCost"));
				String unitsUsed = Integer.toString(rs.getInt("unitsUsed"));
				String serviceCharge = Integer.toString(rs.getInt("serviceCharge"));
				String totalCost = Integer.toString(rs.getInt("totalCost"));
				String settled = Boolean.toString(rs.getBoolean("settled"));
				String userID = Integer.toString(rs.getInt("userID"));
				
				//add to output html table
				output += "<tr><td>" + billID + "</td>"
						+  "<td>" + billDate + "</td>"
						+  "<td>" + unitCost + "</td>"
						+  "<td>" + unitsUsed + "</td>"
						+  "<td>" + serviceCharge + "</td>"
						+  "<td>" + totalCost + "</td>"
						+  "<td>" + settled + "</td>"
						+  "<td>" + userID + "</td>";
				
				//buttons
				output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"
						+ "<td><form method='post' action='items.jsp'>"
						+ "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
						+ "<input name='itemID' type='hidden' value='" + billID
						+ "'>" + "</form></td></tr>";			
			}
		
			con.close();

			// Complete the html table
			output += "</table>";
			
		}
		catch(Exception e) {
			output = "Error while reading the items.";
			System.err.println(e.getMessage());
		}
		
		return output;
	}

}
