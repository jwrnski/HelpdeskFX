package Main;

import java.io.Serializable;

public class IT extends Employee implements Serializable {

    public String division;
    final static String department = "IT";
    IT(String name, int ID, String division){
        super(name, department, ID);
        this.division = division;
    }
    IT(){

    }

    public int getID(){
        return ID;
    }
    public String toString(){
        return "ID number: " + this.ID + "\nName: " + this.name +
                "\nDepartment: " + department + "\nDivision: " + this.division;
    }
}
