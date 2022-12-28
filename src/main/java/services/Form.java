package services;

public  class Form {

    protected String content;
    protected String fieldName;

    public Form(String fieldName, String content){
        this.fieldName = fieldName;
        this.content = content;
    }
    public Form(){};
    
    public String searchForField(String fieldName){
        if(this.fieldName == fieldName){
            return this.content;
        }
        return null;
    }

}
