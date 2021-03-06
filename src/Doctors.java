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
import java.util.HashMap;

public class Doctors {
	
	private String name;
	private String address;
	private String phone;
	private String specialization;
	private HashMap<Drug, Integer> watchList;  
	
	public Doctors() {
		this.name = "";
		this.address = "";
		this.phone = "";
		this.specialization = "";
		this.watchList = new HashMap<Drug, Integer>();
	}
	
	public Doctors(String name, String address, String phone, String specialization, String[][] watchlist) {
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.specialization = specialization;
		this.watchList = new HashMap<Drug, Integer>();
	}
	
	public Doctors(String name, String address, String phone, String specialization) {
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.specialization = specialization;
		this.watchList = new HashMap<Drug, Integer>();
		
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getSpecialization() {
		return specialization;
	}
	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}
	public HashMap<Drug, Integer> getWatchlist() {
		return watchList;
	}
	public void setWatchlist(HashMap<Drug, Integer> watchlist) {
		this.watchList = watchlist;
	} 
	
	public String printGeneralInfo() {
		return getName() + " " + getAddress() + " " + getPhone() + " " + getSpecialization();
	}
	
	public static void contactDoctor(ArrayList<Doctors> doctors, String name) {
		Doctors toBePrinted = findDoctor(doctors, name);
		if(toBePrinted != null) {
		
			System.out.println(findDoctor(doctors, name).getName());
			System.out.println(findDoctor(doctors, name).getAddress());
			System.out.println(findDoctor(doctors, name).getPhone());
			System.out.println(findDoctor(doctors, name).getSpecialization());
			System.out.println();
		}
		else {System.out.println("Doctor could not be found in the system");}
	}
	
	public static Doctors findDoctor(ArrayList<Doctors> doctors, String name){ 				//FINDS A DOCTOR 

		for(int i = 0; i < doctors.size(); i++) {
			if(doctors.get(i).getName().equals(name)) {
				return doctors.get(i);
			}
		}
		return null;

	
	}

}

