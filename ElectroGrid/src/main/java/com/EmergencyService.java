package com;

import model.EmergencyServicesManagement;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/EmergencyServicesManagement")
public class EmergencyService {

	// creating a object from model class
	EmergencyServicesManagement emergencyService = new EmergencyServicesManagement();

	// API for adding a new emergency service request
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String addEmergencyServiceRequest(@FormParam("Electricity_Meter_Failure") String electricityMeter,
			@FormParam("Other_Issue") String otherIssue, @FormParam("Requested_Date") String requestedDate,
			@FormParam("Required_Time_Period") String requiredTime, @FormParam("Phone_Number") Integer phoneNo,
			@FormParam("Problem_Description") String description, @FormParam("Address") String address

	) {

		// calling the insert method through the object
		String addOutput = emergencyService.addEmergencyServiceRequest(electricityMeter, otherIssue, requestedDate,
				requiredTime, phoneNo, description, address);
		return addOutput;

	}

	// API for view all the emergency service requests made by the customer

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String viewEmergencyServiceRequest() {
		// calling the read method o model class through the object
		return emergencyService.viewEmergencyServiceRequest();

	}

	

	



}
