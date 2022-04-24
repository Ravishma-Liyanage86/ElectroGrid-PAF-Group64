package model;

import java.sql.*;

public class Solar {
	//Connect to the DB
		private Connection connect()
		{
			Connection con = null;
			
			try
			{
				Class.forName("com.mysql.jdbc.Driver");
				
				
				con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/electrogrid", "root", "");
			}
			catch (Exception e)
			{e.printStackTrace();}
			
			return con;
		}
		
		public String insertSolarDetails(String customerName, String customerAddress, String capacity, String noOfSolarPanels, String type)//2 change - done
		{
			String output = "";
			
			try
			{
				Connection con = connect();
				
				if(con == null)
				{return "Error while connecting to the database for inserting."; }
				
				
				//create a prepared statement
				String query = " insert into solarpower (`solarID`,`customerName`,`customerAddress`,`capacity`,`noOfSolarPanels`,`type`)"
						+ " values (?, ?, ?, ?, ?, ?)";
				
				PreparedStatement preparedStmt = con.prepareStatement(query);
				
				
				//binding values
				preparedStmt.setInt(1, 0);
				preparedStmt.setString(2, customerName); 
				preparedStmt.setString(3, customerAddress); 
				preparedStmt.setDouble(4, Double.parseDouble(capacity)); 
				preparedStmt.setInt(5, Integer.parseInt(noOfSolarPanels));
				preparedStmt.setString(6, type);
				
				//execute the statement
				preparedStmt.execute();
				con.close();
				
				output = "Inserted successfully";
			}
			catch (Exception e)
			{
				output = "Error while inserting the solar details."; 
				System.err.println(e.getMessage());
			}
			return output;
		}
		
		public String readSolarDetails()
		{
			String output = "";
			
			try
			{
				Connection con = connect();
				
				if(con == null)
				{
					return "Error while connecting to the database for reading."; 
				}
				
				
				// Prepare the html table to be displayed
				output = "<table border='1'><tr><th>Customer Name</th><th>Customer Address</th>" + 
				"<th>Capacity(kW)</th>" +
				"<th>No of Solar Panels</th>" + 
				"<th>Type</th>" + 
				"<th>Update</th><th>Remove</th></tr>";
				
			
				String query = "select * from solarpower"; 
				Statement stmt = con.createStatement(); 
				ResultSet rs = stmt.executeQuery(query);
				
				//iterate through the rows in the result set
				while (rs.next())
				{
					
					String solarID = Integer.toString(rs.getInt("solarID"));
					String customerName = rs.getString("customerName");
					String customerAddress = rs.getString("customerAddress");
					String capacity = Double.toString(rs.getDouble("capacity")); 
					String noOfSolarPanels = Integer.toString(rs.getInt("noOfSolarPanels"));
					String type = rs.getString("type");
					
					
					//Add into the html table
					output += "<tr><td>" + customerName + "</td>"; 
					output += "<td>" + customerAddress + "</td>"; 
					output += "<td>" + capacity + "</td>"; 
					output += "<td>" + noOfSolarPanels + "</td>";
					output += "<td>" + type + "</td>";
					
					
					//buttons
					output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"
						+ "<td><form method='post' action='solar.jsp'>"
						+ "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
						+ "<input name='solarID' type='hidden' value='" + solarID
						+ "'>" + "</form></td></tr>";

				}

				con.close();
				
				//complete the html table
				output += "</table>";
			}
			catch (Exception e)
			{
				output = "Error while reading the solar details.";
				System.err.println(e.getMessage());		
			}
			
			return output;
	}	
		
		
		public String updateSolarDetails(String solarID, String customerName, String customerAddress, String capacity, String noOfSolarPanels, String type)
		{
			String output = "";
			
			try
			{
				Connection con = connect();
				
				if (con == null)
				{
					return "Error while connecting to the database for updating.";
				}
				
				
				//create a prepared statement
				String query = "UPDATE solarpower SET customerName=?,customerAddress=?,capacity=?,noOfSolarPanels=?,type=? WHERE solarID=?";
				
				PreparedStatement preparedStmt = con.prepareStatement(query);
				
				
				//binding values
				preparedStmt.setString(1, customerName); 
				preparedStmt.setString(2, customerAddress); 
				preparedStmt.setDouble(3, Double.parseDouble(capacity)); 
				preparedStmt.setInt(4, Integer.parseInt(noOfSolarPanels));
				preparedStmt.setString(5, type);
				preparedStmt.setInt(6, Integer.parseInt(solarID));

				//execute the statement
				preparedStmt.execute();
				con.close();
				
				output = "Updated successfully";
			}
			catch (Exception e) 
			{
				output = "Error while updating the solar details.";
				System.err.println(e.getMessage());
			}
			return output;
		}
		
		
		public String deleteSolarDetails(String solarID)
		{
			String output = "";
			
			try
			{
				Connection con = connect(); 
				
				if (con == null)
				{return "Error while connecting to the database for deleting."; } 
				
				
				// create a prepared statement
				String query = "delete from solarpower where solarID=?"; 
				
				PreparedStatement preparedStmt = con.prepareStatement(query);
				
				
				// binding values
				preparedStmt.setInt(1, Integer.parseInt(solarID));
				
				// execute the statement
				preparedStmt.execute(); 
				con.close();
				output = "Deleted successfully";
			}
			
			catch (Exception e)
			{
				output = "Error while deleting the solar details."; 
				System.err.println(e.getMessage());
			}
			
			return output;
		}
		

}