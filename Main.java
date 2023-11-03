
//package FinalProject;

import java.util.Scanner;

public class Main {
    static boolean exit = false;

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        median m = new median();
        try {

            while (!exit) {

                Scanner s1 = new Scanner(System.in);
                System.out.println("\nPlease choose an option from below... ");
                System.out.println("1: Median for arrays of length 3, 5 & 7");
                System.out.println("2: Generic Median using order statistics");
                System.out.println("3: Quicksort using randomized pivot value");
                System.out.println("4: Quicksort using median as pivot value");
                System.out.println("5: Exit");

                int option = s1.nextInt();

                if (option == 1)
                    m.median357();

                else if (option == 2)
                    m.genericMedian();

                else if (option == 3)
                    m.quicksortRamdomized();

                else if (option == 4)
                    m.quicksortMedianAsPivot();

                else if (option == 5) {
                    exit = true;
                    break;
                } else
                    System.out.println("\ninvalid input..!");

            }
        }catch(Exception e){
            System.out.println("Invalid Inputs given. Program exited. Please run Main again.");
        }

    }
}
