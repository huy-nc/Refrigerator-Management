import java.text.ParseException;
import java.util.Scanner;

public class MainMenu {
    public static void main(String[] args) throws ParseException {

        Scanner sc = new Scanner(System.in);
        RefrigeratorManager fm = new RefrigeratorManager();

        System.out.println("Welcome to Food Management - @ 2021 by <SE150202 - Nguyễn Công Huy>");

        int choice;
        do{
            System.out.println();
            System.out.print ("=============================>>> Menu <<<============================\n"+
                    "1. Add a new food\n" +
                    "2. Search a food by name\n" +
                    "3. Remove the food by ID\n" +
                    "4. Print the food list in the descending order of expired date\n" +
                    "5. Store the food list to binary search\n" +
                    "6. Quit\n");
            choice = InputData.inputInt("Select the options: ");

            String isContinue;
            String reg = "{1}[yYnN]";
            switch(choice){
                case 1:
                    do{
                        fm.addFood();
                        fm.saveFile();
                        isContinue = InputData.inputPattern("Do you want to continue this option?(Y/N)", reg);
                    }while(isContinue.equalsIgnoreCase("Y"));
                    break;
                case 2:
                    do{
                        fm.searchFoodByName();
                        isContinue = InputData.inputPattern("Do you want to continue this option?(Y/N)", reg);
                    }while(isContinue.equalsIgnoreCase("Y"));
                    break;
                case 3:
                    fm.removeFood();
                    fm.saveFile();
                    break;
                case 4:
                    fm.readList();
                    break;
                case 5:
                    fm.saveFile();
                    System.out.println("The food list have been store!");
                    break;
                case 6:
                    System.out.println("Quit Food Management Program.");
                    break;
                case 7:
                    try{
                        fm.autoAddData();
                        fm.saveFile();
                        System.out.println("Auto Add Success.");
                    }catch(Exception e){
                        System.err.println(e);
                    }
                    break;
            }
        }while(choice != 6);
    }

}
