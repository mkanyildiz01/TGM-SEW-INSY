/**
 * Erstellen der Options und zuweisung der Values
 *
 * @author Kanyildiz Muhammedhizir
 *
 * @class CommandLineInterface.java
 *
 * @datum 04.05.2016
 *
 * @klasse 4AHITM
 *
 */
package Kanyildiz;

import org.apache.commons.cli.*;

import java.sql.*;

public class CommandLineInterface {

    /**
     *
     * @param args Konsolenparameter
     * @throws ParseException
     * @throws SQLException
     *
     */
    public static void main(String[]args) throws ParseException, SQLException {

        CommandLineParser CLP  = new DefaultParser();

        // Option erstellung, damit die CLI eingabe "freigegeben" wird
        // Hier befinden sich die nötigen parameter
        Options options = new Options();
        options.addOption("h_",true,"IP-Adresse");
        // options.addOption("h_","IP-Adresse",true,"IP-Adressen-Feld");
        options.addOption("P_",true,"Port");
        // options.addOption("P_","Port",true,"Port-Feld");
        options.addOption("d_",true, "Datenbank");
        // options.addOption("d_", "Datenbank", true, "DatenbankName");
        options.addOption("u_",true,"Benutzername");
        // options.addOption("u_",,"Benutzername",true ,"Benutzereingabe" );
        options.addOption("p_",true,"Passwort");
        // options.addOption("p_","Passwort",true,"User Passwort");
        // options.addOption("help",true, "Help");
        // options.addOption("help", "Help",true , "Help option anzeigen");


        CommandLine CL =  CLP.parse( options , args );
        // Hier werden die Parameter weiter an die Connection Klasse weitergeleitet
        PostgreSQLConnection.h_ = CL.getOptionValue("h_");
        PostgreSQLConnection.P_ = Integer.valueOf(CL.getOptionValue("p_"));
        PostgreSQLConnection.d_ = CL.getOptionValue("d_");
        PostgreSQLConnection.u_ = CL.getOptionValue("u_");
        PostgreSQLConnection.p_ = CL.getOptionValue("u_");
        PostgreSQLConnection PSQLC= new PostgreSQLConnection();
        PSQLC.PostgreSQLConnection1();
    }
}
