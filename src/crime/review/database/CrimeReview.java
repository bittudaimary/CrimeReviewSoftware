package crime.review.database;

public class CrimeReview {
	private int id;
	private int fir_id;
	private String date_of_review;
	private String action_taken;
	private String last_cd_with_date;
	private String reason_for_pending;
	private String remarks;
	
	public CrimeReview (){
		
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

	public String getDate_of_review() {
		return date_of_review;
	}

	public void setDate_of_review(String date_of_review) {
		this.date_of_review = date_of_review;
	}

	public String getAction_taken() {
		return action_taken;
	}

	public void setAction_taken(String action_taken) {
		this.action_taken = action_taken;
	}

	public String getLast_cd_with_date() {
		return last_cd_with_date;
	}

	public void setLast_cd_with_date(String last_cd_with_date) {
		this.last_cd_with_date = last_cd_with_date;
	}

	public String getReason_for_pending() {
		return reason_for_pending;
	}

	public void setReason_for_pending(String reason_for_pending) {
		this.reason_for_pending = reason_for_pending;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
}
