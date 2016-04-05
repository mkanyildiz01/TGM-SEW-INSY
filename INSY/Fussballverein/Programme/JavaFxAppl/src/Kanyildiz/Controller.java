package Kanyildiz;
import java.sql.Connection;
import java.sql.ResultSet;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.util.Callback;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.*;
import java.text.SimpleDateFormat;

public class Controller{
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
    private TextField tzusatz01;
    @FXML
    private TextArea toutput;
    @FXML
    private TableView tableviewread;
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
    private CheckBox checkzusatz;
    @FXML
    private TextField tqueryread;
    @FXML
    private Label loutputread;
    @FXML
    private TextField tpersnr;
    @FXML
    private ChoiceBox cbposition;
    @FXML
    private TextField tgehalt;
    @FXML
    private DatePicker dpvertragvon;
    @FXML
    private DatePicker dpvertragbis;

    String spersnr = "";
    String spos = "";
    String sgehalt = "";
    String svvon = "";
    String svbis = "";
    String szusatz = "";

    Boolean b1 = false;
    Boolean b2 = false;
    Boolean b3 = false;
    Boolean b4 = false;
    Boolean b5 = false;
    Boolean b6 = false;

    private ObservableList<ObservableList> data;

    public String h_;
    public String u_;
    public String p_;
    public String d_;

    public Connection connection;


    public void OnClickConnect() {
        String ip = tipaddress.getText();
        String port = tport.getText();
        this.d_ = tdbname.getText();
        this.u_ = tusername.getText();
        this.p_ = tuserpw.getText();
        this.h_ = ip + ":" + port;
        DatabaseConnection();
        cbposition.setItems(FXCollections.observableArrayList("Linker Stürmer","Zentraler Stürmer",
                "Rechter Stürmer", "Linker Außenstürmer",
                "Zentral Mittelfeld","Rechter Außsenstürmer",
                "Linker Verteidiger", "Innen Verteidiger Links",
                "Innen Verteidiger Rechts", "Rechter Verteidiger",
                "Torhüter"));
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

        tableviewread.getItems().clear();
        tableviewread.getColumns().clear();
        spersnr = "";
        spos = "";
        sgehalt = "";
        svvon = "";
        svbis = "";
        szusatz = "";
        b1 = false;
        b2 = false;
        b3 = false;
        b4 = false;
        b5 = false;
        b6 = false;
        CheckSelected();
        if (CheckUserInputRead()== true){
            return;
        }
        select(connection);
    }

    public void select(Connection con) {
        Connection c = con;
        data = FXCollections.observableArrayList();
        tqueryread.setText("SELECT " + spersnr + spos + sgehalt + svvon+ svbis +" FROM Spieler" + " " + szusatz);
        try {

            //SQL FOR SELECTING ALL OF CUSTOMER
            String SQL = "SELECT " + spersnr + spos + sgehalt + svvon+ svbis +" FROM Spieler" + " " + szusatz;
            ResultSet rs = c.createStatement().executeQuery(SQL);
            for(int i=0 ; i<rs.getMetaData().getColumnCount(); i++){
                //We are using non property style for making dynamic table
                final int j = i;
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i+1));
                col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList,String>,ObservableValue<String>>(){
                    public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
                        return new SimpleStringProperty(param.getValue().get(j).toString());
                    }
                });
                tableviewread.getColumns().addAll(col);
            }

            while(rs.next()){
                //Iterate Row
                ObservableList<String> row = FXCollections.observableArrayList();
                for(int i=1 ; i<=rs.getMetaData().getColumnCount(); i++){
                    //Iterate Column
                    row.add(rs.getString(i));
                }
                data.add(row);
            }

            //FINALLY ADDED TO TableView
            tableviewread.setItems(data);

        } catch (SQLException e) {
            loutputread.setText(e.getMessage());
        }
    }
    private void CheckSelected(){

        if(checkpersnr.isSelected() == true) {
            spersnr = "persnr";
            b1 = true;
        }
        if(checkpos.isSelected() == true){
            spos = "position";
            b2 = true;
        }

        if(checkgehalt.isSelected() == true){
            sgehalt = "gehalt";
            b3 = true;
        }

        if(checkvvon.isSelected() == true){
            svvon = "vertragvon";
            b4 = true;
        }

        if(checkvbis.isSelected() == true){
            svbis = "vertragbis";
            b5 = true;
        }

        if(checkzusatz.isSelected() == true) {
            szusatz = tzusatz01.getText();
            b6 = true;
        }

        if((b2 == true) && (b1 == true)){
            spos = ",position";
        }else if((b2 == true) && (b1 != true)){
            spos = "position";
        }
        if((b3 == true) && ((b1 == true) ||(b2 == true))){
            sgehalt = ",gehalt";
        }else if((b3 == true) && (b1 != true) && (b2 != true)){
            sgehalt = "gehalt";
        }
        if((b4 == true) && ((b1 == true) || (b2 == true)  || (b3 == true))){
            svvon = ",vertragvon";
        }else if((b4 == true) && (b1 != true) && (b2 != true)  && (b3 != true)){
            svvon = "vertragvon";
        }
        if((b5 == true) && ((b1 == true) || (b2 == true)  || (b3 == true) || (b4 == true))){
            svbis = ",vertragbis";
        }else if((b5 == true) && (b1 != true) && (b2 != true)  && (b3 != true) && (b4 != true)){
            svbis = "vertragbis";
        }
    }
    private boolean CheckUserInputRead(){
        boolean bdrop = szusatz.contains("Drop");
        boolean b2 = szusatz.contains("Update");
        boolean b3 = szusatz.contains("Insert");
        boolean b4 = szusatz.contains("Create");
        boolean b5 = false;
        if((bdrop != false) || (b2 != false) || (b3 != false) || (b4 != false)){
            b5 = true;
            loutputread.setText("Bitte nur Select Anweisungen eingeben.");
        }
        return b5;
    }
    public void CheckButtonInsert(){
        String temp01 = tpersnr.getText();
        Integer PersnrEingabe = Integer.parseInt(temp01);
        String PositionEingabe = (String) cbposition.getValue();
        Double Gehalt = Double.parseDouble(tgehalt.getText());
        Date VertragvonEinfabe = java.sql.Date.valueOf(dpvertragvon.getValue());
        Date VertragbisEinfabe = java.sql.Date.valueOf(dpvertragbis.getValue());
        InsertTab it = new InsertTab();
        it.insert(PersnrEingabe,PositionEingabe,Gehalt,VertragvonEinfabe,VertragbisEinfabe,connection);
    }
}