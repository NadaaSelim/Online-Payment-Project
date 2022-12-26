package services;


import java.util.Scanner;

public class TextFieldDeco extends FormDecorator {
	public TextFieldDeco(Form form, String fieldName) {
		this.form = form;
		this.fieldName = fieldName;
	}
	public void takeInput() throws Exception {
		System.out.println(this.fieldName+" : ");
		Scanner scanner = new Scanner(System.in);
		content = scanner.next();
		scanner.close();
		form.takeInput();
	}
	public void display() {
		System.out.println(content);
		form.display();
	}


}

