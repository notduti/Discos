import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class OwnerDAO extends DAO {

    public static int insert(Owner owner) throws SQLException, ClassNotFoundException {

        conn = connect();
        String sql = "INSERT INTO owners VALUES(NULL, '" +
                owner.getName() + "', '" +
                owner.getSurname() + "', '" +
                owner.getPlace() + "', '" +
                owner.getDate() + "');";
        Statement stmt = conn.createStatement();
        stmt.executeUpdate(sql);

        String sql2 = "SELECT last_insert_rowid() as last FROM owners;";
        Statement stmt2 = conn.createStatement();
        ResultSet rs = stmt2.executeQuery(sql2);
        rs.next();
        return rs.getInt("last");
    }

    public static Owner read(int id) throws SQLException, ClassNotFoundException {

        conn = connect();
        String sql = "SELECT * FROM owners WHERE id = " + id + ";";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        rs.next();

        ArrayList<Disco> discos = DiscoDAO.readAllCf(id);
        return new Owner(rs.getInt("id"), rs.getString("name"),
                rs.getString("surname"), rs.getString("place"),
                rs.getString("date"), discos);
    }

    public static ArrayList<Owner> readAll() throws SQLException, ClassNotFoundException {

        conn = connect();
        String sql = "SELECT * FROM owners;";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        ArrayList<Owner> list = new ArrayList<>();

        while(rs.next()) {

            list.add(new Owner(rs.getInt("id"), rs.getString("name"),
                    rs.getString("surname"), rs.getString("place"),
                    rs.getString("date"), DiscoDAO.readAllCf(rs.getInt("id"))));
        }

        return list;
    }

    public static void update(Owner owner) throws SQLException, ClassNotFoundException {

        conn = connect();
        String sql = "UPDATE owners SET name = '" + owner.getName() +
                "', surname = '" + owner.getSurname() + "', place = '" +
                owner.getPlace() + "', date = '" + owner.getDate() +
                "' WHERE id = " + owner.getId() + ";";
        Statement stmt = conn.createStatement();
        stmt.executeUpdate(sql);
    }
    public static void delete(int id) throws SQLException, ClassNotFoundException {

        conn = connect();
        String sql = "DELETE FROM owners WHERE id = " + id + ";";
        Statement stmt = conn.createStatement();
        stmt.executeUpdate(sql);
    }
}
