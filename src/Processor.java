import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Processor {

	final static String DRUG_FILEPATH = "drug.txt";
	final static String DOCTOR_FILEPATH = "doctors.txt" ;
	final static String PATIENT_FILEPATH = "patient.txt";
	final static String PRESCRIPTION_FILEPATH = "prescription.txt";	
	final static String TRANSACTION_FILEPATH = "transactions.txt";	

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
			ArrayList<String> ingredients = new ArrayList<>();

			temp = new Drug(fields[0], fields[1], fields[2], fields[3], Boolean.parseBoolean(fields[4]));

			for(int i = 0; i < fields.length; i++) {                         //SET CONDITIONS 
				if(fields[i].charAt(0) == ':') {    //giving me an error
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
			
			for(int i = 0; i < fields.length; i++) {                         //SET INGREDIENTS 
				if(fields[i].charAt(0) == '#') {
					ingredients.add(fields[i].substring(1));
				}
				temp.setIngredients(ingredients);	
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
		String[] fields;
		Prescriptions temp = null;
		Scanner in = new Scanner(new BufferedReader(new FileReader(PRESCRIPTION_FILEPATH)));	

		while (in.hasNext()) {

			currentLine = in.nextLine();	         
			fields = currentLine.split(" "); 
			ArrayList<Druglines> drugLines = new ArrayList<>();

			temp = new Prescriptions (fields[0], fields[1]); 
			temp.setDoctor(Doctors.findDoctor(doctors, fields[2] + " " + fields[3]));

			if(temp.getDoctor() == null) {     									                     //checks if the doctor exists 
				System.out.println("Could not find doctor, please add it to our doctors data base");
				continue;
			}
			
			temp.setPatient(Patients.findPatient(patients, fields[4] + " " + fields[5]));

			if(temp.getPatient() == null) {     									                     //checks if the patient exists 
				System.out.println("Could not find patient, please add it to our patients data base");
				continue;
			}
			
			for(int i = 0; i < fields.length; i++) {                         //SET DRUGLINEs 
				if(fields[i].charAt(0) == ':') {
					Druglines tempD = new Druglines(Drug.findDrug(drugs, fields[i].substring(1)), fields[i + 1], Integer.parseInt(fields[i + 2]),
							Integer.parseInt(fields[i + 3]));
					drugLines.add(tempD);
					
					watchListUpdate(Drug.findDrug(drugs, (fields[i].substring(1))), 
							Doctors.findDoctor(doctors, fields[2] + " " + fields[3]));	
					
							
				}

				temp.setDrugLines(drugLines);	

			}
			
			prescriptions.add(temp);
		}  
		in.close();

		return prescriptions;
	}
	
	public static void readTransactions() throws IOException {             

		String currentLine;	     
		String[] fields;
		Scanner in = new Scanner(new BufferedReader(new FileReader(TRANSACTION_FILEPATH)));	

		while (in.hasNext()) {

			currentLine = in.nextLine();	         
			fields = currentLine.split(" ");  				
            
			
			if (fields[0].equals("FD")) { 
			   Drug temp = Drug.findDrug(drugs, fields[1]);
			   Prescriptions.DrugDoctorRatio(doctors, prescriptions, temp,Integer.parseInt(fields[2]));	   
			}
			if (fields[0].equals("FC")) {  					
				Drug.printDrugContradictions(fields[1], drugs);
			}
			if (fields[0].equals("CD")) {  					
				Doctors.contactDoctor(doctors, fields[1] + " " + fields[2]);
			}
			if (fields[0].equals("FP")) { 
				Prescriptions.fillPrescription(doctors, patients, prescriptions, fields, drugs);
				
			}
			

		}  
		in.close(); 

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
	
	public static void addPatient() {
		Scanner scnr = new Scanner(System.in);
		Patients temp = new Patients();
		
		System.out.println("Please write name of patient");
		temp.setName(scnr.next());
		System.out.println("Please write Address of patient");
		temp.setAddress(scnr.next());
		System.out.println("Please write SSN of patient");
		temp.setSSN(scnr.next());
		System.out.println("Please write phone of patient");
		temp.setPhone(scnr.next());
		System.out.println("Please write Insurance provider of patient");
		temp.setInsuranceProvider(scnr.next());
		scnr.close();
	
	}
	
	public static void addDrug() {
		Scanner scnr = new Scanner(System.in);
		Drug temp = new Drug();
		
		System.out.println("Please write name of Drug:\n");
		temp.setName(scnr.next());
		System.out.println("Please write Chemical Name of drug:\n");
		temp.setChemicalName(scnr.next());
		System.out.println("Please write Manufacturer of drug:\n");
		temp.setManufacturer(scnr.next());
		System.out.println("Please write the drug type of the drug:\n");
		temp.setDrugtype(scnr.next());
		System.out.println("Is this drug in the watchlist?\n");
		temp.setInWatchlist(Boolean.parseBoolean(scnr.next()));
		scnr.close();	
	}
	
	public static void addDoctor() {
		Scanner scnr = new Scanner(System.in);
		Doctors temp = new Doctors();
		
		System.out.println("Please write name of doctor");
		temp.setName(scnr.next());
		System.out.println("Please write Address of doctor");
		temp.setAddress(scnr.next());
		System.out.println("Please write specialization of doctor");
		temp.setSpecialization(scnr.next());
		System.out.println("Please write phone of patient");
		temp.setPhone(scnr.next());
		
		scnr.close();
	}
	
	public static void commitDrugs() throws IOException {                      
		 
		PrintWriter writer = new PrintWriter("drug.txt", "UTF-8");

		for(int i = 0; i < drugs.size(); i++) {
			writer.println(drugs.get(i).printGeneralInfo() +  drugs.get(i).printConditions() + " " +
			drugs.get(i).printContradictions() + drugs.get(i).printIngredients()) ;
				           
		}

		writer.close();

	}
	
	public static void commitPatients() throws IOException {                      
		 
		PrintWriter writer = new PrintWriter("patient.txt", "UTF-8");

		for(int i = 0; i < patients.size(); i++) {
			writer.println(patients.get(i).printGeneralInfo());
		}

		writer.close();

	}
	
	public static void commitDoctors() throws IOException {                      
		 
		PrintWriter writer = new PrintWriter("doctors.txt", "UTF-8");

		for(int i = 0; i < doctors.size(); i++) {
			writer.println(doctors.get(i).printGeneralInfo());
		}

		writer.close();

	}
	
	public static void commitPrescriptions() throws IOException {                      
		 
		PrintWriter writer = new PrintWriter("prescription.txt", "UTF-8");

		for(int i = 0; i < prescriptions.size(); i++) {
			writer.println(prescriptions.get(i).printGeneralInfo() + prescriptions.get(i).printDrugLines());
			
		}

		writer.close();

	}
	
	public static void commitAll() throws IOException {
		commitDrugs(); 
		commitPatients();
		commitDoctors();
		commitPrescriptions();
	}
	
	public static void main(String[] args) throws IOException {
		processorInit();
		readTransactions(); //error in prescription writing
		commitAll();
	
		System.out.println("I compiled");
		
		
	}
}

