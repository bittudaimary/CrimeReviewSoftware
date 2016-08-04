package crime.review.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CrimeReviewQueries {
	private Connection connection;
	private PreparedStatement prestatement;
	private ResultSet resultset;
	private DbaseConnection dbconnect;
	
	public CrimeReviewQueries() {
		dbconnect = new DbaseConnection();
		connection = dbconnect.getConnection();
	}
	
	//ADD CRIME REVIEW
	public boolean add(CrimeReview crime_review) {
		try {
			String query = "insert into crime_review (fir_id,date_of_review,action_taken,last_cd_with_date,reason_for_pending,remarks) values(?,?,?,?,?,?)";
			prestatement = connection.prepareStatement(query);
			prestatement.setInt(1,crime_review.getFir_id());
			prestatement.setString(2,crime_review.getDate_of_review());
			prestatement.setString(3,crime_review.getAction_taken());
			prestatement.setString(4,crime_review.getLast_cd_with_date());
			prestatement.setString(5,crime_review.getReason_for_pending());
			prestatement.setString(6,crime_review.getRemarks());
			prestatement.executeUpdate();
			prestatement.close();
			return true;
		} catch (Exception e) {
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
			return false;
		}
	}
	
	//UPDATE CRIME REVIEW
	public boolean update(CrimeReview crime_review) {
		try {
			String query = "update crime_review set fir_id=?,date_of_review=?,action_taken=?,last_cd_with_date=?,reason_for_pending=?,remarks=? where id=?";
			prestatement = connection.prepareStatement(query);
			prestatement.setInt(1,crime_review.getFir_id());
			prestatement.setString(2,crime_review.getDate_of_review());
			prestatement.setString(3,crime_review.getAction_taken());
			prestatement.setString(4,crime_review.getLast_cd_with_date());
			prestatement.setString(5,crime_review.getReason_for_pending());
			prestatement.setString(6,crime_review.getRemarks());
			prestatement.setInt(7,crime_review.getId());
			prestatement.executeUpdate();
			prestatement.close();
			return true;
		} catch (Exception e) {
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
			return false;
		}
	}
	
	//GET CRIME REVIEW FROM ID
	public CrimeReview getCrime_review_from_id(int id){
		CrimeReview crime_review = null;
		try {
			String query = "select * from crime_review where id = ?";
			prestatement = connection.prepareStatement(query);
			prestatement.setInt(1, id);
			resultset = prestatement.executeQuery();
			if (resultset.next()) {
				crime_review = new CrimeReview();
				crime_review.setId(resultset.getInt("id"));
				crime_review.setFir_id(resultset.getInt("fir_id"));
				crime_review.setDate_of_review(resultset.getString("date_of_review"));
				crime_review.setAction_taken(resultset.getString("action_taken"));
				crime_review.setLast_cd_with_date(resultset.getString("last_cd_with_date"));
				crime_review.setReason_for_pending(resultset.getString("reason_for_pending"));
				crime_review.setRemarks(resultset.getString("remarks"));
			}
			prestatement.close();
		} catch (Exception e) {
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
		return crime_review;
	}
	
	//GET CRIME REVIEW FROM FIR ID
	public List<CrimeReview> getCrime_review_from_fir_id(int fir_id){
		List<CrimeReview> list_crime_review = new ArrayList<CrimeReview>();
		CrimeReview crime_review = null;
		try {
			String query = "select * from crime_review where fir_id = ?";
			prestatement = connection.prepareStatement(query);
			prestatement.setInt(1, fir_id);
			resultset = prestatement.executeQuery();
			while(resultset.next()) {
				crime_review = new CrimeReview();
				crime_review.setId(resultset.getInt("id"));
				crime_review.setFir_id(resultset.getInt("fir_id"));
				crime_review.setDate_of_review(resultset.getString("date_of_review"));
				crime_review.setAction_taken(resultset.getString("action_taken"));
				crime_review.setLast_cd_with_date(resultset.getString("last_cd_with_date"));
				crime_review.setReason_for_pending(resultset.getString("reason_for_pending"));
				crime_review.setRemarks(resultset.getString("remarks"));
				list_crime_review.add(crime_review);
			}
			prestatement.close();
		} catch (Exception e) {
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
		return list_crime_review;
	}
	
	//GET CRIME REVIEW FROM DATE OF REVIEW
	public List<CrimeReview> getCrime_review_from_date_of_review(String date_of_review){
		List<CrimeReview> list_crime_review = new ArrayList<CrimeReview>();
		CrimeReview crime_review = null;
		try {
			String query = "select * from crime_review where date_of_review = ?";
			prestatement = connection.prepareStatement(query);
			prestatement.setString(1, date_of_review);
			resultset = prestatement.executeQuery();
			while(resultset.next()) {
				crime_review = new CrimeReview();
				crime_review.setId(resultset.getInt("id"));
				crime_review.setFir_id(resultset.getInt("fir_id"));
				crime_review.setDate_of_review(resultset.getString("date_of_review"));
				crime_review.setAction_taken(resultset.getString("action_taken"));
				crime_review.setLast_cd_with_date(resultset.getString("last_cd_with_date"));
				crime_review.setReason_for_pending(resultset.getString("reason_for_pending"));
				crime_review.setRemarks(resultset.getString("remarks"));
				list_crime_review.add(crime_review);
			}
			prestatement.close();
		} catch (Exception e) {
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
		return list_crime_review;
	}
	
	//GET CRIME REVIEW FROM ACTION TAKEN
	public List<CrimeReview> getCrime_review_from_action_taken(String action_taken){
		List<CrimeReview> list_crime_review = new ArrayList<CrimeReview>();
		CrimeReview crime_review = null;
		try {
			String query = "select * from crime_review where action_taken = ?";
			prestatement = connection.prepareStatement(query);
			prestatement.setString(1, action_taken);
			resultset = prestatement.executeQuery();
			while(resultset.next()) {
				crime_review = new CrimeReview();
				crime_review.setId(resultset.getInt("id"));
				crime_review.setFir_id(resultset.getInt("fir_id"));
				crime_review.setDate_of_review(resultset.getString("date_of_review"));
				crime_review.setAction_taken(resultset.getString("action_taken"));
				crime_review.setLast_cd_with_date(resultset.getString("last_cd_with_date"));
				crime_review.setReason_for_pending(resultset.getString("reason_for_pending"));
				crime_review.setRemarks(resultset.getString("remarks"));
				list_crime_review.add(crime_review);
			}
			prestatement.close();
		} catch (Exception e) {
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
		return list_crime_review;
	}
	
	//GET CRIME REVIEW FROM LAST CD WITH DATE
	public List<CrimeReview> getCrime_review_from_last_cd_with_date(String last_cd_with_date){
		List<CrimeReview> list_crime_review = new ArrayList<CrimeReview>();
		CrimeReview crime_review = null;
		try {
			String query = "select * from crime_review where last_cd_with_date = ?";
			prestatement = connection.prepareStatement(query);
			prestatement.setString(1, last_cd_with_date);
			resultset = prestatement.executeQuery();
			while(resultset.next()) {
				crime_review = new CrimeReview();
				crime_review.setId(resultset.getInt("id"));
				crime_review.setFir_id(resultset.getInt("fir_id"));
				crime_review.setDate_of_review(resultset.getString("date_of_review"));
				crime_review.setAction_taken(resultset.getString("action_taken"));
				crime_review.setLast_cd_with_date(resultset.getString("last_cd_with_date"));
				crime_review.setReason_for_pending(resultset.getString("reason_for_pending"));
				crime_review.setRemarks(resultset.getString("remarks"));
				list_crime_review.add(crime_review);
			}
			prestatement.close();
		} catch (Exception e) {
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
		return list_crime_review;
	}
	
	//GET CRIME REVIEW FROM REASON FOR PENDING
	public List<CrimeReview> getCrime_review_from_reason_for_pending(String reason_for_pending){
		List<CrimeReview> list_crime_review = new ArrayList<CrimeReview>();
		CrimeReview crime_review = null;
		try {
			String query = "select * from crime_review where reason_for_pending = ?";
			prestatement = connection.prepareStatement(query);
			prestatement.setString(1, reason_for_pending);
			resultset = prestatement.executeQuery();
			while(resultset.next()) {
				crime_review = new CrimeReview();
				crime_review.setId(resultset.getInt("id"));
				crime_review.setFir_id(resultset.getInt("fir_id"));
				crime_review.setDate_of_review(resultset.getString("date_of_review"));
				crime_review.setAction_taken(resultset.getString("action_taken"));
				crime_review.setLast_cd_with_date(resultset.getString("last_cd_with_date"));
				crime_review.setReason_for_pending(resultset.getString("reason_for_pending"));
				crime_review.setRemarks(resultset.getString("remarks"));
				list_crime_review.add(crime_review);
			}
			prestatement.close();
		} catch (Exception e) {
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
		return list_crime_review;
	}
	
	public List<CrimeReview> getCrime_review_from_remarks(String remarks){
		List<CrimeReview> list_crime_review = new ArrayList<CrimeReview>();
		CrimeReview crime_review = null;
		try {
			String query = "select * from crime_review where remarks = ?";
			prestatement = connection.prepareStatement(query);
			prestatement.setString(1, remarks);
			resultset = prestatement.executeQuery();
			while(resultset.next()) {
				crime_review = new CrimeReview();
				crime_review.setId(resultset.getInt("id"));
				crime_review.setFir_id(resultset.getInt("fir_id"));
				crime_review.setDate_of_review(resultset.getString("date_of_review"));
				crime_review.setAction_taken(resultset.getString("action_taken"));
				crime_review.setLast_cd_with_date(resultset.getString("last_cd_with_date"));
				crime_review.setReason_for_pending(resultset.getString("reason_for_pending"));
				crime_review.setRemarks(resultset.getString("remarks"));
				list_crime_review.add(crime_review);
			}
			prestatement.close();
		} catch (Exception e) {
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
		return list_crime_review;
	}
	
	//CLOSE CONNECTION
	public void close(){
		dbconnect.close();
	}
}
