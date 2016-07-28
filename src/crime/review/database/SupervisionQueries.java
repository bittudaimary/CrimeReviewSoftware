package crime.review.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SupervisionQueries {
	private Connection connection;
	private PreparedStatement prestatement;
	private ResultSet resultset;
	
	public SupervisionQueries() {
		try{
			// Load MS access driver class
			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5433/crime_database", "postgres","123456");
		}catch (Exception e){
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
	}
	
	//ADD SUPERVISION
	public boolean add(Supervision supervision) {
		try {
			String query = "insert into supervision (fir_id,officer_id) values(?,?)";
			prestatement = connection.prepareStatement(query);
			prestatement.setInt(1,supervision.getFir_id());
			prestatement.setInt(2,supervision.getOfficer_id());
			prestatement.executeUpdate();
			prestatement.close();
			return true;
		} catch (Exception e) {
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
			return false;
		}
	}
	
	public boolean update(Supervision supervision) {
		try {
			String query = "update supervision set fir_id=?,officer_id=? where id=?";
			prestatement = connection.prepareStatement(query);
			prestatement.setInt(1,supervision.getFir_id());
			prestatement.setInt(2,supervision.getOfficer_id());
			prestatement.setInt(3,supervision.getId());
			prestatement.executeUpdate();
			prestatement.close();
			return true;
		} catch (Exception e) {
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
			return false;
		}
	}
	
	//GET SUUPERVISION FROM ID
	public Supervision getSupervision_from_id(int id){
		Supervision supervision = null;
		try {
			String query = "select * from supervision where id = ?";
			prestatement = connection.prepareStatement(query);
			prestatement.setInt(1, id);
			resultset = prestatement.executeQuery();
			if (resultset.next()) {
				supervision = new Supervision();
				supervision.setId(resultset.getInt("id"));
				supervision.setFir_id(resultset.getInt("fir_id"));
				supervision.setOfficer_id(resultset.getInt("officer_id"));
			}
			prestatement.close();
		} catch (Exception e) {
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
		return supervision;
	}
	
	//GET SUPERVISION FROM FIR ID
	public Supervision getSupervision_from_fir_id(int fir_id){
		Supervision supervision = null;
		try {
			String query = "select * from supervision where fir_id = ?";
			prestatement = connection.prepareStatement(query);
			prestatement.setInt(1, fir_id);
			resultset = prestatement.executeQuery();
			if (resultset.next()) {
				supervision = new Supervision();
				supervision.setId(resultset.getInt("id"));
				supervision.setFir_id(resultset.getInt("fir_id"));
				supervision.setOfficer_id(resultset.getInt("officer_id"));
			}
			prestatement.close();
		} catch (Exception e) {
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
		return supervision;
	}
	
	//GET SUPERVISION FROM OFFICER ID
	public List<Supervision> getSupervision_from_officer_id(int officer_id){
		List<Supervision> list_supervision = new ArrayList<Supervision>();
		Supervision supervision = null;
		try {
			String query = "select * from supervision where officer_id = ?";
			prestatement = connection.prepareStatement(query);
			prestatement.setInt(1, officer_id);
			resultset = prestatement.executeQuery();
			while(resultset.next()) {
				supervision = new Supervision();
				supervision.setId(resultset.getInt("id"));
				supervision.setFir_id(resultset.getInt("fir_id"));
				supervision.setOfficer_id(resultset.getInt("officer_id"));
				list_supervision.add(supervision);
			}
			prestatement.close();
		} catch (Exception e) {
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
		return list_supervision;
	}
	
	//GET ALL SUPERVISION
	public List<Supervision> getAllSupervision(){
		List<Supervision> list_supervision = new ArrayList<Supervision>();
		Supervision supervision = null;
		try {
			String query = "select * from supervision";
			prestatement = connection.prepareStatement(query);
			resultset = prestatement.executeQuery();
			while(resultset.next()) {
				supervision = new Supervision();
				supervision.setId(resultset.getInt("id"));
				supervision.setFir_id(resultset.getInt("fir_id"));
				supervision.setOfficer_id(resultset.getInt("officer_id"));
				list_supervision.add(supervision);
			}
			prestatement.close();
		} catch (Exception e) {
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
		return list_supervision;
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
