package Kanyildiz;

import java.sql.*;

public class Statements {

    public void insert(int nummer, String beschreibung, Connection con) throws SQLException {

        String sql = "INSERT INTO Maschine (nummer, beschreibung) VALUES (?, ?)";

        PreparedStatement statement = con.prepareStatement(sql);
        statement.setInt(1, nummer);
        statement.setString(2, beschreibung);

        statement.executeUpdate();
    }

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

    public void update(Integer nummer, String beschreibung, Connection con) throws SQLException {

            String sql = "UPDATE Maschine SET beschreibung=? WHERE nummer=?";

            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, beschreibung);
            statement.setInt(2, nummer);

            statement.executeUpdate();
    }

    public void delete(int nummer, Connection con) throws SQLException {

        String sql = "DELETE FROM Maschine WHERE nummer=?";

            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, nummer);

            statement.executeUpdate();

    }
}