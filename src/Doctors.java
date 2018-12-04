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
	
}
