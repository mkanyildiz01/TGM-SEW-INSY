package Kanyildiz;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.*;

public class Controller {
    @FXML
    private TextField tipaddress;
    @FXML
    private TextField tport;
    @FXML
    private TextField tdbname;
    @FXML
    private TextField tusername;
    @FXML
    private TextField tuserpw;
    @FXML
    private TextArea toutput;
    @FXML
    private TableColumn tbpersn;
    @FXML
    private TableColumn tbposition;
    @FXML
    private TableColumn tbgehalt;

    public String h_;
    public String u_;
    public String p_;
    public String d_;

    private boolean b1;
    private boolean b2;
    private boolean b3;
    private boolean b4;
    private boolean b5;
    private boolean b6;
    private boolean b7;

    public Connection connection;

    private final ObservableList<Spieler> data =
            FXCollections.observableArrayList(
                    new Spieler("Jacob", "Smith", "jacob.smith@example.com")
            );

    public void OnClickConnect() {
        String ip = tipaddress.getText();
        String port = tport.getText();
        this.d_ = tdbname.getText();
        this.u_ = tusername.getText();
        this.p_ = tuserpw.getText();
        this.h_ = ip + ":" + port;
        DatabaseConnection();
    }

    public void DatabaseConnection() {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://" + h_ + "/" + d_, u_, p_);
            toutput.appendText("Die Connection war erfolgreich fahren Sie  mit den  Nächsten Tabs fort!");

        } catch (ClassNotFoundException e) {
            toutput.appendText(e.getMessage() + "\n");
        } catch (SQLException e) {
            toutput.appendText(e.getMessage() + "\n");
        }
    }

    public void OnClickExecuteQueryRead() {

        // b1 = checkpersnr.isSelected();

        CRUDHandler ch01 = new CRUDHandler();
        ch01.select(connection);
        TableView01();
    }
    public void TableView01(){
        String[] a1 = new String [1000];
        a1[0] = "Hallo";
        a1[1] = "Hallo1";
    }
}
