package Main;

import java.io.Serializable;

public class Office extends Employee implements Serializable {

    public String division;
    final static String department = "Office";
    Office(String name, int ID, String division){
        super(name, department, ID);
        this.division = division;
    }
    Office(){

    }
    public String toString(){
        return "ID number: " + this.ID + "\nName: " + this.name +
                "\nDepartment: " + department + "\nDivision: " + this.division;
    }
}
