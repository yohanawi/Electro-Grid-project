package com;

import model.Bill;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import com.google.gson.*;
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Bill")
public class BillService {
	Bill BillObj = new Bill();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readBill() {
		return BillObj.readBill();
	}

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertBill(
			@FormParam("Bill_Amount") String Bill_Amount,
			@FormParam("Bill_User_Contact_No") String Bill_User_Contact_No,
			@FormParam("User_Email") String User_Email) {
		String output = BillObj.insertBill(Bill_Amount, Bill_User_Contact_No, User_Email);
		return output;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)

	public String updateBill(String billData) {
		
		JsonObject BillObject = new JsonParser().parse(billData).getAsJsonObject();
		
		String Bill_ID = BillObject.get("Bill_ID").getAsString();
		String Bill_Amount = BillObject.get("Bill_Amount").getAsString();
		String Bill_User_Contact_No = BillObject.get("Bill_User_Contact_No").getAsString();
		String User_Email = BillObject.get("User_Email").getAsString();
		
		String output = BillObj.updateBill(Bill_ID, Bill_Amount, Bill_User_Contact_No, User_Email);
		return output;
	}
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteBill(String billData) {
		
		Document doc = Jsoup.parse(billData, "", Parser.xmlParser());

		String Bill_ID = doc.select("Bill_ID").text();
		String output = BillObj.deleteBill(Bill_ID);
		return output;
		
	}
}
