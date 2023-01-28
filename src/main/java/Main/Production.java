package Main;

import java.io.Serializable;

public class Production extends Employee implements Serializable {

    public String division;
    public final static String department = "Production";
    Production(String name, int ID, String division){
        super(name, department, ID);
        this.division = division;
    }
    Production(){

    }
    public int getID(){
        return ID;
    }
    public String toString(){
        return "ID number: " + this.ID + "\nName: " + this.name +
                "\nDepartment: " + department + "\nDivision: " + this.division;
    }


}
