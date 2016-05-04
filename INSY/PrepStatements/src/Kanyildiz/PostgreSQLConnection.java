/**
 * Erstellung der Connection und Aufnehmen der Zeit(100 000 Datensätze)
 *
 * @author Kanyildiz Muhammedhizir
 *
 * @class PostgreSQLConnection.java
 *
 * @datum 04.05.2016
 *
 * @klasse 4AHITM
 *
 */

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

    /**
     *
     * @throws SQLException
     */
    public void PostgreSQLConnection1() throws SQLException {

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

    /**
     * Aufrufen des Insert-Statements und Inserten von 100 000 Datensätzen(Die Zeit wird aufgenommen.)
     * @throws SQLException
     */
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

    /**
     * Aufrufen des Select-Statements und Selecten von 100 000 Datensätzen(Die Zeit wird aufgenommen.)
     *
     * @throws SQLException
     */
    public void SelectBefehl() throws SQLException {
        TStart = System.nanoTime();
        statements.select(con);
        TEnde = System.nanoTime();
        STime = TEnde - TStart;
    }

    /**
     * Aufrufen des Update-Statements und Updaten von 100 000 Datensätzen(Die Zeit wird aufgenommen.)
     *
     * @throws SQLException
     */
    public void UpdateBefehl() throws SQLException {
        TStart = System.nanoTime();
        for(int i=700; i<100700; i++){
            statements.update(i,"MaschineneBezeichnung_"+i,con);
        }
        TEnde = System.nanoTime();
        UTime = TEnde - TStart;
    }

    /**
     * Aufrufen des Delete-Statements und Deleten von 100 000 Datensätzen(Die Zeit wird aufgenommen.)
     *
     * @throws SQLException
     */
    public void DeleatDefehl() throws SQLException {
        TStart = System.nanoTime();
        for(int i=700; i<100700; i++){
            statements.delete(i,con);
        }
        TEnde = System.nanoTime();
        DTime = TEnde - TStart;
    }

    /**
     * Die Zeitaufzeichnungen werden zusammengeführt und der Konsole ausgegeben.
     */
    public void TimeOut(){
        System.out.println("INSERT: "+ ITime+" sek" + "SELECT: "+ STime+" sek" + "UPDATE: "+ UTime +" sek" + "DELEAT: "+ DTime +" sek");
    }
}
