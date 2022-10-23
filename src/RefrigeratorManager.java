import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Scanner;

public class RefrigeratorManager {

    Scanner sc = new Scanner(System.in);
    ArrayList<Food> foodManager = new ArrayList<>();

    // Add Food
    public void addFood(){
        try{
            String _ID, _name, _type, _place;
            int _weight;
            Date _expirationDate;
            _ID = inputID();
            _name = InputData.inputNameNotNullString("Input name: ");
            _weight = InputData.inputInt("Input weight(gam): ", 1, 20000);
            _type = inputType();
            _place = inputPlace();
            _expirationDate = InputData.inputDate("Input expiration date: ", new Date());
            Food f = new Food(_ID, _name, _weight, _type, _place, _expirationDate);
            foodManager.add(f);
            System.out.println("=======>>>> Add Food Success! <<<<=======");
        }catch(Exception e){
            System.err.println(e);
        }
    }

    int checkID(String id){
        int index = -1;
        int i = 0;
        for(Food f: foodManager){
            if(f.getID().equalsIgnoreCase(id)){
                index = i++;
            }
        }
        return index;
    }

    public String inputID(){
        String id = null;
        do{
            id = InputData.inputIDNotNullString("Enter ID: ", "ID can not empty! Please re-enter: ");
            if (checkID(id) != -1){
                System.err.println("ID existed!");
            }
            else break;
        } while (true);
        return id;
    }
    public String inputType() {
        String type = null;
        System.out.print("Types of food.\n" +
                "1. Vegetable\n" +
                "2. Meat\n" +
                "3. Seafood\n" +
                "4. Egg\n" +
                "5. Drinks\n" +
                "6. Canned food\n");
        int choice;
        do{
            choice = InputData.inputInt("Choose type of food: ");
            if(choice < 1 || choice > 6){
                System.err.println("Place must be 1 in 6 options above!");
            }
            else{
                switch(choice) {
                    case 1:
                        type = "Vegetable";
                        break;
                    case 2:
                        type = "Meat";
                        break;
                    case 3:
                        type = "Seafood";
                        break;
                    case 4:
                        type = "Egg";
                        break;
                    case 5:
                        type = "Drinks";
                        break;
                    case 6:
                        type = "Canned food";
                        break;
                }
            }
        }while(choice < 1 || choice > 6);
        return type;
    }

    public String inputPlace() {
        String place = null;
        System.out.print("Place the food in refrigerator."
                + "\n1. Freezer"
                + "\n2. Cooler"
                + "\n3. Refrigerator door\n");
        int choice;
        do{
            choice = InputData.inputInt("Choose place for food: ");
            if(choice < 1 || choice > 3){
                System.err.println("Place must be 1 in 3 options above!");
            }
            else{
                switch(choice) {
                    case 1:
                        place = "Freezer";
                        break;
                    case 2:
                        place = "Cooler";
                        break;
                    case 3:
                        place = "Refrigerator door";
                        break;
                }
            }
        }while(choice < 1 || choice > 3);
        return place;
    }

    //Read food from list
    public void readList(){
        if(foodManager.isEmpty()){
            System.err.println("Empty list!");
        }
        else{
            Collections.sort(foodManager, new sortExpirationDate());
            System.out.println("====================================================>>>>Food List<<<<===================================================");
            System.out.println("|         ID         |        Name        |       Weight       |        Type        |       Place        |  Expired Date");
            System.out.println("------------------------------------------------------------------------------------------------------------------------");
            for (Food list : foodManager){
                list.Display();
            }
            System.out.println("------------------------------------------------------------------------------------------------------------------------");
        }
    }

    //Reasearch food by name
    public void searchFoodByName(){
        if(foodManager.isEmpty()){
            System.err.println("Empty list!");
        }
        else{
            try{
                String sName = InputData.inputNameNotNullString("Input food name need to search: ");
                boolean isFound = false;
                    System.out.println("==================================================>>>>Food List<<<<==================================================");
                    for  (Food food: foodManager){
                        if (food.getName().contains(sName)){
                            food.Display();
                            isFound = true;
                        }
                    }
                    if(isFound == false){
                        System.err.println("Food '" + sName + "' not found!");
                    }
                    System.out.println("---------------------------------------------------------------------------------------------------------------------");
            }catch(Exception e) {
                System.err.println(e);
            }
        }
    }

    //Remove food by ID
    public void removeFood() {
        if (foodManager.isEmpty())
            System.err.println("Empty list!");
        else {
            String dID = InputData.inputIDNotNullString("Input food ID to delete: ", "Not found!");
            Food f = null;
            for (int i = 0; i < foodManager.size(); i++) {
                if (foodManager.get(i).getID().equalsIgnoreCase(dID)){
                    f = foodManager.get(i);
                    break;
                }
            }
            if (f != null){
                System.out.println("===========================================>>>> Food want to remove <<<<===========================================");
                f.Display();
                System.out.println("--------------------------------------------------------------------------------------------------------------------");
                String reg = "{1}[yYnN]";
                String isRemove = InputData.inputPattern("Do you want to remove this food? (Y/N)", reg);
                if (isRemove.equalsIgnoreCase("Y")){
                    foodManager.remove(f);
                    System.out.println("Food deleted.");
                }
            } else {
                System.err.println("Food doesn't exist!");
            }
        }
    }

    File f = new File("Data.txt");
    public void saveFile(){
        PrintStream ps;
        if(foodManager.isEmpty())
            System.err.println("Empty list!");
        else{
            try{
                ps = new PrintStream(new FileOutputStream(f));
                for(Food food: foodManager){
                    ps.println(food.toString());
                }
                ps.close();
            }catch(Exception e){
                System.err.println(e);
            }
        }
    }

    private class sortExpirationDate implements Comparator<Food>{

        @Override
        public int compare(Food o1, Food o2) {
            return o2.getExpirationDate().compareTo(o1.getExpirationDate());
        }

    }

    //===========================================================================================================

    public void autoAddData() throws ParseException{
        DateFormat d = new SimpleDateFormat("dd/MM/yyyy");
        foodManager.add(new Food("123", "Rau Cai", 2000, "Vegetable", "Cooler", d.parse("24/10/2021")));
        foodManager.add(new Food("234", "Rau Lang", 1000, "Vegetable", "Cooler", d.parse("25/10/2021")));
        foodManager.add(new Food("345", "Thit hop", 500, "Canned food", "Refrigerator door", d.parse("22/12/2022")));
    }
}

