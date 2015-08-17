package pxv425;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
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
	
//	public LocalDateTime getCurrentDate(){
//		return LocalDateTime.now();
//	}
	
	private boolean isValidDayAndTime(){
		Calendar calendar = Calendar.getInstance();
		int today = calendar.get(Calendar.DAY_OF_WEEK);
		SimpleDateFormat time = new SimpleDateFormat("HH:mm:ss");
		if(today == Calendar.SATURDAY || today == Calendar.SUNDAY){
			return false;
		}
		else{
			if(time.format(calendar.getTime()).compareTo("16:29:59") == 1 || time.format(calendar.getTime()).compareTo("09:59:59") == -1){
				return false;
			}
			else{
				return true;
			}
		}
	}
	
	public boolean useIsValidDayAndTime(){
		return isValidDayAndTime();
	}
}
