package crime.review.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RankQueries {
	private Connection connection;
	private PreparedStatement prestatement;
	private ResultSet resultset;
	private DbaseConnection dbconnect;
	
	public RankQueries() {
		dbconnect = new DbaseConnection();
		connection = dbconnect.getConnection();
	}
	
	//ADD Rank
	public boolean add(Rank rank) {
		try {
			String query = "insert into rank (name) values(?)";
			prestatement = connection.prepareStatement(query);
			prestatement.setString(1,rank.getName());
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
	public boolean edit(Rank rank) {
		try {
			String query = "update rank set name=? where id=?";
			prestatement = connection.prepareStatement(query);
			prestatement.setString(1,rank.getName());
			prestatement.setInt(2,rank.getId());
			prestatement.executeUpdate();
			prestatement.close();
			return true;
		} catch (Exception e) {
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
			return false;
		}
	}
	
	//GET Get rank from id
	public Rank getRank_from_id(int id){
		Rank rank = null;
		try {
			String query = "select * from rank where id = ?";
			prestatement = connection.prepareStatement(query);
			prestatement.setInt(1, id);
			resultset = prestatement.executeQuery();
			if (resultset.next()) {
				rank = new Rank();
				rank.setId(resultset.getInt("id"));
				rank.setName(resultset.getString("name"));
			}
			prestatement.close();
		} catch (Exception e) {
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
		return rank;
	}
	
	//GET get rank from name
	public Rank getRank_from_name(String name){
		Rank rank = null;
		try {
			String query = "select * from rank where name = ?";
			prestatement = connection.prepareStatement(query);
			prestatement.setString(1, name);
			resultset = prestatement.executeQuery();
			if (resultset.next()) {
				rank = new Rank();
				rank.setId(resultset.getInt("id"));
				rank.setName(resultset.getString("name"));
			}
			prestatement.close();
		} catch (Exception e) {
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
		return rank;
	}	
	
	//get all rank
	public List<Rank> getAllRank(){
		List<Rank> list_rank = new ArrayList<Rank>();
		Rank rank = null;
		try {
			String query = "select * from rank";
			prestatement = connection.prepareStatement(query);
			resultset = prestatement.executeQuery();
			while(resultset.next()) {
				rank = new Rank();
				rank.setId(resultset.getInt("id"));
				rank.setName(resultset.getString("name"));
				list_rank.add(rank);
			}
			prestatement.close();
		} catch (Exception e) {
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
		return list_rank;
	}
	
	//CLOSE CONNECTION
	public void close(){
		dbconnect.close();
	}
}
