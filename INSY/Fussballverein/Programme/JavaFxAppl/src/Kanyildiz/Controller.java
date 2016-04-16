/**
 * autor: Kanyildiz Muhamemdhizir
 * klasse: 4AHITM
 * Datum: 16.04.2016
 * FileName: Controller.java
 * Package: Kanyildiz
 **/
package Kanyildiz;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.util.Callback;

import java.sql.*;

public class Controller{
    /**
     * @FXML Nimmet sozusagen die Elemente vom .fxml file und deswegen ist es auch wichtig die Elemente im .fxml file
     *       richtig zu schreiben.
     */
    @FXML private TextField tipaddress;
    @FXML private TextField tport;
    @FXML private TextField tdbname;
    @FXML private TextField tusername;
    @FXML private TextField tuserpw;
    @FXML private TextField tzusatz01;
    @FXML private TextArea toutput;
    @FXML private TableView tableviewread;
    @FXML private CheckBox checkpersnr;
    @FXML private CheckBox checkpos;
    @FXML private CheckBox checkgehalt;
    @FXML private CheckBox checkvvon;
    @FXML private CheckBox checkvbis;
    @FXML private CheckBox checkzusatz;
    @FXML private TextField tqueryread;
    @FXML private Label loutputread;
    @FXML private TextField tpersnr;
    @FXML private ChoiceBox cbposition;
    @FXML private TextField tgehalt;
    @FXML private DatePicker dpvertragvon;
    @FXML private DatePicker dpvertragbis;

    // Select sachen
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

    // Für die Füllung des TableViews
    private ObservableList<ObservableList> data;

    // Connection Infos
    public String h_;
    public String u_;
    public String p_;
    public String d_;

    // Die Connection selbst
    public Connection connection;


    /**
     *  Die OnClickConnect() Methode ist der Button im ersten Tab.
     *  Es nimmet alle Informationen der Textfelder im ersten Tab und ruft die
     *  Mehtode DatabaseConnection() auf.
     *  (Es spiechert jede Information in einem Textfeld ab.)
     **/
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

    /**
     *  Die DatabaseConnection() Mehtode ruft den postgresql Driver auf und verbindet
     *  sich somit mit der Datenbank, wenn die Infomation richtig eingegebn wurde.
     *  Execptions werden Aufgefangen
     */
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

    /**
     *  Die Methode OnClickExecuteQueryRead() wird aufgerufen wenn der Button Execute
     *  Query im 2. Tab gedrückt wird. Es nimmt die ganze information die der
     *  Benutzer eingeben kann und ruft mit diesen Infos die methode CheckSelected und
     *  select(connection) auf.
     **/
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

            //Select Anweißung für den Spieler
            String SQL = "SELECT " + spersnr + spos + sgehalt + svvon+ svbis +" FROM Spieler" + " " + szusatz;
            ResultSet rs = c.createStatement().executeQuery(SQL);
            for(int i=0 ; i<rs.getMetaData().getColumnCount(); i++){
                //non property style für eine dynamische Table
                final int j = i;
                //@col nimmt die spalten namen und fült die table view damit.
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i+1));
                col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList,String>,ObservableValue<String>>(){
                    public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
                        return new SimpleStringProperty(param.getValue().get(j).toString());
                    }
                });
                tableviewread.getColumns().addAll(col);
            }
            // Füllen der Datensätze
            while(rs.next()){
                //Iterate Spalten
                ObservableList<String> row = FXCollections.observableArrayList();
                for(int i=1 ; i<=rs.getMetaData().getColumnCount(); i++){
                    //Iterate Zeilen
                    row.add(rs.getString(i));
                }
                data.add(row);
            }

            //Zur TableView hinzufügen
            tableviewread.setItems(data);

        } catch (SQLException e) {
            loutputread.setText(e.getMessage());
        }
    }

    /**
     * Überprüft ob im 2.Tab etwas selectiert wurde und somit fühlt es die Strings falls es selected wurde.
     */
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

    /**
     * Die Methode CheckUserInputRed() überprüft ob der User etwas anderes machen will
     * @return
     */
    private boolean CheckUserInputRead(){
        String temp = szusatz.toLowerCase();
        boolean bdrop = temp.contains("drop");
        boolean b2 = temp.contains("update");
        boolean b3 = temp.contains("insert");
        boolean b4 = temp.contains("create");
        boolean b5 = false;
        if((bdrop != false) || (b2 != false) || (b3 != false) || (b4 != false)){
            b5 = true;
            loutputread.setText("Bitte nur Select Anweisungen eingeben.");
        }
        return b5;
    }

    /**
     *  Die Methode CheckButtonInsert() nimmt alle im 3. Tab eingegebenen Infos und gibt die der Methode it.insert(...) über.
     *  Wirdausgelöst wenn der Button gedrückt wird.
     **/
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