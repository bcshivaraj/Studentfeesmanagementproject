
public class User {
	
	private String name,password,email,contactno;
	public User() {}
	public User(String name, String password, String email, String contactno) {
		super();
		this.name = name;
		this.password = password;
		this.email = email;
		this.contactno = contactno;
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getContactno() {
		return contactno;
	}
	public void setContactno(String contactno) {
		this.contactno = contactno;
	}


}
