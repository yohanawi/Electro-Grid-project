package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Bill {

	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, billname, password
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/new","root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public String insertBill(String Bill_Amount, String Bill_User_Contact_No, String User_Email) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = " insert into bill(`Bill_ID`,`Bill_Amount`,`Bill_User_Contact_No`,`User_Email`)" + " values (?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, Bill_Amount);
			preparedStmt.setString(3, Bill_User_Contact_No);
			preparedStmt.setString(4, User_Email);

			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
		} catch (Exception e) {
			output = "Error while inserting the Bill.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String readBill() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table border=\"1\"><tr><th>Bill ID</th><th>Bill Amount</th><th>Contact No</th><th>Email</th></tr>";
			String query = "select * from bill";
			Statement stmt = (Statement) con.createStatement();
			ResultSet rs = ((java.sql.Statement) stmt).executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				String Bill_ID = Integer.toString(rs.getInt("Bill_ID"));
				String Bill_Amount = rs.getString("Bill_Amount");
				String Bill_User_Contact_No = rs.getString("Bill_User_Contact_No");
				String User_Email = rs.getString("User_Email");

				output += "<tr><td>" + Bill_ID + "</td>";
				output += "<td>" + Bill_Amount + "</td>";
				output += "<td>" + Bill_User_Contact_No + "</td>";
				output += "<td>" + User_Email + "</td>";
			}
			con.close();

			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the Bill.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String updateBill(String Bill_ID, String Bill_Amount, String Bill_User_Contact_No, String User_Email) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for updating.";
			}

			// create a prepared statement
			String query = "UPDATE bill SET Bill_Amount=?,Bill_User_Contact_No=?,User_Email=? WHERE Bill_ID=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setString(1, Bill_Amount);
			preparedStmt.setString(2, Bill_User_Contact_No);
			preparedStmt.setString(3, User_Email);
			preparedStmt.setInt(4, Integer.parseInt(Bill_ID));

			// execute the statement
			preparedStmt.execute();
			con.close();

			output = "Updated successfully";
		} catch (Exception e) {
			output = "Error while updating the Bill.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String deleteBill(String Bill_ID) {

		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}

			// create a prepared statement
			String query = "delete from bill where Bill_ID=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, Integer.parseInt(Bill_ID));

			// execute the statement
			preparedStmt.execute();
			con.close();

			output = "Deleted successfully";
		} catch (Exception e) {
			output = "Error while deleting the Bill.";
			System.err.println(e.getMessage());
		}
		return output;
	}
}
