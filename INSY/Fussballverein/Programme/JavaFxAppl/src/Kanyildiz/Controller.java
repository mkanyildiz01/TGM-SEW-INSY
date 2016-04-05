package Kanyildiz;
import java.sql.Connection;
import java.sql.ResultSet;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

import java.awt.event.ActionEvent;
import java.sql.*;

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
    private TextArea toutput;
    @FXML
    private TableView tableviewread;
    @FXML
    private CheckBox checkpersnr;
    @FXML
    private CheckBox checkposition;
    @FXML
    private CheckBox checkgehalt;
    @FXML
    private CheckBox checkvvon;
    @FXML
    private CheckBox checkbis;

    String spersnr = "";
    String spos = "";
    String sgehalt = "";
    String svvon = "";
    String svbis = "";


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
        select(connection);
    }

    public void select(Connection con) {
        Connection c = con;
        data = FXCollections.observableArrayList();

        try {

            //SQL FOR SELECTING ALL OF CUSTOMER
            String SQL = "SELECT * from Spieler";
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
            e.printStackTrace();
        }
    }
    private void CheckSelected(){
        if(checkpersnr.isSelected() == true) {
            spersnr = "persnr";
        }
        if(checkposition.isSelected() == true){
            spos = "position";
        }
        if(checkgehalt.isSelected() == true){
            sgehalt = "gehalt";
        }

    }
}