public class Uebung2Aufgabe1a {
    public static void main(String[] args) {
        /*
            Aufgabenstellung:
            Schreiben Sie ein Java-Programm mit den folgenden statischen Methoden (nutzen
Sie Ihre bisherigen Lösungen, ggf. schreiben Sie Ihren Code entsprechend um):
a) Berechnung des Durchschnitts der Elemente in einem Array;

Durchschnitt (arithmetische Summe): Alle Werte der Elemente summieren
und durch die Anzahl der Elemente teilen
Das Ergebnis als double zurückgeben, da Durchschnittswerte können Dezimalzahlen haben.
Error Check: Zahlen können nicht furch 0 dividiert werden,
also Anzahl der Elemente in dem Array (array length)
kann nicht Null sein --> Falls leere Array 0.0 zurückgeben, Division durch 0 vermeiden
         */
    }

    public static double berechneDurchschnitt(double[] arr) {
        if (arr == null || arr.length == 0) {
            /*
          arr==null prüft ob das Element überhaupt existiert ( ist das Array überhaupt da?)
          arr.length==0 prüft ob das existierende Array überhaupt Elemente enthält (Enthält das Elmeent mindestens 1 Element)
          nur wenn das Element existiert und Elemente enthält , kann man weiterrechnen
          Hier Vermeiden wir Division durch 0
          arr ist ein Array Objekt und ein Rferenz deswegen kannst du nicht arr==0 schreiben, musst du "null" schreiben
          aber arr.length is an integer kannst du arr.lentgh==0 schreiben
             */
            return 0.0; // es gibt keinem Durchscnitt bei einem leeren Array
        }
        // int summe=0;  --> falsch Typ muss double sein da arr[i] vpm Typ double ist
        double summe = 0.0;

        for (int i = 0; i < arr.length; i++) {
            summe += arr[i]; // direktem Zugriff auf das Element per Index
        }
        return (double) summe / arr.length;
    }

    //Übungsblatt 2 Aufgabe 1 b) Standardabweichung berechnen
    /*
    Schritte um die Standardabweichung zu berechenen
    Schritt 1: check if the array überhaupt exists and does it have mindestens 1 element
    Schritt2 : berechne Durcschnitt des Arrys ( schin gemacht just call the method to calculate the mittelwert and put it in an integer variable
    Schritt3: make a for schleife to calculate the deviance of each element from the middle wert (go thru all elements wth a fpr schleife wth zugriff per index ,
    subtract the mittelwert frim them and store the difference in a variable) -> than square the difference and add it to summeQuadrat
    Schritt 4: summeQuadrat/anzahl der Elmenete = variance
    Schritt 5: Wurzel von dem Variance ziehen und Antwort ist die Standardabweichung

     */
    public static double berechneStandardabweichung(double[] arr) {
        if (arr == null || arr.length == 0) {
            return 0.0; //Standardabweichung ist für leeres Array nicht definiert
        }
        double durchschnitt = berechneDurchschnitt(arr); //Mittelwert berechnen
        double summeQuadrat = 0.0; // Summe der quadratischen Abweichungen
        for (int i = 0; i < arr.length; i++) {
            double abweichung = arr[i] - durchschnitt;
            summeQuadrat += abweichung * abweichung;
        }
        double varianz = summeQuadrat / arr.length;
        double standardAbweichung = Math.sqrt(varianz); // Standardabweichung ist die Quadratwurzel aus dem Varianz
        return standardAbweichung;
    }

