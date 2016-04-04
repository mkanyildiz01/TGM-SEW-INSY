package Kanyildiz;

import java.sql.*;

import org.postgresql.ds.PGSimpleDataSource;

public class CRUDHandler {
    //private Connection con;
    private PreparedStatement prep;
    private ResultSet res;
    // create read update delete..
    PGSimpleDataSource ds = new PGSimpleDataSource();

    /**
     * In der insert() Methode werden 4 Parameter übergeben. Diese sind nummer, vorname , nachname und Connection
     * Die ersten 3 Parameter sind für die Query nötig und der Connection Parameter ist dafür notwendig um die CRUD Methode mit der Klasse SQL zu verbinden.
     * Die ersten 3 Parameter werden mit der in der Query vorkommenden Reihenfolge der Fragezeichen (?) in die sets()übergeben.
     */
    public void insert(int num, String vname, String nname,Connection con){
        try {
            String sql = "INSERT INTO Person (nummer, vorname, nachname) VALUES (?, ?, ?)";

            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, num);
            statement.setString(2, vname);
            statement.setString(3, nname);

            statement.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }

    }

    /*
     *Hier wird eine ganz simple SELECT Abfrage ausgeführt und wird mit einer while() Scheife ausgegeben.
     */
    public ResultSet select(Connection con) {
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM Spieler LIMIT 10");
            int count = 0;
            while (rs.next()) {
                Integer persnr = rs.getInt(1);
                String position = rs.getString(2);
                String gehalt = rs.getString(3);
                String vertragvon = rs.getString(4);
                String vertragbis = rs.getString(5);
                String output = "Spieler #%d: %s - %s - %s - %s - %s";
                System.out.println(String.format(output, ++count, persnr, position, gehalt, vertragvon, vertragbis));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    // Hier werdern die vor- und nachnamen der Personen and der gegebenen nummer geändert
    public void update(String vname, String nname, int num, Connection con){
        try {
            String sql = "UPDATE Person SET vorname=?, nachname=? WHERE nummer=?";

            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, vname);
            statement.setString(2, nname);
            statement.setInt(3, num);

            statement.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Hier werdern die vor- und nachnamen der Personen and der gegebenen nummer gelöscht
    public void delete(int num,Connection con){
        try {
            String sql = "DELETE FROM Person WHERE nummer=?";

            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, num);

            statement.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

}