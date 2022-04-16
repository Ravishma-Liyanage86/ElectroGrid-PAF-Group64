package com;

import model.Bill;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/Bills")
public class BillService {
	
	Bill billObj = new Bill();
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readBills() {
		//return "Hello ElectroGrid";
		return billObj.readBills();
	}

}
