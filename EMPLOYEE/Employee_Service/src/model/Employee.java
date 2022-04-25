package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Employee {

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

	public String insertEmployee(String EMPName, String EMPAddress, String EMPEmail, String EMPContactNO) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = " insert into employee(`EMPID`,`EMPName`,`EMPAddress`,`EMPEmail`,`EMPContactNO`)" + " values (?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, EMPName);
			preparedStmt.setString(3, EMPAddress);
			preparedStmt.setString(4, EMPEmail);
			preparedStmt.setString(5, EMPContactNO);

			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
		} catch (Exception e) {
			output = "Error while inserting the Employee.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String readEmployee() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table border=\"1\"><tr><th>Employee ID</th><th>Customer Name</th><th>Address</th><th>Email</th><th>Contact Number</th></tr>";
			String query = "select * from employee";
			Statement stmt = (Statement) con.createStatement();
			ResultSet rs = ((java.sql.Statement) stmt).executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				String EMPID = Integer.toString(rs.getInt("EMPID"));
				String EMPName = rs.getString("EMPName");
				String EMPAddress = rs.getString("EMPAddress");
				String EMPEmail = rs.getString("EMPEmail");
				String EMPContactNO = rs.getString("EMPContactNO");

				output += "<tr><td>" + EMPID + "</td>";
				output += "<td>" + EMPName + "</td>";
				output += "<td>" + EMPAddress + "</td>";
				output += "<td>" + EMPEmail + "</td>";
				output += "<td>" + EMPContactNO + "</td>";
			}
			con.close();

			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the Employee.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String updateEmployee(String EMPID, String EMPName, String EMPAddress, String EMPEmail, String EMPContactNO) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for updating.";
			}

			// create a prepared statement
			String query = "UPDATE employee SET EMPName=?,EMPAddress=?,EMPEmail=?,EMPContactNO=? WHERE EMPID=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setString(1, EMPName);
			preparedStmt.setString(2, EMPAddress);
			preparedStmt.setString(3, EMPEmail);
			preparedStmt.setString(4, EMPContactNO);
			preparedStmt.setInt(5, Integer.parseInt(EMPID));

			// execute the statement
			preparedStmt.execute();
			con.close();

			output = "Updated successfully";
		} catch (Exception e) {
			output = "Error while updating the Employee.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String deleteEmployee(String EMPID) {

		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}

			// create a prepared statement
			String query = "delete from employee where EMPID=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, Integer.parseInt(EMPID));

			// execute the statement
			preparedStmt.execute();
			con.close();

			output = "Deleted successfully";
		} catch (Exception e) {
			output = "Error while deleting the Employee.";
			System.err.println(e.getMessage());
		}
		return output;
	}
}
