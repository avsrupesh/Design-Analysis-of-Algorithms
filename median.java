//package FinalProject;


import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class median {


    public float medianOrderStat(int[] a){
        // this method returns the median based on the size of the array
        int n = a.length;
        float result;

        if(n==0)
            // if there are no elements in the array, return 0
            return (float) 0.0;
        else if(n%2==0) {
            //length of array is even
            int i1= n/2;
            int i2 = n/2+1;

            //using order statistics to calculate the median
            int val1 = randomizedSelect(a,0, n-1, i1);
            int val2 = randomizedSelect(a,0, n-1, i2);

            //calculate average of middle 2 values for even-length array
            result = (float)(val1+val2)/2;
        }

        else //length of array is odd
            result = (float)randomizedSelect(a, 0, n-1, (n+1)/2);

        return result;
    }

    //randomized method to choose pivot
    public int randomizedSelect(int[] a, int p, int r, int i)
    {
        // k smaller then no. of elements
        if (i > 0 && i <= r - p + 1) {
            //performing partition
            int q = partition(a, p, r);
            //pivot is same as answer
            if (q - p == i - 1)
                return a[q];
            //iterating left subarray
            if (q - p > i - 1)
                return randomizedSelect(a, p, q - 1, i);
            //recurring right subarray
            return randomizedSelect(a, q + 1, r, i - q + p - 1);
        }
        //k greater than no. of elements in the array
        return Integer.MAX_VALUE;
    }

    //method for partitioning the array
    public int partition(int[] a, int start, int end)
    {
        int x = a[end], i = start;
        for (int j = start; j <= end - 1; j++) {
            if (a[j] < x) {
                // Swapping a[i] and a[j]
                int temp = a[i];
                a[i] = a[j];
                a[j] = temp;

                i++;
            }
        }

        // Swapping a[i] and a[last]
        int temp = a[i];
        a[i] = a[end];
        a[end] = temp;

        return i;
    }

    //quicksort by selecting the random partition element
    public void quicksortRandomPivot(int a[], int p, int r) {
        if(p<r){
            int q = randomizedPartition(a, p, r);
            quicksortRandomPivot(a, p,q-1);
            quicksortRandomPivot(a, q+1,r);
        }
    }

    int randomizedPartition(int a[], int p, int r) {
        // i takes random value between p and r
        int i = ThreadLocalRandom.current().nextInt(p, r+1);
        int t = a[i];
        a[i] = a[r];
        a[r] = t;
        return partition(a, p, r);
    }


    //method to choose median as pivot for quicksort
    public void subArrQuicksort(int[] a, int front, int back) {
        int size = back - front + 1;
        if (size <= 3)
            normalSort(a, front, back);
        else {
            int median = subMedian(a, front, back);
            int partition = split(a, front, back, median);
            subArrQuicksort(a, front, partition - 1);
            subArrQuicksort(a, partition + 1, back);
        }
    }

    //below function works during recursive calls where size of array becomes <=3
    public void normalSort(int[] a, int front, int back) {
        int size = back - front + 1;
        if (size <= 1)
            return;
        if (size == 2) {
            if (a[front] > a[back])
                swapping(a, front, back);
            return;
        } else {
            if (a[front] > a[back - 1])
                swapping(a, front, back - 1);
            if (a[front] > a[back])
                swapping(a, front, back);
            if (a[back - 1] > a[back])
                swapping(a, back - 1, back);
        }
    }

    //median-of-3 approach used here
    //this method picks pivot as the median of 3 elements chosen(first and last as boundaries)
    //refer section 7.5(page 188) in the textbook
    public int subMedian(int[] a, int left, int right) {
        int mid = (left + right) / 2;
        if (a[left] > a[mid])
            swapping(a, left, mid);
        if (a[left] > a[right])
            swapping(a, left, right);
        if (a[mid] > a[right])
            swapping(a, mid, right);
        swapping(a, mid, right - 1);
        return a[right - 1];
    }

    //method to split the array around pivot
    public int split(int[] a, int left, int right, int pivot) {
        int l = left;
        int r = right - 1;
        while (true) {
            while (a[++l] < pivot)
                ;
            while (a[--r] > pivot)
                ;
            if (l >= r)
                break;
            else
                swapping(a, l, r);
        }
        swapping(a, l, right - 1);
        return l;
    }

    public void swapping(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    //method to print the array
    void printArray(int a[], int n) {

        for (int i = 0; i < n; i++)
            System.out.print(a[i] + " ");
    }

    //below are the functions implementing the options available in the menu
 public void median357(){

     //finds median based on the order statistics

     Scanner s = new Scanner(System.in);
     int a1[] = new int[3];
     int a2[] = new int[5];
     int a3[] = new int[7];


     System.out.println("1st Array: Enter any 3 integers: ");
     for(int i=0; i<3; i++) {
         a1[i] = s.nextInt();
     }

     System.out.println("2nd Array: Enter any 5 integers: ");
     for(int i=0; i<5; i++) {
         a2[i] = s.nextInt();
     }

     System.out.println("3rd Array: Enter any 7 integers: ");
     for(int i=0; i<7; i++) {
         a3[i] = s.nextInt();
     }

     System.out.println("\n1st array: ");
     printArray(a1, a1.length);
     System.out.println("Median: "+medianOrderStat(a1));

     System.out.println("\n2nd array: ");
     printArray(a2, a2.length);
     System.out.println("Median: "+medianOrderStat(a2));

     System.out.println("\n3rd array: ");
     printArray(a3, a3.length);
     System.out.println("Median: "+medianOrderStat(a3));

 }

 public void genericMedian(){
     //method to find median by choosing randomized pivot element
     //implements order statistics

     Scanner s = new Scanner(System.in);
     System.out.println("\nPlease enter the input size of array:");
     int arraySize = s.nextInt();
     int[] arr = new int[arraySize];

     System.out.println("Please enter the elements (Integers) of array:");
     for(int i=0; i<arraySize; i++) {
         arr[i] = s.nextInt();
     }

     System.out.println("\nPlease enter value of k btw 1 and "+arraySize+" (eg. 1 gives 1st smallest element): ");
     int k = s.nextInt();
     System.out.println(+k+" th smallest element is: "+randomizedSelect(arr, 0, arr.length-1, k));
     System.out.println("Median is: "+medianOrderStat(arr));

 }

 public void quicksortRamdomized(){
     //method to perform quicksort by randomized pivot approach using order statistics

     Scanner s = new Scanner(System.in);
     System.out.println("\nPlease enter the input size of array:");
     int arraySize = s.nextInt();
     int[] arr = new int[arraySize];

     System.out.println("Please enter the elements (Integers) of array:");
     for(int i=0; i<arraySize; i++) {
         arr[i] = s.nextInt();
     }

     long startTime = System.nanoTime();
     quicksortRandomPivot(arr, 0, arr.length-1);
     long endTime = System.nanoTime();
     System.out.println("\nSorted array using randomized partition element: ");
     printArray(arr, arr.length);

     System.out.println("\nTime taken to execute: "+(endTime-startTime)+" nanoseconds");
 }

 public void quicksortMedianAsPivot(){
     //method to perform quicksort by choosing median as pivot

     Scanner s = new Scanner(System.in);
     System.out.println("\nPlease enter the input size of array:");
     int arraySize = s.nextInt();
     int[] arr = new int[arraySize];

     System.out.println("Please enter the elements (Integers) of array:");
     for(int i=0; i<arraySize; i++) {
         arr[i] = s.nextInt();
     }

     long startTime = System.nanoTime();
     subArrQuicksort(arr, 0, arr.length - 1);
     long endTime = System.nanoTime();
     System.out.println("\nSorted array using median as pivot: ");
     printArray(arr, arr.length);

     System.out.println("\nTime taken to execute: "+(endTime-startTime)+" nanoseconds");

 }


}
