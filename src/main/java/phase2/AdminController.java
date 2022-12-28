package phase2;

import java.util.List;


import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import Database.*;
import Payment.*;
import services.*;
import Transactions.*;
import Users.*;


@RestController
@Component
@RequestMapping("/Admin")
public class AdminController {
	
  
	private Accounts accounts;
	Catalog catalog;
	TransactionsLog translog;
	RefundRequestsLog rlog;
	public AdminController() {
//		this.accounts=accounts;
//		this.catalog=catalog;
			accounts=Accounts.getInstance();
			translog=TransactionsLog.getLog();
			rlog=RefundRequestsLog.getRefundLog();
		
	}
	
	///TODO change return data type if needed
	
	
	
	/// case 1 -> add discount
	/// case 1.1->add overall discount
	@PutMapping("/addOverallDiscount")
	@ResponseBody
	public String addOverallDiscount( @RequestBody Discount overallDiscount )  {
		
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
	@GetMapping("/listUserTransactions/{username}")
	@ResponseBody
	public List<Transaction> listUserTransactions(@PathVariable String Username){
		
		return translog.listAllUserTrans(Username);
	}
	///case 3.1->list all refund requests and approve or reject
	@GetMapping("/listRefundRequests")
	@ResponseBody
	public List<Transaction> listRefundRequests(){
		
		return rlog.getRequests();
	}
	
	///case 3.2 ->accept or reject a request
	@PutMapping("/accept/{transnumber}")
	@ResponseBody
	public String accept(@RequestBody int transnumber) {
	
		Transaction t=rlog.getRequests().get(transnumber-1);
		t.getMethod().add(t.getAmount());
		t.setRefunded(true);
		rlog.removeRequest(t);
		return "accepted";
		
	}
	
	@PutMapping("/reject/{transnumber}")
	@ResponseBody
	public String reject(@RequestBody int transnumber) {
		
		Transaction t=rlog.getRequests().get(transnumber-1);
		t.setRefunded(false);
		rlog.removeRequest(t);
		return "Rejected";
		
	}
	
	
	
	
	
	
	

}
