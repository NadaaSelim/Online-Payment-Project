package controllers;

import java.util.ArrayList;

import Database.*;
import Payment.*;
import services.*;
import Users.*;

public class PayForServiceController {

	public boolean payForService(Service service, GeneralUser user, PaymentMethod method) throws Exception {
		service.displayForm();
		if(service.handle(user, method)) {
			return true;
		}
		return false;
		
	}
	
	public ArrayList<Service> searchForService(String word, Catalog c) {
		
		return c.searchCatalog(word);
		
	}
}
