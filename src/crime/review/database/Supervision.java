package crime.review.database;

public class Supervision {
	private int id;
	private int fir_id;
	private int officer_id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getOfficer_id() {
		return officer_id;
	}
	public void setOfficer_id(int officer_id) {
		this.officer_id = officer_id;
	}
	public int getFir_id() {
		return fir_id;
	}
	public void setFir_id(int fir_id) {
		this.fir_id = fir_id;
	}
}
