package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Payment {

	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/new","root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public String insertPay(String CustomerName, String CustomerAddress, String CustomerAccountNo, String Time, String Date) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = " insert into payment(`Payment_id`,`CustomerName`,`CustomerAddress`,`CustomerAccountNo`,`Time`,`Date`)" + " values (?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, CustomerName);
			preparedStmt.setString(3, CustomerAddress);
			preparedStmt.setString(4, CustomerAccountNo);
			preparedStmt.setString(5, Time);
			preparedStmt.setString(6, Date);

			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
		} catch (Exception e) {
			output = "Error while inserting the Payment.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String readPay() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table border=\"1\"><tr><th>Payment ID</th><th>Customer Name</th><th>Customer Address</th><th>Customer Account No</th><th>Payment Time</th><th>Payment Date</th></tr>";
			String query = "select * from payment";
			Statement stmt = (Statement) con.createStatement();
			ResultSet rs = ((java.sql.Statement) stmt).executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				String Payment_id = Integer.toString(rs.getInt("Payment_id"));
				String CustomerName = rs.getString("CustomerName");
				String CustomerAddress = rs.getString("CustomerAddress");
				String CustomerAccountNo = rs.getString("CustomerAccountNo");
				String Time = rs.getString("Time");
				String Date = rs.getString("Date");

				output += "<tr><td>" + Payment_id + "</td>";
				output += "<td>" + CustomerName + "</td>";
				output += "<td>" + CustomerAddress + "</td>";
				output += "<td>" + CustomerAccountNo + "</td>";
				output += "<td>" + Time + "</td>";
				output += "<td>" + Date + "</td>";
			}
			con.close();

			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the Payment.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String updatePay(String Payment_id, String CustomerName, String CustomerAddress, String CustomerAccountNo, String Time, String Date) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for updating.";
			}

			// create a prepared statement
			String query = "UPDATE payment SET CustomerName=?,CustomerAddress=?,CustomerAccountNo=?,Time=?,Date=? WHERE Payment_id=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setString(1, CustomerName);
			preparedStmt.setString(2, CustomerAddress);
			preparedStmt.setString(3, CustomerAccountNo);
			preparedStmt.setString(4, Time);
			preparedStmt.setString(5, Date);
			preparedStmt.setInt(6, Integer.parseInt(Payment_id));

			// execute the statement
			preparedStmt.execute();
			con.close();

			output = "Updated successfully";
		} catch (Exception e) {
			output = "Error while updating the Payment.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String deletePay(String Payment_id) {

		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}

			// create a prepared statement
			String query = "delete from payment where Payment_id=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, Integer.parseInt(Payment_id));

			// execute the statement
			preparedStmt.execute();
			con.close();

			output = "Deleted successfully";
		} catch (Exception e) {
			output = "Error while deleting the Payment.";
			System.err.println(e.getMessage());
		}
		return output;
	}
}
