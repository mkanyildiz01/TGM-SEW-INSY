import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.GregorianCalendar;

public class Spieler {

    String[] arraygehalt;
    int[] arraypnummer;

    String [] arrayposition = { "Linker Stürmer","Zentraler Stürmer",
                                "Rechter Stürmer", "Linker Außenstürmer",
                                "Zentral Mittelfeld","Rechter Außsenstürmer",
                                "Linker Verteidiger", "Innen Verteidiger Links",
                                "Innen Verteidiger Rechts", "Rechter Verteidiger",
                                "Torhüter"};

    public Spieler() throws FileNotFoundException, UnsupportedEncodingException {

        PrintWriter writer = null;
        writer = new PrintWriter("Spieler.sql", "UTF-8");

        personnummer();
        position();
        gehalt();

        for (int i = 0; i < 1400003; i++) {
            int i01 = arraypnummer[i];
            String s01 = arraygehalt[i];

            GregorianCalendar gc = new GregorianCalendar();

            int year = randBetween(1975, 1995);

            gc.set(gc.YEAR, year);

            int dayOfYear = randBetween(1, gc.getActualMaximum(gc.DAY_OF_YEAR));

            gc.set(gc.DAY_OF_YEAR, dayOfYear);

            writer.print("INSERT INTO Spieler VALUES( " + i01 + " , " + "..." + " , " + s01 + ", DATE '" + gc.get(gc.YEAR) + "-" + (gc.get(gc.MONTH) + 1) + "-" + gc.get(gc.DAY_OF_MONTH) + "' , ");

            int year2 = randBetween(2010, 2020);

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

        arraypnummer = new int[4000000];
        int i1 = 2599998;
        for (int i = 0 ; i <= 1400003 ;i++){
            arraypnummer[i] = i1;
            i1++;
        }
    }
    private void position(){


    }

    private void gehalt(){


        int i2 = (15000 - 4500) + 1;
        int i3 = (99 - 0) + 1;
        arraygehalt = new String[4000000];
        for (int i = 0 ; i <= 1400003 ;i++){

            int i1 = (int)(Math.random() * i2) + 4500;
            int i5 = (int)(Math.random() * i3) + 0;
            arraygehalt[i] = i1+"."+i5;

        }

    }
}
