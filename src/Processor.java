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

	public static ArrayList<Doctors> readDoctors() throws IOException {  //Reads the doctor.txt file             

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

	public static ArrayList<Patients> readPatients() throws IOException {             //Reads the patient.txt file  

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

	public static ArrayList<Prescriptions> readPrescriptions() throws IOException {             //Reads the prescription.txt file  

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
					
					Drug tempN = Drug.findDrug(drugs, (fields[i].substring(1)));  //Checks if drug is null
					if(tempN == null) {
						System.out.println("Cant find this drug, please add it in order to search the watchlist database.");
						continue;
					}
					Doctors tempDoc = Doctors.findDoctor(doctors, fields[2] + " " + fields[3]); //checks if docto is null
					if(tempDoc == null) {
						System.out.println("Cant find this doctor, please add it in order to search the watchlist database.");
						continue;
					}
					
					watchListUpdate(tempN, tempDoc);
							
				}

				temp.setDrugLines(drugLines);	

			}
			
			prescriptions.add(temp);
		}  
		in.close();

		return prescriptions;
	}
	
	public static void readTransactions() throws IOException {      ///Reads the transactions.txt file  and executes commands          

		String currentLine;	     
		String[] fields;
		Scanner in = new Scanner(new BufferedReader(new FileReader(TRANSACTION_FILEPATH)));	

		while (in.hasNext()) {

			currentLine = in.nextLine();	         
			fields = currentLine.split(" ");  				
            
			
			if (fields[0].equals("FD")) { 
			   Drug temp = Drug.findDrug(drugs, fields[1]);
			   if(temp == null) {
				   System.out.println("Couldnt find the drug, please search for a valid drug");
				   continue;
			   }
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
	
	public static void watchListUpdate(Drug drug, Doctors doctor) {    //It populates each doctor watchlist based on the prescription.txt
		
		HashMap<Drug, Integer> watchlist = doctor.getWatchlist();  
		
		if(drug.isInWatchlist() == true && watchlist.containsKey(drug) == false) {
			watchlist.put(drug, 0);
		}
		
		if(drug.isInWatchlist() == true && watchlist.containsKey(drug) == true) {
			int timesPrescribed = watchlist.get(drug) + 1;
			watchlist.replace(drug, timesPrescribed);
			
		}
		
		
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
	
	public static void commitAll() throws IOException {   //Writes to every txt. file (Updates the database)
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

