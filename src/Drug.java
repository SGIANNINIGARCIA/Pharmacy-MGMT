import java.util.ArrayList;


public class Drug {
	
	private String name;
	private String chemicalName;
	private String Manufacturer;
	private String drugtype;
	private boolean isInWatchlist;
	private ArrayList<String> conditions;
	private ArrayList<String> contradictions;
	private ArrayList<String> ingredients;
	
	
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
	
	public ArrayList<String> getIngredients() {
		return ingredients;
	}


	public void setIngredients(ArrayList<String> ingredients) {
		this.ingredients = ingredients;
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
	
	public String printIngredients() {
	String contra = "";
		
		for(int i = 0; i < ingredients.size(); i++) {
			contra = contra + "#" + ingredients.get(i) + " ";
		}
		return contra;
	}
	
	public String printConditions() {
		String tobePrinted = "";
		
		for(int i = 0; i < conditions.size(); i++) {
			tobePrinted = tobePrinted + " :" + conditions.get(i);
		}
		return tobePrinted;
	}
	
	public static Drug findDrug(ArrayList<Drug> drugs, String name){ 				//FINDS A DRUG 

		for(int i = 0; i < drugs.size(); i++) {
			if(drugs.get(i).getName().equals(name)) {
				return drugs.get(i);
			}
		}
		return null;

	}
	
	public static void printDrugContradictions(String drug, ArrayList<Drug> drugs) {
		Drug drugToBePrinted = findDrug(drugs, drug);
		if(drugToBePrinted != null) {
		
			System.out.println("" + drugToBePrinted.getName() + " has a negative effect" +
				" with the following drugs:"	);
			for(int i = 0; i < drugToBePrinted.getContradictions().size(); i++) {
				System.out.print(drugToBePrinted.getContradictions().get(i) + " ");
			}
			System.out.println("\n");		
		
		}
		else {System.out.println("Drug not found in the System");}
	}
	
}
