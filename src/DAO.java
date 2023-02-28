import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAO {

    protected static Connection conn = null;
    private static final String FILENAME = "Z:\\INFORMATICA\\Java\\sqlite\\sqlite-tools-win32-x86-3400000\\db_discos.db";

    protected static Connection connect() throws SQLException, ClassNotFoundException {

        if(conn == null) {

            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:" + FILENAME;
            conn = DriverManager.getConnection(url);
        }
        return conn;
    }
}
