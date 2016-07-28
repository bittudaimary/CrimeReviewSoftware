package crime.review.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class FIRQueries {
	private Connection connection;
	private PreparedStatement prestatement;
	private ResultSet resultset;

	public FIRQueries() {
		try {
			// Load MS access driver class
			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5433/crime_database", "postgres","123456");
		} catch (Exception e) {
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
	}

	// ADD FIR
	public boolean add(FIR fir) {
		try {
			String query = "insert into fir (case_no,section_of_law,date_of_registration,date_of_occurence,police_officer_id,police_station_id,place_of_occurence,major_head_id,minor_head_id,sr_or_nonsr,class_of_offence) values(?,?,?,?,?,?,?,?,?,?,?)";
			prestatement = connection.prepareStatement(query);
			prestatement.setString(1, fir.getCase_no());
			prestatement.setString(2, fir.getSection_of_law());
			prestatement.setString(3, fir.getDate_of_registration());
			prestatement.setString(4, fir.getDate_of_occurrence());
			prestatement.setInt(5, fir.getPolice_officer_id());
			prestatement.setInt(6, fir.getPolice_station_id());
			prestatement.setString(7, fir.getPlace_of_occurence());
			prestatement.setInt(8, fir.getMajor_head_id());
			prestatement.setInt(9, fir.getMinor_head_id());
			prestatement.setBoolean(10, fir.getSr_or_nonsr());
			prestatement.setInt(11, fir.getClass_of_offence());
			prestatement.executeUpdate();
			prestatement.close();
			return true;
		} catch (Exception e) {
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
			return false;
		}
	}

	// UPDATE FIR
	public boolean update(FIR fir) {
		try {
			String query = "update fir set case_no=?,section_of_law=?,date_of_registration=?,date_of_occurence=?,police_officer_id=?,police_station_id=?,place_of_occurence=?,major_head_id=?,minor_head_id=?,sr_or_nonsr=?,class_of_offence=? where fir_id=?";
			prestatement = connection.prepareStatement(query);
			prestatement.setString(1, fir.getCase_no());
			prestatement.setString(2, fir.getSection_of_law());
			prestatement.setString(3, fir.getDate_of_registration());
			prestatement.setString(4, fir.getDate_of_occurrence());
			prestatement.setInt(5, fir.getPolice_officer_id());
			prestatement.setInt(6, fir.getPolice_station_id());
			prestatement.setString(7, fir.getPlace_of_occurence());
			prestatement.setInt(8, fir.getMajor_head_id());
			prestatement.setInt(9, fir.getMinor_head_id());
			prestatement.setBoolean(10, fir.getSr_or_nonsr());
			prestatement.setInt(11, fir.getClass_of_offence());
			prestatement.setInt(12, fir.getFir_id());
			prestatement.executeUpdate();
			prestatement.close();
			return true;
		} catch (Exception e) {
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
			return false;
		}
	}

	// GET FIR FROM FIR_ID
	public FIR getFir_from_firid(int firid) {
		FIR newfir = null;
		try {
			String query = "select * from fir where fir_id = ?";
			prestatement = connection.prepareStatement(query);
			prestatement.setInt(1, firid);
			resultset = prestatement.executeQuery();
			if (resultset.next()) {
				newfir = new FIR();
				newfir.setFir_id(resultset.getInt("fir_id"));
				newfir.setCase_no(resultset.getString("case_no"));
				newfir.setSection_of_law(resultset.getString("section_of_law"));
				newfir.setDate_of_registration(resultset
						.getString("date_of_registration"));
				newfir.setDate_of_occurrence(resultset
						.getString("date_of_occurence"));
				newfir.setPolice_officer_id(resultset
						.getInt("police_officer_id"));
				newfir.setPolice_station_id(resultset
						.getInt("police_station_id"));
				newfir.setPlace_of_occurence(resultset
						.getString("place_of_occurence"));
				newfir.setMajor_head_id(resultset.getInt("major_head_id"));
				newfir.setMinor_head_id(resultset.getInt("minor_head_id"));
				newfir.setSr_or_nonsr(resultset.getBoolean("sr_or_nonsr"));
				newfir.setClass_of_offence(resultset.getInt("class_of_offence"));
			}
			prestatement.close();
		} catch (Exception e) {
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
		return newfir;
	}

	// GET FIR FROM CASE NO
	List<FIR> getFir_from_case_no(String case_no) {
		List<FIR> list_fir = new ArrayList<FIR>();
		FIR newfir = null;
		try {
			String query = "select * from fir where case_no = ?";
			prestatement = connection.prepareStatement(query);
			prestatement.setString(1, case_no);
			resultset = prestatement.executeQuery();
			while (resultset.next()) {
				newfir = new FIR();
				newfir.setFir_id(resultset.getInt("fir_id"));
				newfir.setCase_no(resultset.getString("case_no"));
				newfir.setSection_of_law(resultset.getString("section_of_law"));
				newfir.setDate_of_registration(resultset
						.getString("date_of_registration"));
				newfir.setDate_of_occurrence(resultset
						.getString("date_of_occurence"));
				newfir.setPolice_officer_id(resultset
						.getInt("police_officer_id"));
				newfir.setPolice_station_id(resultset
						.getInt("police_station_id"));
				newfir.setPlace_of_occurence(resultset
						.getString("place_of_occurence"));
				newfir.setMajor_head_id(resultset.getInt("major_head_id"));
				newfir.setMinor_head_id(resultset.getInt("minor_head_id"));
				newfir.setSr_or_nonsr(resultset.getBoolean("sr_or_nonsr"));
				newfir.setClass_of_offence(resultset.getInt("class_of_offence"));
				list_fir.add(newfir);
			}
			prestatement.close();
		} catch (Exception e) {
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
		return list_fir;
	}

	// GET FIR FROM SECTION OF LAW
	List<FIR> getFir_from_section_of_law(String section_of_law) {
		List<FIR> list_fir = new ArrayList<FIR>();
		FIR newfir = null;
		try {
			String query = "select * from fir where section_of_law = ?";
			prestatement = connection.prepareStatement(query);
			prestatement.setString(1, section_of_law);
			resultset = prestatement.executeQuery();
			while (resultset.next()) {
				newfir = new FIR();
				newfir.setFir_id(resultset.getInt("fir_id"));
				newfir.setCase_no(resultset.getString("case_no"));
				newfir.setSection_of_law(resultset.getString("section_of_law"));
				newfir.setDate_of_registration(resultset
						.getString("date_of_registration"));
				newfir.setDate_of_occurrence(resultset
						.getString("date_of_occurence"));
				newfir.setPolice_officer_id(resultset
						.getInt("police_officer_id"));
				newfir.setPolice_station_id(resultset
						.getInt("police_station_id"));
				newfir.setPlace_of_occurence(resultset
						.getString("place_of_occurence"));
				newfir.setMajor_head_id(resultset.getInt("major_head_id"));
				newfir.setMinor_head_id(resultset.getInt("minor_head_id"));
				newfir.setSr_or_nonsr(resultset.getBoolean("sr_or_nonsr"));
				newfir.setClass_of_offence(resultset.getInt("class_of_offence"));
				list_fir.add(newfir);
			}
			prestatement.close();
		} catch (Exception e) {
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
		return list_fir;
	}

	// GET FIR FROM DATE OR REGISTRATION
	List<FIR> getFir_from_date_of_registration(String date_of_registration) {
		List<FIR> list_fir = new ArrayList<FIR>();
		FIR newfir = null;
		try {
			String query = "select * from fir where date_of_registration = ?";
			prestatement = connection.prepareStatement(query);
			prestatement.setString(1, date_of_registration);
			resultset = prestatement.executeQuery();
			while (resultset.next()) {
				newfir = new FIR();
				newfir.setFir_id(resultset.getInt("fir_id"));
				newfir.setCase_no(resultset.getString("case_no"));
				newfir.setSection_of_law(resultset.getString("section_of_law"));
				newfir.setDate_of_registration(resultset
						.getString("date_of_registration"));
				newfir.setDate_of_occurrence(resultset
						.getString("date_of_occurence"));
				newfir.setPolice_officer_id(resultset
						.getInt("police_officer_id"));
				newfir.setPolice_station_id(resultset
						.getInt("police_station_id"));
				newfir.setPlace_of_occurence(resultset
						.getString("place_of_occurence"));
				newfir.setMajor_head_id(resultset.getInt("major_head_id"));
				newfir.setMinor_head_id(resultset.getInt("minor_head_id"));
				newfir.setSr_or_nonsr(resultset.getBoolean("sr_or_nonsr"));
				newfir.setClass_of_offence(resultset.getInt("class_of_offence"));
				list_fir.add(newfir);
			}
			prestatement.close();
		} catch (Exception e) {
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
		return list_fir;
	}

	// GET FIR FROM DATE OR OCCURENCE
	List<FIR> getFir_from_date_of_occurence(String date_of_occurence) {
		List<FIR> list_fir = new ArrayList<FIR>();
		FIR newfir = null;
		try {
			String query = "select * from fir where date_of_occurence = ?";
			prestatement = connection.prepareStatement(query);
			prestatement.setString(1, date_of_occurence);
			resultset = prestatement.executeQuery();
			while (resultset.next()) {
				newfir = new FIR();
				newfir.setFir_id(resultset.getInt("fir_id"));
				newfir.setCase_no(resultset.getString("case_no"));
				newfir.setSection_of_law(resultset.getString("section_of_law"));
				newfir.setDate_of_registration(resultset
						.getString("date_of_registration"));
				newfir.setDate_of_occurrence(resultset
						.getString("date_of_occurence"));
				newfir.setPolice_officer_id(resultset
						.getInt("police_officer_id"));
				newfir.setPolice_station_id(resultset
						.getInt("police_station_id"));
				newfir.setPlace_of_occurence(resultset
						.getString("place_of_occurence"));
				newfir.setMajor_head_id(resultset.getInt("major_head_id"));
				newfir.setMinor_head_id(resultset.getInt("minor_head_id"));
				newfir.setSr_or_nonsr(resultset.getBoolean("sr_or_nonsr"));
				newfir.setClass_of_offence(resultset.getInt("class_of_offence"));
				list_fir.add(newfir);
			}
			prestatement.close();
		} catch (Exception e) {
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
		return list_fir;
	}

	// GET FIR FROM POLICE OFFICER ID
	List<FIR> getFir_from_police_officer_id(int police_officer_id) {
		List<FIR> list_fir = new ArrayList<FIR>();
		FIR newfir = null;
		try {
			String query = "select * from fir where police_officer_id = ?";
			prestatement = connection.prepareStatement(query);
			prestatement.setInt(1, police_officer_id);
			resultset = prestatement.executeQuery();
			while (resultset.next()) {
				newfir = new FIR();
				newfir.setFir_id(resultset.getInt("fir_id"));
				newfir.setCase_no(resultset.getString("case_no"));
				newfir.setSection_of_law(resultset.getString("section_of_law"));
				newfir.setDate_of_registration(resultset
						.getString("date_of_registration"));
				newfir.setDate_of_occurrence(resultset
						.getString("date_of_occurence"));
				newfir.setPolice_officer_id(resultset
						.getInt("police_officer_id"));
				newfir.setPolice_station_id(resultset
						.getInt("police_station_id"));
				newfir.setPlace_of_occurence(resultset
						.getString("place_of_occurence"));
				newfir.setMajor_head_id(resultset.getInt("major_head_id"));
				newfir.setMinor_head_id(resultset.getInt("minor_head_id"));
				newfir.setSr_or_nonsr(resultset.getBoolean("sr_or_nonsr"));
				newfir.setClass_of_offence(resultset.getInt("class_of_offence"));
				list_fir.add(newfir);
			}
			prestatement.close();
		} catch (Exception e) {
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
		return list_fir;
	}

	// GET FIR FROM POLICE STATION ID
	List<FIR> getFir_from_police_station_id(int police_station_id) {
		List<FIR> list_fir = new ArrayList<FIR>();
		FIR newfir = null;
		try {
			String query = "select * from fir where police_station_id = ?";
			prestatement = connection.prepareStatement(query);
			prestatement.setInt(1, police_station_id);
			resultset = prestatement.executeQuery();
			while (resultset.next()) {
				newfir = new FIR();
				newfir.setFir_id(resultset.getInt("fir_id"));
				newfir.setCase_no(resultset.getString("case_no"));
				newfir.setSection_of_law(resultset.getString("section_of_law"));
				newfir.setDate_of_registration(resultset
						.getString("date_of_registration"));
				newfir.setDate_of_occurrence(resultset
						.getString("date_of_occurence"));
				newfir.setPolice_officer_id(resultset
						.getInt("police_officer_id"));
				newfir.setPolice_station_id(resultset
						.getInt("police_station_id"));
				newfir.setPlace_of_occurence(resultset
						.getString("place_of_occurence"));
				newfir.setMajor_head_id(resultset.getInt("major_head_id"));
				newfir.setMinor_head_id(resultset.getInt("minor_head_id"));
				newfir.setSr_or_nonsr(resultset.getBoolean("sr_or_nonsr"));
				newfir.setClass_of_offence(resultset.getInt("class_of_offence"));
				list_fir.add(newfir);
			}
			prestatement.close();
		} catch (Exception e) {
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
		return list_fir;
	}

	// GET FIR FROM PLACE OF OCCURENCE
	List<FIR> getFir_from_place_of_occurence(String place_of_occurence) {
		List<FIR> list_fir = new ArrayList<FIR>();
		FIR newfir = null;
		try {
			String query = "select * from fir where place_of_occurence = ?";
			prestatement = connection.prepareStatement(query);
			prestatement.setString(1, place_of_occurence);
			resultset = prestatement.executeQuery();
			while (resultset.next()) {
				newfir = new FIR();
				newfir.setFir_id(resultset.getInt("fir_id"));
				newfir.setCase_no(resultset.getString("case_no"));
				newfir.setSection_of_law(resultset.getString("section_of_law"));
				newfir.setDate_of_registration(resultset
						.getString("date_of_registration"));
				newfir.setDate_of_occurrence(resultset
						.getString("date_of_occurence"));
				newfir.setPolice_officer_id(resultset
						.getInt("police_officer_id"));
				newfir.setPolice_station_id(resultset
						.getInt("police_station_id"));
				newfir.setPlace_of_occurence(resultset
						.getString("place_of_occurence"));
				newfir.setMajor_head_id(resultset.getInt("major_head_id"));
				newfir.setMinor_head_id(resultset.getInt("minor_head_id"));
				newfir.setSr_or_nonsr(resultset.getBoolean("sr_or_nonsr"));
				newfir.setClass_of_offence(resultset.getInt("class_of_offence"));
				list_fir.add(newfir);
			}
			prestatement.close();
		} catch (Exception e) {
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
		return list_fir;
	}

	// GET FIRS FROM MAJOR HEAD ID
	List<FIR> getFir_from_major_head_id(int major_head_id) {
		List<FIR> list_fir = new ArrayList<FIR>();
		FIR newfir = null;
		try {
			String query = "select * from fir where major_head_id = ?";
			prestatement = connection.prepareStatement(query);
			prestatement.setInt(1, major_head_id);
			resultset = prestatement.executeQuery();
			while (resultset.next()) {
				newfir = new FIR();
				newfir.setFir_id(resultset.getInt("fir_id"));
				newfir.setCase_no(resultset.getString("case_no"));
				newfir.setSection_of_law(resultset.getString("section_of_law"));
				newfir.setDate_of_registration(resultset
						.getString("date_of_registration"));
				newfir.setDate_of_occurrence(resultset
						.getString("date_of_occurence"));
				newfir.setPolice_officer_id(resultset
						.getInt("police_officer_id"));
				newfir.setPolice_station_id(resultset
						.getInt("police_station_id"));
				newfir.setPlace_of_occurence(resultset
						.getString("place_of_occurence"));
				newfir.setMajor_head_id(resultset.getInt("major_head_id"));
				newfir.setMinor_head_id(resultset.getInt("minor_head_id"));
				newfir.setSr_or_nonsr(resultset.getBoolean("sr_or_nonsr"));
				newfir.setClass_of_offence(resultset.getInt("class_of_offence"));
				list_fir.add(newfir);
			}
			prestatement.close();
		} catch (Exception e) {
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
		return list_fir;
	}

	// GET FIRS FROM MINOR HEAD ID
	List<FIR> getFir_from_minor_head_id(int minor_head_id) {
		List<FIR> list_fir = new ArrayList<FIR>();
		FIR newfir = null;
		try {
			String query = "select * from fir where minor_head_id = ?";
			prestatement = connection.prepareStatement(query);
			prestatement.setInt(1, minor_head_id);
			resultset = prestatement.executeQuery();
			while (resultset.next()) {
				newfir = new FIR();
				newfir.setFir_id(resultset.getInt("fir_id"));
				newfir.setCase_no(resultset.getString("case_no"));
				newfir.setSection_of_law(resultset.getString("section_of_law"));
				newfir.setDate_of_registration(resultset
						.getString("date_of_registration"));
				newfir.setDate_of_occurrence(resultset
						.getString("date_of_occurence"));
				newfir.setPolice_officer_id(resultset
						.getInt("police_officer_id"));
				newfir.setPolice_station_id(resultset
						.getInt("police_station_id"));
				newfir.setPlace_of_occurence(resultset
						.getString("place_of_occurence"));
				newfir.setMajor_head_id(resultset.getInt("major_head_id"));
				newfir.setMinor_head_id(resultset.getInt("minor_head_id"));
				newfir.setSr_or_nonsr(resultset.getBoolean("sr_or_nonsr"));
				newfir.setClass_of_offence(resultset.getInt("class_of_offence"));
				list_fir.add(newfir);
			}
			prestatement.close();
		} catch (Exception e) {
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
		return list_fir;
	}

	// GET FIR FROM SR OR NON SR CASES
	List<FIR> getFir_from_sr_or_nonsr(Boolean sr_or_nonsr) {
		List<FIR> list_fir = new ArrayList<FIR>();
		FIR newfir = null;
		try {
			String query = "select * from fir where sr_or_nonsr = ?";
			prestatement = connection.prepareStatement(query);
			prestatement.setBoolean(1, sr_or_nonsr);
			resultset = prestatement.executeQuery();
			while (resultset.next()) {
				newfir = new FIR();
				newfir.setFir_id(resultset.getInt("fir_id"));
				newfir.setCase_no(resultset.getString("case_no"));
				newfir.setSection_of_law(resultset.getString("section_of_law"));
				newfir.setDate_of_registration(resultset
						.getString("date_of_registration"));
				newfir.setDate_of_occurrence(resultset
						.getString("date_of_occurence"));
				newfir.setPolice_officer_id(resultset
						.getInt("police_officer_id"));
				newfir.setPolice_station_id(resultset
						.getInt("police_station_id"));
				newfir.setPlace_of_occurence(resultset
						.getString("place_of_occurence"));
				newfir.setMajor_head_id(resultset.getInt("major_head_id"));
				newfir.setMinor_head_id(resultset.getInt("minor_head_id"));
				newfir.setSr_or_nonsr(resultset.getBoolean("sr_or_nonsr"));
				newfir.setClass_of_offence(resultset.getInt("class_of_offence"));
				list_fir.add(newfir);
			}
			prestatement.close();
		} catch (Exception e) {
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
		return list_fir;
	}

	// GET FIR FROM CLASSIFICATION OF OFFENCE
	List<FIR> getFir_from_class_of_offence(int class_of_offence) {
		List<FIR> list_fir = new ArrayList<FIR>();
		FIR newfir = null;
		try {
			String query = "select * from fir where class_of_offence = ?";
			prestatement = connection.prepareStatement(query);
			prestatement.setInt(1, class_of_offence);
			resultset = prestatement.executeQuery();
			while (resultset.next()) {
				newfir = new FIR();
				newfir.setFir_id(resultset.getInt("fir_id"));
				newfir.setCase_no(resultset.getString("case_no"));
				newfir.setSection_of_law(resultset.getString("section_of_law"));
				newfir.setDate_of_registration(resultset
						.getString("date_of_registration"));
				newfir.setDate_of_occurrence(resultset
						.getString("date_of_occurence"));
				newfir.setPolice_officer_id(resultset
						.getInt("police_officer_id"));
				newfir.setPolice_station_id(resultset
						.getInt("police_station_id"));
				newfir.setPlace_of_occurence(resultset
						.getString("place_of_occurence"));
				newfir.setMajor_head_id(resultset.getInt("major_head_id"));
				newfir.setMinor_head_id(resultset.getInt("minor_head_id"));
				newfir.setSr_or_nonsr(resultset.getBoolean("sr_or_nonsr"));
				newfir.setClass_of_offence(resultset.getInt("class_of_offence"));
				list_fir.add(newfir);
			}
			prestatement.close();
		} catch (Exception e) {
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
		return list_fir;
	}
	
	//COUNT OF PENDING FIR
	public int get_count_of_pending_fir(int district_id){
		int count = 0;
		try {
			String query = "select count(fir_id) from fir where fir_id not in (select dispose.fir_id from dispose) and fir.police_station_id in (select police_station.id from police_station where district_id=?)";
			prestatement = connection.prepareStatement(query);
			prestatement.setInt(1,district_id);
			resultset = prestatement.executeQuery();
			if(resultset.next()){
				count = resultset.getInt(1);
			}
		} catch (Exception e) {
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
		return count;
	}
	
	//COUNT OF PENDING FIR WRT POLICE STATION
	public int get_count_of_pending_fir_from_ps(int police_station_id){
		int count = 0;
		try {
			String query = "select count(fir_id) from fir where fir_id not in (select dispose.fir_id from dispose) and fir.police_station_id=?";
			prestatement = connection.prepareStatement(query);
			prestatement.setInt(1,police_station_id);
			resultset = prestatement.executeQuery();
			if(resultset.next()){
				count = resultset.getInt(1);
			}
		} catch (Exception e) {
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
		return count;
	}
	
	//GET PENDING FIR OF POLICE STATION
	public List<FIR> getPendingFIROfPoliceStation(int police_station_id){
		List<FIR> list_fir = new ArrayList<FIR>();
		FIR newfir = null;
		try {
			String query = "select * from fir where fir_id not in (select dispose.fir_id from dispose) and fir.police_station_id=?";
			prestatement = connection.prepareStatement(query);
			prestatement.setInt(1, police_station_id);
			resultset = prestatement.executeQuery();
			while (resultset.next()) {
				newfir = new FIR();
				newfir.setFir_id(resultset.getInt("fir_id"));
				newfir.setCase_no(resultset.getString("case_no"));
				newfir.setSection_of_law(resultset.getString("section_of_law"));
				newfir.setDate_of_registration(resultset
						.getString("date_of_registration"));
				newfir.setDate_of_occurrence(resultset
						.getString("date_of_occurence"));
				newfir.setPolice_officer_id(resultset
						.getInt("police_officer_id"));
				newfir.setPolice_station_id(resultset
						.getInt("police_station_id"));
				newfir.setPlace_of_occurence(resultset
						.getString("place_of_occurence"));
				newfir.setMajor_head_id(resultset.getInt("major_head_id"));
				newfir.setMinor_head_id(resultset.getInt("minor_head_id"));
				newfir.setSr_or_nonsr(resultset.getBoolean("sr_or_nonsr"));
				newfir.setClass_of_offence(resultset.getInt("class_of_offence"));
				list_fir.add(newfir);
			}
			prestatement.close();
		} catch (Exception e) {
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
		return list_fir;
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
