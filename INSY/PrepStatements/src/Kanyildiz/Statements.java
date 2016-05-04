/**
 * Definiert die CRUD Befehle und den Inhalt der Befehle.
 *
 * @author Kanyildiz Muhammedhizir
 *
 * @class Statements.java
 *
 * @datum 04.05.2016
 *
 * @klasse 4AHITM
 *
 */

package Kanyildiz;

import java.sql.*;

public class Statements {

    /**
     * Fuegt Daten in Tabelle als Values ein Folgende Syntax:
     * (nummer, bezeichnung)
     *
     * @param nummer Ist ein Integer-Wert und PK von der Tabelle
     *
     * @param beschreibung Eine Produktbeschreibung als String
     *
     * @param con Die Connection für die Datenbank
     *
     */
    public void insert(int nummer, String beschreibung, Connection con) throws SQLException {

        String sql = "INSERT INTO Maschine (nummer, beschreibung) VALUES (?, ?)";

        PreparedStatement statement = con.prepareStatement(sql);
        statement.setInt(1, nummer);
        statement.setString(2, beschreibung);

        statement.executeUpdate();
    }
    /**
     * Liest aus der Datenbank die Zeile mit der gewuenschten Datensätzen an.
     *
     * @param con Die Connection für die Datenbank, damit alles ausgegeben werden kann.
     */
    public ResultSet select(Connection con) throws SQLException {

        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM Maschine");
        int count = 0;
        while (rs.next()) {
            String nummer = rs.getString(1);
            String beschreibung = rs.getString(2);

            String output = "Maschine #%d: %s - %s";
            System.out.println(String.format(output, ++count, nummer, beschreibung));
            }
        return null;
    }
    /**
     * Veraendert bzw. Updated Daten in der Datenbank
     *
     * @param nummer Ist ein Integer-Wert und PK von der Tabelle
     *
     * @param beschreibung Eine Produktbeschreibung als String
     *
     * @param con Die Connection für die Datenbank
     *
     */
    public void update(Integer nummer, String beschreibung, Connection con) throws SQLException {

            String sql = "UPDATE Maschine SET beschreibung=? WHERE nummer=?";

            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, beschreibung);
            statement.setInt(2, nummer);

            statement.executeUpdate();
    }

    /**
     * Loescht Daten aus der Datenbank
     *
     * @param nummer Ist ein Integer-Wert und PK von der Tabelle
     *
     * @param con  Die Connection für die Datenbank
     *
     */
    public void delete(int nummer, Connection con) throws SQLException {

        String sql = "DELETE FROM Maschine WHERE nummer=?";

            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, nummer);

            statement.executeUpdate();

    }
}