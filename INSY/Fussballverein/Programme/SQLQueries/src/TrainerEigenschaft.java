import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;


public class TrainerEigenschaft {
    ArrayList<Integer> arraypnummer = new ArrayList<Integer>();
    ArrayList<String> arraybezeichnung = new ArrayList<String>();
    ArrayList<String> arraychef = new ArrayList<String>();
    ArrayList<String> arrayas = new ArrayList<String>();
    public TrainerEigenschaft() throws FileNotFoundException, UnsupportedEncodingException {

        personnummer();
        bezeichnung();

        PrintWriter writer = null;
        writer = new PrintWriter("TrainerEigenschaft.sql", "UTF-8");

        for (int i = 0; i < 1000000; i++) {
            int i01 = arraypnummer.get(i);
            String s01 = arraybezeichnung.get(i);
            String s02 = arraychef.get(i);
            String s03 = arrayas.get(i);
            writer.println("INSERT INTO TrainerEigenschaft VALUES( " + i01 + " , '" + s01 + "' , '"+ s02 +"' , '" + s03 +"' );");
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
    private void bezeichnung(){
        int i1 = 2;
        int i2 = 0;

        for (int i = 1 ; i <= 2 ; i++){
            if(i == 2){
                arraychef.add("Nein");
                arrayas.add("Ja");

            }else {
                arraychef.add("Ja");
                arrayas.add("Nein");
            }
            arraybezeichnung.add("Mannschaft 1");
        }

        for (int i = 2; i <= 1000000; i++) {
            i2++;
            if (i2 == 2){
                arraybezeichnung.add("Mannschaft " + i1);
                i1++;
            }else{
                arraybezeichnung.add("Mannschaft " + i1);
            }

            if (i2 == 2) {
                i2 = 0;
                arraychef.add("Ja");
                arrayas.add("Nein");
            }else{
                arraychef.add("Nein");
                arrayas.add("Ja");
            }
        }
    }
}
