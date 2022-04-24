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

	// API for update Emergency Service Request
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateEmergencyServiceRequest(String EServiceData) {

		// Convert the input string to a JSON object
		JsonObject eServiceObj = new JsonParser().parse(EServiceData).getAsJsonObject();

		// reading the values from JSON object
		Integer serviceId = eServiceObj.get("EService_ID").getAsInt();
		String electricityMeter = eServiceObj.get("Electricity_Meter_Failure").getAsString();
		String otherIssue = eServiceObj.get("Other_Issue").getAsString();
		String requestedDate = eServiceObj.get("Requested_Date").getAsString();
		String requiredTime = eServiceObj.get("Required_Time_Period").getAsString();
		Integer phoneNo = eServiceObj.get("Phone_Number").getAsInt();
		String description = eServiceObj.get("Problem_Description").getAsString();
		String address = eServiceObj.get("Address").getAsString();

		// calling the update method and pass relevant values

		String updateOutput = emergencyService.updateEmergencyServiceRequest(serviceId, electricityMeter, otherIssue,
				requestedDate, requiredTime, phoneNo, description, address);

		// returning the output
		return updateOutput;

	}
	
	// API for delete emergency service
		@DELETE
		@Path("/")
		@Consumes(MediaType.APPLICATION_XML)
		@Produces(MediaType.TEXT_PLAIN)
		public String deleteEmergencyServiceRequest(String eServiceData) {

			// Convert the input string to an XML document
			Document eServiceDoc= Jsoup.parse(eServiceData, "",Parser.xmlParser());

			// reading the service ID from the XML document
			String serviceID = eServiceDoc.select("EService_ID").text();

			// calling the delete method and pass the service ID
			String deleteOutput = emergencyService.deleteEmergencyServiceRequest(Integer.parseInt(serviceID));

			// returning the output
			return deleteOutput;
		}


//	// API for delete emergency service
//	@DELETE
//	@Path("/")
//	@Consumes(MediaType.APPLICATION_JSON)
//	@Produces(MediaType.TEXT_PLAIN)
//	public String deleteEmergencyServiceRequest(String eServiceData) {
//
//		// Convert the input string to a JSON object
//		JsonObject eServiceObj = new JsonParser().parse(eServiceData).getAsJsonObject();
//
//		// reading the values from JSON object
//		Integer serviceId = eServiceObj.get("EService_ID").getAsInt();
//
//		// calling the delete method and pass the service ID
//		String deleteOutput = emergencyService.deleteEmergencyServiceRequest(serviceId);
//
//		// returning the output
//		return deleteOutput;
//	}

}
