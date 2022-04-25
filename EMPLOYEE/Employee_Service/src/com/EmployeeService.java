package com;

import model.Employee;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import com.google.gson.*;
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Employee")
public class EmployeeService {
	Employee EmployeeObj = new Employee();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readEmployee() {
		return EmployeeObj.readEmployee();
	}

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertEmployee(
			@FormParam("EMPName") String EMPName,
			@FormParam("EMPAddress") String EMPAddress,
			@FormParam("EMPEmail") String EMPEmail,
			@FormParam("EMPContactNO") String EMPContactNO) {
		String output = EmployeeObj.insertEmployee(EMPName, EMPAddress, EMPEmail, EMPContactNO);
		return output;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)

	public String updateEmployee(String EMPDetail) {
		
		JsonObject EmployeeObject = new JsonParser().parse(EMPDetail).getAsJsonObject();
		
		String EMPID = EmployeeObject.get("EMPID").getAsString();
		String EMPName = EmployeeObject.get("EMPName").getAsString();
		String EMPAddress = EmployeeObject.get("EMPAddress").getAsString();
		String EMPEmail = EmployeeObject.get("EMPEmail").getAsString();
		String EMPContactNO = EmployeeObject.get("EMPContactNO").getAsString();
		
		String output = EmployeeObj.updateEmployee(EMPID, EMPName, EMPAddress, EMPEmail, EMPContactNO);
		return output;
	}
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteEmployee(String EMPDetail) {
		
		Document doc = Jsoup.parse(EMPDetail, "", Parser.xmlParser());

		String EMPID = doc.select("EMPID").text();
		String output = EmployeeObj.deleteEmployee(EMPID);
		return output;
		
	}
}
