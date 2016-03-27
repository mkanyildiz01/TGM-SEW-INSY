import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class Mitglied {

    ArrayList<Integer> arraypnummer = new ArrayList<Integer>();
    ArrayList<String> arraybeitrag = new ArrayList<String>();

    public Mitglied() throws FileNotFoundException, UnsupportedEncodingException {
        personnummer();
        beitrag();
        PrintWriter writer = null;
        writer = new PrintWriter("Mitglied.sql", "UTF-8");
        for(int i = 0; i<arraypnummer.size(); i++) {
            String s01 = arraybeitrag.get(i);
            int i01 = arraypnummer.get(i);


            writer.println("INSERT INTO Mitglied VALUES( " + i01 + " , '" + s01 + "' );");
            writer.flush();
        }
        writer.close();
    }

    private void personnummer(){
        for (int i = 0 ; i <= 1345451 ;i++){
            arraypnummer.add(i);
            System.out.println(arraypnummer.get(i));
        }
    }
    private void beitrag(){
        for (int i = 0 ; i <= 1345451 ;i++){
            arraybeitrag.add("Beitrag"+i+": bla bla");
        }
    }

}
