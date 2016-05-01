package Kanyildiz;

import org.postgresql.ds.PGSimpleDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class PostgreSQLConnection {

    public static String h_;
    public static Integer P_;
    public static String d_;
    public static String u_;
    public static String p_;
    public float TStart = Float.parseFloat(null);
    public float TEnde = Float.parseFloat(null);
    public float ITime = Float.parseFloat(null);


    public PostgreSQLConnection() throws SQLException {

        Statements statements = new Statements();
        PGSimpleDataSource PGSDS = new PGSimpleDataSource();

        TStart = (float) 0.0000;
        TEnde = (float) 0.0000;
        ITime = (float) 0.0000;

        PGSDS.setServerName(h_);
        PGSDS.setPortNumber(P_);
        PGSDS.setDatabaseName(d_);
        PGSDS.setUser(u_);
        PGSDS.setPassword(p_);
        Connection con = PGSDS.getConnection();
        Statement st = con.createStatement();
        st.executeQuery("SELECT * FROM Maschine");

        TStart = System.nanoTime();

        for(int i=700; i <= 100700; i++) {
            statements.insert(i, "MaschineneBezeichnung_"+i, con);
        }
        TEnde = System.nanoTime();
        System.out.println();
        ITime = TStart - TEnde;

    }

}
