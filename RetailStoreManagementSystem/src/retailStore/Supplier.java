package retailStore;

public class Supplier {
	private int id;
	private String name;
	private String address;
	private String email;
	private int contactNumber;
	private int contactPerson;
	
	//getters
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getAddress() {
		return address;
	}
	public String getEmail() {
		return email;
	}
	public int getContactNumber() {
		return contactNumber;
	}
	public int getContactPerson() {
		return contactPerson;
	}
	
	//setters
	public void setId(int id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setContactNumber(int contactNumber) {
		this.contactNumber = contactNumber;
	}
	public void setContactPerson(int contactPerson) {
		this.contactPerson = contactPerson;
	}
	
	
}
