package Main;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class EmployeeCreation implements Serializable{

    public static ArrayList<Object> employeeList = new ArrayList<>();

    static JsonFileAppender json = new JsonFileAppender();

    public static void createEmployee(String name, int id, String department, String division) throws IOException {
        switch (department){
            case "Production" ->{
                Production productionEmployee = new Production(name, id, division);
                saveToJSON(productionEmployee);
                addToList(productionEmployee);
            }
            case "Office" ->{
                Office officeEmployee = new Office(name, id, division);
                saveToJSON(officeEmployee);
                addToList(officeEmployee);
            }
            case "IT" ->{
                IT itEmployee = new IT(name, id, division);
                saveToJSON(itEmployee);
                addToList(itEmployee);
            }

        }
    }
    /*public static int assignID(String department, int x){
        int ID = 0;
        switch (department){
            case "Production" -> {
                ID = 10000 + x;
            }
            case "Office" ->{
                ID = 20000 + x;
            }
            case "IT" -> {
                ID = 30000 + x;
            }
        }
        return ID;
    }*/
    public static void saveToJSON(Object employee) throws IOException {
        String fileName = employee.getClass().getSimpleName() + "Employees.json";
        String filePath = "C:\\Users\\Kuba\\Desktop\\HelpdeskFX\\src\\main\\java\\Employees\\" + fileName;
        File file = new File(filePath);
        json.appendToArray(file, employee);
    }
    public static void addToList(Object employee){
        employeeList.add(employee);
    }

}
