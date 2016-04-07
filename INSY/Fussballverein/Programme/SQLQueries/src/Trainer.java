import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class Trainer {

    ArrayList<Integer> arraypnummer = new ArrayList<Integer>();
    String[] arraygehalt;

    public Trainer() throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter writer = null;
        writer = new PrintWriter("Trainer.sql", "UTF-8");
        personnummer();
        gehalt();
        for(int i = 0; i < 254546; i++) {
            int i01 = arraypnummer.get(i);
            String s01 = arraygehalt[i];

            GregorianCalendar gc = new GregorianCalendar();

            int year = randBetween(2009, 2011);

            gc.set(gc.YEAR, year);

            int dayOfYear = randBetween(1, gc.getActualMaximum(gc.DAY_OF_YEAR));

            gc.set(gc.DAY_OF_YEAR, dayOfYear);


            writer.print("INSERT INTO Trainer VALUES( " + i01 +" , " + s01 +", DATE '" + gc.get(gc.YEAR) + "-" + (gc.get(gc.MONTH) + 1) + "-" + gc.get(gc.DAY_OF_MONTH) + "' ,");

            int year2 = randBetween(2014, 2016);

            gc.set(gc.YEAR, year2);

            int dayOfYear2 = randBetween(1, gc.getActualMaximum(gc.DAY_OF_YEAR));

            gc.set(gc.DAY_OF_YEAR, dayOfYear2);

            writer.println("DATE '" + gc.get(gc.YEAR) + "-" + (gc.get(gc.MONTH) + 1) + "-" + gc.get(gc.DAY_OF_MONTH) + "' );");

            writer.flush();

        }

        writer.close();

    }



    private void personnummer(){
        for (int i = 4700901 ; i < 5209993 ;i++){
            if (i % 2 == 0) {
                arraypnummer.add(i);
            }
        }
    }
    private void gehalt(){


        int i2 = (3000 - 1200) + 1;
        int i3 = (99 - 0) + 1;
        arraygehalt = new String[1000001];
        for (int i = 0 ; i <= 1000000 ;i++){

            int i1 = (int)(Math.random() * i2) + 1200;
            int i5 = (int)(Math.random() * i3) + 0;
            arraygehalt[i] = i1+"."+i5;

        }

    }
    private int randBetween(int start, int end) {
        return start + (int)Math.round(Math.random() * (end - start));
    }

}
