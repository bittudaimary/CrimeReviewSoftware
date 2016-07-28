package crime.review.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MajorHeadQueries {
	private Connection connection;
	private PreparedStatement prestatement;
	private ResultSet resultset;
	
	public MajorHeadQueries() {
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
	public boolean add(MajorHead major_head) {
		try {
			String query = "insert into major_head (name) values(?)";
			prestatement = connection.prepareStatement(query);
			prestatement.setString(1,major_head.getName());
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
	public boolean edit(MajorHead major_head) {
		try {
			String query = "update major_head set name=? where id=?";
			prestatement = connection.prepareStatement(query);
			prestatement.setString(1,major_head.getName());
			prestatement.setInt(2,major_head.getId());
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
	public MajorHead getMajorHead_from_id(int id){
		MajorHead major_head = null;
		try {
			String query = "select * from major_head where id = ?";
			prestatement = connection.prepareStatement(query);
			prestatement.setInt(1, id);
			resultset = prestatement.executeQuery();
			if (resultset.next()) {
				major_head = new MajorHead();
				major_head.setId(resultset.getInt("id"));
				major_head.setName(resultset.getString("name"));
			}
			prestatement.close();
		} catch (Exception e) {
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
		return major_head;
	}
	
	//GET DISTRICT FROM NAME
	public MajorHead getMajorHead_from_name(String name){
		MajorHead major_head = null;
		try {
			String query = "select * from major_head where name = ?";
			prestatement = connection.prepareStatement(query);
			prestatement.setString(1, name);
			resultset = prestatement.executeQuery();
			if (resultset.next()) {
				major_head = new MajorHead();
				major_head.setId(resultset.getInt("id"));
				major_head.setName(resultset.getString("name"));
			}
			prestatement.close();
		} catch (Exception e) {
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
		return major_head;
	}	
	
	public List<MajorHead> getAllMajorHead(){
		List<MajorHead> list_major_head = new ArrayList<MajorHead>();
		MajorHead major_head = null;
		try {
			String query = "select * from major_head";
			prestatement = connection.prepareStatement(query);
			resultset = prestatement.executeQuery();
			while(resultset.next()) {
				major_head = new MajorHead();
				major_head.setId(resultset.getInt("id"));
				major_head.setName(resultset.getString("name"));
				list_major_head.add(major_head);
			}
			prestatement.close();
		} catch (Exception e) {
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
		return list_major_head;
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
