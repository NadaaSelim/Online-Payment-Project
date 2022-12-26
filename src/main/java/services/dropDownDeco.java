package services;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class dropDownDeco extends FormDecorator {

	private List<String> list =  new ArrayList<String>();
	public dropDownDeco(Form form, String fieldName, List<String> list ) {
		this.form = form;
		this.fieldName = fieldName;
		this.list = list;
	}
	public void takeInput() throws Exception {
		System.out.println("Please select one option for "+this.fieldName+" : ");
		for (int i = 0 ; i<list.size() ; i++){
			System.out.println("▼ "+ (i+1) +" " +list.get(i));
		}
		Scanner scanner = new Scanner(System.in);
		int value = scanner.nextInt()-1;
		if (value>=0 && value < list.size()){
			content = list.get(value);
			form.takeInput();
			scanner.close();
		}
		else
			scanner.close();
			throw new Exception("The value you entered is out of range");
	}
	public void display() {
		System.out.println(content);
		form.display();
	}

}

