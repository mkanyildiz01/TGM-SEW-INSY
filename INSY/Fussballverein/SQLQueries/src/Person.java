import java.io.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Random;

/**
 * Created by Muhi_2 on 3/23/2016.
 */
public class Person {
    ArrayList<String> arrayvn = new ArrayList<String>();
    ArrayList<String> arraynn = new ArrayList<String>();
    ArrayList<Integer> arraypnummer = new ArrayList<Integer>();
    String [] arr = {"W", "M", "N"};
    int select;
    public Person() throws FileNotFoundException, UnsupportedEncodingException {

        personnummer();
        vnamenname();
        PrintWriter writer = null;
        writer = new PrintWriter("Person.sql", "UTF-8");
        for(int i = 0;i<arraypnummer.size();i++) {
            String s01 = arrayvn.get(i);
            String s02 = arraynn.get(i);
            int i01 = arraypnummer.get(i);
            geschlecht();

            GregorianCalendar gc = new GregorianCalendar();

            int year = randBetween(1975, 1995);

            gc.set(gc.YEAR, year);

            int dayOfYear = randBetween(1, gc.getActualMaximum(gc.DAY_OF_YEAR));

            gc.set(gc.DAY_OF_YEAR, dayOfYear);

            System.out.println();

            writer.println("INSERT INTO Person VALUES( "+ i01 + " , '"+s01+ "' , '"+s02+"' , '" +arr[select]+"' , DATE '"+gc.get(gc.YEAR) + "-" + (gc.get(gc.MONTH) + 1) + "-" + gc.get(gc.DAY_OF_MONTH)+"' );");

        }
        writer.close();

    }

    private void personnummer(){
        for (int i = 1 ; i < 10001 ;i++){
            arraypnummer.add(i);
        }
    }
    private void vnamenname(){
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(new File("resources/VName-NName.txt")));
            String line = null;
            while((line = br.readLine()) != null) {
                // Ganze Zeile:
                // System.out.println(line);
                String[] parts = line.split(",");
                arraynn.add(parts[0]);
                arrayvn.add(parts[1]);
            }

        } catch(FileNotFoundException e) {
            e.printStackTrace();
        } catch(IOException e) {
            e.printStackTrace();
        } finally {
            if(br != null) {
                try {
                    br.close();
                } catch(IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    private void geschlecht(){
        Random random = new Random();

        // randomly selects an index from the arr
        select = random.nextInt(arr.length);
    }
    private void datum(){
       // DATE '2006-03-05'

    }

    private int randBetween(int start, int end) {
        return start + (int)Math.round(Math.random() * (end - start));
    }
}
