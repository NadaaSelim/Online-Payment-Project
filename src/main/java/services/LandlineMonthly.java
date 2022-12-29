package services;
import Database.*;
import Payment.*;
import Transactions.*;
import Users.*;

public class LandlineMonthly extends Service {

	public LandlineMonthly() {
		this.type = "Landline";
		this.description = "Landline Monthly Receipt";
		this.url = "LandlineMonthly";
		this.discount = new Discount();
		this.COD = false;
		this.form = new Form();
	}

	@Override
	public boolean handle(GeneralUser user, PaymentMethod method) {

		String amount = form.searchForField("AMOUNT");
		if (amount.matches("[0-9]+") && form.searchForField("LANDLINE").matches("[0-9]+")) {
			double x = Double.parseDouble(amount);
			Double value = x*discount.use()*user.getDiscount().use();
			if (x > 0 && method.pay(x * discount.use() * user.getDiscount().use())) {
				Transaction t = new PaymentTransaction(user, method, this.type, this.description, value);
				TransactionsLog log = TransactionsLog.getLog();
				log.addTransaction(t);
				return true;
			}
		}
		return false;
	}

}
