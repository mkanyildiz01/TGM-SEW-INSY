/**
 * autor: Kanyildiz Muhamemdhizir
 * klasse: 4AHITM
 * Datum: 16.04.2016
 * FileName: Main.java
 * Package: Kanyildiz
 **/
package Kanyildiz;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    /**
     *
     * @param primaryStage Das "Hauptfenster"
     * @throws Exception Wenn Fehler auftrehten.
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Window01.fxml"));
        primaryStage.setTitle("Fussballverein-Projekt");
        primaryStage.setScene(new Scene(root, 635 , 605));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    /**
     *
     * @param args Ruft die oben genannte Methode auf.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
