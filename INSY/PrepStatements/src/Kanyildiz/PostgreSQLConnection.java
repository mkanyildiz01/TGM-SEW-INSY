package Kanyildiz;

import org.postgresql.ds.PGSimpleDataSource;

import java.sql.SQLException;

public class PostgreSQLConnection {

    public static String h_;
    public static Integer P_;
    public static String d_;
    public static String u_;
    public static String p_;

    public void connection() throws SQLException {

        PGSimpleDataSource ds = new PGSimpleDataSource();
        ds.setServerName(h_);
        ds.setPortNumber(P_);
        ds.setDatabaseName(d_);
        ds.setUser(u_);
        ds.setPassword(p_);

    }

}
