import java.util.ArrayList;

public class Prescriptions {
	private String id;
	private String dateIssued;
	private Doctors doctor;
	private Patients patient;
	private ArrayList<Druglines> drugLines;
	

	public Prescriptions(String id, String dateIssued, String doctor, ArrayList<Druglines> drugLines) {
		this.id = id;
		this.dateIssued = dateIssued;
		this.drugLines = drugLines;
	}
	
	public Prescriptions(String id, String dateIssued) {
		this.id = id;
		this.dateIssued = dateIssued;
		
	}

	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getDateIssued() {
		return dateIssued;
	}


	public void setDateIssued(String dateIssued) {
		this.dateIssued = dateIssued;
	}

	public Doctors getDoctor() {
		return doctor;
	}


	public void setDoctor(Doctors doctor) {
		this.doctor = doctor;
	}

	public ArrayList<Druglines> getDrugLines() {
		return drugLines;
	}

	public void setDrugLines(ArrayList<Druglines> drugLines) {
		this.drugLines = drugLines;
	}

	public Patients getPatient() {
		return patient;
	}

	public void setPatient(Patients patient) {
		this.patient = patient;
	}
	
	public String printGeneralInfo() {
		return getId() + " " + getDateIssued() + " " + getDoctor().getName() + " " + getPatient().getName(); 
		        
	}
	
	public String printDrugLines() {
		String toBePrinted = "";
		for(int i = 0; i < drugLines.size(); i++) {
			toBePrinted = toBePrinted + drugLines.get(i).printDrugLine();		
		}
		
		return toBePrinted;		
	}
	
	
}
