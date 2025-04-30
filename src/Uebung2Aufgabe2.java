import java.util.Scanner; // reads user input
import java.util.Arrays; // prints arrays nicely
public class Uebung2Aufgabe2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // scanner robot listens what the user types

        System.out.print("Wilkommen zu Übung 2!");
        System.out.print("Bitte erstellen Sie Ihre eigenes Array und beantworten die Fragen. Danach werden Sie Nummer aus der Liste auswählen und entsprechendes operation wird auf dem Array performiert.");
        System.out.print("Wie viele Elemente soll dein Array haben?");
        int n = scanner.nextInt(); // reads the number user typed and saves it in n

        double[] arr = new double[n]; // here we're creating the array that the user wants
        for (int i = 0; i < n; i++) {
            System.out.print("Geben Sie den Wert für das Index" + (i + 1) + "ein."); // i oder i+ ?? 0 dan baslama vesaire i ya da i+1 iyi anla
            arr[i] = scanner.nextDouble();
            //(i+1) cuz humans count from q, computer count from 0; number willl be at the spot i saved

        }

        System.out.println("Ihr Array:" + Arrays.toString(arr));

        while (true) { // strating a loop that goes foerever until broken manually as the Aufgabenstellung says
            System.out.println("\n----Menü-----");
            System.out.println("1=Durchschnitt berechnen");
            System.out.println("2=Standardabweichung berechnen");
            System.out.println("3=Maximalwert berechnen");
            System.out.println("4=Minimalwer berechnen");
            System.out.println("5=Statistik berechnen");
            System.out.println("6=Index eines Werts finden");
            System.out.println("7=Wert entfernen");
            System.out.println("8=Wert einfügen");
            System.out.println("9=ein Element suchen");
            System.out.println("10=Die Statistiken des Arrays anzeigen");
            System.out.print("11=das aktuelle Array ausgeben");
            System.out.print("Andere Eingabe=Programm beenden");
            System.out.print("Bitte wählen Sie eine Option:");

            if (!scanner.hasNextInt()) { // überprüft ob ein ganze zahl eingegebn ist sondern kein Alphabet oder ungültige Zahlen wie float
                System.out.println("Ungültige Eingabe. Programm wird beendet.");
                scanner.next(); // reads and thores away
                // o richtig verstanden ?? : liest den "falsvhen Input weg
                continue; //jumps directly to the next loop, it doesn't leave the while(true) loop, skips to the rest of thr code for this "round"

                /*
                What does continue do?--> forget everything below this point in this loop turn, jumo immediately go to the top of the while(true) and start again
                 */

            }

            int operation = scanner.nextInt();
            if (operation < 1 || operation > 11) {
                System.out.print("Please enter a valid number between 1-11  for the operation");
                continue;

            }
            switch (operation) { // depending on user input right operatoin will be performed
                case 1:
                    System.out.printf("Durchschnitt: %.2f%n", Uebung2Aufgabe1a.berechneDurchschnitt(arr));
                    break;
                case 2:
                    System.out.printf("Standardabweichung: %.2f%n", Uebung2Aufgabe1a.berechneStandardabweichung(arr));
                    break;
                case 3:
                    System.out.printf("Maximalwert: %.2f%n", Uebung2Aufgabe1a.berechneMaximum(arr));
                    break;
                case 4:
                    System.out.printf("Minimalwert: %.2f%n", Uebung2Aufgabe1a.berechneMinimum(arr));
                    break;
                case 5:
                case 10:
                    Uebung2Aufgabe1a.berechneStatistik(arr);
                    break;
                case 6:
                case 9:
                    System.out.print("Welchen Wert möchten Sie suchen? ");
                    double suchwert = scanner.nextDouble();
                    int index = -1;
                    for (int i = 0; i < arr.length; i++) {
                        if (arr[i] == suchwert) {
                            index = i;
                            break;
                        }
                    }
                    System.out.println(index == -1 ? "Wert nicht gefunden." : "Index des Wertes: " + index);
                    break;
                case 7:
                    System.out.print("Welchen Wert möchten Sie entfernen? ");
                    double zuEntfernen = scanner.nextDouble();
                    arr = Uebung2Aufgabe1a.entferneElementAusArray(arr, zuEntfernen);
                    System.out.println("Neues Array: " + Arrays.toString(arr));
                    break;
                case 8:
                    System.out.print("Welchen Wert möchten Sie einfügen? ");
                    double neuerWert = scanner.nextDouble();
                    System.out.print("An welcher Position (0 bis " + arr.length + ")? ");
                    int pos = scanner.nextInt();
                    arr = Uebung2Aufgabe1a.feugeEinNeuesElement(arr, neuerWert, pos);
                    System.out.println("Neues Array: " + Arrays.toString(arr));
                    break;
                case 11:
                    System.out.println("Aktuelles Array: " + Arrays.toString(arr));
                    break;
            }
        }
    }

}