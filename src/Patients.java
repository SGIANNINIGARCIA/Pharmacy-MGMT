
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
		
	//END OF SETTERS AND GETTERS//
	///////////METHODS///////////	
	//TODO--------------------//
	//patients have prescription 
}
