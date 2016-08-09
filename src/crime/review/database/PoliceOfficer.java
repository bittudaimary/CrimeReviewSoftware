package crime.review.database;

public class PoliceOfficer {
	private int id;
	private String fname;
	private String mname;
	private String lname;
	private String dob;
	private String contact;
	private int posting_id;
	private int rank;
	
	public PoliceOfficer() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getMname() {
		return mname;
	}

	public void setMname(String mname) {
		this.mname = mname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public int getPosting() {
		return posting_id;
	}

	public void setPosting(int posting_id) {
		this.posting_id = posting_id;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}
	
	public int getPostingId(){
		return posting_id;
	}
	
	public void setPostingId(int ps_id){
		posting_id = ps_id;
	}
	
}
