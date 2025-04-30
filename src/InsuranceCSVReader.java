import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Arrays;
/*
Aufgabe 3: Lesen Sie eine beliebie Spalte, z.B. int, als Array aus einer csv-Datei und testen Sie Ihr Programm erneut

Schritte: Datei öffnen und Scanner erstellen
1. Scanne scanner=new Scanner(new File"insurance.scv");
2. Erste Zeile überspringen(Header)--> enthält (oft) die Spaltenüberschriften, übersprinen da wir nicht bearbeiten wollen
scanner.nextLine();
3. Ziel: Die GGesamtzahl der Datenzeilen heruasfinden:
--> jede Zeile durchgehen und Zähl-varibale um 1 erhöhen
--> Das brauchst du, um später ein Array mit genau dieser Länge zu erstellen
4. Scanner neu starten --> warum müssen wir Scanner neu starten? Antwort habe ich nicht ganz richtig verstanden
scanner=new Scanner(new File("insurance.csv"));
scanner.nextLine();
--> Scanner nochmal öffnen, weil man bei Scanner nicht einfach "zurückspulen"
------>FRAGE. noch
 */

public class InsuranceCSVReader {
    public static void main(String[] args) {
        try {
            // Datei öffnen
            Scanner scanner = new Scanner(new File("insurance.csv"));


            // insutance.csv ist Dateiname ; new File(...) erstellt ein Datenobjekt, das an den Scanner übergeben wird

            // Erste Zeile (Header) wird übersprungen
            scanner.nextLine();

            // Zähle die Zeilen (um die Array-Größe zu finden)
            int zeilen = 0;
            while (scanner.hasNextLine()) {
                scanner.nextLine();
                zeilen++;
            }


            // Scanner neu starten, um Datei von vorne zu lesen --> Frage: Warum müssen wir scanner neu starten?
            scanner = new Scanner(new File("insurance.csv"));
            scanner.nextLine(); // Header wieder überspringen

            // Array zum Speichern von "age"s
            int[] age = new int[zeilen];


            int i = 0;
            while (scanner.hasNextLine()) { // --> But age befindet sich in einer Spalte warum lesen wir dir Zeilen, wir müssen nur den ersten Eintrag in jeder Zeile lesen
                String line = scanner.nextLine(); // first we read the line
                String[] values = line.split(","); // secondly we split the line , now values[0] is the first values of this line like "19"--> spliz(",") Teilt jede Zeile am Komma--> so entsteht ein Array mit allen Werten einer Zeile
                age[i] = Integer.parseInt(values[0]); // thirdly, values[0] we pick only values[0] (but as a string) Age ist die erste Spalte (Index 0),,, fourth we convert it to an integer number Integer.parseInt(values[0]) here as an integer (19),,, fifth store the intetger int the age array age[i]=Integer.parseInt(values[0]);
                i++;
            }
            /*
            new String[] values array is created for every line in the file
            each time the program reads a new line (scanner.nextLine()):
            --> splits the line (line.split(","))
            --> and creates a brand new-array called value[]
            --> for line 1 --> one values[] array is created
            --> for lijne 2 --> another values[] array is created
            --> for line 3 --Y another values[] array is created ans do goes on

            * we do not keep old values[] arrays, we only care about the current line's values[0] (the age is chosen in unserem Fall)
            only values[0] values from each line is saved into our real age[] array
            Fluß:
            read line 1 : "19, female, 27.9,0, yes, southwest,16888.4.924"
            split-->create values[] ["19","27.9","0","yes","southwest","16884.924"]
            pick values[0]--> "19"  --> age[i]=Integer.parseInt(values[0]); --Y picks the first piece of the line (the first "word" after splitting)
            ****PS: in computers and text files (like .csv) everything u read is first treated as a String (text), it doesn't matter if it looks like a number -- it's still a text first
            Integer.parseInt(values[0]) --> is the part that a converts it from text into a number from ("19" --> to 19)
            age[i]=... --> is the part that stores the picked number into the age array

             */
            /*
            Integer.parseInt(values[0]) --> konvertiert diesen Textwert in eine Zahl
            values[0]--> der erste Wert i der Zeile
            age[i]=... speichert diesen Wert in eienm Array age --> Array muss vorher schin deklariert werden
            Fragee: muss man i außerhalb schleife deklarieren ?
             */

            scanner.close();

            System.out.println("Anzahl der eingelesenen Datensätze: " + age.length);
            Scanner inputScanner = new Scanner(System.in);
            System.out.print("Wie viele Alterseinträge möchten Sie sehen?");
            int howMany = inputScanner.nextInt();

            System.out.println("Hier sind die ersten" + howMany + "Alterseinträge:");
            for (int j = 0; j < Math.min(howMany, age.length); j++) {
                System.out.print(age[j] + " ");
            }
        } catch (FileNotFoundException e){
                System.out.println("Datei nicht gefunden: " + e.getMessage());
            }catch (Exception e){
                System.out.println("Fehler beim Einlesen" + e.getMessage());
            }
        }
    }
