package controllers;

import Database.*;
import Payment.*;
import Transactions.*;
import Users.*;

public class AddToWalletController {
	
	public boolean addToWallet(GeneralUser user, CreditCard card, double amount) {
		
		if(user.getWallet().addFunds(card, amount)) {
			Transaction t = new AddToWalletTransaction(user, card, amount);
			TransactionsLog log = TransactionsLog.getLog();
			log.addTransaction(t);
			return true;
		}
		else {
			return false;
		}
	}

}
