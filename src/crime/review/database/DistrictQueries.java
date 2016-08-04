package crime.review.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DistrictQueries {
	private Connection connection;
	private PreparedStatement prestatement;
	private ResultSet resultset;
	private DbaseConnection dbconnect;
	
	public DistrictQueries() {
		dbconnect = new DbaseConnection();
		connection = dbconnect.getConnection();
	}
	
	//ADD DISTRICT
	public boolean add(District district) {
		try {
			String query = "insert into district (name) values(?)";
			prestatement = connection.prepareStatement(query);
			prestatement.setString(1,district.getName());
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
	public boolean edit(District district) {
		try {
			String query = "update district set name=? where id=?";
			prestatement = connection.prepareStatement(query);
			prestatement.setString(1,district.getName());
			prestatement.setInt(2,district.getId());
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
	public District getDistrict_from_id(int id){
		District district = null;
		try {
			String query = "select * from district where id = ?";
			prestatement = connection.prepareStatement(query);
			prestatement.setInt(1, id);
			resultset = prestatement.executeQuery();
			if (resultset.next()) {
				district = new District();
				district.setId(resultset.getInt("id"));
				district.setName(resultset.getString("name"));
			}
			prestatement.close();
		} catch (Exception e) {
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
		return district;
	}
	
	//GET DISTRICT FROM NAME
	public District getDistrict_from_name(String name){
		District district = null;
		try {
			String query = "select * from district where name = ?";
			prestatement = connection.prepareStatement(query);
			prestatement.setString(1, name);
			resultset = prestatement.executeQuery();
			if (resultset.next()) {
				district = new District();
				district.setId(resultset.getInt("id"));
				district.setName(resultset.getString("name"));
			}
			prestatement.close();
		} catch (Exception e) {
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
		return district;
	}	
	
	public List<District> getAllDistrict(){
		List<District> list_district = new ArrayList<District>();
		District district = null;
		try {
			String query = "select * from district";
			prestatement = connection.prepareStatement(query);
			resultset = prestatement.executeQuery();
			while(resultset.next()) {
				district = new District();
				district.setId(resultset.getInt("id"));
				district.setName(resultset.getString("name"));
				list_district.add(district);
			}
			prestatement.close();
		} catch (Exception e) {
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
		return list_district;
	}
	
	//CLOSE CONNECTION
	public void close(){
		dbconnect.close();
	}
}
