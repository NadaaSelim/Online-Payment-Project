package phase2;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import Database.Accounts;
import Payment.CreditCard;
import Users.GeneralUser;

@RestController
public class AccountController {
	
	
	private Accounts accs;
	

	public AccountController() {
		this.accs = Accounts.getInstance(); 
		//test.FirstSpringAppApplication.main(null);
	}

	@GetMapping("login/{username}/{email}/{password}")
	@ResponseBody	
	public String LogIn(@PathVariable String username, @PathVariable String email,@PathVariable String password) {
		List<GeneralUser> copy = accs.getAccs();
		GeneralUser new_user = new GeneralUser(username, email, password);
		if (copy.isEmpty()) {
			return "\nUSER DOES NOT EXIST. TRY AGAIN\n";
		}
		else if(accs.getUser(new_user) != null){
			GeneralUser usr= accs.getUser(new_user);
			return "LOGIN SUCCESSFUL\n"+usr.to_String();
		}
		else {
			return("\nUSER DOES NOT EXIST. TRY AGAIN\n");
		}
	}
	

	@PostMapping("/signup/{username}/{email}/{password}")
	public String signUp(@PathVariable String username, @PathVariable String email,@PathVariable String password) throws Exception {
		List<GeneralUser> copy = accs.getAccs();
		GeneralUser new_user = new GeneralUser(username, email, password);
		if ( copy.isEmpty()) {
			accs.addUser(new_user); //System.out.print(new_user.to_String());
			//return "SIGN UP SUCCESSFUL";
			return ("SIGN UP SUCCESSFUL\n"+new_user.to_String());	
		}
		
		if (accs.userExists(new_user)) {
				return("\nUSERNAME/EMAIL ALREADY EXISTS. PLEASE TRY AGAIN\n");
			}
		
		accs.addUser(new_user); 
		return "SIGN UP SUCCESSFUL\n"+new_user.to_String();
	}
	
	@PutMapping("/addCard/{username}/{cardNum}/{pin}/{balance}")
	@ResponseBody
	public String addCardInfo(@PathVariable String username,@PathVariable int cardNum,@PathVariable int pin,@PathVariable double balance) {
		GeneralUser user = accs.getUser(username);
		if(user == null) {return "Invalid Username.Try Again";}
		user.setCard(new CreditCard(cardNum,pin,balance));
		return "CARD ADDED\n"+user.to_String();		
	}

}