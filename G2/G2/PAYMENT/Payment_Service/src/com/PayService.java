package com;

import model.Payment;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import com.google.gson.*;
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Payment")
public class PayService {
	Payment PayObj = new Payment();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readPay() {
		return PayObj.readPay();
	}

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertPay(
			@FormParam("CustomerName") String CustomerName,
			@FormParam("CustomerAddress") String CustomerAddress,
			@FormParam("CustomerAccountNo") String CustomerAccountNo,
			@FormParam("Time") String Time,
			@FormParam("Date") String Date) {
		String output = PayObj.insertPay(CustomerName, CustomerAddress, CustomerAccountNo, Time, Date);
		return output;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)

	public String updatePay(String paymentDetails) {
		
		JsonObject PaymentObject = new JsonParser().parse(paymentDetails).getAsJsonObject();
		
		String Payment_id = PaymentObject.get("Payment_id").getAsString();
		String CustomerName = PaymentObject.get("CustomerName").getAsString();
		String CustomerAddress = PaymentObject.get("CustomerAddress").getAsString();
		String CustomerAccountNo = PaymentObject.get("CustomerAccountNo").getAsString();
		String Time = PaymentObject.get("Time").getAsString();
		String Date = PaymentObject.get("Date").getAsString();
		
		String output = PayObj.updatePay(Payment_id, CustomerName, CustomerAddress, CustomerAccountNo, Time, Date);
		return output;
	}
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deletePay(String paymentDetails) {
		
		Document doc = Jsoup.parse(paymentDetails, "", Parser.xmlParser());

		String Payment_id = doc.select("Payment_id").text();
		String output = PayObj.deletePay(Payment_id);
		return output;
		
	}
}
