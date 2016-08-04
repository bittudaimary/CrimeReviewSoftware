package crime.review.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PoliceStationQueries {
	private Connection connection;
	private PreparedStatement prestatement;
	private ResultSet resultset;
	private DbaseConnection dbconnect;
	
	public PoliceStationQueries() {
		dbconnect = new DbaseConnection();
		connection = dbconnect.getConnection();
	}
	
	//ADD POLICE STATION
	public boolean add(PoliceStation police_station) {
		try {
			String query = "insert into police_station (name,district_id) values(?,?)";
			prestatement = connection.prepareStatement(query);
			prestatement.setString(1,police_station.getName());
			prestatement.setInt(2,police_station.getDistrict_id());
			prestatement.executeUpdate();
			prestatement.close();
			return true;
		} catch (Exception e) {
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
			return false;
		}
	}
	
	//UPDATE POLICE STATION
	public boolean update(PoliceStation police_station) {
		try {
			String query = "update police_station set name=?,district_id=? where id=?";
			prestatement = connection.prepareStatement(query);
			prestatement.setString(1,police_station.getName());
			prestatement.setInt(2,police_station.getDistrict_id());
			prestatement.setInt(3, police_station.getId());
			prestatement.executeUpdate();
			prestatement.close();
			return true;
		} catch (Exception e) {
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
			return false;
		}
	}
	
	//GET POLICE STATION FROM ID
	public PoliceStation getPolice_station_from_id(int id){
		PoliceStation police_station = null;
		try {
			String query = "select * from police_station where id = ?";
			prestatement = connection.prepareStatement(query);
			prestatement.setInt(1, id);
			resultset = prestatement.executeQuery();
			if (resultset.next()) {
				police_station = new PoliceStation();
				police_station.setId(resultset.getInt("id"));
				police_station.setName(resultset.getString("name"));
				police_station.setDistrict_id(resultset.getInt("district_id"));
			}
			prestatement.close();
		} catch (Exception e) {
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
		return police_station;
	}
	
	//GET POLICE STATION FROM NAME
	public List<PoliceStation> getPolice_station_from_name(String name){
		List<PoliceStation> list_police_station = new ArrayList<PoliceStation>();
		PoliceStation police_station = null;
		try {
			String query = "select * from police_station where name = ?";
			prestatement = connection.prepareStatement(query);
			prestatement.setString(1, name);
			resultset = prestatement.executeQuery();
			while (resultset.next()) {
				police_station = new PoliceStation();
				police_station.setId(resultset.getInt("id"));
				police_station.setName(resultset.getString("name"));
				police_station.setDistrict_id(resultset.getInt("district_id"));
				list_police_station.add(police_station);
			}
			prestatement.close();
		} catch (Exception e) {
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
		return list_police_station;
	}
	
	//GET POLICE STATION FROM DISTRICT ID
	public List<PoliceStation> getPolice_station_from_district_id(int district_id ){
		List<PoliceStation> list_police_station = new ArrayList<PoliceStation>();
		PoliceStation police_station = null;
		try {
			String query = "select * from police_station where district_id = ?";
			prestatement = connection.prepareStatement(query);
			prestatement.setInt(1, district_id);
			resultset = prestatement.executeQuery();
			while (resultset.next()) {
				police_station = new PoliceStation();
				police_station.setId(resultset.getInt("id"));
				police_station.setName(resultset.getString("name"));
				police_station.setDistrict_id(resultset.getInt("district_id"));
				list_police_station.add(police_station);
			}
			prestatement.close();
		} catch (Exception e) {
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
		return list_police_station;
	}
	
	//CLOSE CONNECTION
	public void close(){
		dbconnect.close();
	}
}
