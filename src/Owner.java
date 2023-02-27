import java.sql.SQLException;
import java.util.ArrayList;

public class Owner {

    private int id = 0;
    private String name = null;
    private String surname = null;
    private String place = null;
    private String date = null;
    private ArrayList<Disco> discos = null;

    public Owner() {
    }

    public Owner(int id, String name, String surname, String place, String date, ArrayList<Disco> discos) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.place = place;
        this.date = date;
        this.discos = discos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ArrayList<Disco> getDiscos() {
        return discos;
    }

    public void setDiscos(ArrayList<Disco> discos) {
        this.discos = discos;
    }

    @Override
    public String toString() {
        return "Owner{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", place='" + place + '\'' +
                ", date='" + date + '\'' +
                ", discos=" + discos +
                '}';
    }

    public String[] toRow() {

        String[] ret = new String[5];
        ret[0] = this.getId() + "";
        ret[1] = this.getName();
        ret[2] = this.getSurname();
        ret[3] = this.getPlace();
        ret[4] = this.getDate();

        return ret;
    }

}