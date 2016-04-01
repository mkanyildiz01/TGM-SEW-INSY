package Kanyildiz;

import java.sql.*;

import org.postgresql.ds.PGSimpleDataSource;
import org.postgresql.ds.common.BaseDataSource;

public class CRUDHandler {
    private PreparedStatement prep;
    private ResultSet res;
    PGSimpleDataSource ds = new PGSimpleDataSource();

    public ResultSet select(String s1,String s2,String s3,String s4,String s5,String s6,String s7, Connection con) {
            Statement stmt = null;
            ResultSet rSet = null;
            Integer ic = 0;

            String per = s1;
            String pos = s2;
            String geh = s3;
            String von = s4;
            String bis = s5;
            String where = s6;
            String orderby = s7;

            Boolean b1= false;
            Boolean b2= false;
            Boolean b3= false;
            Boolean b4= false;
            Boolean b5= false;
            Boolean b6= false;
            Boolean b7= false;

            if ((pos != "" ) && (per != "" )){
                pos = "," + pos;
                b1 = true;
                ic++;
            }
            if ((geh != "") && ((per != "") || (pos != ""))){
                geh = "," + geh;
                b2 = true;
                ic++;
            }
            if ((von != "") && ((per != "") || (pos != "") || (geh != ""))){
                von = "," + von;
                b3 = true;
                ic++;
            }
            if ((bis != "") && ((geh != "") || (per != "") || (pos != "") || (von != ""))){
                bis = "," + bis;
                b4 = true;
                ic++;
            }
            if (per != "" ){
                b5 = true;
                ic++;
            }
        Connection connection = con;
        try {
            stmt = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            int persnr = 0;
            String position ="";
            Double gehalt = 0.0;
            Date von1 = null;
            Date bis1 = null;
            if ((s6 != "") || (s7 != "")){
                rSet = stmt.executeQuery("SELECT " + per + pos + geh + von + bis + " FROM spieler WHERE " + s6 + " ORDER BY " + s7);
            }else if (s6 != "") {
                rSet = stmt.executeQuery("SELECT " + per + pos + geh + von + bis + " FROM spieler WHERE " + s6);
            }else if (s7 != "") {
                rSet = stmt.executeQuery("SELECT " + per + pos + geh + von + bis + " FROM spieler ORDER BY " + s6);
            }else if ((s6 == "") || (s7 == "")){
                rSet = stmt.executeQuery("SELECT " + per + pos + geh + von + bis + " FROM spieler");
            }
            while(rSet.next()){
                if(b5 == true) {
                    persnr = rSet.getInt("persnr");
                }else if(b1 == true) {
                    position = rSet.getString("position");
                }else if(b2 == true) {
                    gehalt = rSet.getDouble("gehalt");
                }else if(b3 == true) {
                    von1 = rSet.getDate("vertragvon");
                }else if(b4 == true){
                    bis1 = rSet.getDate("vertragbis");
                }


                    // print the results
                    System.out.format(persnr + "," + position + "," + gehalt + "," + gehalt + "," + von1 + "," + bis1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


}