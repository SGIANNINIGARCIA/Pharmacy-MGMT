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

public class Patients {
	
	private String name;
	private String SSN;
	private String address;
	private String phone;
	private String insuranceProvider;
		
	
	public Patients() {
		this.name = "";
		SSN = "";
		this.address = "";
		this.phone = "";
		this.insuranceProvider = "";
	}
	
	public Patients(String name, String sSN, String address, String phone, String insuranceProvider) {
		this.name = name;
		SSN = sSN;
		this.address = address;
		this.phone = phone;
		this.insuranceProvider = insuranceProvider;
	}
	
	//SETTERS AND GETTERS//
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSSN() {
		return SSN;
	}
	public void setSSN(String sSN) {
		SSN = sSN;
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
	public String getInsuranceProvider() {
		return insuranceProvider;
	}
	public void setInsuranceProvider(String insuranceProvider) {
		this.insuranceProvider = insuranceProvider;
	}
	
	public String printGeneralInfo() {
		return getName() + " " + getSSN() + " " + getAddress() + " " + getPhone() + " " + getInsuranceProvider();
	}
	
	public static Patients findPatient(ArrayList<Patients> patients, String name){ 				//FINDS A PATIENT

		for(int i = 0; i < patients.size(); i++) {
			if(patients.get(i).getName().equals(name)) {
				return patients.get(i);
			}
		}
		return null;

	}
}
