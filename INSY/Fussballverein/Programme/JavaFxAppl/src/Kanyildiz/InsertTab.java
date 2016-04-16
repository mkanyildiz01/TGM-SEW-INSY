/**
 * autor: Kanyildiz Muhamemdhizir
 * klasse: 4AHITM
 * Datum: 16.04.2016
 * FileName: InsertTab.java
 * Package: Kanyildiz
 **/
package Kanyildiz;
import org.postgresql.ds.PGSimpleDataSource;

import java.sql.*;

public class InsertTab {
    private PreparedStatement prep;
    private ResultSet res;
    PGSimpleDataSource ds = new PGSimpleDataSource();

    /**
     *
     * @param num Spieler Nummer
     * @param pos Spieler Position
     * @param gehalt Spieler Gehalt
     * @param vertragvon Spieler Vertrag von
     * @param vertragbis Spieler Vertrag bis
     * @param con Die Datenbank-Connection
     *
     *
     * In der insert() Methode werden 6 Parameter übergeben.
     * Die ersten 5 Parameter werden mit der in der Query vorkommenden Reihenfolge der Fragezeichen (?) in die sets()übergeben.
     * Der 6 Parameter ist für die Connection notwendig.
     **/
    public void insert(int num, String pos, Double gehalt,Date vertragvon, Date vertragbis,Connection con){
        try {
            String sql = "INSERT INTO Spieler (persnr, position, gehalt,vertragvon,vertragbis) VALUES (?, ?, ?, ?, ?)";

            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, num);
            statement.setString(2, pos);
            statement.setDouble(3, gehalt);
            statement.setDate(4, vertragvon);
            statement.setDate(5, vertragbis);

            statement.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
