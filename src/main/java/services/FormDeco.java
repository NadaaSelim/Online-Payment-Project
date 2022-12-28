package services;


public class FormDeco extends Form {

	protected Form form;


	public String searchForField(String fieldName){
		if(this.fieldName == fieldName){
			return this.content;
		}
		else{
			return form.searchForField(fieldName);
		}
	}
}