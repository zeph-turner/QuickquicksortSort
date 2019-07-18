/* Zeph Turner
 * February 2018
 * Algorithms & Analysis
 * 
 * Implementation of Quick Sort algorithm with median of n. Each call to partition
 * will take n indices of the array to be partitioned and sort them on their 
 * values in A. The median will be returned as the value to partition around.
 * The sorting algorithm used to find the median is base quicksort with fixed 
 * partition value (the value returned is the new index of the item that was in 
 * A[r] before the partition). 
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

public class QuickSortQS {
  
  static final int N=1000;
  static double A[]=new double[N];
  static boolean almostSorted = true;
  static boolean printArray = true;
  static int n=3;
  static int C[] = new int[n]; //Used to hold indices
  static int random1 = 10;
  static int random2 = 4;
  
  
  //Implicit parameter = A
  public static void Quicksort(int p, int r){
    if(p < r){
      int q = Partition(p, r);
      Quicksort(p, q-1);
      Quicksort(q+1, r);
    }
  }
  
  //Implicit parameter = C
  public static void QuicksortC(int p, int r){
    if(p < r){
      int q = nonrecurPartition(p, r);
      QuicksortC(p, q-1);
      QuicksortC(q+1, r);
    }
  }
  
  //Partition, but doesn't call getMedian.
  //Also, implicit parameter = C.
  public static int nonrecurPartition(int p, int r) {
    double x=C[r];
    int i = p-1;
    int j = 0;
    int hold;

    for(j = p; j <= r-1; j++){
 
      if(C[j] <= x) {
        i++;
        hold = C[i];
        C[i] = C[j];
        C[j] = hold;
      }
    }
    hold = C[i+1];
    C[i+1] = C[j];
    C[j] = hold;
    
    return i+1;
  }
  
  //Get median. Called ONLY by Quicksort, NOT by QuicksortC.
  public static int getMedian(int p, int r){
    if(r - p < 2){
      //If r-p < 2,
      //we don't have 3 separate numbers of which to find the median,
      //so just
      return r;
    }
    //Populate indices
    
    //Uncomment for testing
    //System.out.println("Call size " + (r-p) + ", unsorted:");
    int ind = 0;
    for(int i = 0; i < n; i++) {
      ind = p + (i)*(int)Math.max((r-p)/(n-1), 1); //If the fraction is too small,
      //just go up by 1 each time until we hit the limit of this interval (r). 
      if(ind <= r) {
        C[i] = ind;
      } else {
        C[i] = r;
      }
      //for testing
      //System.out.println(C[i] + ": " + A[C[i]]);
    }
    //Sort to find the index with the median value out of the n indices
    QuicksortC(0, n-1);
    
    /*
    //For testing
    System.out.println("Sorted:");
    for(int i = 0; i < n; i++) {
      System.out.println(C[i] + ": " + A[C[i]]);
    }
    */
    return(C[(int)Math.floor(n/2.0)]);
  }
  
  //Returns true if A is sorted and false otherwise
  public static boolean checkSorted() {
    for(int i = 0; i < N-1; i++) {
      if(A[i] > A[i+1]) {
        return false;
      }
    }
    return true;
  }
  
  
  //This is the partition that getMedian
  public static int Partition(int p, int r) {
    int medInd = getMedian(p, r);
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
        if(i%1000 < 500){
          A[i] = A[i-1] + (Math.random()*random1) - random2;
        } else {
          A[i] = A[i-1] - (Math.random()*random1) + random2;
        }
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