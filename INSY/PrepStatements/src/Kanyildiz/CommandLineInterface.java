package Kanyildiz;

import org.apache.commons.cli.*;

import java.sql.*;

public class CommandLineInterface {

    public static void main(String[]args) throws ParseException, SQLException {

        CommandLineParser CLP  = new DefaultParser();

        Options options = new Options();
        options.addOption("h_",true,"IP-Adresse");
        options.addOption("P_",true,"Port");
        options.addOption("d_",true, "Datenbank");
        options.addOption("u_",true,"Benutzername");
        options.addOption("p_",true,"Passwort");
        options.addOption("help",true, "Help");

        CommandLine CL =  CLP.parse( options , args );

        PostgreSQLConnection.h_ = CL.getOptionValue("h_");
        PostgreSQLConnection.P_ = Integer.valueOf(CL.getOptionValue("p_"));
        PostgreSQLConnection.d_ = CL.getOptionValue("d_");
        PostgreSQLConnection.u_ = CL.getOptionValue("u_");
        PostgreSQLConnection.p_ = CL.getOptionValue("u_");
        PostgreSQLConnection PSQLC= new PostgreSQLConnection();

    }
}
