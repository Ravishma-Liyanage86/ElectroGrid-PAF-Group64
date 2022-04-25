package model;

import java.sql.*;

public class Customer {

	// A common method to connect to the DB
	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/electrogrid", "root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public String insertCustomer(String name, String nic, String email, String phone, String address) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = " insert into customer (`customerId`,`customerName`,`customerNic`,`customerEmail`,`customerPhone`, `customerAddress`)"
					+ " values (?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, name);
			preparedStmt.setString(3, nic);
			preparedStmt.setString(4, email);
			preparedStmt.setString(5, phone);
			preparedStmt.setString(6, address);

			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
		} catch (Exception e) {
			output = "Error while inserting the item.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String readCustomer() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table border='1'><tr>" + "<th>Customer ID</th>" + "<th>Name</th>" + "<th>NIC</th>"
					+ "<th>Email</th>" + "<th>Phone</th>" + "<th>Address</th>" + "<th>Update</th><th>Remove</th></tr>";

			String query = "select * from customer";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				String customerId = Integer.toString(rs.getInt("customerId"));
				String customerName = rs.getString("customerName");
				String customerNic = rs.getString("customerNic");
				String customerEmail = rs.getString("customerEmail");
				String customerPhone = rs.getString("customerPhone");
				String customerAddress = rs.getString("customerAddress");

				// Add into the html table
				output += "<tr><td>" + customerId + "</td>";
				output += "<td>" + customerName + "</td>";
				output += "<td>" + customerNic + "</td>";
				output += "<td>" + customerEmail + "</td>";
				output += "<td>" + customerPhone + "</td>";
				output += "<td>" + customerAddress + "</td>"; ///////// items.jsp
				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"
						+ "<td><form method='post' action='items.jsp'>"
						+ "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
						+ "<input name='customerId' type='hidden' value='" + customerId + "'>" + "</form></td></tr>";
			}
			con.close();
			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the customers.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String updateCustomer(String ID, String name, String nic, String email, String phone, String address) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
			// create a prepared statement
			String query = "UPDATE customer SET customerName=?,customerNic=?,customerEmail=?,customerPhone=?, customerAddress=? WHERE customerId=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setString(1, name);
			preparedStmt.setString(2, nic);
			preparedStmt.setString(3, email);
			preparedStmt.setString(4, phone);
			preparedStmt.setString(5, address);
			preparedStmt.setInt(6, Integer.parseInt(ID));
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Updated successfully";
		} catch (Exception e) {
			output = "Error while updating the customer.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String deleteCustomer(String customerId) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}
			// create a prepared statement
			String query = "delete from customer where customerId=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(customerId));
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Deleted successfully";
		} catch (Exception e) {
			output = "Error while deleting the item.";
			System.err.println(e.getMessage());
		}
		return output;
	}

}
