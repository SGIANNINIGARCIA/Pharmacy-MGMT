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
	
	public static void DrugDoctorRatio(ArrayList<Doctors> doctors, ArrayList<Prescriptions> prescriptions, Drug drug, int times) {
		
		
		for(int i = 0; i < doctors.size(); i++) {  //GETS DOCTOR A
			int nTimes = 0;
			Doctors doctor = doctors.get(i);
			
			for(int o = 0; o < prescriptions.size(); o++) { //GETS DOCTOR B
				Doctors toBeCompared = prescriptions.get(o).getDoctor();
				for(int p = 0; p < prescriptions.get(o).getDrugLines().size(); p++) {  //FINDS A DESIRED DRUG AND DOCTOR PRESCRIBED
					if(prescriptions.get(o).getDrugLines().get(p).getDrug().getName().equalsIgnoreCase(drug.getName()) &&  //IF DOCTORS AND DRUG MATCH ADD 1
							doctor.equals(toBeCompared) == true	) {
						 nTimes ++;
					}
					
				}
				
			}

			if(nTimes >= times) {    //PRINTS THE MATCHED DOCTORS
				System.out.println("This doctor prescribed " + drug.getName() + " " + nTimes + " time " + doctor.getName() + "\n"); }
		}
	}
	
	public static void fillPrescription(ArrayList<Doctors> doctors, ArrayList<Patients> patients,
			ArrayList<Prescriptions> prescriptions, String[] fields, ArrayList<Drug> drugs) {
		
		 String id = fields[1];
		
			for(int i = 0; i < prescriptions.size(); i++) {
				String toBeCompared = prescriptions.get(i).getId();
				if(id.equalsIgnoreCase(toBeCompared) == true) {
			         updatePrescription(fields, prescriptions.get(i));
			         return;
				}
			}
					Prescriptions temp = new Prescriptions (fields[1], fields[2]); 
					temp.setDoctor(Doctors.findDoctor(doctors, fields[3] + " " + fields[4]));
					ArrayList<Druglines> drugLines = new ArrayList<>();

					if(temp.getDoctor() == null) {     									                     //checks if the doctor exists 
						System.out.println("Could not find doctor, please add it to our doctors data base. " +
					"Can't process new prescription\n");
						return;
					}
					
					temp.setPatient(Patients.findPatient(patients, fields[5] + " " + fields[6]));

					if(temp.getPatient() == null) {     									                     //checks if the patient exists 
						System.out.println("Could not find patient, please add it to our patients data base. "
								+ "Can't process new prescription.\n");
						return;
					}
					
					for(int o = 0; 	o < fields.length; o++) {                         //SET DRUGLINEs 
						if(fields[o].charAt(0) == ':') {
							Druglines tempD = new Druglines(Drug.findDrug(drugs, fields[o].substring(1)), fields[o + 1], Integer.parseInt(fields[o + 2]),
									Integer.parseInt(fields[o + 3]));
							drugLines.add(tempD);
							
							Drug tempN = Drug.findDrug(drugs, (fields[o].substring(1)));  //Checks if drug is null
							if(tempN == null) {
								System.out.println("Cant find this drug, please add it in order to search the watchlist database.\n");
								continue;
							}
							Doctors tempDoc = Doctors.findDoctor(doctors, fields[2] + " " + fields[3]); //checks if docto is null
							if(tempDoc == null) {
								System.out.println("Cant find this doctor, please add it in order to search the watchlist database.\n");
								continue;
							}
							
							Processor.watchListUpdate(tempN, tempDoc);								
								
						}
						temp.setDrugLines(drugLines);	

					}
					
					prescriptions.add(temp);

	}

	public static void updatePrescription(String[] fields, Prescriptions prescription) {

		prescription.setDateIssued(fields[2]); //UPDATES THE DATE
		
		for(int i = 0; i<prescription.getDrugLines().size(); i++) {  //UPDATES DRUGLINES
			for(int o = 0; o < fields.length; o++) {                         
				if(fields[o].charAt(0) == ':') {
					if(prescription.getDrugLines().get(i).getDrug().getName().equalsIgnoreCase(fields[o].substring(1)) &&
							prescription.getDrugLines().get(i).checkRefills() == true	) {
						prescription.getDrugLines().get(i).setRefillsDone(prescription.getDrugLines().get(i).getRefillsDone() + 1);
						prescription.getDrugLines().get(i).setRefillsLeft(prescription.getDrugLines().get(i).getRefillsLeft() - 1);
					}
				}
					
				}
		}
		
		
	}

}
