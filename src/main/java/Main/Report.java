package Main;

import java.io.Serializable;
import java.lang.module.ModuleDescriptor;

public class Report implements Serializable {

    ModuleDescriptor.Exports Report;
    public int ID;
    public String subject;
    public String priority;
    public String description;
    public String status;

    public Report(int ID, String subject, String priority, String description, String status){
        this.ID = ID;
        this.subject = subject;
        this.priority = priority;
        this.description = description;
        this.status = status;
    }
    Report(){

    }
}
