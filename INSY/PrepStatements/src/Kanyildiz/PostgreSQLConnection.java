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
    public float STime = Float.parseFloat(null);
    public float UTime = Float.parseFloat(null);
    public float DTime = Float.parseFloat(null);

    PGSimpleDataSource PGSDS = null;
    Statements statements = null;
    Connection con = null;

    public PostgreSQLConnection() throws SQLException {

        statements = new Statements();
        PGSDS = new PGSimpleDataSource();

        TStart = (float) 0.0000;
        TEnde = (float) 0.0000;
        ITime = (float) 0.0000;

        PGSDS.setServerName(h_);
        PGSDS.setPortNumber(P_);
        PGSDS.setDatabaseName(d_);
        PGSDS.setUser(u_);
        PGSDS.setPassword(p_);

        InsertBefehl();
        SelectBefehl();
        UpdateBefehl();
        DeleatDefehl();
        TimeOut();

    }
    public void InsertBefehl() throws SQLException {
        Connection con = PGSDS.getConnection();
        Statement st = con.createStatement();
        st.executeQuery("SELECT * FROM Maschine");

        TStart = System.nanoTime();

        for(int i=700; i <= 100700; i++) {
            statements.insert(i, "MaschineneBezeichnung_"+i, con);
        }

        TEnde = System.nanoTime();
        ITime = TStart - TEnde;

    }

    public void SelectBefehl() throws SQLException {
        TStart = System.nanoTime();
        statements.select(con);
        TEnde = System.nanoTime();
        STime = TEnde - TStart;
    }

    public void UpdateBefehl() throws SQLException {
        TStart = System.nanoTime();
        for(int i=700; i<100700; i++){
            statements.update(i,"MaschineneBezeichnung_"+i,con);
        }
        TEnde = System.nanoTime();
        UTime = TEnde - TStart;
    }

    public void DeleatDefehl() throws SQLException {
        TStart = System.nanoTime();
        for(int i=700; i<100700; i++){
            statements.delete(i,con);
        }
        TEnde = System.nanoTime();
        DTime = TEnde - TStart;
    }

    public void TimeOut(){
        System.out.println("INSERT: "+ ITime+" sek" + "SELECT: "+ STime+" sek" + "UPDATE: "+ UTime +" sek" + "DELEAT: "+ DTime +" sek");
    }
}
