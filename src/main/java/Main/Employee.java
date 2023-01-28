package Main;

import java.io.Serializable;
import java.lang.module.ModuleDescriptor;

public class Employee implements Serializable {

    ModuleDescriptor.Exports Employee;
    public String name;
    public String department;
    public int ID;

    Employee(String name, String department, int ID){
        this.name = name;
        this.department = department;
        this.ID = ID;
    }
    Employee(){

    }
}
