package com;

import model.User;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import com.google.gson.*;
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/User")
public class UserService {
	User UserObj = new User();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readUser() {
		return UserObj.readUser();
	}

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertUser(
			@FormParam("User_Name") String User_Name,
			@FormParam("User_Address") String User_Address,
			@FormParam("User_Account_No") String User_Account_No,
			@FormParam("User_Contact_No") String User_Contact_No,
			@FormParam("Email") String Email) {
		String output = UserObj.insertUser(User_Name, User_Address, User_Account_No, User_Contact_No, Email);
		return output;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)

	public String updateUser(String userData) {
		
		JsonObject UserObject = new JsonParser().parse(userData).getAsJsonObject();
		
		String User_ID = UserObject.get("User_ID").getAsString();
		String User_Name = UserObject.get("User_Name").getAsString();
		String User_Address = UserObject.get("User_Address").getAsString();
		String User_Account_No = UserObject.get("User_Account_No").getAsString();
		String User_Contact_No = UserObject.get("User_Contact_No").getAsString();
		String Email = UserObject.get("Email").getAsString();
		
		String output = UserObj.updateUser(User_ID, User_Name, User_Address, User_Account_No, User_Contact_No, Email);
		return output;
	}
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteUser(String userData) {
		
		Document doc = Jsoup.parse(userData, "", Parser.xmlParser());

		String User_ID = doc.select("User_ID").text();
		String output = UserObj.deleteUser(User_ID);
		return output;
		
	}
}
