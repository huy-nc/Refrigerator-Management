import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

public class Food{

    private String ID, name, type, place;
    private int weight ;
    private Date expirationDate;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Food(String ID) {
        this.ID = ID;
    }

    public Food() {
    }

    public Food(String ID, String name, int weight, String type, String place, Date expirationDate) {
        this.ID = ID;
        this.name = name;
        this.type = type;
        this.place = place;
        this.expirationDate = expirationDate;
        this.weight = weight;
    }
    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    public void Display() {
        System.out.printf("|%-20s|%-20s|%-20d|%-20s|%-20s|%-20s\n",
                this.getID(), this.getName(), this.getWeight(),
                this.getType(), this.getPlace(), df.format(this.getExpirationDate()));
    }

    @Override
    public String toString() {
        return  ID + ", " + name + ", " + weight + ", " + type
                + ", " + place + ", " + df.format(expirationDate);
    }
}
