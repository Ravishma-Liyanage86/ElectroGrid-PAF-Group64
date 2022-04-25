package model;

import java.sql.*;

//import com.mysql.cj.xdevapi.Statement;

public class Notify {
	//A common method to connect to the DB
	private Connection connect()
	 {
	 Connection con = null;
	 try
	 {
	 Class.forName("com.mysql.jdbc.Driver");

	 //Provide the correct details: DBServer/DBName, username, password
	 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/notificationmanagement", "root", "");
	 }
	 catch (Exception e)
	 {e.printStackTrace();}
	 return con;
	 } 
	
	public String insertNotify(String NotificationCode, String NotificationName, String NotificationDesc)
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for inserting."; }
	 // create a prepared statement
	 String query = " insert into notification  (NotificationID,NotificationCode,NotificationName,NotificationDesc)" + " values (?, ?, ?, ?)";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 
	 
	 // binding values
	 preparedStmt.setInt(1, 0);
	 preparedStmt.setString(2, NotificationCode);
	 preparedStmt.setString(3, NotificationName);
	 preparedStmt.setString(4, NotificationDesc);
	 // execute the statement
	 preparedStmt.execute();
	 con.close();
	 output = "Inserted successfully";
	 }
	 catch (Exception e)
	 {
	 output = "Error while inserting the Notification.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 }
	  
		 public String readNotify()
		 {
		 String output = "";
		 try
		 {
		 Connection con = connect();
		 if (con == null)
		 {return "Error while connecting to the database for reading."; }
		 // Prepare the html table to be displayed
		 output = "<table border='1'><tr><th>Notification Code</th><th>Notification Name</th>" +
				 "<th>Notification Description</th>" +
				 "<th>Update</th><th>Remove</th></tr>";
	
		 String query = "select * from notification";
		 Statement stmt = con.createStatement();
		 ResultSet rs = stmt.executeQuery(query); 
		 // iterate through the rows in the result set
		 while (rs.next())
		 {
		 String NotificationID = Integer.toString(rs.getInt("NotificationID"));
		 String NotificationCode = rs.getString("NotificationCode");
		 String NotificationName = rs.getString("NotificationName");
		 String NotificationDesc = rs.getString("NotificationDesc");
		 // Add into the html table
		 output += "<tr><td>" + NotificationCode + "</td>";
		 output += "<td>" + NotificationName + "</td>";
		 output += "<td>" + NotificationDesc + "</td>";
		 // buttons
		 output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"
				 + "<td><form method='post' action='Notification.jsp'>" + "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
						 + "<input name='NotificationID' type='hidden' value='" + NotificationID
						 + "'>" + "</form></td></tr>";
		 	}
		 con.close();
		 // Complete the html table
		 output += "</table>";
		 }
		 catch (Exception e)
		 {
		 output = "Error while reading the Notification.";
		 System.err.println(e.getMessage());
		 }
		 return output;
		 }
	public String updateNotify(String NotificationID, String NotificationCode, String NotificationName, String NotificationDesc)
	
	{
		 String output = "";
		 try
		 {
		 Connection con = connect();
		 if (con == null)
		 {return "Error while connecting to the database for updating."; }
		 // create a prepared statement
		 String query = "UPDATE notification SET NotificationCode=?,NotificationName=?,NotificationDesc=? WHERE NotificationID=?";
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		 // binding values
		 preparedStmt.setString(1, NotificationCode);
		 preparedStmt.setString(2, NotificationName);
		 preparedStmt.setString(3, NotificationDesc);
		 preparedStmt.setInt(4, Integer.parseInt(NotificationID));
		 // execute the statement
		 preparedStmt.execute();
		 con.close();
		 output = "Updated successfully";
		 }
		 catch (Exception e)
		 {
		 output = "Error while updating the Notification.";
		 System.err.println(e.getMessage());
		 }
		 return output;
		 }
		public String deleteNotiy(String NotificationID)
		 {
		 String output = "";
		 try
		 {
		 Connection con = connect();
		 if (con == null)
		 {return "Error while connecting to the database for deleting."; }
		 // create a prepared statement
		 String query = "delete from notification where NotificationID=?";
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		 // binding values
		 preparedStmt.setInt(1, Integer.parseInt(NotificationID));
		 // execute the statement
		 preparedStmt.execute();
		 con.close();
		 output = "Deleted successfully";
		 }
		 catch (Exception e)
		 {
		 output = "Error while deleting the Notification.";
		 System.err.println(e.getMessage());
		 }
		 return output; 
		 }
}
