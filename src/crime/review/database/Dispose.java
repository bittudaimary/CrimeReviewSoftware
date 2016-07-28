package crime.review.database;

public class Dispose {
	private int id;
	private Boolean type_of_final_form;
	private String date_of_disposal;
	private int fir_id;
	
	public Dispose(){
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Boolean getType_of_final_form() {
		return type_of_final_form;
	}

	public void setType_of_final_form(Boolean type_of_final_form) {
		this.type_of_final_form = type_of_final_form;
	}

	public String getDate_of_disposal() {
		return date_of_disposal;
	}

	public void setDate_of_disposal(String date_of_disposal) {
		this.date_of_disposal = date_of_disposal;
	}

	public int getFir_id() {
		return fir_id;
	}

	public void setFir_id(int fir_id) {
		this.fir_id = fir_id;
	}
}
