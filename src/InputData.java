import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class InputData {
    public static Scanner sc = new Scanner(System.in);

    public static int inputInt(String msg) {
        int data;
        do{
            System.out.println(msg);
            try{
                data = Integer.parseInt(sc.nextLine());
                return data;
            }catch(Exception e){
                System.err.println("Invalid an integer. Please Re-enter!");
            }
        }while(true);
    }

    public static int inputInt(String msg, int min, int Max) {
        int data;
        do{
            System.out.println(msg);
            try{
                data = Integer.parseInt(sc.nextLine());
                if(data < min || data > Max){
                    throw new Exception();
                }
                return data;
            }catch(Exception e){
                System.err.println("Invalid! Weight must be bigger than 0. Please Re-enter: ");
            }
        }while(true);
    }

    public static String inputString(String msg) {
        String data;
        System.out.println(msg);
        data = sc.nextLine().trim();
        return data;
    }

    public static String inputIDNotNullString(String msg, String err){
        String data;
        do{
            System.out.println(msg);
            data = sc.nextLine();
            if(data.length() == 0){
                System.err.println(err);
            }
        }while(data.length() == 0);
        return data;
    }

    public static String inputNameNotNullString(String msg){
        String data;
        do{
            System.out.println(msg);
            data = sc.nextLine();
            if(data.length() == 0){
                System.err.println("Name is not empty. Please Re-enter: ");
            }
        }while(data.length() == 0);
        return data;
    }

    public static Date inputDate(String msg) {
        Date data;
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        df.setLenient(false);
        do {
            try {
                data = df.parse(InputData.inputString(msg));
                return data;
            } catch (Exception e) {
                System.err.println("Invalid date input! Please re-enter: ");
            }
        } while (true);
    }

    public static Date inputDate(String msg, Date now){
        Date data = null;
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        df.setLenient(false);
        do {
            try {
                data = df.parse(InputData.inputString(msg));
                now = df.parse(df.format(now));
                if(now.compareTo(data) > 0) {
                    System.err.println("Value date input lest than date now. Please re-enter: ");
                }
                else {
                    return data;
                }
            } catch (Exception e) {
                System.err.println("Invalid date input! Please re-enter: ");
            }
        } while(true);
    }

    public static String inputPattern(String msg, String pattern) {
        String data;
        do {
            System.out.println(msg);
            data = sc.nextLine().trim().toUpperCase();
            if (!data.matches(pattern)) {
                System.err.println("String is not pattern! Please Re-enter: ");
            }
        } while (!data.matches(pattern));
        return data;
    }
}