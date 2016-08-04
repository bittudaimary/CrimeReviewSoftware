package crime.review.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DisposeQueries {
	private Connection connection;
	private PreparedStatement prestatement;
	private ResultSet resultset;
	private DbaseConnection dbconnect;
	
	public DisposeQueries() {
		dbconnect = new DbaseConnection();
		connection = dbconnect.getConnection();
	}
	
	//ADD DISPOSE
	public boolean add(Dispose dispose) {
		try {
			String query = "insert into dispose (type_of_finalform,date_of_disposal,fir_id) values(?,?,?)";
			prestatement = connection.prepareStatement(query);
			prestatement.setBoolean(1,dispose.getType_of_final_form());
			prestatement.setString(2,dispose.getDate_of_disposal());
			prestatement.setInt(3, dispose.getFir_id());
			prestatement.executeUpdate();
			prestatement.close();
			return true;
		} catch (Exception e) {
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
			return false;
		}
	}
	
	//UPDATE
	public boolean update(Dispose dispose) {
		try {
			String query = "update dispose set type_of_finalform=?,date_of_disposal=?,fir_id=? where id=?";
			prestatement = connection.prepareStatement(query);
			prestatement.setBoolean(1,dispose.getType_of_final_form());
			prestatement.setString(2,dispose.getDate_of_disposal());
			prestatement.setInt(3, dispose.getFir_id());
			prestatement.setInt(4, dispose.getId());
			prestatement.executeUpdate();
			prestatement.close();
			return true;
		} catch (Exception e) {
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
			return false;
		}
	}
	
	//GET DISPOSE FROM ID
	public Dispose getDispose_from_id(int id){
		Dispose dispose = null;
		try {
			String query = "select * from dispose where id = ?";
			prestatement = connection.prepareStatement(query);
			prestatement.setInt(1, id);
			resultset = prestatement.executeQuery();
			if (resultset.next()) {
				dispose = new Dispose();
				dispose.setId(resultset.getInt("id"));
				dispose.setType_of_final_form(resultset.getBoolean("type_of_finalform"));
				dispose.setDate_of_disposal(resultset.getString("date_of_disposal"));
				dispose.setFir_id(resultset.getInt("fir_id"));
			}
			prestatement.close();
		} catch (Exception e) {
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
		return dispose;
	}
	
	//GET DISPOSE FROM FIR ID
	public Dispose getDispose_from_fir_id(int fir_id){
		Dispose dispose = null;
		try {
			String query = "select * from dispose where fir_id = ?";
			prestatement = connection.prepareStatement(query);
			prestatement.setInt(1, fir_id);
			resultset = prestatement.executeQuery();
			if (resultset.next()) {
				dispose = new Dispose();
				dispose.setId(resultset.getInt("id"));
				dispose.setType_of_final_form(resultset.getBoolean("type_of_finalform"));
				dispose.setDate_of_disposal(resultset.getString("date_of_disposal"));
				dispose.setFir_id(resultset.getInt("fir_id"));
			}
			prestatement.close();
		} catch (Exception e) {
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
		return dispose;
	}
	
	//GET DISPOSE FROM TYPE OF FINAL FORM
	public List<Dispose> getDispose_from_type_of_finalform(Boolean type_of_finalform){
		List<Dispose> list_dispose = new ArrayList<Dispose>();
		Dispose dispose = null;
		try {
			String query = "select * from dispose where type_of_finalform = ?";
			prestatement = connection.prepareStatement(query);
			prestatement.setBoolean(1, type_of_finalform);
			resultset = prestatement.executeQuery();
			while(resultset.next()) {
				dispose = new Dispose();
				dispose.setId(resultset.getInt("id"));
				dispose.setType_of_final_form(resultset.getBoolean("type_of_finalform"));
				dispose.setDate_of_disposal(resultset.getString("date_of_disposal"));
				dispose.setFir_id(resultset.getInt("fir_id"));
				list_dispose.add(dispose);
			}
			prestatement.close();
		} catch (Exception e) {
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
		return list_dispose;
	}
	
	//GET DISPOSE FROM DATE OF DISPOSAL
	public List<Dispose> getDispose_from_date_of_disposal(String date_of_disposal){
		List<Dispose> list_dispose = new ArrayList<Dispose>();
		Dispose dispose = null;
		try {
			String query = "select * from dispose where date_of_disposal = ?";
			prestatement = connection.prepareStatement(query);
			prestatement.setString(1, date_of_disposal);
			resultset = prestatement.executeQuery();
			while(resultset.next()) {
				dispose = new Dispose();
				dispose.setId(resultset.getInt("id"));
				dispose.setType_of_final_form(resultset.getBoolean("type_of_finalform"));
				dispose.setDate_of_disposal(resultset.getString("date_of_disposal"));
				dispose.setFir_id(resultset.getInt("fir_id"));
				list_dispose.add(dispose);
			}
			prestatement.close();
		} catch (Exception e) {
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
		return list_dispose;
	}
	
	//CLOSE CONNECTION
	public void close(){
		dbconnect.close();
	}
}
