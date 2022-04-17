package com;

import model.Bill;

import java.sql.Date;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;


@Path("/Bills")
public class BillService {
	
	Bill billObj = new Bill();
	
	//Read bills
	@GET
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_HTML)
	public String readBills(String userData) {
		//return "Hello ElectroGrid";
		//return billObj.readBills();
		
		//Convert the input string to an XML document
		Document doc = Jsoup.parse(userData, "", Parser.xmlParser());
		//Read the value from the element <userID>
		String user = doc.select("userID").text();
		String output = billObj.readBills(user);
		return output;
	}
	
	
	//Create Bill
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String createBill(@FormParam("billingDate") Date billingDate,
			@FormParam("unitCost") int unitCost,
			@FormParam("unitsUsed") int unitsUsed,
			@FormParam("serviceCharge") int serviceCharge,
			@FormParam("billSettled") boolean billSettled,
			@FormParam("userID") int userID) {
		
		String output = billObj.createBill(billingDate, unitCost, unitsUsed, serviceCharge, billSettled, userID);
		return output;
		
	}

	
	//delete bill
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteBill(String billData) {
		
		//Convert the input string to an XML document
		Document doc = Jsoup.parse(billData, "", Parser.xmlParser());
		
		String billID = doc.select("billID").text();
		String output = billObj.deleteBill(billID);
		return output;		
	}

}
