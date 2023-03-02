public class Disco {

    private int iddisco = 0;
    private int id = 0;
    private String name = null;
    private int capacity = 0;

    public Disco(int iddisco, int id, String name, int capacity) {
        this.iddisco = iddisco;
        this.id = id;
        this.name = name;
        this.capacity = capacity;
    }

    public Disco() {}

    public int getIddisco() {
        return iddisco;
    }

    public void setIddisco(int iddisco) {
        this.iddisco = iddisco;
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
                "iddisco=" + iddisco +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", capacity=" + capacity +
                '}';
    }

    public String[] toRow() {

        String[] ret = new String[4];

        ret[0] = this.iddisco + "";
        ret[1] = this.id + "";
        ret[2] = this.name;
        ret[3] = this.capacity + "";

        return ret;
    }
}
