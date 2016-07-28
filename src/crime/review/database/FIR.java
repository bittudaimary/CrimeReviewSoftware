package crime.review.database;

public class FIR {
	private int fir_id;
	private String case_no;
	private String section_of_law;
	private String date_of_registration;
	private String date_of_occurrence;
	private int police_officer_id;
	private int police_station_id;
	private String place_of_occurence;
	private int major_head_id;
	private int minor_head_id;
	private Boolean sr_or_nonsr;
	private int class_of_offence;

	public FIR() {
		
	}

	public int getFir_id() {
		return fir_id;
	}

	public void setFir_id(int fir_id) {
		this.fir_id = fir_id;
	}

	public String getCase_no() {
		return case_no;
	}

	public void setCase_no(String case_no) {
		this.case_no = case_no;
	}

	public  String getDate_of_registration() {
		return date_of_registration;
	}

	public void setDate_of_registration(String date_of_registration) {
		this.date_of_registration = date_of_registration;
	}

	public String getDate_of_occurrence() {
		return date_of_occurrence;
	}

	public void setDate_of_occurrence(String date_of_occurrence) {
		this.date_of_occurrence = date_of_occurrence;
	}

	public String getSection_of_law() {
		return section_of_law;
	}

	public void setSection_of_law(String section_of_law) {
		this.section_of_law = section_of_law;
	}

	public int getPolice_officer_id() {
		return police_officer_id;
	}

	public void setPolice_officer_id(int police_officer_id) {
		this.police_officer_id = police_officer_id;
	}

	public String getPlace_of_occurence() {
		return place_of_occurence;
	}

	public void setPlace_of_occurence(String place_of_occurence) {
		this.place_of_occurence = place_of_occurence;
	}

	public int getPolice_station_id() {
		return police_station_id;
	}

	public void setPolice_station_id(int police_station_id) {
		this.police_station_id = police_station_id;
	}

	public int getMajor_head_id() {
		return major_head_id;
	}

	public void setMajor_head_id(int major_head_id) {
		this.major_head_id = major_head_id;
	}

	public int getMinor_head_id() {
		return minor_head_id;
	}

	public void setMinor_head_id(int minor_head_id) {
		this.minor_head_id = minor_head_id;
	}

	public int getClass_of_offence() {
		return class_of_offence;
	}

	public void setClass_of_offence(int class_of_offence) {
		this.class_of_offence = class_of_offence;
	}

	public Boolean getSr_or_nonsr() {
		return sr_or_nonsr;
	}

	public void setSr_or_nonsr(Boolean sr_or_nonsr) {
		this.sr_or_nonsr = sr_or_nonsr;
	}
}
