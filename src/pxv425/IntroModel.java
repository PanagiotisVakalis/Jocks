package pxv425;

public class IntroModel extends Model {
	
	private LoginModel loginModel;
	private RegisterModel registerModel;
	
	public IntroModel(Client client){
		super(client);
	}
	
	public RegisterModel getRegisterModel(){
		return registerModel;
	}

	private void changeToLoginView(View loginView){
		loginModel = new LoginModel(super.getClient());
		super.getClient().useChangePanel(new LoginView(loginModel));
	}
	
	public void useChangeToLoginView(View loginView){
		changeToLoginView(loginView);
	}
	
	private void changeToRegisterView(View registerView){
		registerModel = new RegisterModel(super.getClient());
//		super.getClient().useChangePanel(new RegisterView(registerModel));
		super.getClient().useChangePanel(registerView);
	}
	
	public void useChangeToRegisterView(View registerView){
		changeToRegisterView(registerView);
	}
}
