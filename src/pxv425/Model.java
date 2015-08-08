package pxv425;

import java.util.Observable;

/**
 * This class contains the Model part of the View - Model
 * separation
 * 
 * @author Panagiotis Vakalis
 * @version 14-07-2015
 *
 */
public abstract class Model extends Observable {

	private Client client;
	
	/**
	 * Constructor of the class
	 * @param client
	 * 
	 * @author Panagiotis Vakalis
	 * @version 14-07-2015
	 */
	public Model(Client client){
		this.client = client;
	}
	
	/**
	 * Method to get the client outside of the class
	 * @return client
	 * 
	 * @author Panagiotis Vakalis
	 * @version 14-07-2015
	 */
	public Client getClient(){
		return client;
	}
	
	public void setClient(Client client){
		this.client = client;
	}
}
