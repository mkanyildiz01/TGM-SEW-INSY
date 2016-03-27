import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class Angestellter {

    int[] arraypnummer;
    int[] arrayueberstunden;
    String[] arraygehalt;
    String[] arrayemail;

    public Angestellter() throws FileNotFoundException, UnsupportedEncodingException {

        PrintWriter writer = null;
        writer = new PrintWriter("Angestellter.sql", "UTF-8");

        personnummer();
        gehalt();
        ueberstunden();
        email();

        for(int i = 0; i<arraypnummer.length-1; i++) {
            int i01 = arraypnummer[i];
            int i02 = arrayueberstunden[i];
            String s01 = arrayemail[i];
            String s02 = arraygehalt[i];
            writer.println("INSERT INTO Angestellter VALUES( " + i01 + " , " + s02 + " , " + i02 + " , '" + s01 +"' );");
            writer.flush();

        }

        writer.close();

    }

    private void personnummer(){

        arraypnummer = new int[1000001];
        int i1 = 1345452;
        for (int i = 0 ; i <= 1000000 ;i++){
            arraypnummer[i] = i1;
            i1++;
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
    private void ueberstunden(){
        arrayueberstunden = new int[1000001];
        int i2 = (20 - 0) + 1;

        for (int i = 0 ; i <= 1000000 ;i++){
            int i1 = (int)(Math.random() * i2) + 20;
            arrayueberstunden[i] = i1;
        }
    }
    private void email(){
        arrayemail = new String[1000001];
        int i1 = 1345451;
        for (int i = 0 ; i <= 1000000 ;i++){
            arrayemail[i] = i1+"blabla@live.at";
            i1++;
        }
    }
}
