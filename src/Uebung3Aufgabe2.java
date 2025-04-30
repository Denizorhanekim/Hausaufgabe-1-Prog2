import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Uebung3Aufgabe2 {

    public static void main(String[] args) {
        String csvFile = "src/insurance.csv"; // --> der Pfad zur CSV-Datei, die wir einlesen wollen
        String line;// --> String line variable speichert jedes Zeile von CSV one by one , while we read it wth the BufferedReader Datei
        String cvsSplitBy = ","; // CSV: Comma-Seperated Values --> Daten sind durch Kommas geteilt

        // Dynamische Listen statt fester Arrays
        ArrayList<String> genders = new ArrayList<>(); // eine Liste für die Geschlechtswerte (male or female)--> jedes Mal wenn wir ein gender beim Einlesen finden --> Speicherung: genders.add("female");
        ArrayList<Integer> ages = new ArrayList<>(); // eine Liste für alle Alterswerte
        /*
        ArrayList-->flexibles Array--> wächst automatisch wenn wir Elemente hinzufügen (in contra to normal Arrays String[] und int[] usw die haben feste Größen die wir am Anfang definiren sollen beim Array Lists ist das nicht so
        Beispiel anch dem Einlesen einiger Zeilen
        z.B.
        19,femalw,......
         22, male....
         dann die Listen
         * genders=["female",male" ];
         * ages=[19,22,45]
         Array Listen sind einfacher zum Sortieren und vewalten,
         .add() zum  Einfügen eines neuen Elementes
         */

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            br.readLine(); // Header überspringen

            while ((line = br.readLine()) != null) {
                String[] data = line.split(cvsSplitBy);

// Alterswert steht beim Splate 0 deswegen data[0]
                int age = Integer.parseInt(data[0]); // Java liest alles als String wir müssen in eine Zahl umwandeln das Textdatei Alterswerte

                // Geschlecht aus Spalte 1
                String gender = data[1];

                // Zur Liste hinzufügen
                ages.add(age);
                genders.add(gender);
            }

        } catch (IOException e) {
            System.out.println("Fehler beim Lesen der Datei: " + e.getMessage());
            return;
        }

        // Sortieren mit Bubble-Sort (einfach, aber korrekt)
        for (int i = 0; i < ages.size() - 1; i++) {
            for (int j = 0; j < ages.size() - 1 - i; j++) {
                if (ages.get(j) > ages.get(j + 1)) {
                    // Alter tauschen
                    int tempAge = ages.get(j);
                    ages.set(j, ages.get(j + 1));
                    ages.set(j + 1, tempAge);

                    // Geschlecht entsprechend mit tauschen
                    String tempGender = genders.get(j); // Speichert den aktuellen Geschlechtswert in eienr temp Variable
                    genders.set(j, genders.get(j + 1));
                    genders.set(j + 1, tempGender);

                    /*
                    Ergebnis :
                    Vorger sortieren: Alter: [42.22] ,   Gender: [female, male]
                    Nach Sortieren: Alter: [22,45] , Gneder[male,female]
                    Dieser Code-Abschnitt sorgt dafür, das diese zwei Listen synchron blieben, wenn wir ihnen sortieren
                     */
                }
            }
        }

        // Ausgabe der sortierten Liste
        System.out.println("Nach Alter sortiert:");
        for (int i = 0; i < ages.size(); i++) {
            System.out.println("Alter: " + ages.get(i) + " - Geschlecht: " + genders.get(i));
        }
    }
}

/*
Erklärung zeilen 29-30
try(......){......} Try with Resoruces Schreibweise
try sorgt dafür, dass die Resourcen wie Dateien automatisch geschlossen werden, z.B. wenn ein Fehler auftritt
Wörtlich:  Versuche die Datei zu öffnen und verwende den BufferedReader --aber schließe automatisch, wenn der Block zu Ende ist
newFileReader(csvFile) --> öffnet ein Textdatei zum Lesen von zeichen
new Bufferedreader(...) liest ganze zeilen effizienter
Ohne BufferedReader müsstest du die Zeischen für Zeischen verarbeiten
br.readLine()--> erste Zeile : Spaltenkopf, enthält keine echte Daten, sondern nur Überschriften --> überspringe
 */

 /*
 Erklärung Seite 32-33
 while((line=br.readLine())!=null)
 -->br.readLine() liest eine Zeile aus der Datei als String
 Dieser Befehl wird solange wiederholt, solange es noch Zeilen gibt.
 solange readLine() ungeleich null zurückgiubt

 line.split(csvSplitBy) --> teilt die Einzelteile, indem die Daten mit Komma getrennt sind
 +++das Ergebnis ost ein String-Array mit den einzelnen Splaten

 String[data]=line.split(",");
 data[0]="19"
 data[1]="female"
 ....

 In jedem Schleifensurchlauf:
 1. Eine Zeile wird gelesen
 2. Die Zeile wird in einzelne Splaten (Strings) zerlegt
  */

/*
Erkärung Seite 35-36

 int age = Integer.parseInt(data[0]);
 --> diese Zeile nimmt den text aus der Splate für das Alter, umwandlung in eine Zahl (int), und Speicherung in der Variable age

 */

/*
Erklärung Seite 46 bis 49

--> catch(IOException e)
fängt einen Fehler (Exception) ab, falls beim Lesen der Datei etwas schiefläuft
IOException ist der Ryp des Fehlers , der auftreten kann
--> diese Fehler kann : 1)Die Datei wurde nicht gefunden 2) Die Datei ist gesperrt oder beschäftigt 3) Ein Lesefehler ist aufgetreten

--> wenn eien Fehler passiert dann wird eine Fehlermeldung auf der Konsole geprinted

return; --> beendet das main() Porgramm sofor ein Fehle bassiert
--Y wenn eien Fehler passiert und das Program weiterläufgt dann kann es dazu führen,dass weitere Fehler  passieren
 */