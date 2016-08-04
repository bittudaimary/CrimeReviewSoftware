package crime.review.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ClassofOffenceQueries {
	private Connection connection;
	private PreparedStatement prestatement;
	private ResultSet resultset;
	private DbaseConnection dbconnect;
	
	public ClassofOffenceQueries() {
		dbconnect = new DbaseConnection();
		connection = dbconnect.getConnection();
	}
	
	//ADD DISTRICT
	public boolean add(ClassofOffence class_of_offence) {
		try {
			String query = "insert into class_of_offence (name) values(?)";
			prestatement = connection.prepareStatement(query);
			prestatement.setString(1,class_of_offence.getName());
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
	public boolean edit(ClassofOffence class_of_offence) {
		try {
			String query = "update class_of_offence set name=? where id=?";
			prestatement = connection.prepareStatement(query);
			prestatement.setString(1,class_of_offence.getName());
			prestatement.setInt(2,class_of_offence.getId());
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
	public ClassofOffence getClassofOffence_from_id(int id){
		ClassofOffence class_of_offence = null;
		try {
			String query = "select * from class_of_offence where id = ?";
			prestatement = connection.prepareStatement(query);
			prestatement.setInt(1, id);
			resultset = prestatement.executeQuery();
			if (resultset.next()) {
				class_of_offence = new ClassofOffence();
				class_of_offence.setId(resultset.getInt("id"));
				class_of_offence.setName(resultset.getString("name"));
			}
			prestatement.close();
		} catch (Exception e) {
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
		return class_of_offence;
	}
	
	//GET DISTRICT FROM NAME
	public ClassofOffence getClassofOffence_from_name(String name){
		ClassofOffence class_of_offence = null;
		try {
			String query = "select * from class_of_offence where name = ?";
			prestatement = connection.prepareStatement(query);
			prestatement.setString(1, name);
			resultset = prestatement.executeQuery();
			if (resultset.next()) {
				class_of_offence = new ClassofOffence();
				class_of_offence.setId(resultset.getInt("id"));
				class_of_offence.setName(resultset.getString("name"));
			}
			prestatement.close();
		} catch (Exception e) {
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
		return class_of_offence;
	}	
	
	public List<ClassofOffence> getAllClassofOffence(){
		List<ClassofOffence> list_class_of_offence = new ArrayList<ClassofOffence>();
		ClassofOffence class_of_offence = null;
		try {
			String query = "select * from class_of_offence";
			prestatement = connection.prepareStatement(query);
			resultset = prestatement.executeQuery();
			while(resultset.next()) {
				class_of_offence = new ClassofOffence();
				class_of_offence.setId(resultset.getInt("id"));
				class_of_offence.setName(resultset.getString("name"));
				list_class_of_offence.add(class_of_offence);
			}
			prestatement.close();
		} catch (Exception e) {
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
		return list_class_of_offence;
	}
	
	//CLOSE CONNECTION
	public void close(){
		dbconnect.close();
	}
}
