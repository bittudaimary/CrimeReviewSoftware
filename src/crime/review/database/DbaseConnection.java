package crime.review.database;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbaseConnection {

	private Connection connection;
	
	public DbaseConnection(){
		try{
			// Load MS access driver class
			Class.forName("org.postgresql.Driver");
			setConnection(DriverManager.getConnection("jdbc:postgresql://localhost:5433/crime_database", "postgres","123456"));
		}catch (Exception e){
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
	}

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}
	
	public void close() {
		try {
			connection.close();
		}catch (Exception e){
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
	}
	
}
