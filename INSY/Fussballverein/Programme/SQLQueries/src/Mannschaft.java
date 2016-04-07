import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Random;

public class Mannschaft {

    ArrayList<String> arraybezeichnung = new ArrayList<String>();
    String [] arr = {"S-Klasse","A-Klasse", "B-Klasse", "C-Klasse","D-Klasse"};
    int select;


    public  Mannschaft() throws FileNotFoundException, UnsupportedEncodingException {

        bezeichnung();

        PrintWriter writer = null;
        writer = new PrintWriter("Mannschaft.sql", "UTF-8");

        for(int i = 0; i< arraybezeichnung.size(); i++) {

            String s01 = arraybezeichnung.get(i);
            Random01();

            GregorianCalendar gc = new GregorianCalendar();
            int year = randBetween(2010, 2020);
            gc.set(gc.YEAR, year);
            int dayOfYear = randBetween(1, gc.getActualMaximum(gc.DAY_OF_YEAR));
            gc.set(gc.DAY_OF_YEAR, dayOfYear);

            writer.println("INSERT INTO Mannschaft VALUES( '" + s01 +"', '"+ arr[select] + "' , DATE '" + gc.get(gc.YEAR) + "-" + (gc.get(gc.MONTH) + 1) + "-" + gc.get(gc.DAY_OF_MONTH) +"' );");
            writer.flush();

        }

        writer.close();

    }

    private int randBetween(int start, int end) {
        return start + (int)Math.round(Math.random() * (end - start));
    }

    private void bezeichnung(){
        for (int i = 1 ; i <= 127273 ;i++){
            arraybezeichnung.add("Mannschaft " + i);
        }
    }

    private void Random01(){
        Random random = new Random();

        // randomly selects an index from the arr
        select = random.nextInt(arr.length);
    }
}
