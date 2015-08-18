package pxv425;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Observable;

/**
 * This class contains the Model part of the View - Model separation
 * 
 * @author Panagiotis Vakalis
 * @version 14-07-2015
 *
 */
public abstract class Model extends Observable {

	private Client client;

	/**
	 * Constructor of the class
	 * 
	 * @param client
	 * 
	 * @author Panagiotis Vakalis
	 * @version 14-07-2015
	 */
	public Model(Client client) {
		this.client = client;
	}

	/**
	 * Method to get the client outside of the class
	 * 
	 * @return client
	 * 
	 * @author Panagiotis Vakalis
	 * @version 14-07-2015
	 */
	public Client getClient() {
		return client;
	}

	/**
	 * Method to set the client outside of the class
	 * 
	 * @param client
	 * 
	 * @author Panagiotis Vakalis
	 * @version 14-07-2015
	 */
	public void setClient(Client client) {
		this.client = client;
	}

	/**
	 * Method to check whether the stock market is open or closed
	 * 
	 * @return true, if the time and the day are ok return false, if the time
	 *         and the day are not ok
	 * 
	 * @author Panagiotis Vakalis
	 * @version 14-07-2015
	 */
	private boolean isValidDayAndTime() {
		/*
		 * Create a calendar object
		 */
		Calendar calendar = Calendar.getInstance();
		/*
		 * Get the current day
		 */
		int today = calendar.get(Calendar.DAY_OF_WEEK);
		SimpleDateFormat time = new SimpleDateFormat("HH:mm:ss");
		if (today == Calendar.SATURDAY || today == Calendar.SUNDAY) {
			/*
			 * If the current day is either Saturday or Sunday then the stock
			 * market is closed. So, the method returns false
			 */
			return false;
		} else {
			/*
			 * If the current day is neither Saturday nor Sunday
			 */
			if (time.format(calendar.getTime()).compareTo("16:29:59") == 1
					|| time.format(calendar.getTime()).compareTo("09:59:59") == -1) {
				/*
				 * If the time is after 16:29:59 or before 09:59:59 then the
				 * stock market is closed and the method returns false
				 */
				return false;
			} else {
				/*
				 * If the time is between the already mentioned times then the
				 * marker is open and the method returns true
				 */
				return true;
			}
		}
	}

	/**
	 * Method to check whether the stock market is open or closed
	 * 
	 * @return true, if the time and the day are ok return false, if the time
	 *         and the day are not ok
	 * 
	 * @author Panagiotis Vakalis
	 * @version 14-07-2015
	 */
	public boolean useIsValidDayAndTime() {
		return isValidDayAndTime();
	}
}
