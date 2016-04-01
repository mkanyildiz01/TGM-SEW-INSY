import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class FanClub {

    ArrayList<Integer> arraypnummer = new ArrayList<Integer>();
    ArrayList<String> arrayname = new ArrayList<String>();
    ArrayList<String> arrayobman = new ArrayList<String>();
    ArrayList<Integer> arraysid = new ArrayList<Integer>();
    public FanClub() throws FileNotFoundException, UnsupportedEncodingException {
        personnummer();
        name();
        PrintWriter writer = null;
        writer = new PrintWriter("FanClub.sql", "UTF-8");
        writer.println("INSERT INTO FanClub(persnr,name,sid,gegruendet,istobman) VALUES ");

        for(int i = 0; i<arraypnummer.size(); i++) {
            int i01 = arraypnummer.get(i);
            String s01 = arrayname.get(i);
            int i02 = arraysid.get(i);
            String s02 = arrayobman.get(i);
            GregorianCalendar gc = new GregorianCalendar();

            int year = randBetween(1975, 1995);

            gc.set(gc.YEAR, year);

            int dayOfYear = randBetween(1, gc.getActualMaximum(gc.DAY_OF_YEAR));

            gc.set(gc.DAY_OF_YEAR, dayOfYear);

            writer.println("( " + i01 +
                                                        " , '" +
                                                        s01 +
                                                        "' , " +
                                                        i02 +
                                                        " , DATE '" + gc.get(gc.YEAR) + "-" + (gc.get(gc.MONTH) + 1) + "-" + gc.get(gc.DAY_OF_MONTH) +
                                                        "' , '" +
                                                        s02 +
                                                        "' ),");
            writer.flush();
        }
        writer.close();


    }

    private void personnummer(){
        for (int i = 1 ; i <= 1345451 ;i++){
            arraypnummer.add(i);
        }
    }
    private void name(){

        int i1 = 2;
        int i2 = 0;

        for (int i = 1 ; i <= 5 ; i++){
            if(i == 5){
                arrayobman.add("Ja");
            }else {
                arrayobman.add("Nein");
            }
            arrayname.add("FanClub 1");
            arraysid.add(1);
        }

        for (int i = 5; i <= arraypnummer.size(); i++) {
            i2++;
            if (i2 == 5) {
                i1++;
                i2 = 0;
                arrayobman.add("Ja");
            }else{
                arrayobman.add("Nein");
            }
            arrayname.add("FanClub " + i1);
            arraysid.add(i1);



        }
    }
    private int randBetween(int start, int end) {
        return start + (int)Math.round(Math.random() * (end - start));
    }
}