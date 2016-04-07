import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class Standort {

    ArrayList<Integer> arraypnummer = new ArrayList<Integer>();
    ArrayList<String> arrayland = new ArrayList<String>();
    ArrayList<String> arrayort = new ArrayList<String>();
    ArrayList<String> arrayplz = new ArrayList<String>();

    public Standort() throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter writer = null;
        writer = new PrintWriter("Standort.sql", "UTF-8");

        sid();
        land();
        plz();
        ort();

        for(int i = 0; i <= 269091; i++) {
            int i01 = arraypnummer.get(i);
            String s01 = arrayland.get(i);
            String s02 = arrayort.get(i);
            String s03 = arrayplz.get(i);
            writer.println("INSERT INTO Standort VALUES ( " + i01 +  ", '" + s01 + "' , '" + s02+ "' , '"+ s03 + "' );");

            writer.flush();

        }

        writer.close();

    }



    private void sid(){
        for (int i = 1 ; i <= 269091 ;i++){
            arraypnummer.add(i);
        }
    }
    private void land(){
        for (int i = 1 ; i <= 269091 ;i++){
            arrayland.add("Land " + i);
        }
    }
    private void ort(){
        for (int i = 1 ; i <= 269091 ;i++){
            arrayort.add("Ort " + i);
        }
    }
    private void plz(){
        for (int i = 1 ; i <= 269091 ;i++){
            arrayplz.add("Plz " + i);
        }
    }

}