    public static double berechneMaximum(double[] arr) {
        if (arr == null || arr.length == 0) {
            return 0.0;
        }
        double maximumWert = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > maximumWert) {
                //  arr[i]=maximumWert; --> Das ist falsch , überschreibst du den Array-Inhalt--> das sollst du nicht tun
                maximumWert = arr[i];
            }

        }
        return maximumWert;

    }

    public static double berechneMinimum(double[] arr) {
        if (arr == null || arr.length == 0) {
            return 0.0;
        }
        double minimumWert = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < minimumWert) {
                minimumWert = arr[i];
            }
        }
        return minimumWert;
    }

    public static void berechneStatistik(double[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }

        System.out.printf("Der Durschnitt des Arrays ist gleich: %.2f%n", berechneDurchschnitt(arr));
        System.out.printf("Die Standardabweichung des Arrays ist gleich: %.2f%n", berechneStandardabweichung(arr));
        System.out.printf("Der Maximumwert in dem Array ist gleich: %.2f%n", berechneMaximum(arr));
        System.out.printf("Der Minimumwert in dem Array ist gleich: %.2f%n", berechneMinimum(arr));
    }

    // Übungsblatt 2 Aufgabe 1 e) Entfernen eines bestimmten Elements in einem Array

    /*
    1. Prüfe ob das Array überhaupt exisitiert oder Elemente enthält --> wenn leeres Array oder nicht existiert --> return array direkt --> kannst du nichts entfernen
    2. Durchlaufe das Array mit einer klassischen for-Schleife und finde das erste Vorkommen von dem "wertDerEntferntWerdeSoll
    --> wenn du den gesuchten Wert nicht findest , gib das Array zurück
    3. Nachdem du das Element gefunden hast musst du ein neuses Array erstellen das 1 Element weniger als der alten Array hat
    --> in Java kannst du ein Element aus dem Array nicht einfach löschen
    4. Kopiere alle alten lemente in das neue Array
    --> kopiere mit einer Sxhleife alle Werte außer dem einen, den du entfernen willst
    ** Alle Werte vor dem zu entfernenden Index kopierst du einfach
    ** Sobald du beim zu entfernenden Element bist, überspringe es
    ** Danach kopiere  die restlichen Elementen weiter
    5. return das neue Array

     */
    public static double[] entferneElementAusArray(double[] arr, double wertDerEntferntWerdenSoll) {
        if (arr == null || arr.length == 0) {
            return arr;
        }
        int indexZuEntfernen = -1; //index noch nicht gefunden (-1 hat diese Bedeutung)
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == wertDerEntferntWerdenSoll) {
                indexZuEntfernen = i;
                break; //Schleife beenden nachdem esrten Vorkommen
            }

            // Wenn der Wert kann nicht gefunden werden guib das Original Array zurück
        }
        if (indexZuEntfernen == -1) return arr;

        // neues Array erstellen --> enthlt den gesuchten Wert nicht , hat 1 Elment weniger als Original
        double[] neuesArray = new double[arr.length - 1];
            /*
            for(int i=0;i<arr.lenght:i++){
                if(arr[i]!=arr[indexZuEntfernen]){
                    neuesArray[i]=arr[i];
                }
                --> falsch !! brauchst 2 verschiedene Zählervariable

Erklärung von meinem Fehler:
Vorstelle: arr.length=5; indexZuEntfernen=2; neuesArray.lenght=4
Wenn du neuesArry[i]=arr[i] schreibst, würdest du den zweiten Element nicht entfernen sindern die letzte Element entferen
Lösung für dieses Problem ist 2 verschidenee Zählervariablen zu verwenden
Zöhlvariable i wird durch das Original array arr lUFEN
Zählvariable j wird durch das neue Array neuesArray laufen (aber wird d´nicht durch das entfernete Elemente laufen wie wir schon wollen)
Merke: Wenn du ein Element aus einem Array entfernst, brauchst du beom Kopieren zwei Schleife-Zähler.
Ein Zähler (i) fürs Original-Array und ain naderes Tähler (j) fürs Neues-Array --> weil sie l´nicht mehr synchron zueinander laufen

             */


        int j = 0; // Zähler fürs Neues
        for (int i = 0; i < arr.length; i++) {
            if (i != indexZuEntfernen) {
                neuesArray[j] = arr[i]; // wenn i=indexZuEntfernen es wird sprungen
                j++;
            }

        }
        /*
i geht immer eins höher --> jedes Element überprüfen beim alten Array
j geht nur dann eins weiter wenn ein Wert übernommen wird --> indexZuEntfernen überspringen wund nicht kopiert
         */
        return neuesArray;
    }
    //Uebungsblatt 2 Aufgabe 1 h) Einfügen ein neues Element an einer bestimmten Position in ein Array
public static double[] feugeEinNeuesElement(double[] arr,double neuerWert,int position) {
    if (arr == null || arr.length == 0) {
        return arr;
    }

    if (position < 0 || position > arr.length) {
        System.out.println("Ungültige position, bitte wählen Sie ein Position zwischen 0 und"+arr.length);
        return arr;
    }
    double[] neuesArray = new double[arr.length + 1];
    int j = 0;
    for (int i = 0; i < arr.length; i++) {
        if (i == position) {
            neuesArray[j] = neuerWert;
        } else {
            neuesArray[j] = arr[i];
            j++;
        }

    }
    return neuesArray;
}


}

