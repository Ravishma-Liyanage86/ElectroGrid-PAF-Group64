package com;

import model.Solar;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

//For JSON
import com.google.gson.*;

//For XML
import org.jsoup.*;
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document;

@Path("/Solar")

public class SolarService {
	
	//read
		Solar solarObj = new Solar();
		
		@GET
		@Path("/") 
		@Produces(MediaType.TEXT_HTML) 
		public String readSolarDetails()
		{
			return solarObj.readSolarDetails();
		}
		
		//insert
		@POST
		@Path("/")
		@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
		@Produces(MediaType.TEXT_PLAIN)
		
		public String insertSolarDetails(@FormParam("customerName") String customerName,
				@FormParam("customerAddress") String customerAddress, 
				@FormParam("capacity") String capacity,
				@FormParam("noOfSolarPanels") String noOfSolarPanels, 
				@FormParam("type") String type)
		
		{
			String output = solarObj.insertSolarDetails(customerName, customerAddress, capacity, noOfSolarPanels, type); 
			return output;
		}
		
		//update
		@PUT
		@Path("/") 
		@Consumes(MediaType.APPLICATION_JSON) 
		@Produces(MediaType.TEXT_PLAIN)
		
		public String updateSolarDetails(String solarData)
		{
			//Convert the input string to a JSON object
			
			JsonObject solarObject = new JsonParser().parse(solarData).getAsJsonObject();
			
			//Read the values from the JSON object
			
			String solarID = solarObject.get("solarID").getAsString(); 
			String customerName = solarObject.get("customerName").getAsString(); 
			String customerAddress = solarObject.get("customerAddress").getAsString(); 
			String capacity = solarObject.get("capacity").getAsString(); 
			String noOfSolarPanels = solarObject.get("noOfSolarPanels").getAsString();
			String type = solarObject.get("type").getAsString();
			
			
			String output = solarObj.updateSolarDetails(solarID, customerName, customerAddress, capacity, noOfSolarPanels, type); 
			return output;
		}
		
		//delete
		@DELETE
		@Path("/") 
		@Consumes(MediaType.APPLICATION_XML) 
		@Produces(MediaType.TEXT_PLAIN)
		
		public String deleteSolarDetails(String solarData)
		{
			//Convert the input string to an XML document
			
			Document doc = Jsoup.parse(solarData, "", Parser.xmlParser()); 
			
			
			//Read the value from the element <solarID>
			String solarID = doc.select("solarID").text(); 
			
			
			String output = solarObj.deleteSolarDetails(solarID); 
			
			return output;
		}

}
