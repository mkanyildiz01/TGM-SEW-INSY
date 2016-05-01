package Kanyildiz;

import org.apache.commons.cli.*;

import java.sql.*;

public class CommandLineInterface {

    public static void main(String[]args) throws ParseException {

        CommandLineParser CLP  = new DefaultParser();

        Options options = new Options();
        options.addOption("h_",true,"IP-Adresse");
        options.addOption("P_",true,"Port");
        options.addOption("d_",true, "Datenbank");
        options.addOption("u_",true,"Benutzername");
        options.addOption("p_",true,"Passwort");
        options.addOption("help",true, "Help");


        CommandLine CL =  CLP.parse( options , args );

        SQL.h_ = CL.getOptionValue("h_");
        SQL.P_ = CL.getOptionValue("p_");
        SQL.d_ = CL.getOptionValue("d_");
        SQL.u_ = CL.getOptionValue("u_");
        SQL.p_ = CL.getOptionValue("u_");
        SQL.help = CL.getOptionValue("help");
        SQL connection = new SQL();
        connnection.connect();
    }
}
