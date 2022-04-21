package model;

import java.sql.*;

public class EmergencyServicesManagement {

	// A common method which use to connect to the database

	private Connection myConnection() {

		// declaring a connection type variable which returns the connection
		Connection myConnect = null;

		try {

			Class.forName("com.mysql.jdbc.Driver");

			// Provide the correct details: Database name, user name, password
			myConnect = DriverManager.getConnection("jdbc:mysql://localhost:3306/electrogrid", "root", "");

		} catch (Exception e) {
			e.printStackTrace();
		}

		// returning the connection
		return myConnect;
	}

	// Creating a method to add new Emergency Service Request
	public String addEmergencyServiceRequest(String electricityMeter, String otherIssue, String requestedDate,
			String requiredTime, Integer phoneNo, String description, String address) {

		String requestOutput = "";

		try {
			// creating theDB connection
			Connection myConnect = myConnection();

			// check whether successfully connected with the db or not
			if (myConnect == null) {
				return "Error occured while connecting to the database for add the request";
			}

			// creating a prepared statement
			String myQuerry = " insert into emergency_service( EService_ID, Electricity_Meter_Failure, Other_Issue, Requested_Date, Required_Time_Period, Phone_Number, Problem_Description, Address)"
					+ "values( ?, ?, ?, ?, ?, ?, ?, ?)";

			PreparedStatement preparedStmt = myConnect.prepareStatement(myQuerry);

			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, electricityMeter);
			preparedStmt.setString(3, otherIssue);
			preparedStmt.setString(4, requestedDate);
			preparedStmt.setString(5, requiredTime);
			preparedStmt.setInt(6, phoneNo);
			preparedStmt.setString(7, description);
			preparedStmt.setString(8, address);

			// execute the statement

			preparedStmt.execute();

			// closing the database connection
			myConnect.close();

			// states whether emergency service request has made successfully
			requestOutput = "Your emergency service request have made successfully";

		} catch (Exception e) {

			// displaying if an error occur while adding the request
			requestOutput = "OOps! Can not make your request";
			System.err.println(e.getMessage());
		}

		// returning the output
		return requestOutput;
	}

	// Creating a method to view created Emergency Service Request
	public String viewEmergencyServiceRequest() {

		String viewOutput = "";

		try {
			// creating theDB connection
			Connection myConnect = myConnection();

			// check whether successfully connected with the db or not
			if (myConnect == null) {
				return "Error occured while connecting to the database for view the request";
			}

			// prepare the html table to display the emergency service request made by
			// customer

			viewOutput = "<table border='2'><tr><th>Service Id</th><th>Request Type:Electricity Meter Failure</th><th>Request Type:Other Issue</th>"
					+ "<th>Requested Date</th>" + "<th>Required Time Period(Within)</th>"
					+ "<th>Phone Number</th><th>Description about the Problem</th><th>Address</th></tr>";

			// creating the query to view all emergency service request made by customer
			String myQuerry = "select * from emergency_service";
			Statement myStmt = myConnect.createStatement();
			ResultSet myResult = myStmt.executeQuery(myQuerry);

			// iterate through the rows to read all the emergency services request made by
			// customer

			while (myResult.next()) {
				Integer serviceID = myResult.getInt("EService_ID");
				String electricityMeter = myResult.getString("Electricity_Meter_Failure");
				String otherIssue = myResult.getString("Other_Issue");
				String requestedDate = myResult.getString("Requested_Date");
				String requiredTime = myResult.getString("Required_Time_Period");
				Integer phoneNo = myResult.getInt("Phone_Number");
				String description = myResult.getString("Problem_Description");
				String address = myResult.getString("Address");

				// put the taken data into html table
				viewOutput += "<tr><td>" + serviceID + "</td>";
				viewOutput += "<td>" + electricityMeter + "</td>";
				viewOutput += "<td>" + otherIssue + "</td>";
				viewOutput += "<td>" + requestedDate + "</td>";
				viewOutput += "<td>" + requiredTime + "</td>";
				viewOutput += "<td>" + phoneNo + "</td>";
				viewOutput += "<td>" + description + "</td>";
				viewOutput += "<td>" + address + "</td></tr>";

			}

			// closing the database connection
			myConnect.close();

			// Completing the Emergency service request table
			viewOutput += "</table>";

		} catch (Exception e) {
			// displaying if an error occur while reading the previously made request
			viewOutput = " Error! Unable to view your Emergency Service Request";
			System.err.println(e.getMessage());

		}

		// returning the output
		return viewOutput;

	}

	
	

	
}
