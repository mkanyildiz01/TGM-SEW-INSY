import java.io.*;
import java.util.ArrayList;

/**
 * Created by Muhi_2 on 3/23/2016.
 */
public class Person {
    ArrayList<String> arrayvn = new ArrayList<String>();
    ArrayList<String> arraynn = new ArrayList<String>();
    ArrayList<Integer> arraypnummer = new ArrayList<Integer>();

    public Person(){
        vnamenname();
        personnummer();
    }

    private void personnummer(){
        for (int i = 1; i<10001;i++){
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
}
