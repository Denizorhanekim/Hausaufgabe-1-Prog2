public class Uebung3Aufgabe1 {


        public static void bubbleSort(int[] arr) {
            int n = arr.length;
            boolean swapped;

            for (int i = 0; i < n - 1; i++) {
                swapped = false;


                for (int j = 0; j < n - 1 - i; j++) {
                    if (arr[j] > arr[j + 1]) {
                        // Swap arr[j] and arr[j+1]
                        int temp = arr[j];
                        arr[j] = arr[j + 1];
                        arr[j + 1] = temp;
                        swapped = true;
                    }
                }

                if (!swapped) {
                    break;
                }
            }
        }

        public static void main(String[] args) {
            int[] numbers = {5, 2, 9, 1, 5, 6};

            System.out.println("Before sortieren:");
            for (int i = 0; i < numbers.length; i++) {
                System.out.print(numbers[i] + " ");
            }

            bubbleSort(numbers);

            System.out.println("\nAfter sortieren:");
            for (int i = 0; i < numbers.length; i++) {
                System.out.print(numbers[i] + " ");
            }
        }
    }

