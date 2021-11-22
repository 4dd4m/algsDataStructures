import java.util.Arrays;
import java.util.Random;
public class SelectionSort {

    public static int numCompare = 0, numswaps = 0;

    public static void bubblesort(int[] arr) {
        int firstPos = 0;
        int lastPos = arr.length-1;
        int innerLast = lastPos;
        int temp, lastSwapPos;


        while(firstPos < lastPos){
            lastSwapPos = firstPos;
            for (int j = firstPos; j < lastPos; j++) {
                numCompare++;
                if(arr[j] > arr[j+1]){
                    temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                    numswaps++;
                    lastSwapPos = j;
                }
            }
            lastPos = lastSwapPos;
            //System.out.println("Pass: " + Arrays.toString(arr));
        }

    }

    public static int[] randomArray(int size){
        Random rand = new Random();
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = rand.nextInt(100);
        }
        return arr;
    }

    public static void selectionSort(int[] arr){
        int firstPos = 0;
        int lastPos = arr.length-1;
        int temp, smallestPos;

        for (int i = firstPos; i < lastPos; i++) {
            smallestPos = i;
            for (int j = i+1; j <= lastPos; j++) {
                if(arr[j] < arr[smallestPos]){
                    smallestPos = j;
                }
            }
            temp = arr[smallestPos];
            arr[smallestPos] = arr [i];
            arr[i] = temp;
        }
    }

    public static void main(String[] args) {
        int[] arr;
        arr = randomArray(100);
        System.out.println(Arrays.toString(arr));
        System.out.println("--------------------");
        selectionSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
