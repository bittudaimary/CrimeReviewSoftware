package crime.review.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PoliceOfficerQueries {
	private Connection connection;
	private PreparedStatement prestatement;
	private ResultSet resultset;
	
	public PoliceOfficerQueries() {
		try{
			// Load MS access driver class
			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5433/crime_database", "postgres","123456");
		}catch (Exception e){
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
	}
	
	//ADD POLICE OFFICER
	public boolean add(PoliceOfficer police_officer) {
		try {
			String query = "insert into police_officer (fname,mname,lname,posting,dob,contact) values(?,?,?,?,?,?)";
			prestatement = connection.prepareStatement(query);
			prestatement.setString(1, police_officer.getFname());
			prestatement.setString(2, police_officer.getMname());
			prestatement.setString(3, police_officer.getLname());
			prestatement.setString(4, police_officer.getPosting());
			prestatement.setString(5, police_officer.getDob());
			prestatement.setString(6, police_officer.getContact());
			prestatement.executeUpdate();
			prestatement.close();
			return true;
		} catch (Exception e) {
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
			return false;
		}
	}
	
	//UPDATE POLICE OFFICER
	public boolean update(PoliceOfficer police_officer) {
		try {
			String query = "update police_officer set fname=?,mname=?,lname=?,posting=?,dob=?,contact=? where id=?";
			prestatement = connection.prepareStatement(query);
			prestatement.setString(1, police_officer.getFname());
			prestatement.setString(2, police_officer.getMname());
			prestatement.setString(3, police_officer.getLname());
			prestatement.setString(4, police_officer.getPosting());
			prestatement.setString(5, police_officer.getDob());
			prestatement.setString(6, police_officer.getContact());
			prestatement.setInt(7, police_officer.getId());
			prestatement.executeUpdate();
			prestatement.close();
			return true;
		} catch (Exception e) {
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
			return false;
		}
	}
	
	//GET POLICE OFFICER FROM POLICE ID
	public PoliceOfficer getPoliceOfficer_from_id(int id){
		PoliceOfficer police_officer = null;
		try {
			String query = "select * from police_officer where id = ?";
			prestatement = connection.prepareStatement(query);
			prestatement.setInt(1, id);
			resultset = prestatement.executeQuery();
			if (resultset.next()) {
				police_officer = new PoliceOfficer();
				police_officer.setId(resultset.getInt("id"));
				police_officer.setFname(resultset.getString("fname"));
				police_officer.setMname(resultset.getString("mname"));
				police_officer.setLname(resultset.getString("lname"));
				police_officer.setPosting(resultset.getString("posting"));
				police_officer.setDob(resultset.getString("dob"));
				police_officer.setContact(resultset.getString("contact"));
			}
			prestatement.close();
		} catch (Exception e) {
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
		return police_officer;
	}
	
	//GET POLICE OFFICER FROM FNAME
	public List<PoliceOfficer> getPoliceOfficer_from_fname(String fname){
		List<PoliceOfficer> list_police_officer = new ArrayList<PoliceOfficer>();
		PoliceOfficer police_officer = null;
		try {
			String query = "select * from police_officer where fname = ?";
			prestatement = connection.prepareStatement(query);
			prestatement.setString(1, fname);
			resultset = prestatement.executeQuery();
			while(resultset.next()) {
				police_officer = new PoliceOfficer();
				police_officer.setId(resultset.getInt("id"));
				police_officer.setFname(resultset.getString("fname"));
				police_officer.setMname(resultset.getString("mname"));
				police_officer.setLname(resultset.getString("lname"));
				police_officer.setPosting(resultset.getString("posting"));
				police_officer.setDob(resultset.getString("dob"));
				police_officer.setContact(resultset.getString("contact"));
				list_police_officer.add(police_officer);
			}
			prestatement.close();
		} catch (Exception e) {
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
		return list_police_officer;
	}
	
	//GET POLICE OFFICER FROM MNAME
	public List<PoliceOfficer> getPoliceOfficer_from_mname(String mname){
		List<PoliceOfficer> list_police_officer = new ArrayList<PoliceOfficer>();
		PoliceOfficer police_officer = null;
		try {
			String query = "select * from police_officer where mname = ?";
			prestatement = connection.prepareStatement(query);
			prestatement.setString(1, mname);
			resultset = prestatement.executeQuery();
			while(resultset.next()) {
				police_officer = new PoliceOfficer();
				police_officer.setId(resultset.getInt("id"));
				police_officer.setFname(resultset.getString("fname"));
				police_officer.setMname(resultset.getString("mname"));
				police_officer.setLname(resultset.getString("lname"));
				police_officer.setPosting(resultset.getString("posting"));
				police_officer.setDob(resultset.getString("dob"));
				police_officer.setContact(resultset.getString("contact"));
				list_police_officer.add(police_officer);
			}
			prestatement.close();
		} catch (Exception e) {
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
		return list_police_officer;
	}
	
	//GET POLICE OFFICER FROM LNAME
	public List<PoliceOfficer> getPoliceOfficer_from_lname(String lname){
		List<PoliceOfficer> list_police_officer = new ArrayList<PoliceOfficer>();
		PoliceOfficer police_officer = null;
		try {
			String query = "select * from police_officer where lname = ?";
			prestatement = connection.prepareStatement(query);
			prestatement.setString(1, lname);
			resultset = prestatement.executeQuery();
			while(resultset.next()) {
				police_officer = new PoliceOfficer();
				police_officer.setId(resultset.getInt("id"));
				police_officer.setFname(resultset.getString("fname"));
				police_officer.setMname(resultset.getString("mname"));
				police_officer.setLname(resultset.getString("lname"));
				police_officer.setPosting(resultset.getString("posting"));
				police_officer.setDob(resultset.getString("dob"));
				police_officer.setContact(resultset.getString("contact"));
				list_police_officer.add(police_officer);
			}
			prestatement.close();
		} catch (Exception e) {
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
		return list_police_officer;
	}
	
	//GET POLICE OFFICER FROM POSTING
	public List<PoliceOfficer> getPoliceOfficer_from_posting(String posting){
		List<PoliceOfficer> list_police_officer = new ArrayList<PoliceOfficer>();
		PoliceOfficer police_officer = null;
		try {
			String query = "select * from police_officer where posting = ?";
			prestatement = connection.prepareStatement(query);
			prestatement.setString(1, posting);
			resultset = prestatement.executeQuery();
			while(resultset.next()) {
				police_officer = new PoliceOfficer();
				police_officer.setId(resultset.getInt("id"));
				police_officer.setFname(resultset.getString("fname"));
				police_officer.setMname(resultset.getString("mname"));
				police_officer.setLname(resultset.getString("lname"));
				police_officer.setPosting(resultset.getString("posting"));
				police_officer.setDob(resultset.getString("dob"));
				police_officer.setContact(resultset.getString("contact"));
				list_police_officer.add(police_officer);
			}
			prestatement.close();
		} catch (Exception e) {
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
		return list_police_officer;
	}
	
	//GET POLICE OFFICER FROM DOB
	public List<PoliceOfficer> getPoliceOfficer_from_dob(String dob){
		List<PoliceOfficer> list_police_officer = new ArrayList<PoliceOfficer>();
		PoliceOfficer police_officer = null;
		try {
			String query = "select * from police_officer where dob = ?";
			prestatement = connection.prepareStatement(query);
			prestatement.setString(1, dob);
			resultset = prestatement.executeQuery();
			while(resultset.next()) {
				police_officer = new PoliceOfficer();
				police_officer.setId(resultset.getInt("id"));
				police_officer.setFname(resultset.getString("fname"));
				police_officer.setMname(resultset.getString("mname"));
				police_officer.setLname(resultset.getString("lname"));
				police_officer.setPosting(resultset.getString("posting"));
				police_officer.setDob(resultset.getString("dob"));
				police_officer.setContact(resultset.getString("contact"));
				list_police_officer.add(police_officer);
			}
			prestatement.close();
		} catch (Exception e) {
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
		return list_police_officer;
	}
	
	//GET POLICE OFFICER FROM CONTACT
	public List<PoliceOfficer> getPoliceOfficer_from_contact(String contact){
		List<PoliceOfficer> list_police_officer = new ArrayList<PoliceOfficer>();
		PoliceOfficer police_officer = null;
		try {
			String query = "select * from police_officer where contact = ?";
			prestatement = connection.prepareStatement(query);
			prestatement.setString(1, contact);
			resultset = prestatement.executeQuery();
			while(resultset.next()) {
				police_officer = new PoliceOfficer();
				police_officer.setId(resultset.getInt("id"));
				police_officer.setFname(resultset.getString("fname"));
				police_officer.setMname(resultset.getString("mname"));
				police_officer.setLname(resultset.getString("lname"));
				police_officer.setPosting(resultset.getString("posting"));
				police_officer.setDob(resultset.getString("dob"));
				police_officer.setContact(resultset.getString("contact"));
				list_police_officer.add(police_officer);
			}
			prestatement.close();
		} catch (Exception e) {
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
		return list_police_officer;
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