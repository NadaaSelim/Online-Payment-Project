package services;

import java.util.Scanner;

public  class Form {

    protected String content;
    protected String fieldName;

    public Form(String fieldName){
        this.fieldName = fieldName;
    }
    public Form(){};
    public void takeInput() throws Exception {
        System.out.println(fieldName+" : ");
        Scanner scanner = new Scanner(System.in);
        content = scanner.next();
        scanner.close();
    }
    public void display() {
        System.out.println(content);
    }

    public String searchForField(String fieldName){
        if(this.fieldName == fieldName){
            return this.content;
        }
        return null;
    }

    public void setContent(String content) {this.content = content;}

}

