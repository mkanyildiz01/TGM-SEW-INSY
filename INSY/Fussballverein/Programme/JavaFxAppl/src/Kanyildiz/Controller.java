package Kanyildiz;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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
    private CheckBox checkpersnr;
    @FXML
    private CheckBox checkpos;
    @FXML
    private CheckBox checkgehalt;
    @FXML
    private CheckBox checkvvon;
    @FXML
    private CheckBox checkvbis;
    @FXML
    private TextField tfullquery;
    @FXML
    private CheckBox checkwhere;
    @FXML
    private CheckBox checkorderby;
    @FXML
    private TextField twhere;
    @FXML
    private TextField torderby;

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

        b1 = checkpersnr.isSelected();
        String s1 = "";

        b2 = checkpos.isSelected();
        String s2 = "";

        b3 = checkgehalt.isSelected();
        String s3 = "";

        b4 = checkvvon.isSelected();
        String s4 = "";

        b5 = checkvbis.isSelected();
        String s5 = "";

        b6 = checkwhere.isSelected();
        String s6 = "";

        b7 = checkorderby.isSelected();
        String s7 = "";

        if (b1 == true) {
            s1 = "persnr";
        }
        if (b2 == true) {
            s2 = "position";
        }
        if (b3 == true) {
            s3 = "gehalt";
        }
        if (b4 == true) {
            s4 = "vertragvon";
        }
        if (b5 == true) {
            s5 = "vertragbis";
        }
        if (b6 == true) {
            s6 = twhere.getText();
        }
        if (b7 == true) {
            s7 = torderby.getText();
        }
        CRUDHandler crudh01 = new CRUDHandler();
        crudh01.select(s1, s2, s3, s4, s5,s6,s7, connection);
    }
}