package com;
import model.Customer;


//For REST Service
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 
//For JSON
import com.google.gson.*; 
//For XML
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document; 
@Path("/Customers") 
public class CustomerService {
	
	Customer customerObj = new Customer(); 
	@GET
	@Path("/") 
	@Produces(MediaType.TEXT_HTML) 
	public String readCustomers() 
	 { 
		return customerObj.readCustomer();
	 } 

	@POST
	@Path("/") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String insertCustomer(
	 @FormParam("customerName") String customerName, 
	 @FormParam("customerNic") String customerNic, 
	 @FormParam("customerEmail") String customerEmail, 
	 @FormParam("customerPhone") String customerPhone,
	 @FormParam("customerAddress") String customerAddress) 
	{ 
	 String output = customerObj.insertCustomer(customerName, customerNic, customerEmail, customerPhone, customerAddress); 
	return output; 
	}


	@PUT
	@Path("/") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String updateCustomer(String customerData) 
	{ 
	//Convert the input string to a JSON object 
	 JsonObject customerObject = new JsonParser().parse(customerData).getAsJsonObject(); 
	//Read the values from the JSON object
	 String customerId = customerObject.get("customerId").getAsString(); 
	 String customerName = customerObject.get("customerName").getAsString(); 
	 String customerNic = customerObject.get("customerNic").getAsString(); 
	 String customerEmail = customerObject.get("customerEmail").getAsString(); 
	 String customerPhone = customerObject.get("customerPhone").getAsString(); 
	 String customerAddress = customerObject.get("customerAddress").getAsString(); 
	 String output = customerObj.updateCustomer(customerId, customerName, customerNic, customerEmail, customerPhone, customerAddress); 
	return output; 
	}


	@DELETE
	@Path("/") 
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String deleteCustomer(String customerData) 
	{ 
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(customerData, "", Parser.xmlParser()); 
	 
	//Read the value from the element <itemID>
	 String customerId = doc.select("customerId").text(); 
	 String output = customerObj.deleteCustomer(customerId); 
	return output; 
	}

}
