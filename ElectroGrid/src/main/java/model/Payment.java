package model; 
import java.sql.*; 
public class Payment 
{ //A common method to connect to the DB
private Connection connect() 
 { 
 Connection con = null; 
 try
 { 
 Class.forName("com.mysql.jdbc.Driver"); 
 
 //Provide the correct details: DBServer/DBName, username, password 
 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3307/payment", "root", ""); 
 } 
 catch (Exception e) 
 {e.printStackTrace();} 
 return con; 
 } 

//insert
public String insertPayment(String paymentName, String paymentAddress, String paymentMeterNo, String paymentAccountNo, String paymentAmount) 
 { 
 String output = ""; 
 try
 { 
 Connection con = connect(); 
 if (con == null) 
 {return "Error while connecting to the database for inserting."; } 
 // create a prepared statement
 String query = " insert into payments(`paymentCode`,`paymentName`,`paymentAddress`,`paymentMeterNo`,`paymentAccountNo`, `paymentAmount`)"
 + " values (?, ?, ?, ?, ?, ?)"; 
 PreparedStatement preparedStmt = con.prepareStatement(query); 
 // binding values
 preparedStmt.setInt(1, 0); 
 preparedStmt.setString(2, paymentName); 
 preparedStmt.setString(3, paymentAddress); 
 preparedStmt.setString(4, paymentMeterNo); 
 preparedStmt.setString(5, paymentAccountNo); 
 preparedStmt.setDouble(6, Double.parseDouble(paymentAmount)); 
 // execute the statement
 
 preparedStmt.execute(); 
 con.close(); 
 output = "Inserted successfully"; 
 } 
 catch (Exception e) 
 { 
 output = "Error while inserting the payment."; 
 System.err.println(e.getMessage()); 
 } 
 return output; 
 } 


public String readPayments() 
 { 
 String output = ""; 
 try
 { 
 Connection con = connect(); 
 if (con == null) 
 {return "Error while connecting to the database for reading."; } 
 // Prepare the html table to be displayed
 output = "<table border='1'><tr><th>Payment Name</th><th>Payment Address</th>" +
 "<th>Payment Meter Number</th>" + 
 "<th>Payment Account Number</th>" +
 "<th>Payment Amount</th>" +
 "<th>Update</th><th>Remove</th></tr>"; 
 
 String query = "select * from payments"; 
 Statement stmt = con.createStatement(); 
 ResultSet rs = stmt.executeQuery(query); 
 // iterate through the rows in the result set
 while (rs.next()) 
 { 
 String paymentCode = Integer.toString(rs.getInt("paymentCode")); 
 String paymentName = rs.getString("paymentName"); 
 String paymentAddress = rs.getString("paymentAddress"); 
 String paymentMeterNo = rs.getString("paymentMeterNo"); 
 String paymentAccountNo = rs.getString("paymentAccountNo"); 
 String paymentAmount = Double.toString(rs.getDouble("paymentAmount")); 
 // Add into the html table
 output += "<tr><td>" + paymentName + "</td>"; 
 output += "<td>" + paymentAddress + "</td>"; 
 output += "<td>" + paymentMeterNo + "</td>"; 
 output += "<td>" + paymentAccountNo + "</td>"; 
 output += "<td>" + paymentAmount + "</td>"; 
 // buttons
 output += "<td><input name='btnUpdate' type='button' value='Update'  class='btn btn-secondary'></td>"
 + "<td><form method='post' action='payments.jsp'>" + "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
 + "<input name='itemID' type='hidden' value='" + paymentCode 
 + "'>" + "</form></td></tr>"; 
 } 
 con.close(); 
 // Complete the html table
 output += "</table>"; 
 } 
 catch (Exception e) 
 { 
 output = "Error while reading the payments."; 
 System.err.println(e.getMessage()); 
 } 
 return output; 
 } 


public String updatePayments(String paymentCode, String paymentName, String paymentAddress, String paymentMeterNo, String paymentAccountNo, String paymentAmount) 
 
 { 
 String output = ""; 
 try
 { 
 Connection con = connect(); 
 if (con == null) 
 {return "Error while connecting to the database for updating."; } 
 // create a prepared statement
 String query = "UPDATE payments SET paymentName=?,paymentAddress=?,paymentMeterNo=?,paymentAccountNo=?,paymentAmount=?  WHERE paymentCode=?"; 
 PreparedStatement preparedStmt = con.prepareStatement(query); 
 // binding values
 preparedStmt.setString(1, paymentName); 
 preparedStmt.setString(2, paymentAddress); 
 preparedStmt.setString(3, paymentMeterNo); 
 preparedStmt.setString(4, paymentAccountNo); 
 preparedStmt.setDouble(5,Double.parseDouble(paymentAmount));
 preparedStmt.setInt(6, Integer.parseInt(paymentCode)); 
 // execute the statement
 preparedStmt.execute(); 
 con.close(); 
 output = "Updated successfully"; 
 } 
 catch (Exception e) 
 { 
 output = "Error while updating the payment."; 
 System.err.println(e.getMessage()); 
 } 
 return output; 
 } 


public String deletePayment(String paymentCode) 
 { 
 String output = ""; 
 try
 { 
 Connection con = connect(); 
 if (con == null) 
 {return "Error while connecting to the database for deleting."; } 
 // create a prepared statement
 String query = "delete from payments where paymentCode=?"; 
 PreparedStatement preparedStmt = con.prepareStatement(query); 
 // binding values
 preparedStmt.setInt(1, Integer.parseInt(paymentCode)); 
 // execute the statement
 preparedStmt.execute(); 
 con.close(); 
 output = "Deleted successfully"; 
 } 
 catch (Exception e) 
 { 
 output = "Error while deleting the payment."; 
 System.err.println(e.getMessage()); 
 } 
 return output; 
 } 
} 