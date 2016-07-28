package crime.review.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EndorseQueries {

	private Connection connection;
	private PreparedStatement prestatement;
	private ResultSet resultset;
	
	public EndorseQueries() {
		try{
			// Load MS access driver class
			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5433/crime_database", "postgres","123456");
		}catch (Exception e){
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
	}
	
	//ADD ENDORSE
	public boolean add(Endorse endorse) {
		try {
			String query = "insert into endorse (fir_id,endorse_date,police_officer_id,reason_for_endorsement) values(?,?,?,?)";
			prestatement = connection.prepareStatement(query);
			prestatement.setInt(1,endorse.getFir_id());
			prestatement.setString(2,endorse.getEndorse_date());
			prestatement.setInt(3,endorse.getPolice_officer_id());
			prestatement.setString(4,endorse.getReason_for_endorsement());
			prestatement.executeUpdate();
			prestatement.close();
			return true;
		} catch (Exception e) {
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
			return false;
		}
	}
	
	//UPDATE ENDORSE
	public boolean update(Endorse endorse) {
		try {
			String query = "update endorse set fir_id=?,endorse_date=?,police_officer_id=?,reason_for_endorsement=? where id=?";
			prestatement = connection.prepareStatement(query);
			prestatement.setInt(1,endorse.getFir_id());
			prestatement.setString(2,endorse.getEndorse_date());
			prestatement.setInt(3,endorse.getPolice_officer_id());
			prestatement.setString(4,endorse.getReason_for_endorsement());
			prestatement.setInt(5,endorse.getId());
			prestatement.executeUpdate();
			prestatement.close();
			return true;
		} catch (Exception e) {
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
			return false;
		}
	}
	
	//GET ENDORSE FROM ID
	public Endorse getEndorse_from_id(int id){
		Endorse endorse = null;
		try {
			String query = "select * from endorse where id = ?";
			prestatement = connection.prepareStatement(query);
			prestatement.setInt(1, id);
			resultset = prestatement.executeQuery();
			if (resultset.next()) {
				endorse = new Endorse();
				endorse.setId(id);
				endorse.setFir_id(resultset.getInt("fir_id"));
				endorse.setEndorse_date(resultset.getString("endorse_date"));
				endorse.setPolice_officer_id(resultset.getInt("police_officer_id"));
				endorse.setReason_for_endorsement(resultset.getString("reason_for_endorsement"));
			}
			prestatement.close();
		} catch (Exception e) {
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
		return endorse;
	}
	
	//GET ENDORSE FROM FIR ID
	public List<Endorse> getEndorse_from_fir_id(int fir_id){
		List<Endorse> list_endorse = new ArrayList<Endorse>();
		Endorse endorse = null;
		try {
			String query = "select * from endorse where fir_id = ?";
			prestatement = connection.prepareStatement(query);
			prestatement.setInt(1, fir_id);
			resultset = prestatement.executeQuery();
			while(resultset.next()){
				endorse = new Endorse();
				endorse.setId(resultset.getInt("id"));
				endorse.setFir_id(resultset.getInt("fir_id"));
				endorse.setEndorse_date(resultset.getString("endorse_date"));
				endorse.setPolice_officer_id(resultset.getInt("police_officer_id"));
				endorse.setReason_for_endorsement(resultset.getString("reason_for_endorsement"));
				list_endorse.add(endorse);
			}
			prestatement.close();
		} catch (Exception e) {
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
		return list_endorse;
	}
	
	//GET ENDORSE FROM ENDORSE DATE
	public List<Endorse> getEndorse_from_endorse_date(String endorse_date){
		List<Endorse> list_endorse = new ArrayList<Endorse>();
		Endorse endorse = null;
		try {
			String query = "select * from endorse where endorse_date = ?";
			prestatement = connection.prepareStatement(query);
			prestatement.setString(1, endorse_date);
			resultset = prestatement.executeQuery();
			while(resultset.next()){
				endorse = new Endorse();
				endorse.setId(resultset.getInt("id"));
				endorse.setFir_id(resultset.getInt("fir_id"));
				endorse.setEndorse_date(resultset.getString("endorse_date"));
				endorse.setPolice_officer_id(resultset.getInt("police_officer_id"));
				endorse.setReason_for_endorsement(resultset.getString("reason_for_endorsement"));
				list_endorse.add(endorse);
			}
			prestatement.close();
		} catch (Exception e) {
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
		return list_endorse;
	}
	
	//GET ENDORSE FROM POLICE OFFICER ID
	public List<Endorse> getEndorse_from_police_officer_id(int police_officer_id){
		List<Endorse> list_endorse = new ArrayList<Endorse>();
		Endorse endorse = null;
		try {
			String query = "select * from endorse where police_officer_id = ?";
			prestatement = connection.prepareStatement(query);
			prestatement.setInt(1, police_officer_id);
			resultset = prestatement.executeQuery();
			while(resultset.next()){
				endorse = new Endorse();
				endorse.setId(resultset.getInt("id"));
				endorse.setFir_id(resultset.getInt("fir_id"));
				endorse.setEndorse_date(resultset.getString("endorse_date"));
				endorse.setPolice_officer_id(resultset.getInt("police_officer_id"));
				endorse.setReason_for_endorsement(resultset.getString("reason_for_endorsement"));
				list_endorse.add(endorse);
			}
			prestatement.close();
		} catch (Exception e) {
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
		return list_endorse;
	}
	
	//GET ENDORSE FROM REASON FOR ENDORSEMENT
	public List<Endorse> getEndorse_from_reason_for_endorsement(String reason_for_endorsement){
		List<Endorse> list_endorse = new ArrayList<Endorse>();
		Endorse endorse = null;
		try {
			String query = "select * from endorse where reason_for_endorsement = ?";
			prestatement = connection.prepareStatement(query);
			prestatement.setString(1, reason_for_endorsement);
			resultset = prestatement.executeQuery();
			while(resultset.next()){
				endorse = new Endorse();
				endorse.setId(resultset.getInt("id"));
				endorse.setFir_id(resultset.getInt("fir_id"));
				endorse.setEndorse_date(resultset.getString("endorse_date"));
				endorse.setPolice_officer_id(resultset.getInt("police_officer_id"));
				endorse.setReason_for_endorsement(resultset.getString("reason_for_endorsement"));
				list_endorse.add(endorse);
			}
			prestatement.close();
		} catch (Exception e) {
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
		return list_endorse;
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
