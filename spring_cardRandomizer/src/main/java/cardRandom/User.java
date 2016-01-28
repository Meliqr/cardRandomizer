package cardRandom;

public class User {
    private long id;
    private String userName, pass, firstName, lastName, emailAddress;

    
    public User(String userName, String pass) {
        this.userName = userName;
        this.pass = pass;
    }
    
    public User(long id, String userName, String firstName, String lastName, String emailAddress) {
        this.id = id;
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
    }
    public User(){
    }

    @Override
    public String toString() {
        return String.format(
                "Customer[id=%d, userName='%s', firstName='%s', lastName='%s', emailAddress='%s']",
                id, userName, firstName, lastName, emailAddress);
    }

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}
    

}
