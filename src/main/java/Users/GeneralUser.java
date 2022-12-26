package Users;
import Payment.*;

public class GeneralUser extends User{
	
	private Discount discount;
	private Wallet wallet;
	private CreditCard card;

	public GeneralUser(String username, String email, String password) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.discount = new Discount(0.9,1);
		wallet = new Wallet();
		
	}


	public void setWallet(Wallet wallet) {
		this.wallet = wallet;
	}


	public Discount getDiscount() {
		return discount;
	}


	public void setDiscount(Discount discount) {
		this.discount = discount;
	}


	public Wallet getWallet() {
		return wallet;
	}


	public CreditCard getCard() {
		return card;
	}


	public void setCard(CreditCard card) {
		this.card = card;
	}

}
