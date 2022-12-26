package Database;

import java.util.Collections;

import java.util.LinkedList;
import java.util.List;


import Users.*;

public class Accounts {
	
	private List<GeneralUser> accs; 

	Accounts() {
		accs = new LinkedList<GeneralUser>();
	}

	public List<GeneralUser> getAccs() {
		return Collections.unmodifiableList(accs);
	}
	public List<GeneralUser> getAccsTomodify()
	{
		return accs;
	}
	
	public GeneralUser getUser(GeneralUser user){ 
		for(int i = 0 ; i<accs.size() ; i++){
			if(accs.get(i).getEmail().equals(user.getEmail()) && accs.get(i).getUsername().equals(user.getUsername()) && accs.get(i).getPassword().equals(user.getPassword())){
				return accs.get(i);
			}
		}
		return null;
	}
	public void addUser(GeneralUser user) {
		accs.add(user);
	}
	
	public boolean userExists(GeneralUser user) {
		for(int i = 0 ; i<accs.size() ; i++){
			if(accs.get(i).getEmail().equals(user.getEmail()) && accs.get(i).getUsername().equals(user.getUsername()) || accs.get(i).getPassword().equals(user.getPassword())){
				return true;
			}
		}
		return false;
	}

}
