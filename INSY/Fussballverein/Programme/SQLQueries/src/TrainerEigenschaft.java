import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;


public class TrainerEigenschaft {
    int[] arraypnummer;
    ArrayList<String> arraybezeichnung = new ArrayList<String>();
    ArrayList<String> arraychef = new ArrayList<String>();
    ArrayList<String> arrayas = new ArrayList<String>();
    public TrainerEigenschaft() throws FileNotFoundException, UnsupportedEncodingException {

        personnummer();
        bezeichnung();

        PrintWriter writer = null;
        writer = new PrintWriter("TrainerEigenschaft.sql", "UTF-8");
        writer.println("INSERT INTO TrainerEigenschaft(persnr,bezeichnung,istcheftrainer,istassistent) VALUES ");

        for (int i = 0; i < arraypnummer.length-1; i++) {
            int i01 = arraypnummer[i];
            String s01 = arraybezeichnung.get(i);
            String s02 = arraychef.get(i);
            String s03 = arrayas.get(i);
            writer.println("( " + i01 + " , '" + s01 + "' , '"+ s02 +"' , '" + s03 +"' ),");
            writer.flush();
        }
        writer.close();
    }

    private void personnummer(){

        arraypnummer = new int[3000000];
        int i1 = 2345452;
        for (int i = 0 ; i <= 254546 ;i++){
            arraypnummer[i] = i1;
            i1++;
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

        for (int i = 2; i <= arraypnummer.length-1; i++) {
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
