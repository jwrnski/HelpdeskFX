package Main;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.IntStream;

public class IDValidator {

    public static String name;

    public static boolean validateFromList(Object o){
        boolean contains;
        contains = EmployeeCreation.employeeList.contains(o);
        return contains;
    }

    public static String checkDepartment(int id){
        if(id > 10000 && id < 10999) return "Production";
        else if (id > 20000 && id < 20999) return "Office";
        else if(id > 30000 && id < 30999) return "IT";
        return null;
    }

    public static boolean validateFromJSON(int id) throws IOException {
        boolean contains;
        String fileName;
        String department = checkDepartment(id);
        fileName = department + "Employees.json";
        String path = "\\C:\\Users\\Kuba\\Desktop\\HelpdeskFX\\src\\main\\java\\Employees\\" + fileName;
        try {
            File jsonFile = new File(path);
            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNode = mapper.readTree(jsonFile);
            String arrayString = jsonNode.toString();
            switch (Objects.requireNonNull(department)) {
                case "Production" -> {
                    ArrayList<Production> ProdemployeeList = mapper.readerForListOf(Production.class).readValue(arrayString);
                    contains = ProdemployeeList.stream().anyMatch(p -> p.checkID() == id);
                    //for (Production emp : ProdemployeeList) System.out.println(emp + "\n");
                    if(contains){
                        for(int i = 0; i<ProdemployeeList.size(); i++)
                            if(ProdemployeeList.get(i).ID == id)
                                name = ProdemployeeList.get(i).name;
                    }
                    return contains;
                }
                case "Office" -> {
                    ArrayList<Office> OfficeemployeeList = mapper.readerForListOf(Office.class).readValue(arrayString);
                    contains = OfficeemployeeList.stream().anyMatch(p -> p.checkID() == id);
                    //for (Office emp : OfficeemployeeList) System.out.println(emp + "\n");
                    if(contains){
                        for(int i = 0; i<OfficeemployeeList.size(); i++)
                            if(OfficeemployeeList.get(i).ID == id)
                                name = OfficeemployeeList.get(i).name;
                    }
                    return contains;
                }
                case "IT" -> {
                    ArrayList<IT> ITemployeeList = mapper.readerForListOf(IT.class).readValue(arrayString);
                    contains = ITemployeeList.stream().anyMatch(p -> p.checkID() == id);
                    //for (IT emp : ITemployeeList) System.out.println(emp + "\n");
                    if(contains){
                        for(int i = 0; i<ITemployeeList.size(); i++)
                            if(ITemployeeList.get(i).ID == id)
                                name = ITemployeeList.get(i).name;
                    }
                    return contains;
                }
            }
        }
        catch(Exception e){
            /*Platform.runLater(()-> setLogData("No user with this ID."));
            LogInfo.getLogData();*/
            System.out.println(e);
        }
        return false;
    }

    /*public final class LogInfo {
        private static StringProperty logData = new SimpleStringProperty();
        public static StringProperty logDataProperty() { return logData; }
        public static void setLogData(String data) { logData.set(data); }
        public static String getLogData() { return logData.get(); }
    }*/
}

