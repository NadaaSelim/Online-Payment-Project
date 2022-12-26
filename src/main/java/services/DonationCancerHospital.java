package services;
import Database.*;

import Payment.*;
import Transactions.*;
import Users.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DonationCancerHospital extends Service{
	
	List<String> list = new ArrayList<String>(Arrays.asList("57357","500 500","Baheya"));
	
	public DonationCancerHospital() {
		this.type = "Donation";
		this.description = "Donation to a cancer hospital";
		this.discount  = new Discount();
		this.COD = true;
		this.form = new Form("PHONE");
		this.form = new TextFieldDeco(this.form, "AMOUNT");
		this.form = new dropDownDeco(this.form, "HOSPITAL", list);
		
	}

	@Override
	public boolean handle(GeneralUser user, PaymentMethod method) {
		String amount = form.searchForField("AMOUNT");
		if(amount.matches("[0-9]+") && form.searchForField("PHONE").matches("[0-9]+"))
		{
			double x = Double.parseDouble(amount);
			Double value = x*discount.use();
			if (x > 0 && method.pay(x*discount.use()))
			{
            	String s = this.description + ": "+form.searchForField("HOSPITAL");
				Transaction t =  new PaymentTransaction(user,method,this.type,s,value);
            	TransactionsLog log = TransactionsLog.getLog();
            	log.addTransaction(t);
            	return true;
            }
		}
		return false;
	}

}
	
