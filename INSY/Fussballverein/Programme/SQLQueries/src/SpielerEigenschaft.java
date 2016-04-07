import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class SpielerEigenschaft {

    ArrayList<Integer> arraypnummer = new ArrayList<Integer>();
    ArrayList<Integer> arraytnummer = new ArrayList<Integer>();
    ArrayList<String> arraybezeichnung = new ArrayList<String>();
    ArrayList<String> arraykapitean = new ArrayList<String>();
    public SpielerEigenschaft() throws FileNotFoundException, UnsupportedEncodingException {

        personnummer();
        bezeichnung();
        nummer();

        PrintWriter writer = null;
        writer = new PrintWriter("SpielerEigenschaft.sql", "UTF-8");

        for (int i = 0; i < 1400003; i++) {
            int i01 = arraypnummer.get(i);
            String s01 = arraybezeichnung.get(i);
            String s02 = arraykapitean.get(i);
            int i02 = arraytnummer.get(i);
            writer.println("INSERT INTO SpielerEigenschaft VALUES( " + i01 + " , '" + s01 + "' , '"+ s02 + "' , "+ i02 +" );");
            writer.flush();
        }
        writer.close();
    }

    private void personnummer(){
        for (int i = 5209994; i <= 8010000;i++){
            if (i % 2 == 0) {
                arraypnummer.add(i);
            }
        }
    }
    private void bezeichnung(){
        int i1 = 2;
        int i2 = 0;

        for (int i = 1 ; i <= 11 ; i++){
            if(i == 11){
                arraykapitean.add("Ja");
            }else {
                arraykapitean.add("Nein");
            }
            arraybezeichnung.add("Mannschaft 1");

        }

        for (int i = 11; i <= arraypnummer.size(); i++) {
            i2++;
            if (i2 == 11){
                arraybezeichnung.add("Mannschaft " + i1);
                i1++;
            }else{
                arraybezeichnung.add("Mannschaft " + i1);
            }

            if (i2 == 11) {
                i2 = 0;
                arraykapitean.add("Ja");
            }else{
                arraykapitean.add("Nein");
            }
        }
    }
    private void nummer() {
        int i1 = 0;
        int i2 = 0;
        for (int i = 1; i <= arraypnummer.size(); i++) {
            i2++;
            i1++;
            if(i2 == 11){
                arraytnummer.add(i1);
                i1 = 0;
                i2 = 0;
            }else{
                arraytnummer.add(i1);
            }
        }
    }
}
