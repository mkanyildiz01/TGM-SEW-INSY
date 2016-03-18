import java.io.File;
import java.io.IOException;

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
                @tele: Boolean for checking if its true
            */
            NodeList vorwahl = doc.getElementsByTagName("Phone");
            boolean tele;
            int itele = 0;
            for (int i = 0, nodsize = vorwahl.getLength(); i < nodsize; i++) {
                // Die Nods der Verschidenen über Elemente holen
                Node temp1 = vorwahl.item(i);
                // Eine Node weiter speichern
                Node temp2 = vorwahl.item(i++);
                // Speichern der Nods(Vorwahlen) in Strings
                temp1.toString().substring(0, 4);
                // Speichern der Nods(Vorwahlen) in Strings
                temp2.toString().substring(0, 4);
                // Vergleichen ob die Nummern passen
                if (temp1.equals(temp2)) {
                    tele = true;
                    itele ++;
                }
            }
            // Ausgabe wenn es gleich ist
            if (tele = true) {
                System.out.println("|Es gibt " + itele + " Kunden mit der gleichen|");
                System.out.println("|Vorwahl                          |");
                System.out.println("+---------------------------------+");
            }
        }catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}