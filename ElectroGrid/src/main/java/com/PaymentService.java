package com; 
import model.Payment; 
//For REST Service
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 
//For JSON
import com.google.gson.*; 
//For XML
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document; 
@Path("/Payments") 
public class PaymentService 
{ 
 Payment paymentObj = new Payment(); 
@GET
@Path("/") 
@Produces(MediaType.TEXT_HTML) 
public String readPayments() 
 { 
	return paymentObj.readPayments(); 
 } 

@POST
@Path("/") 
@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
@Produces(MediaType.TEXT_PLAIN) 
public String insertPayment(@FormParam("paymentName") String paymentName, 
 @FormParam("paymentAddress") String paymentAddress, 
 @FormParam("paymentMeterNo") String paymentMeterNo, 
 @FormParam("paymentAccountNo") String paymentAccountNo,
 @FormParam("paymentAmount") String paymentAmount ) 
{ 
 String output = paymentObj.insertPayment(paymentName, paymentAddress, paymentMeterNo, paymentAccountNo, paymentAmount); 
return output; 
}


@PUT
@Path("/") 
@Consumes(MediaType.APPLICATION_JSON) 
@Produces(MediaType.TEXT_PLAIN) 
public String updatePayment(String paymentData) 
{ 
//Convert the input string to a JSON object 
 JsonObject paymentObject = new JsonParser().parse(paymentData).getAsJsonObject(); 
//Read the values from the JSON object
 String paymentCode = paymentObject.get("paymentCode").getAsString(); 
 String paymentName = paymentObject.get("paymentName").getAsString(); 
 String paymentAddress = paymentObject.get("paymentAddress").getAsString(); 
 String paymentMeterNo = paymentObject.get("paymentMeterNo").getAsString(); 
 String paymentAccountNo = paymentObject.get("paymentAccountNo").getAsString(); 
 String paymentAmount = paymentObject.get("paymentAmount").getAsString(); 
 String output = paymentObj.updatePayments(paymentCode, paymentName, paymentAddress, paymentMeterNo, paymentAccountNo, paymentAmount); 
return output; 
}

@DELETE
@Path("/") 
@Consumes(MediaType.APPLICATION_XML) 
@Produces(MediaType.TEXT_PLAIN) 
public String deletePayment(String paymentData) 
{ 
//Convert the input string to an XML document
 Document doc = Jsoup.parse(paymentData, "", Parser.xmlParser()); 
 
//Read the value from the element <itemID>
 String paymentCode = doc.select("paymentCode").text(); 
 String output = paymentObj.deletePayment(paymentCode); 
return output; 
}

}