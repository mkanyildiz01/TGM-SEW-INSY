import java.io.*;
import java.util.ArrayList;

public class Spielt {

    int[] arraypnummer;
    int[] arraydauer;

    String[] arrs01 = new String[1000000];
    String[] arrs02 = new String[1400003];

    public Spielt() throws FileNotFoundException, UnsupportedEncodingException {

        datum01();
        personnummer();
        dauer();

        PrintWriter writer0 = null;

        writer0 = new PrintWriter("Spielt.sql", "UTF-8");
        for(int i = 0; i <= arrs02.length-1; i++) {
            String s01 = arrs02[i];
            Integer i01 = arraypnummer[i];
            Integer i02 = arraydauer[i];
            writer0.println("INSERT INTO Spielt VALUES( " + i01 + ", DATE '" + s01 + "' , " + i02 + " );");
            writer0.flush();
        }
        writer0.close();
    }

    private void datum01() {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(new File("Datum.sql")));
            String line = null;
            int i = 0;
            while ((line = br.readLine()) != null) {
                arrs01[i] = line;
                i++;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        int i1 = 0;
        int i2 = 0;
        for (int i = 0; i < 1400003; i++) {
            if (i2 == 11) {
                i1++;
                i2 = 0;
            }
            arrs02[i] = arrs01[i1];
            i2++;
        }
    }
    private void personnummer(){

        arraypnummer = new int[4000000];
        int i1 = 2599998;
        for (int i = 0 ; i <= 1400003 ;i++){
            arraypnummer[i] = i1;
            i1++;
        }
    }
    private void dauer(){


        int i2 = (90 - 70) + 1;
        arraydauer = new int[4000000];
        for (int i = 0 ; i <= 1400003 ;i++){

            int i1 = (int)(Math.random() * i2) + 70;
            arraydauer[i] = i1;

        }

    }
}




