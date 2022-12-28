package phase2;

import java.util.List;


import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import Database.*;
import Payment.*;
import services.*;
import Transactions.*;
import Users.*;

@SpringBootApplication
@RestController
@Component
public class AdminController {
	
  
	private Accounts accounts;
	Catalog catalog;
	TransactionsLog translog=TransactionsLog.getLog();
	RefundRequestsLog rlog=RefundRequestsLog.getRefundLog();
	public AdminController() {
//		this.accounts=accounts;
//		this.catalog=catalog;
			this.accounts=new Accounts();
		
	}
	
	///TODO change return data type if needed
	
	
	
	/// case 1 -> add discount
	/// case 1.1->add overall discount
	@PostMapping("/addOverallDiscount/{factor}/{numberOftimestobeused}")
	@ResponseBody
	public String addOverallDiscount(@PathVariable Double factor,@PathVariable int numberOftimestobeused)  {
		Discount overallDiscount=new Discount(factor,numberOftimestobeused);
		List<GeneralUser> accountsModify=accounts.getAccsTomodify();
		if(accountsModify.isEmpty())
			return "no users";
		for(GeneralUser U:accountsModify)
		{
			U.setDiscount(overallDiscount);
		}
		return "discount added";
		
		
	}
	//// case 1.2 -> add specific discount for a certain service
	/// throws exception if entered a wrong services description
	///TODO modify (display list of all possible services)
	public void addSpecificDiscount(Double factor,int numberOftimestobeused, Service s ) 
	{
		Discount specficDiscount =new Discount(factor,numberOftimestobeused);
		s.setDiscount(specficDiscount);

	}
	
	/// case 2 -> list user transactions if list empty should be handled in boundary
	public List<Transaction> listUserTransactions(String Username){
		
		return translog.listAllUserTrans(Username);
	}
	///case 3.1->list all refund requests and approve or reject
	public List<Transaction> listRefundRequests(){
		
		return rlog.getRequests();
	}
	
	///case 3.2 ->accept or reject a request
	///TODO commit
	public boolean accept(int transnumber) {
	
		Transaction t=rlog.getRequests().get(transnumber-1);
		t.getMethod().add(t.getAmount());
		t.setRefunded(true);
		rlog.removeRequest(t);
		return true;
		
	}
	
	public boolean reject(int transnumber) {
		
		Transaction t=rlog.getRequests().get(transnumber-1);
		t.setRefunded(false);
		rlog.removeRequest(t);
		return true;
		
	}
	
	
	
	
	
	
	

}
