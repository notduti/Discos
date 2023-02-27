import java.sql.*;
import java.util.ArrayList;

public class DiscoDAO extends DAO {

    public static void insert(Disco disco) throws SQLException, ClassNotFoundException {

        conn = connect();
        //disco.toString();
        String sql = "INSERT INTO discos VALUES("
                + disco.getId() + ",'"
                + disco.getName() + "',"
                + disco.getCapacity() + ");";
        Statement stmt = conn.createStatement();
        stmt.executeUpdate(sql);
    }

    public static Disco read (int id) throws SQLException, ClassNotFoundException {

        conn = connect();
        String sql = "SELECT * FROM discos WHERE id = " + id + ";";
        Statement stmt = conn.createStatement();
        ResultSet result = stmt.executeQuery(sql);
        if(result.next())
            return null;

        return new Disco(result.getInt("id"),
                                result.getString("name"),
                                result.getInt("capacity"));
    }

    public static ArrayList<Disco> readAll() throws SQLException, ClassNotFoundException {

        conn = connect();
        ArrayList<Disco> discos = new ArrayList<>();
        String sql = "SELECT * FROM discos;";
        Statement stmt = conn.createStatement();
        ResultSet result = stmt.executeQuery(sql);

        while(result.next()){
            discos.add(new Disco(result.getInt("id"),
                    result.getString("name"),
                    result.getInt("capacity")));
        }

        return discos;
    }

    public static ArrayList<Disco> readAllCf(int id) throws SQLException, ClassNotFoundException {

        conn = connect();
        String sql = "SELECT * FROM discos WHERE id = " + id + ";";
        Statement stmt = conn.createStatement();
        ResultSet result = stmt.executeQuery(sql);
        ArrayList<Disco> discos = new ArrayList<>();

        while(result.next()) {
            discos.add(new Disco(result.getInt("id"),
                    result.getString("name"),
                    result.getInt("capacity")));
        }
        return discos;
    }

    public static void update(Disco disco) throws SQLException, ClassNotFoundException {

        conn = connect();
        String sql = "UPDATE discos SET nome='" + disco.getName() +
                "', capacity = "+disco.getCapacity() +
                " WHERE id = " + disco.getId() + ";";
        Statement stmt = conn.createStatement();
        stmt.executeUpdate(sql);
    }

    public static void delete(int id) throws SQLException, ClassNotFoundException {

        conn = connect();
        String sql = "DELETE FROM discos WHERE id=" + id + ";";
        Statement stmt = conn.createStatement();
        stmt.executeUpdate(sql);
    }
}
