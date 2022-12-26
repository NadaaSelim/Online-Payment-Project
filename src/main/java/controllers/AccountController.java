package controllers;

import java.util.List;

import Database.*;
import Payment.*;
import Users.*;


public class AccountController {
	private Accounts accs;

	public AccountController(Accounts accs) {
		this.accs = accs;
	}

	public GeneralUser LogIn(String username, String email, String password) throws Exception {
		List<GeneralUser> copy = accs.getAccs();
		GeneralUser new_user = new GeneralUser(username, email, password);
		if (copy.isEmpty()) {
			throw new Exception("\nNO USERS REGISTERD YET\n");
		}
		else if(accs.getUser(new_user) != null){
			return accs.getUser(new_user);
		}
		else {
			throw new Exception("\nUSER DOES NOT EXIST. TRY AGAIN\n");
		}
	}

	public GeneralUser signUp(String username, String email, String password) throws Exception {
		List<GeneralUser> copy = accs.getAccs();
		GeneralUser new_user = new GeneralUser(username, email, password);
		if (copy.isEmpty()) {
			accs.addUser(new_user);
			return new_user;
		}
		
		if (accs.userExists(new_user)) {
				throw new Exception("\nUSERNAME/EMAIL ALREADY EXISTS. PLEASE TRY AGAIN\n");
			}
		
		accs.addUser(new_user);
		return new_user;
	}
	
	public void addCardInfo(GeneralUser user, int cardNum, int pin, double balance) {
		
		user.setCard(new CreditCard(cardNum,pin,balance));
	}

}