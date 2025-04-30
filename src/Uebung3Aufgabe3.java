import java.util.ArrayList;
import java.util.Scanner;
/*
Was ist in dieser Aufgabe von uns erwartet?
wir sollen Kundendaten einlesen, anzeigen, hinzufpgen, löschen, sortieren und filtern können

Die Kunden haben Attributen:
ID:Altzer, Geschlechr, Region, BMI, Anzahl vin Kinder, Rayuherstatus, Kosten

 Interaktive Benutzermenü : Das Menp erlaubt dem Benutzer, eien operation durch Eingabe einer Zahl auszuwählen

 Unser system ermäöglivht Statistische Auswertung:
 Anzahl der Kunden,Durchschnittsalter,Durchschnittlicher BMI,Durchschnittliche Kinderanzahl,Durchschnittliche Kosten,Minimal- und Maximalwerte,

 */

public class Uebung3Aufgabe3 {

    // --- Customer class --- enthält Attributen des Kunden
    static class Customer {
        int id;
        int age;
        double bmi;
        int children;
        boolean smoker;
        String gender;
        String region;
        double charges;

        public Customer(int id, int age, double bmi, int children, boolean smoker, String gender, String region, double charges) {
            this.id = id;
            this.age = age;
            this.bmi = bmi;
            this.children = children;
            this.smoker = smoker;
            this.gender = gender;
            this.region = region;
            this.charges = charges;
        }

        @Override
        public String toString() {
            return "{ID=" + id + ", Age=" + age + ", BMI=" + bmi + ", Children=" + children +
                    ", Smoker=" + smoker + ", Gender=" + gender + ", Region=" + region + ", Charges=" + charges + "}";
        }
    }

    // --- SimpleCRM class --- enthält Hauptlogik
    public static class SimpleCRM {
        static void loadCustomersFromCSV(String filepath) {
            try (Scanner fileScanner = new Scanner(new java.io.File(filepath))) {
                fileScanner.nextLine(); // Header überspringen
                while (fileScanner.hasNextLine()) {
                    String line = fileScanner.nextLine();
                    String[] data = line.split(",");

                    int age = Integer.parseInt(data[0]);
                    String gender = data[1];
                    double bmi = Double.parseDouble(data[2]);
                    int children = Integer.parseInt(data[3]);
                    boolean smoker = data[4].equalsIgnoreCase("yes");
                    String region = data[5];
                    double charges = Double.parseDouble(data[6]);
                    int id = customers.size() + 1;

                    customers.add(new Customer(id, age, bmi, children, smoker, gender, region, charges));
                }
                System.out.println("Datei erfolgreich eingelesen!");
            } catch (Exception e) {
                System.out.println("Fehler beim Einlesen der Datei: " + e.getMessage());
            }
        }

        static ArrayList<Customer> customers = new ArrayList<>();

        public static void main(String[] args) {
            loadCustomersFromCSV("insurance.csv"); // CSV-Daten werden beim Start geladen

            Scanner scanner = new Scanner(System.in);


            while (true) {
                System.out.println("\nPlease choose the operation:");
                System.out.println("1 - Output all clients");
                System.out.println("11 - All male customers");
                System.out.println("12 - All female customers");
                System.out.println("13 - All customers in southwest");
                System.out.println("14 - All customers in southeast");
                System.out.println("2 - Automated report");
                System.out.println("21 - Male customers report");
                System.out.println("22 - Female customers report");
                System.out.println("23 - Customers in southwest report");
                System.out.println("24 - Customers in southeast report");
                System.out.println("3 - Search a customer");
                System.out.println("4 - Delete a customer");
                System.out.println("5 - Add a customer");
                System.out.println("6 - Sort by:");
                System.out.println("61 - By age\n62 - By number of children\n63 - By sex\n64 - By region\n65 - By BMI\n66 - By charges");
                System.out.println("7 - Exit");

                int choice = scanner.nextInt();
                switch (choice) {
                    case 1 -> displayAllCustomers();
                    case 11 -> filterCustomersByGender("male");
                    case 12 -> filterCustomersByGender("female");
                    case 13 -> filterCustomersByRegion("southwest");
                    case 14 -> filterCustomersByRegion("southeast");
                    case 2 -> automatedReport(customers);
                    case 21 -> automatedReport(filterListByGender("male"));
                    case 22 -> automatedReport(filterListByGender("female"));
                    case 23 -> automatedReport(filterListByRegion("southwest"));
                    case 24 -> automatedReport(filterListByRegion("southeast"));
                    case 3 -> searchCustomerById();
                    case 4 -> deleteCustomerById();
                    case 5 -> addCustomer();
                    case 61 -> sortCustomersByAttribute("age");
                    case 62 -> sortCustomersByAttribute("children");
                    case 63 -> sortCustomersByAttribute("gender");
                    case 64 -> sortCustomersByAttribute("region");
                    case 65 -> sortCustomersByAttribute("bmi");
                    case 66 -> sortCustomersByAttribute("charges");
                    case 7 -> { System.out.println("Goodbye!"); return; }
                    default -> System.out.println("Invalid choice. Try again.");
                }
            }
        }

