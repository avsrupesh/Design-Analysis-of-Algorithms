/** Tests the quicksort algorithms. 
  * Generates arrays of random integers and times the execution of the  
  * quicksorts. 
  */

import java.util.Random;

public class TestQuicksort {
    public static void main(String[] args) {
        median testObject = new median();

        int arraySize = 1000000;
        int minValue = 0;
        int maxValue = 100;
        int numberOfTests = 10;
        
        
        //Test randomized quicksort numberOfTest times, each with a randomly generated array.

        for (int testNumber = 0; testNumber < numberOfTests; testNumber++) {
            //Generate an array of random integers for each test and test the two quicksort algorithms with the same array. 
            int[] testArray = generateRandomIntArray(arraySize, minValue, maxValue); 
            testQuicksortRandomized(testArray, testNumber);
            testQuicksortMedianPivot(testArray, testNumber);
            System.out.println("-----------------------------------------------------------------------------------------");
        }
    }

    private static int[] generateRandomIntArray(int arraySize, int minValue, int maxValue) {
        int[] a = new int[arraySize];
        Random generator = new Random();
        for (int i = 0; i < arraySize; i++) {
            a[i] = generator.nextInt(maxValue - minValue) + minValue;
        }
        return a; 
    }

    private static void printArray(int[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
    }

    /** Tests randomized pivot quicksort. Returns execution time. */
    private static void testQuicksortRandomized(int[] arr, int testNumber) {
        median tester = new median();
        long startTime = System.nanoTime();
        tester.quicksortRandomPivot(arr, 0, arr.length-1);
        long endTime = System.nanoTime();
        // System.out.println("\nSorted array using randomized partition element: ");
        //printArray(arr);

        System.out.println("\nTEST NUMBER: " + testNumber + " Time taken to execute randomized quicksort: "+ (endTime-startTime) +" nanoseconds");
    }

     /** Tests median pivot quicksort. */
    private static void testQuicksortMedianPivot(int[] arr, int testNumber) {
        median tester = new median();
        long startTime = System.nanoTime();
        tester.subArrQuicksort(arr, 0, arr.length-1);
        long endTime = System.nanoTime();
        // System.out.println("\nSorted array using randomized partition element: ");
        //printArray(arr);

        System.out.println("\nTEST NUMBER: " + testNumber + ": Time taken to execute median-pivot quicksort: "+ (endTime-startTime) +" nanoseconds");
    }
}   
