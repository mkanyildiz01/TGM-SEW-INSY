import java.util.ArrayList;

public class Spiel{
    ArrayList<String> arraybezeichnung = new ArrayList<String>();

    public Spiel() {

        bezeichnung();

    }

    private void bezeichnung(){

        for (int i = 1 ; i <= 127273 ;i++){

            arraybezeichnung.add("Mannschaft " + i);

        }

    }

}
