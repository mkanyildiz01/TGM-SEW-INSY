import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Random;

public class Person {
    ArrayList<String> arrayvn = new ArrayList<String>();
    ArrayList<String> arraynn = new ArrayList<String>();
    ArrayList<Integer> arraypnummer = new ArrayList<Integer>();
    String [] arr = {"M"};
    //String [] arr = {"W", "M", "N"};
    int select;
    public Person() throws FileNotFoundException, UnsupportedEncodingException {
        try {
        personnummer();
        vnamenname();
        PrintWriter writer0 = null;
        writer0 = new PrintWriter("Person.sql", "UTF-8");
        for(int i = 0; i <= 4000000; i++) {
                String s01 = arrayvn.get(i);
                String s02 = arraynn.get(i);
                int i01 = arraypnummer.get(i);
                geschlecht();

                GregorianCalendar gc = new GregorianCalendar();

            int year = randBetween(1985, 1995);

            gc.set(gc.YEAR, year);

            int dayOfYear = randBetween(1, gc.getActualMaximum(gc.DAY_OF_YEAR));

            gc.set(gc.DAY_OF_YEAR, dayOfYear);

                System.out.println();

                writer0.println("INSERT INTO Person VALUES( " + i01 + " , '" + s01 + "' , '" + s02 + "' , '" + arr[select] + "' , DATE '" + gc.get(gc.YEAR) + "-" + (gc.get(gc.MONTH) + 1) + "-" + gc.get(gc.DAY_OF_MONTH) + "' );");
                writer0.flush();
        }
        writer0.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    private void personnummer(){
        for (int i = 10000 ; i <= 8010001 ;i++) {
            if (i % 2 == 0) {
                arraypnummer.add(i);
            }
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

    private int randBetween(int start, int end) {
        return start + (int)Math.round(Math.random() * (end - start));
    }
}
