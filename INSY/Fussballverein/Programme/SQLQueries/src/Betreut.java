import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class Betreut {

    ArrayList<Integer> arraypnummer = new ArrayList<Integer>();
    ArrayList<String> arrayname = new ArrayList<String>();

    public Betreut() throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter writer = null;
        writer = new PrintWriter("Betreut.sql", "UTF-8");

        personnummer();
        name();

        for(int i = 0; i<1000000; i++) {
            int i01 = arraypnummer.get(i);
            String s01 = arrayname.get(i);

            GregorianCalendar gc = new GregorianCalendar();

            int year = randBetween(1975, 2015);

            gc.set(gc.YEAR, year);

            int dayOfYear = randBetween(1, gc.getActualMaximum(gc.DAY_OF_YEAR));

            gc.set(gc.DAY_OF_YEAR, dayOfYear);


            writer.print("INSERT INTO Betreut VALUES( " + i01 +" , '" + s01 +"' , DATE '" + gc.get(gc.YEAR) + "-" + (gc.get(gc.MONTH) + 1) + "-" + gc.get(gc.DAY_OF_MONTH) + "' ,");

            int year2 = randBetween(1975, 2020);

            gc.set(gc.YEAR, year2);

            int dayOfYear2 = randBetween(1, gc.getActualMaximum(gc.DAY_OF_YEAR));

            gc.set(gc.DAY_OF_YEAR, dayOfYear2);

            writer.println("DATE '" + gc.get(gc.YEAR) + "-" + (gc.get(gc.MONTH) + 1) + "-" + gc.get(gc.DAY_OF_MONTH) + "' );");

            writer.flush();

        }

        writer.close();

    }
    private int randBetween(int start, int end) {
        return start + (int)Math.round(Math.random() * (end - start));
    }


    private void personnummer(){
        for (int i = 2700901 ; i <= 4700901;i++){
            if (i % 2 == 0) {
                arraypnummer.add(i);
            }
        }
    }
    private void name() {
        int i1 = 0;
        int i2 = 0;

        for (int i = 1; i <= 1000000; i++) {
            if(i1 == 269091){
                i1=0;
            }
            i2++;
            if (i2 == 1) {
                i1++;
                i2 = 0;
            }
            arrayname.add("FanClub " + i1);
        }
    }


}
