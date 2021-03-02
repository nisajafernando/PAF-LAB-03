package com;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;

public class Item {

	public Connection connect()
	{
	 Connection con = null;

	 try
	 { 
	 Class.forName("com.mysql.jdbc.Driver");
	 con= DriverManager.getConnection("jdbc:mysql://127.0.0.1:1433/item",
	 "root", "");
	 //For testing
	 System.out.print("Successfully connected");
	 }
	 catch(Exception e)
	 {
	 e.printStackTrace();
	 }

	 return con;
	}
	
	public String insertItem(String code, String name, String price, String desc) {
		String output = "";
		
		try {
		Connection con = connect();
		
		if(con == null) {
			
			return "Error while connecting to the database";
			
		}
		
		String query = "insert into items(ItemID,ItemCode,ItemName,ItemPrice,ItemDesc)"+" values(?,?,?,?,?)";
		
		PreparedStatement preparedStmt = con.prepareStatement(query);
		
		preparedStmt.setInt(1, 0);
		preparedStmt.setString(2, code);
		preparedStmt.setString(3, name);
		preparedStmt.setDouble(4, Double.parseDouble(price));
		preparedStmt.setString(5, desc);
		
		preparedStmt.execute();
		con.close();
		
		output = "Inserted successfully";
		
		}
		
		catch(Exception e){
			output = "Error while inserting";
			System.err.println(e.getMessage());
		}
		
		return output;
	}
	
	public String readItems() {
		
		String output = "";
		
		try {
			
			Connection con = connect();
			
			if (con == null)
			{
				return "Error while connecting to the database for reading.";
			}
			 // Prepare the html table to be displayed
		
			 output = "<table border='1'><tr><th>Item Code</th>"
			  +"<th>Item Name</th><th>Item Price</th>"
			  + "<th>Item Description</th>"
			  + "<th>Update</th><th>Remove</th></tr>";
			
			 String query = "select * from items";
			 Statement stmt = con.createStatement();
			 ResultSet rs = stmt.executeQuery(query);
			 
			 // iterate through the rows in the result set
			 while (rs.next())
			 {
				 String ItemID = Integer.toString(rs.getInt("ItemID"));
				 String ItemCode = rs.getString("ItemCode");
				 String ItemName = rs.getString("ItemName");
				 String ItemPrice = Double.toString(rs.getDouble("ItemPrice"));
				 String ItemDesc = rs.getString("ItemDesc");
				 // Add into the html table
				 output += "<tr><td>" + ItemCode + "</td>";
				 output += "<td>" + ItemName + "</td>";
				 output += "<td>" + ItemPrice + "</td>"; 
				
				output += "<td>" + ItemDesc + "</td>";
				 // buttons
				 output += "<td><input name='btnUpdate' "
				 + " type='button' value='Update'></td>"
				 + "<td><form method='post' action='items.jsp'>"
				 + "<input name='btnRemove' "
				 + " type='submit' value='Remove'>"
				 + "<input name='itemID' type='hidden' "
				 + " value='" + ItemID + "'>" + "</form></td></tr>";
			 	}
			 con.close();
			 // Complete the html table
			 output += "</table>";
			}
			catch (Exception e)
			{
				 output = "Error while reading the items.";
				 System.err.println(e.getMessage());
			}
		
			return output;
	}
}
