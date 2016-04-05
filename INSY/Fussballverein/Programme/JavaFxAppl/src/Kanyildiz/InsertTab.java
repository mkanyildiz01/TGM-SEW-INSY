package Kanyildiz;
import java.sql.*;

import org.postgresql.ds.PGSimpleDataSource;


public class InsertTab {
    private PreparedStatement prep;
    private ResultSet res;
    PGSimpleDataSource ds = new PGSimpleDataSource();

    /**
     * In der insert() Methode werden 4 Parameter übergeben. Diese sind nummer, vorname , nachname und Connection
     * Die ersten 3 Parameter sind für die Query nötig und der Connection Parameter ist dafür notwendig um die CRUD Methode mit der Klasse SQL zu verbinden.
     * Die ersten 3 Parameter werden mit der in der Query vorkommenden Reihenfolge der Fragezeichen (?) in die sets()übergeben.
     */
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
