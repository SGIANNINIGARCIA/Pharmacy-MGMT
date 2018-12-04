
public class Druglines {
	
	private Drug drug;
	private String dosage;
	private int refillsLeft;
	private int refillsDone;
	
	public Druglines(Drug drug, String dosage, int refillsLeft, int refillsDone) {
		super();
		this.drug = drug;
		this.dosage = dosage;
		this.refillsLeft = refillsLeft;
		this.refillsDone = refillsDone;
	}

	public Drug getDrug() {
		return drug;
	}

	public void setDrug(Drug drug) {
		this.drug = drug;
	}

	public String getDosage() {
		return dosage;
	}

	public void setDosage(String dosage) {
		this.dosage = dosage;
	}

	public int getRefillsLeft() {
		return refillsLeft;
	}

	public void setRefillsLeft(int refillsLeft) {
		this.refillsLeft = refillsLeft;
	}

	public int getRefillsDone() {
		return refillsDone;
	}

	public void setRefillsDone(int refillsDone) {
		this.refillsDone = refillsDone;
	}
	
	public String printDrugLine() {
		
		return " :" + drug.getName() + " " + getDosage() + " " + String.valueOf(getRefillsLeft()) + " " 
		+ String.valueOf(getRefillsDone());
	}
	
	
	
	

}
