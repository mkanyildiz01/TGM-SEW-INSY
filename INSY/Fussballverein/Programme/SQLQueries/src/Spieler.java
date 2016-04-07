import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class Spieler {

    String[] arraygehalt;
    ArrayList<Integer> arraypnummer = new ArrayList<Integer>();
    ArrayList<String> arraypos = new ArrayList<String>();

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
            int i01 = arraypnummer.get(i);
            String s01 = arraygehalt[i];
            String s02 = arraypos.get(i);
            GregorianCalendar gc = new GregorianCalendar();

            int year = randBetween(2010, 2012);
            gc.set(gc.YEAR, year);
            int dayOfYear = randBetween(1, gc.getActualMaximum(gc.DAY_OF_YEAR));
            gc.set(gc.DAY_OF_YEAR, dayOfYear);

            writer.print("INSERT INTO Spieler VALUES( " + i01 + " , '" + s02 + "' , " + s01 + ", DATE '" + gc.get(gc.YEAR) + "-" + (gc.get(gc.MONTH) + 1) + "-" + gc.get(gc.DAY_OF_MONTH) + "' , ");

            int year2 = randBetween(2013, 2016);
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
        for (int i = 5209994; i <= 8010000;i++){
            if (i % 2 == 0) {
                arraypnummer.add(i);
            }
        }
    }
    private void position(){
        int i1=0;
        for (int i = 0 ; i <= 1400003 ;i++){
            i1++;
            if(i1 == 1){
                arraypos.add("Torhüter");
            }else if(i1 == 2){
                arraypos.add("Rechter Verteidiger");
            }else if(i1 == 3){
                arraypos.add("Innen Verteidiger Rechts");
            }else if(i1 == 4){
                arraypos.add("Innen Verteidiger Links");
            }else if(i1 == 5){
                arraypos.add("Linker Verteidiger");
            }else if(i1 == 6){
                arraypos.add("Rechter Außsenstürmer");
            }else if(i1 == 7){
                arraypos.add("Zentral Mittelfeld");
            }else if(i1 == 8){
                arraypos.add("Linker Außenstürmer");
            }else if(i1 == 9){
                arraypos.add("Rechter Stürmer");
            }else if(i1 == 10){
                arraypos.add("Zentraler Stürmer");
            }else if(i1 == 11){
                arraypos.add("Linker Stürmer");
                i1 = 0;
            }
        }

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
