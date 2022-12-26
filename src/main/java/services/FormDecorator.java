package services;


public abstract class FormDecorator extends Form {

	 protected Form form;

	public abstract void display();

	public String searchForField(String fieldName){
		if(this.fieldName == fieldName){
			return this.content;
		}
		else{
			return form.searchForField(fieldName);
		}
	}
}
