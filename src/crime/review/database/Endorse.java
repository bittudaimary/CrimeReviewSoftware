package crime.review.database;

public class Endorse {
	private int id;
	private int fir_id;
	private String endorse_date;
	private int police_officer_id;
	private String reason_for_endorsement;
	
	public Endorse(){
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getFir_id() {
		return fir_id;
	}

	public void setFir_id(int fir_id) {
		this.fir_id = fir_id;
	}

	public String getEndorse_date() {
		return endorse_date;
	}

	public void setEndorse_date(String endorse_date) {
		this.endorse_date = endorse_date;
	}

	public String getReason_for_endorsement() {
		return reason_for_endorsement;
	}

	public void setReason_for_endorsement(String reason_for_endorsement) {
		this.reason_for_endorsement = reason_for_endorsement;
	}

	public int getPolice_officer_id() {
		return police_officer_id;
	}

	public void setPolice_officer_id(int police_officer_id) {
		this.police_officer_id = police_officer_id;
	}
}
