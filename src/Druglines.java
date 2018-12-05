/**
 * @authors Sandro Giannini Garcia, Tristin Johnson, Jay Jinarek
 * Files: Doctors.java, Drug.java, Druglines.java, Patients.java, Prescriptions.java, Processor.java
 * Class: CMS270
 * 
 * “On my honor, I have not given, nor received, nor witnessed any unauthorized assistance on this work.”
 * 
 * "I worked on this assignment alone, using only this and previous semester's course materials, and some other resources"
 * 
 * Description: This program reads from a set of file containing the information of the management of a pharmacy, and it manages the data with a set
 * of commands.
 *   
 */
public class Druglines {
	
	private Drug drug;
	private String dosage;
	private int refillsLeft;
	private int refillsDone;
	
	public Druglines(Drug drug, String dosage, int refillsLeft, int refillsDone) {
	
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
	
	public boolean checkRefills() {
		if (this.getRefillsLeft() <= 0) {
			System.out.println("This prescription has no refills left\n");
			return false;
		}
		else return true;
	}
	
	
	
	

}
