import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Processor {

	final static String DRUG_FILEPATH = "drug.txt";
	final static String DOCTOR_FILEPATH = "doctors.txt" ;
	final static String PATIENT_FILEPATH = "patient.txt";
	final static String PRESCRIPTION_FILEPATH = "prescription.txt";	

	static ArrayList<Drug> drugs;
	static ArrayList<Doctors> doctors;
	static ArrayList<Patients> patients;
	static ArrayList<Prescriptions> prescriptions;


	public static void processorInit() throws IOException {          //Initializes all text files
		drugs = readDrugs();    
		doctors = readDoctors();		
		patients = readPatients();
		prescriptions = readPrescriptions();

	}	

	public static ArrayList<Drug> readDrugs() throws IOException {   //Reads the drug.txt file      

		String currentLine;	     
		ArrayList<Drug> drugs = new ArrayList<>();
		String[] fields;

		Drug temp = null;
		Scanner in = new Scanner(new BufferedReader(new FileReader(DRUG_FILEPATH)));	

		while (in.hasNext()) {

			currentLine = in.nextLine();	         
			fields = currentLine.split(" "); 
			ArrayList<String> conditions = new ArrayList<>();
			ArrayList<String> contradictions = new ArrayList<>();

			temp = new Drug(fields[0], fields[1], fields[2], fields[3], Boolean.parseBoolean(fields[4]));

			for(int i = 0; i < fields.length; i++) {                         //SET CONDITIONS 
				if(fields[i].charAt(0) == ':') {
					conditions.add(fields[i].substring(1));
				}
				temp.setConditions(conditions);	

			}

			for(int i = 0; i < fields.length; i++) {                         //SET CONTRADICTIONS 
				if(fields[i].charAt(0) == '?') {
					contradictions.add(fields[i].substring(1));
				}
				temp.setContradictions(contradictions);	
			}

			drugs.add(temp);
		}  
		in.close();

		return drugs;
	}

	public static ArrayList<Doctors> readDoctors() throws IOException {             

		String currentLine;	     
		ArrayList<Doctors> doctors = new ArrayList<>();
		String[] fields;
		Doctors temp = null;
		Scanner in = new Scanner(new BufferedReader(new FileReader(DOCTOR_FILEPATH)));	

		while (in.hasNext()) {

			currentLine = in.nextLine();	         
			fields = currentLine.split(" ");  				

			temp = new Doctors(fields[0] + " " + fields[1], fields[2] + " " + fields[3] + " " + fields[4],
					fields[5], fields[6]);   

			doctors.add(temp);
		}  
		in.close();

		return doctors;
	}

	public static ArrayList<Patients> readPatients() throws IOException {             

		String currentLine;	     
		ArrayList<Patients> patients = new ArrayList<>();
		String[] fields;
		Patients temp = null;
		Scanner in = new Scanner(new BufferedReader(new FileReader(PATIENT_FILEPATH)));	

		while (in.hasNext()) {

			currentLine = in.nextLine();	         
			fields = currentLine.split(" ");  				

			temp = new Patients(fields[0] + " " + fields[1], fields[2], fields[3] + " " + fields[4] + " " + fields[5],
					fields[6], fields[7]);    

			patients.add(temp);
		}  
		in.close();

		return patients;
	}

	public static ArrayList<Prescriptions> readPrescriptions() throws IOException {             

		String currentLine;	     
		ArrayList<Prescriptions> prescriptions = new ArrayList<>();
		ArrayList<Druglines> drugLines = new ArrayList<>();
		String[] fields;
		Prescriptions temp = null;
		Scanner in = new Scanner(new BufferedReader(new FileReader(PRESCRIPTION_FILEPATH)));	

		while (in.hasNext()) {

			currentLine = in.nextLine();	         
			fields = currentLine.split(" ");  				

			temp = new Prescriptions (fields[0], fields[1]); 
			temp.setDoctor(findDoctor(fields[2] + " " + fields[3]));

			if(temp.getDoctor() == null) {     									                     //checks if the doctor exists 
				System.out.println("Could not find doctor, please add it to our doctors data base");
			}
			
			temp.setPatient(findPatient(fields[4] + " " + fields[5]));

			if(temp.getPatient() == null) {     									                     //checks if the patient exists 
				System.out.println("Could not find patient, please add it to our patients data base");
			}
			
			for(int i = 0; i < fields.length; i++) {                         //SET DRUGLINEs 
				if(fields[i].charAt(0) == ':') {
					Druglines tempD = new Druglines(findDrug(fields[i].substring(1)), fields[i + 1], Integer.parseInt(fields[i + 2]),
							Integer.parseInt(fields[i + 3]));
					drugLines.add(tempD);
					
					watchListUpdate(findDrug(fields[i].substring(1)), findDoctor(fields[2] + " " + fields[3]));									
						
				}
				temp.setDrugLines(drugLines);	

			}
			
			prescriptions.add(temp);
		}  
		in.close();

		return prescriptions;
	}

	public static Doctors findDoctor(String name){ 				//FINDS A DOCTOR 

		for(int i = 0; i < doctors.size(); i++) {
			if(doctors.get(i).getName().equals(name)) {
				return doctors.get(i);
			}
		}
		return null;

	}
	
	public static Drug findDrug(String name){ 				//FINDS A DRUG 

		for(int i = 0; i < drugs.size(); i++) {
			if(drugs.get(i).getName().equals(name)) {
				return drugs.get(i);
			}
		}
		return null;

	}
	
	public static Patients findPatient(String name){ 				//FINDS A PATIENT

		for(int i = 0; i < patients.size(); i++) {
			if(patients.get(i).getName().equals(name)) {
				return patients.get(i);
			}
		}
		return null;

	}
	
	public static void watchListUpdate(Drug drug, Doctors doctor) {
		
		HashMap<Drug, Integer> watchlist = doctor.getWatchlist();  //lets see if it updates the watchlist
		
		if(drug.isInWatchlist() == true && watchlist.containsKey(drug) == false) {
			watchlist.put(drug, 0);
		}
		
		if(drug.isInWatchlist() == true && watchlist.containsKey(drug) == true) {
			int timesPrescribed = watchlist.get(drug) + 1;
			watchlist.replace(drug, timesPrescribed);
			
		}
		
		
	}
	
	public static void main(String[] args) throws IOException {
		processorInit();
		System.out.println("I compiled!");
		for(int i = 0; i < drugs.size(); i++) {
			System.out.print(drugs.get(i).printGeneralInfo() + " ");
			System.out.println(drugs.get(i).printContradictions());
		}
	}
}
