import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

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
            //Exercise Number 1 Wie viele Kunden und wie viele Bestellungen sind gespeichert?
            System.out.println("+---------------------------------+");
            System.out.println("| Number 1:                       |");
            System.out.println("+---------------------------------+");
            /*
                @kunden: A Node List with all Customers-Elements
                @buchungen: A Node List with all Orders-Elements
            */
            NodeList kunden = doc.getElementsByTagName("Customer");
            NodeList buchungen = doc.getElementsByTagName("Order");
            System.out.println("| Es wurden "+buchungen.getLength()+" Buchungen getätigt!|");

            //Exercise Number 2 Welche CustomerID besitzt der vierte Kunde?
            System.out.println("+---------------------------------+");
            System.out.println("| Number 2:                       |");
            System.out.println("+---------------------------------+");
            /*
                @node4: node4 is taking the 4th node of the customers.
                @kunde: Is all of the 4th Element(All Nodes Included).
                @name: Is taking the CustomerID as node and "converting" to a String.

            */
            Node node4 = kunden.item(3);
            Element kunde = (Element)node4;
            String name = kunde.getAttribute("CustomerID");
            System.out.println("|Die ID vom 4. Kunden lautet: "+name +"|");

            //Exercise Number 3 Wie lautet die vollständige Adresse von der Firma Lazy K Kountry Store?
            System.out.println("+---------------------------------+");
            System.out.println("| Number 3:                       |");
            System.out.println("+---------------------------------+");
            System.out.println("| 1.Addr 2.City 3.Region 4.Post   |");
            System.out.println("| 5.Land                          |");
            System.out.println("+---------------------------------+");
            /*
                @firmanr3: Is taking the Child Element of the 2. Element(Customers-Element)
                @adresse: Is going through all Elements, witch are in FullAddress
                @temp1: Is getting 1 Node by 1 and saving as a String value.
                        So I can Print it...(A Node cant be printed in the console with
                        System.out.println().)

            */
            NodeList firmanr3 = doc.getElementsByTagName("FullAddress").item(2).getChildNodes();
            for (int i = 0, size = firmanr3.getLength(); i < size; i++) {
                Node adresse = firmanr3.item(i);
                String tmp1 = adresse.getTextContent();
                System.out.print(tmp1);
            }

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

            //Exercise Number 5 Gibt es Kunden, die nicht aus den USA sind?
            System.out.println("| Number 5:                       |");
            System.out.println("+---------------------------------+");
            /*
                @land: Takes all node elements with the name country.
                @iland: Integer that counts all customers.
                @ilanduse: Integer that counts all customers, witch are from the U.S.
                @address: Is a node list with all the addresses(1 by 1).
            */
            NodeList land = doc.getElementsByTagName("Country");
            int iland = 0;
            int ilandusa = 0;
            for (int i = 0, nodsize = land.getLength(); i < nodsize; i++) {
                Node address = land.item(i);
                iland++;
                if (address.getTextContent().equals("USA")) {
                    ilandusa ++;
                }
            }
            if (ilandusa == iland) {
                System.out.println("| Alle Kunden sind aus der USA: "+iland+" " + "|");
            }else{
                System.out.println("| Es sind nicht alle Kunden aus der USA: "+iland+" " + "|");
            }
            //Exercise Number 6 Welche(r) Kunde(n ) hatte(n ) die meisten Bestellungen?
            System.out.println("| Number 6:                       |");
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
            TreeMap<String,Integer> top01 = new TreeMap<String,Integer>();
            top01.put("GREAL",greal01);
            top01.put("HUNGC",hungc01);
            top01.put("LAZYK",lazyk01);
            top01.put("LETSS",letss01);

            System.out.println(top01);


            //Exercise Number 7 Wann war die letzte Bestellung von LAZYK?
            System.out.println("+---------------------------------+");
            System.out.println("| Number 7:                       |");
            System.out.println("+---------------------------------+");
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
                @temp01: A DateFormate to parse in my new Date.
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

                //Coparing the Strings
                if (Name1.equals("LAZYK")) {
                    Node Temp02 = BestellDatum.item(i);
                    String Datum01 = Temp02.getTextContent();
                    String Datum02 = Datum01.substring(0,10);
                    String stemp01 = Datum01.substring(12,19);
                    String DateTime01 = Datum02 + " " + stemp01;
                    Date temp01 = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss").parse(DateTime01);


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
            System.out.println("| " + DatumD + "    |");

            //Exercise Number 8 Von wie vielen verschiedenen Mitarbeitern wurde GREAL bedient?
            System.out.println("+---------------------------------+");
            System.out.println("| Number 8:                       |");
            System.out.println("+---------------------------------+");
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
                if (name01.equals("GREAL")) {
                    ts01.add(name02);
                }

            }
            //Output
            System.out.println("| GREAL: Von " + ts01.size() + " verschiedenen      |");
            System.out.println("| Mitarbeitern                    |");
            
        }catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}