        static void displayAllCustomers() {
            for (Customer c : customers) {
                System.out.println(c);
            }
        }

        static void filterCustomersByGender(String gender) {
            for (Customer c : customers) {
                if (c.gender.equalsIgnoreCase(gender)) {
                    System.out.println(c);
                }
            }
        }

        static void filterCustomersByRegion(String region) {
            for (Customer c : customers) {
                if (c.region.equalsIgnoreCase(region)) {
                    System.out.println(c);
                }
            }
        }

        static ArrayList<Customer> filterListByGender(String gender) {
            ArrayList<Customer> list = new ArrayList<>();
            for (Customer c : customers) {
                if (c.gender.equalsIgnoreCase(gender)) list.add(c);
            }
            return list;
        }

        static ArrayList<Customer> filterListByRegion(String region) {
            ArrayList<Customer> list = new ArrayList<>();
            for (Customer c : customers) {
                if (c.region.equalsIgnoreCase(region)) list.add(c);
            }
            return list;
        }

        static void automatedReport(ArrayList<Customer> list) {
            if (list.isEmpty()) {
                System.out.println("No customers found.");
                return;
            }
            double sumAge = 0, sumBMI = 0, sumCharges = 0;
            int sumChildren = 0;
            double maxCharges = Double.MIN_VALUE, minCharges = Double.MAX_VALUE;

            for (Customer c : list) {
                sumAge += c.age;
                sumBMI += c.bmi;
                sumCharges += c.charges;
                sumChildren += c.children;
                if (c.charges > maxCharges) maxCharges = c.charges;
                if (c.charges < minCharges) minCharges = c.charges;
            }

            System.out.println("Number of customers: " + list.size());
            System.out.println("Average age: " + (sumAge / list.size()));
            System.out.println("Average BMI: " + (sumBMI / list.size()));
            System.out.println("Average children: " + (double)sumChildren / list.size());
            System.out.println("Average charges: " + (sumCharges / list.size()));
            System.out.println("Minimum charges: " + minCharges);
            System.out.println("Maximum charges: " + maxCharges);
        }

        static void searchCustomerById() {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter ID: ");
            int id = scanner.nextInt();
            for (Customer c : customers) {
                if (c.id == id) {
                    System.out.println("Customer found: " + c);
                    return;
                }
            }
            System.out.println("Customer not found.");
        }

        static void deleteCustomerById() {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter ID: ");
            int id = scanner.nextInt();
            customers.removeIf(c -> c.id == id);
            System.out.println("Customer deleted if existed.");
        }

        static void addCustomer() {
            Scanner scanner = new Scanner(System.in);
            System.out.print("ID: ");
            int id = scanner.nextInt();
            System.out.print("Age: ");
            int age = scanner.nextInt();
            System.out.print("BMI: ");
            double bmi = scanner.nextDouble();
            System.out.print("Children: ");
            int children = scanner.nextInt();
            System.out.print("Smoker (true/false): ");
            boolean smoker = scanner.nextBoolean();
            scanner.nextLine();
            System.out.print("Gender: ");
            String gender = scanner.nextLine();
            System.out.print("Region: ");
            String region = scanner.nextLine();
            System.out.print("Charges: ");
            double charges = scanner.nextDouble();

            customers.add(new Customer(id, age, bmi, children, smoker, gender, region, charges));
            System.out.println("Customer is added.");
        }

        static void sortCustomersByAttribute(String attribute) {
            customers.sort((c1, c2) -> {
                switch (attribute) {
                    case "age" -> { return Integer.compare(c1.age, c2.age); }
                    case "children" -> { return Integer.compare(c1.children, c2.children); }
                    case "gender" -> { return c1.gender.compareTo(c2.gender); }
                    case "region" -> { return c1.region.compareTo(c2.region); }
                    case "bmi" -> { return Double.compare(c1.bmi, c2.bmi); }
                    case "charges" -> { return Double.compare(c1.charges, c2.charges); }
                    default -> { return 0; }
                }
            });
            System.out.println("Customers sorted by " + attribute + ".");
        }
    }
}
