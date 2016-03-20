import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DomPrinter {
    public static void printNode(Node node, String prefix) {
        if (node == null) {
            return;
        }
        System.out.print(prefix + node.getNodeName());
        if (node.getNodeValue() != null) {
            System.out.println(":" + node.getNodeValue());
        } else {
            System.out.println();
        }
        NamedNodeMap cl = node.getAttributes();
        if (cl != null) {
            for (int i = 0; i < cl.getLength(); i++) {
                Node attr = cl.item(i);
                System.out.println(prefix + "\t" + attr.getNodeName() + "->"
                        + attr.getNodeValue());
            }
        }
        NodeList nl = node.getChildNodes();
        if (nl != null) {
            for (int i = 0; i < nl.getLength(); i++) {
                Node child = nl.item(i);
                printNode(child, prefix + "\t");
            }
        }
    }
    public static void main(String [] args) {
        Document doc = null;
        /*
            (DocumentBilderFactory defines a factory API for a Xml document.)
            It defines a factory API that enables applications to obtain
            a parser that produces DOM object trees from XML documents.
        */
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            /*
                 Creates a new instance of a DocumentBuilder using the currently
                configured parameters.
            */
            DocumentBuilder builder = factory.newDocumentBuilder();
            /*
                A "new" file is been initialized.
            */
            doc = builder.parse(new File("resources\\customerOrders.xml"));
            /*
                Nodes are everything in XML:
                Elements,Attributes,Values,Free-Spaces,Commentaries...
            */
            //--------------------------------------------------------------------------------------------------------\\
            //--------------------------------------------------------------------------------------------------------\\
            //Exercise Number 1 Wie viele Kunden und wie viele Bestellungen sind gespeichert?
            System.out.println("+---------------------------------+");
            System.out.println("| Number 1:                       |");
            System.out.println("+---------------------------------+");
            System.out.println("| Eingabe:(1): Order + Customer   |");
            System.out.println("| Eingabe:(2): Order              |");
            System.out.println("| Eingabe:(3): Customer           |");
            System.out.println("+---------------------------------+");
            /*
                @kunden: A Node List with all Customers-Elements
                @buchungen: A Node List with all Orders-Elements
            */
            NodeList kunden = doc.getElementsByTagName("Customer");
            NodeList buchungen = doc.getElementsByTagName("Order");
            //Buffer to save the input
            BufferedReader br00 = new BufferedReader(new InputStreamReader(System.in));
            //User input
            int inputint00 = Integer.parseInt(br00.readLine());
            // Comparing the input.
            if(inputint00 == 1){
                System.out.println("| Es wurden "+buchungen.getLength()+" Buchungen getätigt!|");
                System.out.println("| Es existieren "+kunden.getLength()+" Kunden.         |");
            }else if(inputint00 == 2){
                System.out.println("| Es wurden "+buchungen.getLength()+" Buchungen getätigt!|");
            }else if(inputint00 == 3){
                System.out.println("| Es existieren "+kunden.getLength()+" Kunden.         |");
            }else{
                System.out.println("| Bitte geben Sie eine Zahl zw. 1-3 ein. |");
                System.exit(0);
            }
            //--------------------------------------------------------------------------------------------------------\\
            //--------------------------------------------------------------------------------------------------------\\
            //Exercise Number 2 Welche CustomerID besitzt der vierte Kunde?
            System.out.println("+---------------------------------+");
            System.out.println("| Number 2:CustomerID Eingabe(0-3)|");
            System.out.println("+---------------------------------+");

            //Buffer to save the input
            BufferedReader br01 = new BufferedReader(new InputStreamReader(System.in));
            //String input01 = br01.readLine();
            //User input
            int inputint01 = Integer.parseInt(br01.readLine());
            //Comparing the Input
            if((inputint01 < 0) || (inputint01 > 3)){
                System.out.println("Die Eingabe muss zwischen 0-3 sein!");
            }
            System.out.println("|---------------------------------|");
            /*
                @node4: node4 is taking the 4th node of the customers.
                @kunde: Is all of the 4th Element(All Nodes Included).
                @name: Is taking the CustomerID as node and "converting" to a String.

            */
            Node node4 = kunden.item(inputint01);
            Element kunde = (Element)node4;
            String name = kunde.getAttribute("CustomerID");
            System.out.println("|Die ID vom "+ inputint01 +" Kunden lautet: "+name +"|");
            //--------------------------------------------------------------------------------------------------------\\
            //--------------------------------------------------------------------------------------------------------\\
            //Exercise Number 3 Wie lautet die vollständige Adresse von der Firma Lazy K Kountry Store?
            System.out.println("+---------------------------------+");
            System.out.println("| Number 3:Eingabe(0-3)           |");
            System.out.println("+---------------------------------+");
            System.out.println("| (0) : Great Lakes Food Market   |");
            System.out.println("| (1) : Hungry Coyote Import Store|");
            System.out.println("| (2) : Lazy K Kountry Store      |");
            System.out.println("| (3) : 12 Orchestra Terrace      |");
            System.out.println("+---------------------------------+");
            /*
                @firmanr3: Is taking the Child Element of the 2. Element(Customers-Element)
                @adresse: Is going through all Elements, witch are in FullAddress
                @temp1: Is getting 1 Node by 1 and saving as a String value.
                        So I can Print it...(A Node cant be printed in the console with
                        System.out.println().)

            */
            //Buffer to save the input
            BufferedReader br02 = new BufferedReader(new InputStreamReader(System.in));
            //String input01 = br01.readLine();
            //User input
            int inputint02 = Integer.parseInt(br02.readLine());
            //Comparing the Input
            if((inputint02 < 0) || (inputint02 > 3)){
                System.out.println("Die Eingabe muss zwischen 0-3 sein!");
            }
            NodeList firmanr3 = doc.getElementsByTagName("FullAddress").item(inputint02).getChildNodes();
            for (int i = 0, size = firmanr3.getLength(); i < size; i++) {
                Node adresse = firmanr3.item(i);
                String tmp1 = adresse.getTextContent();
                System.out.print(tmp1);
            }

            //--------------------------------------------------------------------------------------------------------\\
            //--------------------------------------------------------------------------------------------------------\\
            //Exercise Number 4 Gibt es Kunden, welche dieselbe dreistellige Vorwahl verwenden?
            System.out.println();
            System.out.println("+---------------------------------+");
            System.out.println("| Number 4:                       |");
            System.out.println("+---------------------------------+");
            /*
                @vorwahl: All Node Elements with the name Phone
                @tele: Boolean
                @temp1: Is getting the the full i PhoneNumber
                @temp2: Is getting the the full i+1 PhoneNumber
            */
            NodeList vorwahl = doc.getElementsByTagName("Phone");
            boolean tele;
            int itele = 0;
            for (int i = 0, nodsize = vorwahl.getLength(); i < nodsize; i++) {
                Node temp1 = vorwahl.item(i);
                Node temp2 = vorwahl.item(i++);
                // Filtering the first 5 Numbers of the Phone
                temp1.toString().substring(0, 4);
                temp2.toString().substring(0, 4);
                // Comparing the both Variables
                if (temp1.equals(temp2)) {
                    tele = true;
                    itele ++;
                }
            }
            // If the Phone number is equal
            if (tele = true) {
                System.out.println("|Es gibt " + itele + " Kunden mit der gleichen|");
                System.out.println("|Vorwahl                          |");
                System.out.println("+---------------------------------+");
            }

            //--------------------------------------------------------------------------------------------------------\\
            //--------------------------------------------------------------------------------------------------------\\
            //Exercise Number 5 Gibt es Kunden, die nicht aus den USA sind?
            System.out.println("| Number 5: Eingabe(Länder)z.b.USA|");
            System.out.println("+---------------------------------+");
            /*
                @land: Takes all node elements with the name country.
                @iland: Integer that counts all customers.
                @ilanduse: Integer that counts all customers, witch are from the U.S.
                @address: Is a node list with all the addresses(1 by 1).
            */
            //Buffer to save the input
            BufferedReader br03 = new BufferedReader(new InputStreamReader(System.in));
            //User input
            String input01 = br01.readLine();

            NodeList land = doc.getElementsByTagName("Country");
            int inputland = 0;
            for (int i = 0, nodsize = land.getLength(); i < nodsize; i++) {
                Node address = land.item(i);
                if (address.getTextContent().equals(input01)) {
                    inputland ++;
                }
            }
            System.out.println("| Es sind " + inputland + " Mitarbeiter aus: " + input01 +"   |");

            //--------------------------------------------------------------------------------------------------------\\
            //--------------------------------------------------------------------------------------------------------\\
            //Exercise Number 6 Welche(r) Kunde(n ) hatte(n ) die meisten Bestellungen?
            System.out.println("+---------------------------------+");
            System.out.println("| Number 6: Eingabe(1-2)          |");
            System.out.println("+---------------------------------+");
            System.out.println("| Eingabe:(1): Die Wenigsten Beste|");
            System.out.println("| Eingabe:(2): Die Meisten Bestell|");
            System.out.println("+---------------------------------+");
            /*
                @bestellungen: Takes all node elements with the name Order.
                @greal01,hungc01,lazyk01,letss01: Integers, witch are used to count up.
                @order: Getting the child elements from Order.
                @name1: Getting the customer name.
                @top01: TreeMap, to write the data down and print it out.
            */

            NodeList bestellungen = doc.getElementsByTagName("Order");
            int greal01 = 0; int hungc01 = 0; int lazyk01 = 0; int letss01 = 0;
            for (int i = 0, nodsize = bestellungen.getLength(); i < nodsize; i++) {
                Node order = bestellungen.item(i).getChildNodes().item(1);
                String name1 = order.getTextContent();
                // Comparing the names and counting up the variables.
                if (name1.equals("GREAL")) {
                    greal01++;
                } else if (name1.equals("HUNGC")) {
                    hungc01++;
                } else if (name1.equals("LAZYK")) {
                    lazyk01++;
                } else if (name1.equals("LETSS")) {
                    letss01++;
                }
            }
            // Writing down the data and printing them out.
            TreeMap<Integer,String> top01 = new TreeMap<Integer,String>();
            top01.put(greal01,"GREAL");
            top01.put(hungc01,"HUNGC");
            top01.put(lazyk01,"LAZYK");
            top01.put(letss01,"LETSS");
            //Buffer to save the input
            BufferedReader br04 = new BufferedReader(new InputStreamReader(System.in));
            //String input01 = br01.readLine();
            //User input
            int inputint04 = Integer.parseInt(br04.readLine());
            //Comparing the User Input
            if(inputint04 == 1) {
                Object firstKey = top01.keySet().toArray()[0];
                Object valueForFirstKey = top01.get(firstKey);
                System.out.println("| Die Wenigsten: " + firstKey + " = " + valueForFirstKey);
            }else if(inputint04 == 2){
                Object firstKey = top01.keySet().toArray()[top01.size()-1];
                Object valueForFirstKey = top01.get(firstKey);
                System.out.println("| Die Meisten: " + firstKey + " = " + valueForFirstKey);
            }else{
                System.out.println("Die Eingabe muss zwischen 1-2 sein!");
            }

            //--------------------------------------------------------------------------------------------------------\\
            //--------------------------------------------------------------------------------------------------------\\
            //Exercise Number 7 Wann war die letzte Bestellung von LAZYK?
            System.out.println("+---------------------------------+");
            System.out.println("| Number 7:Eingabe(1-4)           |");
            System.out.println("+---------------------------------+");
            System.out.println("| (1) : GREAL                     |");
            System.out.println("| (2) : HUNGC                     |");
            System.out.println("| (3) : LAZYK                     |");
            System.out.println("| (4) : LETSS                     |");
            System.out.println("+---------------------------------+");

            //Buffer to save the input
            BufferedReader br05 = new BufferedReader(new InputStreamReader(System.in));
            //String input01 = br01.readLine();
            //User input
            int inputint05 = Integer.parseInt(br05.readLine());
            //@sinput05 the correct customer witch define's the user
            String sinput05 = null;
            //Comparing the User Input
            // Comparing the input.
            if(inputint05 == 1){
                sinput05 = "GREAL";
            }else if(inputint05 == 2){
                sinput05 = "HUNGC";
            }else if(inputint05 == 3){
                sinput05 = "LAZYK";
            }else if(inputint05 == 4){
                sinput05 = "LETSS";
            }else{
                System.out.println("| Bitte geben Sie eine Zahl zw. 1-4 ein. |");
                System.exit(0);
            }

            /*
                @KundenID: Takes all node elements with the name customerID.
                @BestellDatum: Takes all node elements with the name OrderDate.
                @i1: Interger
                @DatumD: The final date(The latest Date)
                @Temp1: Only the CustomerID value.
                @Name1: String value of Temp1
                @Temp02: The current Orders, Date
                @Datum01: The hole Date of the Order into a String
                @Datum02: The yy-mm-dd of the Date into a String
                @stemp01: The Time Stamp of the Order
                @DateTime01: @Datum02 and @stamp01 combined
                @temp01: A DateFormat to parse in a new Date.
                         (Its needed to compare different dates.)
                @Temp03: @Temp02(its taking the next Date)
                @Datum03: @Datum01
                @Datum04: @Datum02
                @stemp02: @stemp01
                @DateTime02: @DateTime01
                @temp02: @temp01

            */


            NodeList KundenID = doc.getElementsByTagName("CustomerID");
            NodeList BestellDatum = doc.getElementsByTagName("OrderDate");
            Integer i1 = 0;
            Date DatumD = null;
            for (int i = 0, size = bestellungen.getLength(); i < size; i++) {
                Node Temp1 = KundenID.item(i);
                String Name1 = Temp1.getTextContent();

                //Comparing the Strings
                if (Name1.equals(sinput05)) {
                    Node Temp02 = BestellDatum.item(i);
                    String Datum01 = Temp02.getTextContent();
                    String Datum02 = Datum01.substring(0,10);
                    String stemp01 = Datum01.substring(12,19);
                    String DateTime01 = Datum02 + " " + stemp01;
                    Date temp01 = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss").parse(DateTime01);

                    //If its the first loop it takes the next element, because
                    //it needs something to compare.
                    if(i1 == 0){
                        Node Temp03 = BestellDatum.item(i+1);
                        String Datum03 = Temp03.getTextContent();
                        String Datum04 = Datum03.substring(0,10);
                        String stemp02 = Datum03.substring(12,19);
                        String DateTime02 = Datum04 + " " + stemp02;
                        Date temp02 = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss").parse(DateTime02);

                        // Comparing both Dates and setting them to DatumD
                        if(temp01.after(temp02)){
                            DatumD = temp01;
                        }else{
                            DatumD = temp02;
                        }

                    }else{
                        if(temp01.after(DatumD)){
                            DatumD = temp01;
                        }
                    }
                    i1++;

                }

            }
            System.out.println("| "+ sinput05 + ": " + DatumD + "|");

            //--------------------------------------------------------------------------------------------------------\\
            //--------------------------------------------------------------------------------------------------------\\
            //Exercise Number 8 Von wie vielen verschiedenen Mitarbeitern wurde GREAL bedient?
            System.out.println("+---------------------------------+");
            System.out.println("| Number 8:Eingabe(1-4)           |");
            System.out.println("+---------------------------------+");
            System.out.println("| (1) : GREAL                     |");
            System.out.println("| (2) : HUNGC                     |");
            System.out.println("| (3) : LAZYK                     |");
            System.out.println("| (4) : LETSS                     |");
            System.out.println("+---------------------------------+");

            //Buffer to save the input
            BufferedReader br06 = new BufferedReader(new InputStreamReader(System.in));
            //String input01 = br01.readLine();
            //User input
            int inputint06 = Integer.parseInt(br05.readLine());
            //@sinput05 the correct customer witch define's the user
            String sinput06 = null;
            //Comparing the User Input
            // Comparing the input.
            if(inputint06 == 1){
                sinput06 = "GREAL";
            }else if(inputint06 == 2){
                sinput06 = "HUNGC";
            }else if(inputint06 == 3){
                sinput06 = "LAZYK";
            }else if(inputint06 == 4){
                sinput06 = "LETSS";
            }else{
                System.out.println("| Bitte geben Sie eine Zahl zw. 1-4 ein. |");
                System.exit(0);
            }

            /*
                @KundenID1: Takes all node elements with the name CustomerID.
                @EmployeeID1: Takes all node elements with the name EmployeeID.
                @ts01: TreeSet, takes the EmployeeID(TreeSet doesn't take the same Element value 2 times)
                @tempID01: Takes the current(for int i) the CustomerID value.
                @tempID02: Takes the current(for int i) the EmployeeID value.
                @name01: @tempID01 to String
                @name02: @tempID02 to String
            */
            NodeList KundenID1 = doc.getElementsByTagName("CustomerID");
            NodeList EmployeeID1 = doc.getElementsByTagName("EmployeeID");
            TreeSet<String> ts01 = new TreeSet<String>();

            for (int i = 0, nodsize = bestellungen.getLength(); i < nodsize; i++) {
                Node tempID01 = KundenID1.item(i);
                Node tempID02 = EmployeeID1.item(i);
                String name01 = tempID01.getTextContent();
                String name02 = tempID02.getTextContent();
                //Comparing the customer value, if its equal it adds the value to the
                //TreeSet
                if (name01.equals(sinput06)) {
                    ts01.add(name02);
                }

            }
            //Output
            System.out.println("| "+ sinput06 +": Von " + ts01.size() + " verschiedenen      |");
            System.out.println("| Mitarbeitern                    |");

            //--------------------------------------------------------------------------------------------------------\\
            //--------------------------------------------------------------------------------------------------------\\
            //Exercise Number 9 Welches Gewicht hat LETSS insgesamt verschicken lassen?
            System.out.println("+---------------------------------+");
            System.out.println("| Number 9:Eingabe(1-4)           |");
            System.out.println("+---------------------------------+");
            System.out.println("| (1) : GREAL                     |");
            System.out.println("| (2) : HUNGC                     |");
            System.out.println("| (3) : LAZYK                     |");
            System.out.println("| (4) : LETSS                     |");
            System.out.println("+---------------------------------+");

            //Buffer to save the input
            BufferedReader br07 = new BufferedReader(new InputStreamReader(System.in));
            //String input01 = br01.readLine();
            //User input
            int inputint07 = Integer.parseInt(br05.readLine());
            //@sinput05 the correct customer witch define's the user
            String sinput07 = null;
            //Comparing the User Input
            // Comparing the input.
            if(inputint07 == 1){
                sinput07 = "GREAL";
            }else if(inputint07 == 2){
                sinput07 = "HUNGC";
            }else if(inputint07 == 3){
                sinput07 = "LAZYK";
            }else if(inputint07 == 4){
                sinput07 = "LETSS";
            }else{
                System.out.println("| Bitte geben Sie eine Zahl zw. 1-4 ein. |");
                System.exit(0);
            }
            /*
                @KundenID2: Takes all node elements with the name CustomerID.
                @Gewicht: Takes all node elements with the name Freight.
                @dGewicht: Double, to Save the Freight
                @tempID: Takes the current(for int i) the CustomerID value.
                @name01: @tempID to String
                @name02: @tempID02 to String
            */
            NodeList KundenID2 = doc.getElementsByTagName("CustomerID");
            NodeList Gewicht = doc.getElementsByTagName("Freight");
            double dGewicht = 0.0;
            //Going through the elements
            for (int i = 0, nodesize = bestellungen.getLength(); i < nodesize; i++) {
                Node tempID = KundenID2.item(i);
                String name01 = tempID.getTextContent();

                //Comparing the Strings
                if (name01.equals(sinput07)){
                    String s01 = Gewicht.item(i).getTextContent();
                    double Gewicht2 = Double.parseDouble(s01);
                    dGewicht += Gewicht2;
                }
            }
            System.out.println("| Das Gewicht beträgt: "+ dGewicht + "     |");
            System.out.println("+---------------------------------+");

            //--------------------------------------------------------------------------------------------------------\\
            //--------------------------------------------------------------------------------------------------------\\

            // Initialisieren des Elements language
            Element language = doc.createElement("language");
            // Schleife zum einfügen der Node in jedem Kunden
            for (int i = 0, nodesize = KundenID.getLength(); i < nodesize; i++) {
                KundenID.item(i).appendChild(language);
            }
            // Vorbereiten zum Speichern
            DOMSource source = new DOMSource(doc);
            // Ausgabe-Kanal festlegen
            StreamResult result = new StreamResult(new File("output.xml"));
            // Mit Hilfe der factory kann ein Transformer geholt werden
            TransformerFactory tFactory = TransformerFactory.newInstance();
            // Laden des Transformers
            Transformer transformer = tFactory.newTransformer();
            // Speichern der vorbereiteten Daten im Ausgabe-Kanal
            transformer.transform(source, result);

        }catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }
}