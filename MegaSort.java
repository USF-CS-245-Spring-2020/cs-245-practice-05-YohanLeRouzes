import java.util.*;
import java.io.*;

public class MegaSort
{
    public static void sort(int [] arr) {
        sort(arr, 0, arr.length-1);
    }

    public static void sort(int [] arr, int left, int right) {
        int mid=arr.length/2;
        if(arr.length<2)
            return; //Return if arr.length is less than 2 instead of calling an error
        int[] Left = new int [mid]; //Size of the left array is half the length of arr
        int[] Right = new int [arr.length-mid]; //Size of the right array is the rest of the array arr
        for (int n=0; n<mid; n++)
        	Left[n] = arr[n]; //Copying arr values into both Left and Right arrays
        for (int m=mid; m<arr.length; m++)
        	Right[m-mid]=arr[m];
        sort(Left); //sort Left array
        sort(Right); //sort Right array
        merge(arr, Left, Right); //merge both sorted arrays together
    }
    
    public static void merge(int [] arr, int[] left, int[] right) {
        int n1 = left.length; //n1 is the length of the left array
        int n2 = right.length; //n2 is the length of the right array
        int i=0; //counter i
        int j=0; //counter j
        int indexTarget=0;
        while(i < n1 && j < n2) { //while i is less than the length of the left array and j less than the length of the right array
            if(left[i] <= right[j]) { //if left value is bigger than right value
                arr[indexTarget] = left[i]; //arr takes left as value
                i++;
            }
            else {
                arr[indexTarget] = right[j]; //arr takes right as value
                j++;
            }
            indexTarget++;
        }
        while(i < n1) { //Situation where right array is shorter than left array
            arr[indexTarget]= left[i];
            indexTarget++;
            i++;
        }
        while(j < n2) { //Situation where left array is shorter than right array
            arr[indexTarget]= right[j];
            indexTarget++;
            j++;
        }
    }
    
    public static void main(String[] args) throws FileNotFoundException {
        int[] arr = new int[1000000];     //Creating array with a million maximum entries
        File file = new File("1m-ints.txt"); //Looking and opening file 1m-ints.txt
        Scanner scan = new Scanner(file); //Scanning the data's file
        int k = 0;
        while(scan.hasNextLine()) { //While the file has data
            String milnum = scan.nextLine();//milnum equals the value of that specific line
            int sorted = Integer.parseInt(milnum);
            arr[k++] = sorted;//arr[k++] is equal to the value sorted which is the integer parsed by the file
        }
        sort(arr); //Sort the array arr
        for(int n = 0; n < arr.length; n++)
            System.out.println(arr[n]); //Print all the sorted integers of the file 1m-ints.txt
    }
}