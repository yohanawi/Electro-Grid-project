package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class User {

	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/customer_management","root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public String insertUser(String User_Name, String User_Address, String User_Account_No, String User_Contact_No, String Email) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = " insert into user(`User_ID`,`User_Name`,`User_Address`,`User_Account_No`,`User_Contact_No`,`Email`)" + " values (?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, User_Name);
			preparedStmt.setString(3, User_Address);
			preparedStmt.setString(4, User_Account_No);
			preparedStmt.setString(5, User_Contact_No);
			preparedStmt.setString(6, Email);

			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
		} catch (Exception e) {
			output = "Error while inserting the User.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String readUser() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table border=\"1\"><tr><th>User ID</th><th>User Name</th><th>User Address</th><th>User Account No</th><th>User Contact No</th><th>Email</th></tr>";
			String query = "select * from user";
			Statement stmt = (Statement) con.createStatement();
			ResultSet rs = ((java.sql.Statement) stmt).executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				String User_ID = Integer.toString(rs.getInt("User_ID"));
				String User_Name = rs.getString("User_Name");
				String User_Address = rs.getString("User_Address");
				String User_Account_No = rs.getString("User_Account_No");
				String User_Contact_No = rs.getString("User_Contact_No");
				String Email = rs.getString("Email");

				output += "<tr><td>" + User_ID + "</td>";
				output += "<td>" + User_Name + "</td>";
				output += "<td>" + User_Address + "</td>";
				output += "<td>" + User_Account_No + "</td>";
				output += "<td>" + User_Contact_No + "</td>";
				output += "<td>" + Email + "</td>";
			}
			con.close();

			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the User.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String updateUser(String User_ID, String User_Name, String User_Address, String User_Account_No, String User_Contact_No, String Email) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for updating.";
			}

			// create a prepared statement
			String query = "UPDATE user SET User_Name=?,User_Address=?,User_Account_No=?,User_Contact_No=?,Email=? WHERE User_ID=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setString(1, User_Name);
			preparedStmt.setString(2, User_Address);
			preparedStmt.setString(3, User_Account_No);
			preparedStmt.setString(4, User_Contact_No);
			preparedStmt.setString(5, Email);
			preparedStmt.setInt(6, Integer.parseInt(User_ID));

			// execute the statement
			preparedStmt.execute();
			con.close();

			output = "Updated successfully";
		} catch (Exception e) {
			output = "Error while updating the User.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String deleteUser(String User_ID) {

		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}

			// create a prepared statement
			String query = "delete from user where User_ID=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, Integer.parseInt(User_ID));

			// execute the statement
			preparedStmt.execute();
			con.close();

			output = "Deleted successfully";
		} catch (Exception e) {
			output = "Error while deleting the User.";
			System.err.println(e.getMessage());
		}
		return output;
	}
}
