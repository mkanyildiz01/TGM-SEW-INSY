import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;

public class validate {
    public static void main (String[] args) {

        String xmlfilename;
        File f1 = null;
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setValidating(false);
        factory.setNamespaceAware(true);

        DocumentBuilder builder = null;
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        builder.setErrorHandler(new SimpleErrorHandler());
        try {
            try{
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                System.out.println("------XML Datei INFO:------");
                System.out.print("PATH:");
                xmlfilename = br.readLine();
                f1 = new File(xmlfilename);
            }catch(IOException e){
                System.out.println("Die Eingabe konnte nicht durchgefuehrt werden. Fehler: " + e.getMessage());
            }
            Document document = builder.parse(new InputSource(String.valueOf(f1)));
            System.out.println("------OUTPUT:------");
            System.out.println("Die Wohlgeformtheit des Files ist Valide.");
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e){
            System.out.println("Das File wurde nicht gefunden!");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}