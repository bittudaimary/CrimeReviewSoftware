package crime.review.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MinorHeadQueries {
	private Connection connection;
	private PreparedStatement prestatement;
	private ResultSet resultset;
	
	public MinorHeadQueries() {
		try{
			// Load MS access driver class
			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5433/crime_database", "postgres","123456");
		}catch (Exception e){
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
	}
	
	//ADD DISTRICT
	public boolean add(MinorHead minor_head) {
		try {
			String query = "insert into minor_head (name) values(?)";
			prestatement = connection.prepareStatement(query);
			prestatement.setString(1,minor_head.getName());
			prestatement.executeUpdate();
			prestatement.close();
			return true;
		} catch (Exception e) {
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
			return false;
		}
	}
	
	//EDIT DISTRICT
	public boolean edit(MinorHead minor_head) {
		try {
			String query = "update minor_head set name=? where id=?";
			prestatement = connection.prepareStatement(query);
			prestatement.setString(1,minor_head.getName());
			prestatement.setInt(2,minor_head.getId());
			prestatement.executeUpdate();
			prestatement.close();
			return true;
		} catch (Exception e) {
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
			return false;
		}
	}
	
	//GET DISTRIICT FROM DISTRICT ID
	public MinorHead getMinorHead_from_id(int id){
		MinorHead minor_head = null;
		try {
			String query = "select * from minor_head where id = ?";
			prestatement = connection.prepareStatement(query);
			prestatement.setInt(1, id);
			resultset = prestatement.executeQuery();
			if (resultset.next()) {
				minor_head = new MinorHead();
				minor_head.setId(resultset.getInt("id"));
				minor_head.setName(resultset.getString("name"));
			}
			prestatement.close();
		} catch (Exception e) {
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
		return minor_head;
	}
	
	//GET DISTRICT FROM NAME
	public MinorHead getMinorHead_from_name(String name){
		MinorHead minor_head = null;
		try {
			String query = "select * from minor_head where name = ?";
			prestatement = connection.prepareStatement(query);
			prestatement.setString(1, name);
			resultset = prestatement.executeQuery();
			if (resultset.next()) {
				minor_head = new MinorHead();
				minor_head.setId(resultset.getInt("id"));
				minor_head.setName(resultset.getString("name"));
			}
			prestatement.close();
		} catch (Exception e) {
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
		return minor_head;
	}	
	
	public List<MinorHead> getAllMinorHead(){
		List<MinorHead> list_minor_head = new ArrayList<MinorHead>();
		MinorHead minor_head = null;
		try {
			String query = "select * from minor_head";
			prestatement = connection.prepareStatement(query);
			resultset = prestatement.executeQuery();
			while(resultset.next()) {
				minor_head = new MinorHead();
				minor_head.setId(resultset.getInt("id"));
				minor_head.setName(resultset.getString("name"));
				list_minor_head.add(minor_head);
			}
			prestatement.close();
		} catch (Exception e) {
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
		return list_minor_head;
	}
	
	//CLOSE CONNECTION
	public void close(){
		try {
			connection.close();
		} catch (Exception e) {
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
	}
}
