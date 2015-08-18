package pxv425;

/**
 * This class contains the model for the IntroView which will be the first Panel
 * which will be shown
 * 
 * @author Panagiotis Vakalis
 * @version 14-07-2015
 *
 */
public class IntroModel extends Model {

	private LoginModel loginModel;
	private RegisterModel registerModel;

	/**
	 * Constructor of the class
	 * 
	 * @param client
	 * 
	 * @author Panagiotis Vakalis
	 * @version 14-07-2015
	 */
	public IntroModel(Client client) {
		super(client);
	}

	/**
	 * Method to get the registerModel
	 * 
	 * @author Panagiotis Vakalis
	 * @version 14-07-2015
	 */
	public RegisterModel getRegisterModel() {
		return registerModel;
	}

	/**
	 * Method to change to login view
	 * 
	 * @param login
	 *            view
	 * 
	 * @author Panagiotis Vakalis
	 * @version 14-07-2015
	 */
	private void changeToLoginView(View loginView) {
		loginModel = new LoginModel(super.getClient());
		super.getClient().useChangePanel(new LoginView(loginModel));
	}

	/**
	 * Method to change to login view
	 * 
	 * @param login
	 *            view
	 * 
	 * @author Panagiotis Vakalis
	 * @version 14-07-2015
	 */
	public void useChangeToLoginView(View loginView) {
		changeToLoginView(loginView);
	}

	/**
	 * Method to change to register view
	 * 
	 * @param register
	 *            view
	 * 
	 * @author Panagiotis Vakalis
	 * @version 14-07-2015
	 */
	private void changeToRegisterView(View registerView) {
		registerModel = new RegisterModel(super.getClient());
		super.getClient().useChangePanel(registerView);
	}

	/**
	 * Method to change to register view
	 * 
	 * @param register
	 *            view
	 * 
	 * @author Panagiotis Vakalis
	 * @version 14-07-2015
	 */
	public void useChangeToRegisterView(View registerView) {
		changeToRegisterView(registerView);
	}
}
