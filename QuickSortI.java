/* Zeph Turner
 * February 2018
 * Algorithms & Analysis
 * 
 * Implementation of Quick Sort algorithm with median of n. Each call to partition
 * will take n indices of the array to be partitioned and sort them on their 
 * values in A. The median will be returned as the value to partition around.
 * The sorting algorithm used to find the median is insertion sort. The call to
 * insertion sort will incidentally sort the values of A[] (the outer array) at those
 * indices as well, improving later partitions.
 * Sorts a static array A[] of doubles.
 * The array is populated with random doubles from 0 to 1, or, if almostSorted is true,
 * with random doubles in almost-sorted order of variable range.
 * Options: 
 *     N: array size to generate and sort;
 *     printArray: boolean controlling whether contents of array are output.
 *     almostSorted: If this is true, the array is populated with almost-sorted values.
 *     n: number of items to compare to choose the median during partition.
 * Output:
 *     results of checkSorted before and after the sort is run, and runtime of the 
 *     call to insertionSort(). Also will print unsorted and sorted array if
 *     printArray is true.
 * */

public class QuickSortI {
  
  static final int N=1000000;
  static double A[]=new double[N];
  static final int n0 = (int)(0.001*(double)N);
  static int[] medInds = new int[n0];
  static final boolean almostSorted = true;
  static boolean printArray = false;
  static int random1=5;
  static int random2=4;
  
  public static void Quicksort(int p, int r){
    if(p < r){
      int q = Partition(p, r);
      Quicksort(p, q-1);
      Quicksort(q+1, r);
    }
  }
  
  public static int getMedian(int p, int r, int n){
    if(p == r){
      return p;
    }
    //Populate indices
    for(int i = 0; i < n; i++) {
      medInds[i] = p + (i)*((r-p)/(n-1));
    }
    
    //Sort to find the index with the median value out of the three indices
    //medKeys passed to sort by A[medInds[i]], not medInds[i].
    medInds = insertionSort(medInds);
    return(medInds[(int)Math.floor(n/2.0)]);
  }
  

  
  public static int[] insertionSort(int[] C) {
    int j;
    int i = 0;
    double key = 0;
    int indKey = 0;
    //For each item in B...
    for(j = 1; j < C.length; j++) {
      //The value by which we sort is the item in that index of A
      key = A[C[j]]; //Value from actual array at index C[j]
      indKey = C[j];
      i = j-1;
      while(i > -1 && A[C[i]] > key) {
        A[C[i+1]]=A[C[i]]; //We will actually sort these values in A[] as well
        C[i+1]=C[i];       //Sort C alongside
        i--;
      }
      A[C[i+1]] = key;
      C[i+1] = indKey;
    }
    return C;
  }
  
  public static boolean checkSorted() {
    //Returns true if the list is sorted and false otherwise.
    for(int i = 0; i < N-1; i++) {
      if(A[i] > A[i+1]) {
        return false;
      }
    }
    return true;
  }
 
  
  
  public static int Partition(int p, int r) {
    //find median of n0 with getMedian
    int medInd = getMedian(p, r, n0);
    //set x to median
    double x=A[medInd];
    int i = p-1;
    int j = 0;
    double hold;
    //Swap median to last position
    hold = A[r];
    A[r] = A[medInd];
    A[medInd]=A[r];
    
    for(j = p; j <= r-1; j++){
 
      if(A[j] <= x) {
        i++;
        hold = A[i];
        A[i] = A[j];
        A[j] = hold;
      }
    }
    hold = A[i+1];
    A[i+1] = A[j];
    A[j] = hold;
    
    return i+1;
  }
  
  public static void main(String[] args){
    long startTime;
    long stopTime;
    
    if(!almostSorted) {
      //Random aray
      int i = 0;
      for(i = 0; i < A.length; i++){
        A[i] = Math.random();
      }
    } else {
    
    //Almost sorted array
      A[0] = (Math.random()*10)+1;
      for(int i = 1; i < A.length; i++){
        A[i] = A[i-1] + (Math.random()*random1) - random2;
      }
    }
    
    
    if(printArray){
      for(int i = 0; i < N-1; i++) {
        System.out.println(A[i]);
      }
    }
    
    
    System.out.println(checkSorted());
    
    startTime = System.currentTimeMillis();
    Quicksort(0, A.length-1);
    stopTime = System.currentTimeMillis();
    System.out.println("Elapsed time = " + (stopTime - startTime) + " milliseconds.");
    
    System.out.println(checkSorted());
    
    if(printArray){
      for(int i = 0; i < N-1; i++) {
        System.out.println(A[i]);
      }
    }
    
  }
}