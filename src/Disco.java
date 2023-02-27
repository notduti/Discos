public class Disco {

    private int id = 0;
    private String name = null;
    private int capacity = 0;

    public Disco(int id, String name, int capacity) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
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

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return "Disco{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", capacity=" + capacity +
                '}';
    }

    public String[] toRow() {

        String[] ret = new String[3];

        ret[0] = this.id + "";
        ret[1] = this.name;
        ret[2] = this.capacity + "";

        return ret;
    }
}
