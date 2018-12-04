import java.util.ArrayList;


public class Drug {
	
	private String name;
	private String chemicalName;
	private String Manufacturer;
	private String drugtype;
	private boolean isInWatchlist;
	private ArrayList<String> conditions;
	private ArrayList<String> contradictions;
	
	public Drug() {

		this.name = "";
		this.chemicalName = "";
		Manufacturer = "";
		this.drugtype = "";
		
	}
	
	
	public Drug(String name, String chemicalName, String manufacturer, String drugtype, ArrayList<String> conditions,
			ArrayList<String> contradictions) {

		this.name = name;
		this.chemicalName = chemicalName;
		Manufacturer = manufacturer;
		this.drugtype = drugtype;
		this.conditions = conditions;
		this.contradictions = contradictions;
	}
	
	public Drug(String name, String chemicalName, String manufacturer, String drugtype, boolean watchlist) {

		this.name = name;
		this.chemicalName = chemicalName;
		Manufacturer = manufacturer;
		this.drugtype = drugtype;
		this.isInWatchlist = watchlist;
		
	}
	//SETTERS AND GETTERS//
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getChemicalName() {
		return chemicalName;
	}
	public void setChemicalName(String chemicalName) {
		this.chemicalName = chemicalName;
	}
	public String getManufacturer() {
		return Manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		Manufacturer = manufacturer;
	}
	public String getDrugtype() {
		return drugtype;
	}
	public void setDrugtype(String drugtype) {
		this.drugtype = drugtype;
	}
	public ArrayList<String> getConditions() {
		return conditions;
	}
	public void setConditions(ArrayList<String> conditions) {
		this.conditions = conditions;
	}
	public ArrayList<String> getContradictions() {
		return contradictions;
	}
	public void setContradictions(ArrayList<String> contradictions) {
		this.contradictions = contradictions;
	}


	public boolean isInWatchlist() {
		return isInWatchlist;
	}


	public void setInWatchlist(boolean isInWatchlist) {
		this.isInWatchlist = isInWatchlist;
	}
	
	public String printGeneralInfo() {
		return getName() + " " + getChemicalName() + " " + getManufacturer() + " " + getDrugtype() 
		        + " " + Boolean.toString(isInWatchlist());
	}
	
	public String printContradictions() {
		String contra = "";
		
		for(int i = 0; i < contradictions.size(); i++) {
			contra = contra + "?" + contradictions.get(i) + " ";
		}
		return contra;
	}
	
	public String printConditions() {
		String tobePrinted = "";
		
		for(int i = 0; i < conditions.size(); i++) {
			tobePrinted = tobePrinted + ":" + conditions.get(i) + " ";
		}
		return tobePrinted;
	}
	
		//END OF SETTERS AND GETTERS//
		///////////METHODS///////////	
		//TODO-------
	
}
