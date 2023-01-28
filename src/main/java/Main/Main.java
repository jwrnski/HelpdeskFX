package Main;

public class Main {

    public static String[] divisionProd = {"Assembly", "Processing", "Machining"};
    public static String[] divisionIT = {"Design", "Programming", "Sysadmin"};
    public static String[] divisionOffice = {"Reception", "Sales", "Accounting"};

    public static String[] Divisions(String dep){
        switch(dep){
            case "Production" -> {
                return divisionProd;
            }
            case "Office" -> {
                return divisionOffice;
            }
            case "IT" -> {
                return divisionIT;
            }
        }
        return new String[0];
    }
}
