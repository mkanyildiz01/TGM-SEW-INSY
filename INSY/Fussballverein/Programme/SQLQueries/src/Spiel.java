import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Spiel{

    ArrayList<String> arraybezeichnung = new ArrayList<String>();
    ArrayList<String> arraybezeichnung02 = new ArrayList<String>();
    ArrayList<String> arraybezeichnung03 = new ArrayList<String>();

    String [] arr = {"Sieg", "Unentschieden", "Niederlage"};

    public Spiel() throws FileNotFoundException, UnsupportedEncodingException, ParseException {

        bezeichnung();
        gegner();
        ergebnis();
        int i1 = 1;

        Date date = new Date(2014,01,01);
        PrintWriter writer = null;
        String s04 = null;
        writer = new PrintWriter("Spiel.sql", "UTF-8");
        PrintWriter writer1 = null;
        writer1 = new PrintWriter("Datum.sql", "UTF-8");

        Date myDate = new Date(1220227200L * 1000L);

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        for(int i = 0; i< arraybezeichnung.size(); i++) {

            String s01 = arraybezeichnung.get(i);
            String s02 = arraybezeichnung02.get(i);
            String s03 = arraybezeichnung03.get(i);
            java.sql.Date sqlDate = new java.sql.Date(addDays(myDate, i).getTime());

            writer.println("INSERT INTO Spiel VALUES( DATE '" + sqlDate + "' , '" + s01 + "' , '" + s02 + "' , '" + s03 + "' );");
            writer1.println("(DATE '" + sqlDate + "');");

            writer.flush();
            writer1.flush();

        }

        writer.close();
        writer1.close();
    }

    private int randBetween(int start, int end) {
        return start + (int)Math.round(Math.random() * (end - start));
    }

    private void bezeichnung(){

        for (int i = 1 ; i <= 127273 ;i++){

            arraybezeichnung.add("Mannschaft " + i);

        }

    }
    private void gegner(){
        for (int i = 127273 ; i >= 1 ;i--){

            arraybezeichnung02.add("Mannschaft " + i);

        }
    }
    private void ergebnis(){
        int i1 = 0;
        for (int i = 0 ; i < 127273 ;i++){
            if(i1 == 3){
                i1 = 0;
            }
            arraybezeichnung03.add(arr[i1]);
            i1++;

        }
    }

    public static Date addDays(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days); //minus number would decrement the days
        return cal.getTime();
    }

}
