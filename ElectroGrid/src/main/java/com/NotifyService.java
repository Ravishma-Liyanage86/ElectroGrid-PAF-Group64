package com;

import model.Notify;
//For REST Service
		import javax.ws.rs.*;
		import javax.ws.rs.core.MediaType;
//For JSON
		import com.google.gson.*;
//For XML
	import org.jsoup.*;
	import org.jsoup.parser.*;
	import org.jsoup.nodes.Document;
	@Path("/Notify")
	public class NotifyService
	{
	 Notify NotificationObj = new Notify();
	
	//@GET
	//@Path("/")
	//@Produces(MediaType.TEXT_HTML)
	//public String readNotify()
	// {
	 //return "Hello";
	 //}

@GET
@Path("/")
@Produces(MediaType.TEXT_HTML)
public String readItems()
 {
 return NotificationObj.readNotify();
}

//insert
@POST
@Path("/")
@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
@Produces(MediaType.TEXT_PLAIN)
public String insertNotify(@FormParam("NotificationCode") String NotificationCode,
 @FormParam("NotificationName") String NotificationName,
 @FormParam("NotificationDesc") String NotificationDesc)
{
 String output = NotificationObj.insertNotify(NotificationCode, NotificationName, NotificationDesc);
return output;
}

//Update
@PUT
@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.TEXT_PLAIN)
public String updateNotify(String NotificationData)
{
//Convert the input string to a JSON object
 JsonObject NotificationObject = new JsonParser().parse(NotificationData).getAsJsonObject();
//Read the values from the JSON object
 String NotificationID = NotificationObject.get("NotificationID").getAsString();
 String NotificationCode = NotificationObject.get("NotificationCode").getAsString();
 String NotificationName = NotificationObject.get("NotificationName").getAsString();
 String NotificationDesc = NotificationObject.get("NotificationDesc").getAsString();
 String output = NotificationObj.updateNotify(NotificationID, NotificationCode, NotificationName, NotificationDesc);
return output;
}
//delete

@DELETE
@Path("/")
@Consumes(MediaType.APPLICATION_XML)
@Produces(MediaType.TEXT_PLAIN)
public String deleteNotify(String NotificationData)
{
//Convert the input string to an XML document
 Document doc = Jsoup.parse(NotificationData, "", Parser.xmlParser());

//Read the value from the element <itemID>
 String NotificationID = doc.select("NotificationID").text();
 String output = NotificationObj.deleteNotiy(NotificationID);
return output;
}


}
