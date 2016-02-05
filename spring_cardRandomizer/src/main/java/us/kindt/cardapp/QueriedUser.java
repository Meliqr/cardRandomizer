package us.kindt.cardapp;

public class QueriedUser extends User{

	private String queryResponse;

	
	public QueriedUser(long id, String userName, String firstName, String lastName, String emailAddress, String queryResponse) {
		super(id, userName, firstName, lastName, emailAddress);
		this.queryResponse = queryResponse;
	}
	public QueriedUser(String userName, String pass, String queryResponse) {
		super(userName, pass);
		this.queryResponse = queryResponse;
	}
	public QueriedUser(String queryResponse) {
		super();
		this.queryResponse = queryResponse;
	}
	public QueriedUser(){
		super();
	}
	public String getQueryResponse() {
		return queryResponse;
	}

	public void setQueryResponse(String queryResponse) {
		this.queryResponse = queryResponse;
	}
	
	
}
